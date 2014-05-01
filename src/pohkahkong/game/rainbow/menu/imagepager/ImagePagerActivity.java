package pohkahkong.game.rainbow.menu.imagepager;

import java.util.Timer;
import java.util.TimerTask;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.menu.MenuSoundService;
import pohkahkong.game.rainbow.menu.MenuActivityManager;
import pohkahkong.game.rainbow.menu.main.MainActivity;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.HandlerService;
import pohkahkong.game.rainbow.service.IntentService;
import pohkahkong.game.rainbow.view.ImageButton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public abstract class ImagePagerActivity extends FragmentActivity {
	protected static final String TAG = "ImagePagerActivity";
	protected MenuActivityManager activityManager = MenuActivityManager.getInstance();
	protected DimensionService dimensionService = DimensionService.getInstance();
	protected ImagePagerImageService imageService = ImagePagerImageService.getInstance();
	private MenuSoundService soundService = MenuSoundService.getInstance();
	protected IntentService intentService = IntentService.getInstance();
	protected HandlerService handlerService = HandlerService.getInstance();

	protected ImagePager imagePager;
	protected ViewPager viewPager;
	private ImageButton skipIB;
	protected Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityManager.onCreate(this);
		
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);		
		setContentView(R.layout.imagepager_main);
		skipIB = (ImageButton) findViewById(R.id.imagePagerSkipIB);				
		viewPager = (ViewPager) findViewById(R.id.imagePagerViewPager);
		
		
		imagePager = new ImagePager(getSupportFragmentManager(), imageService.getCount());				
		viewPager.setAdapter(imagePager);		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
    		public void onPageScrollStateChanged(int arg0) {
    		}

    		public void onPageScrolled(int arg0, float arg1, int arg2) {
    		}

    		public void onPageSelected(int position) {
    			soundService.screenChange();
    		}        	
    	});
		
		skipIB.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			soundService.buttonClick();
    			handlerService.run(new Runnable() {
    				public void run() {
    					intentService.startActivity(MainActivity.class);
    					activityManager.setTransiting();
    				}
    			}, 1000);
    		}        	
    	});
	}	
	
	@Override
	protected void onPause() {		
		activityManager.onPause();
		if (timer!=null) {
			timer.cancel();
			timer = null;
		}
		super.onPause();
	}

	@Override
	protected void onResume() {		
		activityManager.onResume();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (viewPager.getCurrentItem()<(imageService.getCount()-1)) {
					handlerService.run(new Runnable() {
						public void run() {
							viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);						
						}					
					});
				} else {
					intentService.startActivity(MainActivity.class);
					activityManager.setTransiting();
				}
			}			
		}, 5000, 5000);
		super.onResume();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {	
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			Log.i(TAG, "KEYCODE_VOLUME_UP");
			soundService.increaseVolume();
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			Log.i(TAG, "KEYCODE_VOLUME_DOWN");
			soundService.decreaseVolume();
		} 		
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (viewPager.getCurrentItem() == 0) {
	            super.onBackPressed();
	        } else {
	        	viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
	        }
		}	
		return true;
	}

	protected class ImagePager extends FragmentStatePagerAdapter {
		protected int size;

		public ImagePager(FragmentManager fm, int size) {
			super(fm);
			this.size = size;
		}

		@Override
		public Fragment getItem(int position) {
			return ImageFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			return size;
		}		
	}
}