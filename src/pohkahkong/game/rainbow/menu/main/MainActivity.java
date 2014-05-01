package pohkahkong.game.rainbow.menu.main;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import pohkahkong.game.rainbow.menu.MenuImageService;
import pohkahkong.game.rainbow.menu.MenuSoundService;
import pohkahkong.game.rainbow.menu.MenuActivityManager;
import pohkahkong.game.rainbow.menu.stage.StageActivity;
import pohkahkong.game.rainbow.service.HandlerService;
import pohkahkong.game.rainbow.service.IntentService;
import pohkahkong.game.rainbow.service.MusicService;
import pohkahkong.game.rainbow.service.PreferencesService;
import pohkahkong.game.rainbow.service.ToastService;
import pohkahkong.game.rainbow.view.ImageButton;
import pohkahkong.game.rainbow.view.ToggleImageButton;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MainActivity extends Activity {	
	private PreferencesService preferencesService = PreferencesService.getInstance();
	private MenuImageService imageService = MenuImageService.getInstance();
	private MenuSoundService soundService = MenuSoundService.getInstance();	
	private MusicService musicService = MusicService.getInstance();
	private ToastService toastService = ToastService.getInstance();
	private IntentService intentService = IntentService.getInstance();
	private HandlerService handlerService = HandlerService.getInstance();
	private MenuActivityManager activityManager = MenuActivityManager.getInstance();
	
	private RelativeLayout mainRL;
	private MainView mainView;	
	private ImageView mainSupportIV;
	private ImageButton playIB;
	private ToggleImageButton soundTIB;
	private ImageButton androidIB;
	private ImageButton facebookIB;
	private ImageButton exitIB;
	
	private boolean rating = false;
	private boolean loading = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.onCreate(this);        
        
        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);        
        setContentView(R.layout.main);
        mainRL = (RelativeLayout) findViewById(R.id.mainRL);
    	playIB = (ImageButton) findViewById(R.id.mainPlayIB);    	
    	mainView = (MainView) findViewById(R.id.mainView); 
    	mainSupportIV = (ImageView) findViewById(R.id.mainSupportIV);    	
    	soundTIB = (ToggleImageButton) findViewById(R.id.mainSoundTIB);
    	androidIB = (ImageButton) findViewById(R.id.mainAndroidIB);
    	facebookIB = (ImageButton) findViewById(R.id.mainFacebookIB);
    	exitIB = (ImageButton) findViewById(R.id.mainExitIB);                            
                
		initMainView();
		initButtons();
    }
    
    private void initMainView() {    	
		mainView.init();		
		imageService.loadBitmaps(new OnCompleteListener() {
			public void onComplete(Object[] objects) {				
				mainView.load();
				activityManager.load();
				if (preferencesService.getRatingPlayCount() >= 
						Integer.parseInt(getResources().getString(R.string.max_ratingplaycount))) {					
					setRating(true);
					preferencesService.setRatingPlayCount(0);
				}				
				mainRL.setVisibility(View.VISIBLE);
				loading = false;
			}			
		});
    }
    
    private void initButtons() {
    	playIB.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			soundService.buttonClick();
    			intentService.startActivity(StageActivity.class);
    			activityManager.setTransiting();
    		}        	
    	});

    	soundTIB.setToggled(!preferencesService.isSoundEnabled());        
    	soundTIB.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			if (!soundTIB.isToggled()) { // sound enabled
    				soundService.setEnabled(true);
    				musicService.setEnabled(true);
    				musicService.play();
    				preferencesService.setSoundEnabled(true);
    				soundService.buttonClick();
    				toastService.showMessage("Sound is enabled");
    			} else {
    				soundService.setEnabled(false);
    				musicService.setEnabled(false);
    				musicService.stop();
    				preferencesService.setSoundEnabled(false);
    				soundService.buttonClick();
    				toastService.showMessage("Sound is disabled");  					
    			}

    		}        	
    	});

    	androidIB.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			preferencesService.setRatingPlayCount(-1);
    			soundService.buttonClick();
    			handlerService.run(new Runnable() {
    				public void run() {
    					intentService.startUrlActivity(getResources().getString(R.string.android_url));    				
    				}
    			}, 1000);
    		}        	
    	});

    	facebookIB.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			soundService.buttonClick();
    			handlerService.run(new Runnable() {
    				public void run() {
    					activityManager.onPause();
    	    			intentService.startUrlActivity(getResources().getString(R.string.facebook_url));    				
    				}
    			}, 1000);
    		}        	
    	});

    	exitIB.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			soundService.buttonClick();
    			handlerService.run(new Runnable() {
    				public void run() {
    					mainRL.setVisibility(View.GONE);
    					imageService.release();
    					activityManager.finish();    					
    				}
    			}, 1000);
    		}        	
    	});
    }
    	
	@Override
	protected void onPause() {	
		mainRL.setVisibility(View.GONE);
		mainView.pause();
		activityManager.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (!loading) {
			mainRL.setVisibility(View.VISIBLE);
			mainView.resume();
		}		
		activityManager.onResume();
		if (rating) {
			setRating(false);
		}
		super.onResume();
	}
		
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			soundService.increaseVolume();
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			soundService.decreaseVolume();
		}	
		return true;
	}
	
	public void setRating(boolean rating) {
		this.rating = rating;
		if (rating) {
			mainSupportIV.setVisibility(View.VISIBLE);
			mainView.setTranslucent(true);
			soundTIB.setAlpha(100);
			facebookIB.setAlpha(100);
			exitIB.setAlpha(100);
		} else {
			mainSupportIV.setVisibility(View.GONE);
			mainView.setTranslucent(false);
			soundTIB.setAlpha(255);
			facebookIB.setAlpha(255);
			exitIB.setAlpha(255);
		}
	}
}