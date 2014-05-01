package pohkahkong.game.rainbow.game;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.service.AccelerometerService;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GameWait extends RelativeLayout implements GameActivityScreen {
	private static final String TAG = "GameWait";

	private GameImageService gameImageLoader = GameImageService.getInstance();
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	private AccelerometerService accelerometerService = AccelerometerService.getInstance();

	private TextView stageNumTV;
	private TextView middleTV;
	private TextView levelNumTV;
	private ImageView readyIV;
	private ImageView instructionIV;
	private ImageView winIV;
	private ImageView gameOverIV;
	private Paint paint = new Paint();
	private boolean hasInstruction;
	private State state = null;

	private OnEventReceivedListener onEventReceivedListener;


	public GameWait(Context context) {
		super(context);
		if (!isInEditMode()) {
			initViews(context);
		}	
	}

	public GameWait(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			initViews(context);
		}
	}

	private void initViews(Context context) {
		hide();
		inflate(context, R.layout.game_wait, this);
		stageNumTV = (TextView) findViewById(R.id.gameWaitStageSeasonTV);
		middleTV = (TextView) findViewById(R.id.gameWaitStageMiddleTV);
		levelNumTV = (TextView) findViewById(R.id.gameWaitStageLevelTV);
		instructionIV = (ImageView) findViewById(R.id.gameWaitInstructionIV);
		readyIV = (ImageView) findViewById(R.id.gameWaitReadyIV);
		winIV = (ImageView) findViewById(R.id.gameWaitWinIV);
		gameOverIV = (ImageView) findViewById(R.id.gameWaitGameOverIV);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);		
	}

	public void init(int curSeason, int curLevel, final OnEventReceivedListener onEventReceivedListener) {
		this.onEventReceivedListener = onEventReceivedListener;
		stageNumTV.setText(String.valueOf(curSeason));
		levelNumTV.setText(String.valueOf(curLevel));
	}
	
	// game activity states
	public void load() {
		Bitmap instruction = gameImageLoader.getGameWaitInstruction();
		if (instruction!=null) {
			instructionIV.setImageBitmap(instruction);
			hasInstruction = true;
		} else {
			hasInstruction = false;
		}
	}
	
	public void hold() {
		show();
	}

	public void play() {
		hide();
	}

	public void pause() {
		hide();
	}
	
	public void stop() {
		hide();
	}


	public void showStart() {
		if (state==null) {
			changeState(new StartState());
		}
		show();
	}

	public void showWin() {
		changeState(new WinState());
		show();
	}

	public void showGameOver() {
		changeState(new GameOverState());
		show();
	}
	
	public void show() {
		setVisibility(View.VISIBLE);
		setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				state.onClick(view);
				gameSoundLoader.screenChange();
			}			
		});		
	}

	public void hide() {		
		setVisibility(View.GONE);
		setOnClickListener(null);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float accX = accelerometerService.getDeltaX()*10.0f;
		float accY = accelerometerService.getDeltaY()*10.0f;
		if (accX==0.0f && accY==0.0f) {
			paint.setColor(Color.GREEN);
		} else {
			paint.setColor(Color.RED);
		} 		
		canvas.drawRect((getWidth() - readyIV.getWidth()) / 2 + accX - 10,
				(getHeight() - readyIV.getHeight()) / 2 + accY - 10,
				(getWidth() + readyIV.getWidth()) / 2 + accX + 10,
				(getHeight() + readyIV.getHeight()) / 2 + accY + 10, paint);
		invalidate();		
	}
	
	// states
	private void changeState(State state) {
		this.state = state;
		state.run();
	}
	
	private class StartState extends State {
		@Override
		public void run() {
			Log.i(TAG, toString());
			stageNumTV.setVisibility(View.VISIBLE);
			middleTV.setVisibility(View.VISIBLE);
			levelNumTV.setVisibility(View.VISIBLE);
			instructionIV.setVisibility(View.INVISIBLE);
			readyIV.setVisibility(View.INVISIBLE);
			winIV.setVisibility(View.INVISIBLE);
			gameOverIV.setVisibility(View.INVISIBLE);
			setWillNotDraw(true);
		}
		
		public void onClick(View view) {
			if (hasInstruction) {
				changeState(new InstructionState());
			} else {
				changeState(new ReadyState());
			}
		}

		@Override
		public String toString() {
			return "Showing start";
		}		
	}
	
	private class InstructionState extends State {
		@Override
		public void run() {
			Log.i(TAG, toString());
			stageNumTV.setVisibility(View.INVISIBLE);
			middleTV.setVisibility(View.INVISIBLE);
			levelNumTV.setVisibility(View.INVISIBLE);
			instructionIV.setVisibility(View.VISIBLE);
			readyIV.setVisibility(View.INVISIBLE);
			winIV.setVisibility(View.INVISIBLE);
			gameOverIV.setVisibility(View.INVISIBLE);
			setWillNotDraw(true);
		}
		
		public void onClick(View view) {
			changeState(new ReadyState());
		}

		@Override
		public String toString() {
			return "Showing instruction";
		}		
	}
	
	private class ReadyState extends State {
		@Override
		public void run() {
			Log.i(TAG, toString());
			stageNumTV.setVisibility(View.INVISIBLE);
			middleTV.setVisibility(View.INVISIBLE);
			levelNumTV.setVisibility(View.INVISIBLE); 		
			instructionIV.setVisibility(View.INVISIBLE);
			readyIV.setVisibility(View.VISIBLE);
			winIV.setVisibility(View.INVISIBLE);
			gameOverIV.setVisibility(View.INVISIBLE);
			setWillNotDraw(false);
		}
		
		public void onClick(View view) {
			onEventReceivedListener.onEventReceived(GameActivity.Event.PLAYING);
			hide();
		}

		@Override
		public String toString() {
			return "Showing ready";
		}		
	}	
	
	private class WinState extends State {
		@Override
		public void run() {
			Log.i(TAG, toString());
			stageNumTV.setVisibility(View.INVISIBLE);
			middleTV.setVisibility(View.INVISIBLE);
			levelNumTV.setVisibility(View.INVISIBLE);
			instructionIV.setVisibility(View.INVISIBLE);
			readyIV.setVisibility(View.INVISIBLE);
			winIV.setVisibility(View.VISIBLE);
			gameOverIV.setVisibility(View.INVISIBLE);
			setWillNotDraw(true);
		}
		
		public void onClick(View view) {
			onEventReceivedListener.onEventReceived(GameActivity.Event.NEXT);
			hide();
		}

		@Override
		public String toString() {
			return "Showing win";
		}		
	}
	
	private class GameOverState extends State {
		@Override
		public void run() {
			Log.i(TAG, toString());
			stageNumTV.setVisibility(View.INVISIBLE);
			middleTV.setVisibility(View.INVISIBLE);
			levelNumTV.setVisibility(View.INVISIBLE);
			instructionIV.setVisibility(View.INVISIBLE);
			readyIV.setVisibility(View.INVISIBLE);
			winIV.setVisibility(View.INVISIBLE);
			gameOverIV.setVisibility(View.VISIBLE);
			setWillNotDraw(true);
		}
		
		public void onClick(View view) {
			onEventReceivedListener.onEventReceived(GameActivity.Event.RESTART);
			hide();
		}

		@Override
		public String toString() {
			return "Showing game over";
		}		
	}
	
	private abstract class State implements OnClickListener {
		public abstract void run();
		
		public abstract String toString();
	}
}
