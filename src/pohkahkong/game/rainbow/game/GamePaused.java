package pohkahkong.game.rainbow.game;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.view.ImageButton;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GamePaused extends RelativeLayout implements GameActivityScreen {
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	private ImageButton resumeIB;
	private ImageButton mainMenuIB;
	private ImageButton exitIV;
	private ImageButton prevIB;
	private ImageButton restartIB;
	private ImageButton nextIB;
	
	private boolean hasPrev;
	private boolean hasNext;
	
	private OnEventReceivedListener onEventReceivedListener;
	
	public GamePaused(Context context) {
		super(context);
		if (!isInEditMode()) {
			initViews(context);
		}
	}
	
	public GamePaused(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			initViews(context);
		}
	}
	
	private void initViews(Context context) {
		inflate(context, R.layout.game_paused, this);
    	resumeIB = (ImageButton) findViewById(R.id.gamePausedResumeIB);
    	mainMenuIB = (ImageButton) findViewById(R.id.gamePausedMainMenuIB);
    	exitIV = (ImageButton) findViewById(R.id.gamePausedExitIB);    	
    	prevIB = (ImageButton) findViewById(R.id.gamePausedPrevIB);
    	restartIB = (ImageButton) findViewById(R.id.gamePausedRestartIB);
    	nextIB = (ImageButton) findViewById(R.id.gamePausedNextIB);
    	hide();
	}
				
	public void init(boolean hasPrev, boolean hasNext, OnEventReceivedListener onEventReceivedListener) {
		this.hasPrev = hasPrev;
		this.hasNext = hasNext;		
		this.onEventReceivedListener = onEventReceivedListener;
				
		if (hasPrev)
			prevIB.setVisibility(View.VISIBLE);
		else			
			prevIB.setVisibility(View.INVISIBLE);
		if (hasNext)
			nextIB.setVisibility(View.VISIBLE);
		else			
			nextIB.setVisibility(View.INVISIBLE);		
	}
	
	// game activity states
	public void load() {
		hide();
	}

	public void hold() {
		hide();
	}

	public void play() {
		hide();
	}

	public void pause() {
		show();
	}
	
	public void stop() {
		hide();
	}
	
	public void show() {        
		resumeIB.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				hide();				
				onEventReceivedListener.onEventReceived(GameActivity.Event.RESUME);
				gameSoundLoader.buttonClick();
			}			
		});
		mainMenuIB.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				hide();				
				onEventReceivedListener.onEventReceived(GameActivity.Event.MAINMENU);
				gameSoundLoader.buttonClick();
			}			
		});
		exitIV.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {						
				onEventReceivedListener.onEventReceived(GameActivity.Event.EXIT);
				gameSoundLoader.buttonClick();
			}			
		});
		restartIB.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				hide();				
				onEventReceivedListener.onEventReceived(GameActivity.Event.RESTART);
				gameSoundLoader.buttonClick();
			}			
		});
		if (hasPrev) {
			prevIB.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					hide();					
					onEventReceivedListener.onEventReceived(GameActivity.Event.PREV);
					gameSoundLoader.buttonClick();
				}			
			});		
		}
		if (hasNext) {
			nextIB.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					hide();					
					onEventReceivedListener.onEventReceived(GameActivity.Event.NEXT);
					gameSoundLoader.buttonClick();
				}			
			});
		}
        setVisibility(View.VISIBLE);
	}
	
	public void hide() {
        setVisibility(View.GONE);
		resumeIB.setOnClickListener(null);
		mainMenuIB.setOnClickListener(null);
		prevIB.setOnClickListener(null);
		restartIB.setOnClickListener(null);
		nextIB.setOnClickListener(null);
	}
}