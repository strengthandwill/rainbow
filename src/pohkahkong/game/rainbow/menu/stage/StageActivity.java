package pohkahkong.game.rainbow.menu.stage;

import java.util.HashMap;
import java.util.Map;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import pohkahkong.game.rainbow.game.GameActivity;
import pohkahkong.game.rainbow.menu.MenuActivityManager;
import pohkahkong.game.rainbow.menu.MenuSoundService;
import pohkahkong.game.rainbow.service.HandlerService;
import pohkahkong.game.rainbow.service.IntentService;
import pohkahkong.game.rainbow.service.PreferencesService;
import pohkahkong.game.rainbow.view.ImageButton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.RelativeLayout;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class StageActivity extends FragmentActivity {
	private static final String TAG = "StageActivity";
	
	private PreferencesService preferencesService = PreferencesService.getInstance();
	private MenuActivityManager activityManager = MenuActivityManager.getInstance();
	private MenuSoundService soundService = MenuSoundService.getInstance();
	private IntentService intentService = IntentService.getInstance();
	private HandlerService handlerService = HandlerService.getInstance();
	
	private RelativeLayout stageRL;
	private ImageButton exitIB;
	private StageFragment[] stageFragments = new StageFragment[4];
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        activityManager.onCreate(this);
        
        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);        
        setContentView(R.layout.stage);
        pager = (ViewPager) findViewById(R.id.stageViewPager);
        stageRL = (RelativeLayout) findViewById(R.id.stageRL);
        exitIB = (ImageButton) findViewById(R.id.stageExitIB);
        
        initFragments();
        initAdapter();
        initButton();
    }
    
    private void initFragments() {
    	stageFragments[0] = new SpringStageFragment();
    	stageFragments[1] = new SummerStageFragment();
    	stageFragments[2] = new AutumnStageFragment();
    	stageFragments[3] = new WinterStageFragment();

    	for (int i=0; i<stageFragments.length; i++) {
    		stageFragments[i].setOnCompleteListener(new OnCompleteListener() {
    			public void onComplete(Object[] objects) {
    				final Map<String, Integer> extras = new HashMap<String, Integer>();
    				extras.put("season", (Integer) objects[0]);
    				extras.put("level", (Integer) objects[1]);				
    				handlerService.run(new Runnable() {
    					public void run() {
    						intentService.startActivityWithExtra(GameActivity.class, extras);
    						release();    						
    					}					
    				}, 1000);				
    			}    		
    		});        	
    	}
    }
    
    private void initAdapter() {
    	PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());      
    	pager.setAdapter(pagerAdapter);    
    	pager.setOnPageChangeListener(new OnPageChangeListener() {
    		public void onPageScrollStateChanged(int arg0) {
    		}

    		public void onPageScrolled(int arg0, float arg1, int arg2) {
    		}

    		public void onPageSelected(int position) {
    			soundService.screenChange();
    		}        	
    	});
    	pager.setCurrentItem(preferencesService.getSeason()-1);
    }
    
    private void initButton() {
    	exitIB.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			soundService.buttonClick();
    			handlerService.run(new Runnable() {
    				public void run() {
    					release();						
    				}
    			}, 1000);
    		}        	
    	});
    }
    
    private void release() {
    	stageRL.setVisibility(View.GONE);
		pager.removeAllViews();
		activityManager.finish();
    }
    
	@Override
	protected void onPause() {			
		activityManager.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {		
		activityManager.onResume();
		super.onResume();
	}
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {	
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			Log.i(TAG, "KEYCODE_VOLUME_UP");
			//soundService.increaseVolume();
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			Log.i(TAG, "KEYCODE_VOLUME_DOWN");
			//soundService.decreaseVolume();
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (pager.getCurrentItem() == 0) {
				//activityManager.setTransiting();
	            super.onBackPressed();
	        } else {
	            pager.setCurrentItem(pager.getCurrentItem() - 1);
	        }
		}	
		return true;
	}

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
  		public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return stageFragments[position];
        }

        @Override
        public int getCount() {
            return stageFragments.length;
        }
    }
}
