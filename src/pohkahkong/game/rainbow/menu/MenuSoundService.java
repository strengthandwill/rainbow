package pohkahkong.game.rainbow.menu;

import android.content.Context;
import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.service.SoundService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MenuSoundService {
	private static MenuSoundService instance = null;
	private SoundService soundService = SoundService.getInstance();

	private int playerBlink;	
	private int buttonClick;
	private int screenChange;

	private MenuSoundService() {
	}

	public static MenuSoundService getInstance() {
		if (instance==null) {
			instance = new MenuSoundService();
		}
		return instance;
	}
	
	public void init(Context context, boolean enabled) {
		soundService.init(context);
		soundService.setEnabled(enabled);
	}
	
	public void setEnabled(boolean enabled) {
		soundService.setEnabled(enabled);
	}
	
	public void increaseVolume() {
		soundService.increaseVolume();
	}
	
	public void decreaseVolume() {
		soundService.decreaseVolume();
	}
	
	public void load() {
		playerBlink = soundService.addSound(R.raw.player_blink);		
		buttonClick = soundService.addSound(R.raw.button_click);
		screenChange = soundService.addSound(R.raw.screen_change);
	}

	public void playerBlink() {		
		soundService.play(playerBlink);
	}	

	public void buttonClick() {
		soundService.play(buttonClick);
	}
	
	public void screenChange() {		
		soundService.play(screenChange);
	}
	
	public void release() {
		soundService.release();
	}
}