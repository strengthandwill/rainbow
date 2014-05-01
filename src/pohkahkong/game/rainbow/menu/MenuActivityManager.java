package pohkahkong.game.rainbow.menu;

import java.util.ArrayList;
import java.util.List;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.IntentService;
import pohkahkong.game.rainbow.service.MusicService;
import pohkahkong.game.rainbow.service.PreferencesService;
import pohkahkong.game.rainbow.service.ToastService;

import android.app.Activity;
import android.util.Log;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MenuActivityManager {
	private static enum State {STARTING, RUNNING, TRANSITING}
	private static MenuActivityManager instance = null;
	
	private PreferencesService preferencesService = PreferencesService.getInstance();
	private MenuSoundService soundService = MenuSoundService.getInstance();	
	private MusicService musicService = MusicService.getInstance();
	protected DimensionService dimensionService = DimensionService.getInstance();
	private IntentService intentService = IntentService.getInstance();
	private ToastService toastService = ToastService.getInstance();
		
	private State state = State.STARTING;
	private List<Activity> activities = new ArrayList<Activity>();
	
	private MenuActivityManager() {	
	}

	public synchronized static MenuActivityManager getInstance() {
		if (instance==null) {
			instance = new MenuActivityManager();
		}
		return instance;
	}
		
	public void onCreate(Activity activity) {
		activities.add(activity);
		intentService.init(activity);
		if (state == State.STARTING) {
			preferencesService.init(activity);
			soundService.init(activity, preferencesService.isSoundEnabled());
			musicService.init(activity, preferencesService.isSoundEnabled());
			dimensionService.init(activity);
			dimensionService.initTileDimension(
				Integer.parseInt(activity.getResources().getString(R.string.menu_mapwidth)),
				Integer.parseInt(activity.getResources().getString(R.string.menu_mapheight)));
			toastService.init(activity);
			state = State.RUNNING;
		}
		
	}
	
	public void load() {
		soundService.load();
		musicService.play();
		state = State.RUNNING;
	}
		
	public void onResume() {
		if (state == State.RUNNING) {
			soundService.load();
			musicService.play();
		}
		state = State.RUNNING;
	}
	
	public void onPause() {
		if (state == State.RUNNING) {
			
			Log.i("MenuActivityManager", "onPause");
			
			soundService.release();
			musicService.stop();
		}
	}
	
	public void setTransiting() {
		state = State.TRANSITING;
	}
	
	public void finish() {
		soundService.release();
		musicService.stop();
		for (int i=0; i<activities.size(); i++) {
			activities.get(i).finish();
		}
		activities.clear();
		state = State.STARTING;
	}
}
