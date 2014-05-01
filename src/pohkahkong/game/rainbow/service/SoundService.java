package pohkahkong.game.rainbow.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class SoundService {
	private static final int MAX_STREAM = 10;
	private static SoundService instance = null;

	private Context context;
	private SoundPool soundPool = null;
	private AudioManager audioManager;
	private int actualVolume;
	private int maxVolume;
	private boolean enabled = true;

	private SoundService() {
	}

	public static SoundService getInstance() {
		if (instance==null) {
			instance = new SoundService();
		}
		return instance;
	}

	public void init(Context context) {
		this.context = context;
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		actualVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	}

	public int addSound(int id) {
		if (soundPool==null) {
			soundPool = new SoundPool(MAX_STREAM, AudioManager.STREAM_MUSIC, 0);
		}
		return soundPool.load(context, id, 1);
	}

	public void play(int soundID) {
		if (enabled) {
			float volume = ((float) actualVolume) / maxVolume;
			soundPool.play(soundID, volume, volume, 1, 0, 1f);
		}
	}
	
	public void increaseVolume() {
		actualVolume++;
		if (actualVolume>maxVolume) {
			actualVolume = maxVolume;
		}
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, actualVolume, AudioManager.FLAG_SHOW_UI);
	}
	
	public void decreaseVolume() {
		actualVolume--;
		if (actualVolume<0) {
			actualVolume = 0;
		}
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, actualVolume, AudioManager.FLAG_SHOW_UI);
	}

	public boolean enabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void release() {
		if (soundPool!=null) {
			soundPool.release();
			soundPool = null;
		}
	}	
}