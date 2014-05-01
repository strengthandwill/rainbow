package pohkahkong.game.rainbow.game.engine;

import java.util.Timer;
import java.util.TimerTask;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.GameSoundService;
import pohkahkong.game.rainbow.game.engine.GameEngine.Event;
import pohkahkong.game.rainbow.service.HandlerService;
import pohkahkong.game.rainbow.service.XmlParserService;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GameTimer extends RelativeLayout {
	private HandlerService handlerService = HandlerService.getInstance();
	private XmlParserService xmlParserService = XmlParserService.getInstance();	
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();	
	
	private Context context;
	private ImageView clock;
	private TextView textView;
	private Timer timer;
	private Animation animation;
	private int timeCritical;
	private int timeLeft = 0;
	private OnEventReceivedListener onEventReceivedListener;
	public GameTimer(Context context) {
		super(context);
		this.context = context;
		timeCritical = Integer.parseInt(getResources().getString(R.string.game_timercritcal));
	}

	public GameTimer(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		timeCritical = Integer.parseInt(getResources().getString(R.string.game_timercritcal));
	}

	public void setOnEventReceivedListener(OnEventReceivedListener onEventReceivedListener) {
		this.onEventReceivedListener = onEventReceivedListener;
		inflate(context, R.layout.game_timer, this);
		clock = (ImageView) findViewById(R.id.clockIV);
		textView = (TextView) findViewById(R.id.timeTV);
		animation = AnimationUtils.loadAnimation(context, R.anim.gametimer);
		timeLeft = xmlParserService.getDuration();
		textView.setText(timeLeft + "s");
		textView.setTextColor(Color.GREEN);
	}
	
	public void start() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (animation.hasStarted()) {
					timeLeft--;
				}
				if (timeLeft<0) {
					animation.cancel();
					onEventReceivedListener.onEventReceived(Event.GAMEOVER);
					return;
				}
				if (timeLeft<=timeCritical) {
					gameSoundLoader.timeTick();
				}
				
				handlerService.run(new Runnable() {
					public void run() {
						if (timeLeft<=timeCritical) {
							textView.setTextColor(Color.RED);							
						} else {
							textView.setTextColor(Color.GREEN);
						}
						clock.startAnimation(animation);
						textView.setText(timeLeft + "s");			
					}					
				});	
			}
		}, 0, 1000);	
	}
	
	public void stop() {
		if (timer==null) {
			return;
		}
		timer.cancel();
		timer = null;
	}
	

}