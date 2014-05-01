package pohkahkong.game.rainbow.service;

import pohkahkong.game.rainbow.menu.MenuPlayingService;
import android.app.Activity;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MusicService {
	private static MusicService instance = null;
	
	private IntentService intentService = IntentService.getInstance();
	private boolean enabled = true;
	private boolean isPlaying = false;
	
	private MusicService() {
	}

	public static MusicService getInstance() {
		if (instance==null) {
			instance = new MusicService();
		}
		return instance;
	}
	
	public void init(Activity activity, boolean enabled) {
		this.enabled = enabled;
		intentService.init(activity);
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void play() {		
		if (enabled && !isPlaying) {
			intentService.startService(MenuPlayingService.class);
			isPlaying = true;
		}
	}
	
	public void stop() {
		intentService.stopService(MenuPlayingService.class);
		isPlaying = false;
	}
}
