package pohkahkong.game.rainbow.game.engine;

import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.GameActivity;
import pohkahkong.game.rainbow.game.GameActivityScreen;
import pohkahkong.game.rainbow.game.GameSoundService;
import pohkahkong.game.rainbow.game.engine.object.MapGEO;
import pohkahkong.game.rainbow.game.engine.object.RainbowGEO;
import pohkahkong.game.rainbow.game.engine.object.enemy.EnemiesGEO;
import pohkahkong.game.rainbow.game.engine.object.enemy.EnemiesGEO.Type;
import pohkahkong.game.rainbow.game.engine.object.player.PlayerGEO;
import pohkahkong.game.rainbow.service.HandlerService;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GameEngine extends FrameLayout implements GameActivityScreen,
		SurfaceHolder.Callback, OnEventReceivedListener {
	private static final String TAG = "GameEngine";
	public static enum Event {WIN, GAMEOVER};
	
	private HandlerService handlerService = HandlerService.getInstance();
	
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();	
	
	private Context context;
	private SurfaceView gameView;
	private GameTimer timer;
	private GameLoop gameLoop;
	private PlayerGEO playerSet;
	private EnemiesGEO enemySet;
	private MapGEO map;
	private RainbowGEO rainbow;		
	
	private SurfaceHolder holder = null;
	private OnEventReceivedListener onEventReceivedListener;
	private State state = null;
	public GameEngine(Context context) {
		super(context);
		this.context = context;
	}
	
	public GameEngine(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}
		
	public void init(OnEventReceivedListener onEventReceivedListener) {		
		this.onEventReceivedListener = onEventReceivedListener;
		gameView = new SurfaceView(context);
		gameView.getHolder().addCallback(this);
		timer = new GameTimer(context);		
		timer.setOnEventReceivedListener(this);
		addView(gameView);
		addView(timer);
		
		// init objects
		playerSet = new PlayerGEO(this);
		enemySet = new EnemiesGEO();
		map = new MapGEO();
		rainbow = new RainbowGEO();		
	}
	
	// game activity states
	public void load() {
		playerSet.load();
		enemySet.load();
		map.load();
		rainbow.load();
	}

	public void hide() {
		stop();
	}
	
	public void hold() {
		changeState(new PausedState());
	}
	
	public void play() {
		changeState(new PlayingState());
	}	
	
	public void pause() {
		changeState(new PausedState());
	}
	
	public void stop() {
		changeState(new StoppedState());
	}
	
	// game engine events
	private class GameLoop extends Thread {
		private volatile boolean running = true;		
		@Override
		public void run() {
			Log.i(TAG, "New game loop thread started");
			while (running) {				
				if (holder==null) {
					continue;
				}
				update(System.currentTimeMillis());
				detectCollision();
				playBackgroundSounds();					
				Canvas canvas = null;
				try {
					canvas = holder.lockCanvas(null);
					synchronized (holder) {
						drawScreen(canvas);
					}
				} finally {
					if (canvas != null) {
						holder.unlockCanvasAndPost(canvas);
					}
				}
			}
		}
		
		public void terminate() {
			running = false;
		}
	}
	
	private void startGameLoop() {
		if (gameLoop==null) {
			gameLoop = new GameLoop();
			gameLoop.start();
		}
	}
	
	private void stopGameLoop() {
		if (gameLoop!=null) {
			gameLoop.terminate();
			try {
				gameLoop.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				gameLoop = null;
			}
		}
	}
	
	private void drawScreen(Canvas canvas) {
		map.draw(canvas);
		rainbow.draw(canvas);
		enemySet.draw(canvas);		
		playerSet.draw(canvas);	
	}
	
	private void update(long time) {
		rainbow.update(time);
		playerSet.update(time);
		enemySet.update(time);
	}
	
	// win / game over > slip > stun > slow > outside / inside
	private void detectCollision() {
		int playerX = (int) playerSet.getX();
		int playerY = (int) playerSet.getY();

		if (state instanceof PlayingState
				&& rainbow.detectCollision(playerX, playerY)) { // win	
			onEventReceived(Event.WIN);
			playerSet.win();
			return;
		} 

		if (!playerSet.isCollidable()) {
			return;
		}

		Type type = null;
		if (enemySet!=null && (type=enemySet.detectCollision(playerX, playerY))!=null) {
			switch (type) { // check for collision with enemy
			case SUNNY:
				playerSet.slow(); // slow
				break;
			case LIGHTNY:
				playerSet.stun(); // stun
				break;
			case RAINY:
				playerSet.slip(); // slip
				break;
			case SNOWY:
				if (state instanceof PlayingState && playerSet.gameOverEnemy()) {
					onEventReceived(Event.GAMEOVER);					
				}
				break;
			default:
				break;				
			}
		}
		
		if (map.collisionDetection(playerX, playerY)) { // win / game over
			playerSet.outside();
		} else {
			playerSet.inside();			
		}		
	}
	
	private void playBackgroundSounds() {
		if (Math.random()<0.005f) {
			gameSoundLoader.background();
		}
	}

	// game engine states
	public void changeState(State state) {
		this.state = state;
		state.run();
	}
	
	private class PausedState extends State {	
		public PausedState() {			
		}

		@Override
		public void run() {
			Log.i(TAG, toString());
			startGameLoop();
			timer.stop();
			enemySet.pause();
			playerSet.pause();
			setVisibility(View.VISIBLE);
		}

		@Override
		public String toString() {
			return "Paused";
		}

		public void onEventReceived(Object object) {	
		}
	}
	
	private class PlayingState extends State {
		@Override
		public void run() {
			Log.i(TAG, toString());
			startGameLoop();
			timer.start();
			enemySet.play();	
			playerSet.play();
			setVisibility(View.VISIBLE);
		}

		@Override
		public String toString() {
			return "Playing";
		}

		public void onEventReceived(Object object) {
			Event event = (Event) object;
			Log.i(TAG + " onEvent", event.toString());
			switch (event) {
			case WIN:
				playerSet.win();
				handlerService.run(new Runnable() {
					public void run() {
						onEventReceivedListener.onEventReceived(GameActivity.Event.WIN);	
					}					
				});		
				break;
			case GAMEOVER:
				playerSet.gameOver();
				handlerService.run(new Runnable() {
					public void run() {
						onEventReceivedListener.onEventReceived(GameActivity.Event.GAMEOVER);	
					}					
				});
				break;				
			default:
				break;			
			}
		}
	}
	
	private class StoppedState extends State {
		public StoppedState() {			
		}
			
		@Override
		public void run() {
			Log.i(TAG, toString());
			stopGameLoop();
			timer.stop();
			setVisibility(View.GONE);
		}

		@Override
		public String toString() {
			return "Stopped";
		}

		public void onEventReceived(Object object) {
		}
	}
	
	private abstract class State implements OnEventReceivedListener {
		public abstract void run();
		
		public abstract String toString();
	}

	public void onEventReceived(Object object) {
		Event event = (Event) object;
		Log.i(TAG + " onEvent", event.toString());
		state.onEventReceived(event);
	}

	// surfaceview callbacks
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		Log.i(TAG, "surfaceChanged");		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(TAG, "surfaceCreated");
		this.holder = holder;
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.i(TAG, "surfaceDestroyed");
		stop();
	}
}