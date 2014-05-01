package pohkahkong.game.rainbow.menu.stage;

import pohkahkong.game.rainbow.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class StageSoundsDevice {
	private Context context;
	private SoundPool soundPool;
	private boolean isEnabled = true;
	
	private int buttonClick;
	private int screenSwipe;	
	
	public StageSoundsDevice (Context context) {
		this.context = context;		
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		buttonClick = soundPool.load(context, R.raw.button_click, 1);
		screenSwipe = soundPool.load(context, R.raw.screen_change, 1);		
	}
	
	private void play(int soundID) {
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;
		soundPool.play(soundID, volume, volume, 1, 0, 1f);		
	}	
	
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void buttonClick() {
		if (isEnabled)
			play(buttonClick);
	}
	
	public void screenSwipe() {
		if (isEnabled)
			play(screenSwipe);
	}
	
	public void release() {
		if (soundPool!=null)
			soundPool.release();
	}
}