package pohkahkong.game.rainbow.game;

import java.util.HashMap;
import java.util.Map;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.engine.GameEngine;
import pohkahkong.game.rainbow.menu.imagepager.WinActivity;
import pohkahkong.game.rainbow.menu.main.MainActivity;
import pohkahkong.game.rainbow.service.AccelerometerService;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.HandlerService;
import pohkahkong.game.rainbow.service.IntentService;
import pohkahkong.game.rainbow.service.PreferencesService;
import pohkahkong.game.rainbow.service.XmlParserService;
import pohkahkong.game.rainbow.service.VibratorService;
import pohkahkong.game.rainbow.service.GestureService;
import pohkahkong.game.rainbow.service.ToastService;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GameActivity extends Activity implements OnEventReceivedListener {
	private static final String TAG = "GameActivity";
	public static enum Event {COMPLETED, READY, PLAYING, NEXT, PREV, RESTART, EXIT, WIN, GAMEOVER, RESUME, MAINMENU};
	private PreferencesService preferencesService = PreferencesService.getInstance();
	private DimensionService dimensionService = DimensionService.getInstance();
	private IntentService intentService = IntentService.getInstance();
	private GameImageService imageService = GameImageService.getInstance();
	private GameSoundService soundService = GameSoundService.getInstance();
	private HandlerService handlerService = HandlerService.getInstance();
	private AccelerometerService accelerometerService = AccelerometerService.getInstance();
	private VibratorService vibratorService = VibratorService.getInstance();
	private GestureService gestureService = GestureService.getInstance();
	private XmlParserService xmlParserService = XmlParserService.getInstance();
	private ToastService toastService = ToastService.getInstance();
		
	private GameEngine gameEngine;
	private GameGesture gameGesture;	
	private GameWait gameWait;
	private GamePaused gamePaused;	
	private FrameLayout gameFL;
	
	private int season;
	private int level;
	private State state = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		setContentView(R.layout.game);		
		gameFL = (FrameLayout) findViewById(R.id.gameFL);		

		Bundle extras = getIntent().getExtras();
		if (extras!=null) {		
			season = extras.getInt("season");
			level = extras.getInt("level");
		} else {
			season = 1;
			level = 1;
		}
		
		preferencesService.init(this);    		
		dimensionService.init(this);
		dimensionService.initTileDimension(
			Integer.parseInt(getResources().getString(R.string.game_mapwidth)),
			Integer.parseInt(getResources().getString(R.string.game_mapheight)));
		imageService.init(this, season, level);
		intentService.init(this);
		soundService.init(this, season);
		accelerometerService.init(this);
		vibratorService.init(this);
		gestureService.init(this);
		xmlParserService.init(this, season, level);
		toastService.init(this); 
		
		initRatingPlayCount();
		initGameEngine();
		initGameWait();
		initGamePaused();  
		initGestureOverlayView();	
	}
	
	private void initRatingPlayCount() {			
		int ratingPlayCount = preferencesService.getRatingPlayCount();
		if (ratingPlayCount!=-1) {
			ratingPlayCount++;
			preferencesService.setRatingPlayCount(ratingPlayCount);
		}
		Log.i(TAG, "ratingPlayCount " + ratingPlayCount);
	}
    
    private void initGameEngine() {
    	gameEngine = new GameEngine(this);
    	gameEngine.init(this);
    	gameFL.addView(gameEngine);    	
    }
    
    private void initGameWait() {    	    	 	   	
    	gameWait = new GameWait(this);
    	gameWait.init(season, level, this);
		gameFL.addView(gameWait);   
    }               
    
    private void initGamePaused() {
    	boolean hasPrev = (season!=1 || level!=1);
    	boolean hasNext  = ((season!=4 || level!=20) && compareStage(season, level)<0);
    	gamePaused = new GamePaused(this);
    	gamePaused.init(hasPrev, hasNext, this);
    	gameFL.addView(gamePaused);
    }
    
    private void initGestureOverlayView() {		
    	gameGesture = new GameGesture();
		gameFL.addView(gameGesture.getView());    	
    }
    
    // actions    
    public void nextStage() {    
    	int season2 = season;
    	int level2 = level--;
    	level2++;
    	if (level2>20) {
    		season2++;
    		level2 = 1;
    	}
    	if (season2>4) { // game completed
    		intentService.startActivity(WinActivity.class);
    		preferencesService.setCompleted(true);
    		finish();
    		return;
    	}
    	if (compareStage(season2, level2)>0) { // new stage
    		preferencesService.setStage(season2, level2);
    	}
    	startGame(season2, level2);
    }
    
    public void prevStage() {
    	int season2 = season;
    	int level2 = level--;
    	level2--;
    	if (level2<1) {
    		season2--;
    		level2 = 20;    	
    	}    	     	
    	startGame(season2, level2);
    } 
    
    public void restartStage() {
    	startGame(season, level);
    }
    
    public void mainMenu() {
    	intentService.startActivity(MainActivity.class);
    	finish();
    }
    
    private void startGame(int season, int level) {
    	Map<String, Integer> extras = new HashMap<String, Integer>();
    	extras.put("season", season);
    	extras.put("level", level);
    	intentService.startActivityWithExtra(GameActivity.class, extras);
    	finish();
    }

    private int compareStage(int season, int level) {
    	int lastSeason = preferencesService.getSeason();
    	int lastLevel = preferencesService.getLevel();    	
    	
    	if (season>lastSeason) {
    		return 1;
    	} else if (season==lastSeason) {
    		if (level>lastLevel)
    			return 1;
    		else if (level==lastLevel)
    			return 0;
    		else
    			return -1;
    	} else
    		return -1;
    }
    
    // events
	@Override
	protected void onResume() {
		if (state==null) {
			changeState(new LoadingState(new HoldingState()));
		} else if (state instanceof PausedState) {
			Log.i(TAG, "onResume " + state.toString());
			changeState(new LoadingState(state));
		} else {
			Log.i(TAG, "onResume " + state.toString());
			changeState(new LoadingState(new PausedState(state)));			
		}
		super.onResume();
	}	
	
	@Override
	protected void onPause() {
		Log.i(TAG, "onPause " + state.toString());
		if (state instanceof LoadingState) {
			state = null;
		}		
		changeState(new StoppedState(state));
		super.onPause();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (state instanceof LoadingState) {
			return true;
		}		
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			Log.i(TAG, "KEYCODE_VOLUME_UP");
			soundService.increaseVolume();
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			Log.i(TAG, "KEYCODE_VOLUME_DOWN");
			soundService.decreaseVolume();
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			Log.i(TAG, "KEYCODE_BACK " + state.toString());
			soundService.screenChange();
			if (!(state instanceof PausedState)) {
				changeState(new PausedState(state));
			}
		}	
		return true;
	}
	
	// states
	public void changeState(State state) {
		this.state = state;
		state.run();
	}

	private class LoadingState extends State {	
		private State nextState;
				
		public LoadingState(final State nextState) {
			this.nextState = nextState;
		}

		@Override
		public void run() {
			Log.i(TAG, toString());
			gameEngine.hide();
			gameGesture.hide();
			gameWait.hide();
			gamePaused.hide();	
			imageService.loadBitmaps(new OnCompleteListener() {
	    		public void onComplete(Object[] objects) {
	    			soundService.load();
	    			gameWait.load();
	    			gameEngine.load();
	    				    			
	    			accelerometerService.register();	    				    			
	    			gameWait.showStart();	    			
	    			changeState(nextState);
	    			
	    		}
	    	});
		}
		
		public void onEventReceived(Object object) {
		}

		@Override
		public String toString() {
			return "Loading";
		}
	}

	private class HoldingState extends State {	
		@Override
		public void run() {
			Log.i(TAG, toString());
			gameEngine.hold();
			gameGesture.hide();
			gameWait.hold();
			gamePaused.hold();					
		}

		public void onEventReceived(Object object) {
			Event event = (Event) object;
			switch (event) {
			case PLAYING:
				changeState(new PlayingState());
				break;
			case NEXT:
				nextStage();
				break;
			case RESTART:
				restartStage();
				break;
			default:
				break;
			}
		}

		@Override
		public String toString() {
			return "Holding";
		}
	}

	private class PlayingState extends State {
		@Override
		public void run() {
			Log.i(TAG, toString());
			gameEngine.play();
			gameGesture.play();
			gameWait.play();			
			gamePaused.play();
		}

		public void onEventReceived(Object object) {
			Event event = (Event) object;
			switch (event) {
			case WIN:
				gameWait.showWin();
				changeState(new HoldingState());
				break;
			case GAMEOVER:
				gameWait.showGameOver();
				changeState(new HoldingState());				
				break;
			default:
				break;
			}
		}

		@Override
		public String toString() {
			return "Playing";
		}
	}

	private class PausedState extends State {
		private State resumeState;
		
		public PausedState(State resumeState) {
			this.resumeState = resumeState;
		}
		
		@Override
		public void run() {
			Log.i(TAG, toString());
			gameEngine.pause();
			gameGesture.pause();	
			gameWait.pause();
			gamePaused.pause();					
		}

		public void onEventReceived(Object object) {
			Event event = (Event) object;
			switch (event) {
			case RESUME:
				changeState(resumeState);
				break;
			case MAINMENU:
				handlerService.run(new Runnable() {
					public void run() {
						mainMenu();
					}					
				}, 1000);				
				break;
			case NEXT:
				handlerService.run(new Runnable() {
					public void run() {
						nextStage();
					}					
				}, 1000);
				break;
			case PREV:
				handlerService.run(new Runnable() {
					public void run() {
						prevStage();
					}					
				}, 1000);				
				break;
			case RESTART:
				handlerService.run(new Runnable() {
					public void run() {
						restartStage();
					}					
				}, 1000);	
				break;
			case EXIT:
				handlerService.run(new Runnable() {
					public void run() {
						finish();
					}					
				}, 1000);
				
				break;
			default:
				break;
			}
		}

		@Override
		public String toString() {
			return "Paused";
		}
	}
	
	private class StoppedState extends State {	
		private State resumeState;

		public StoppedState(State resumeState) {
			this.resumeState = resumeState;
		}

		@Override
		public void run() {
			Log.i(TAG, toString());
			gameEngine.stop();
			gameGesture.stop();
			gameWait.stop();
			gamePaused.stop();
			
			xmlParserService.release();
			imageService.release();
			soundService.release();			
			handlerService.release();
			accelerometerService.unregister();
			vibratorService.release();	
			state = resumeState;
		}
		
		public void onEventReceived(Object object) {
		}

		@Override
		public String toString() {
			return "Stopped";
		}
	}	
	
	private abstract class State implements OnEventReceivedListener {		
		public abstract String toString();
		
		public abstract void run();
	}

	public void onEventReceived(Object object) {
		final Event event = (Event) object;
		Log.i(TAG , "onEventReceived " + event.toString());
		state.onEventReceived(event);
	}
}
