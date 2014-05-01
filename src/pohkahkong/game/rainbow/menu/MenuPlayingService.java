package pohkahkong.game.rainbow.menu;

import pohkahkong.game.rainbow.R;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MenuPlayingService extends Service {
	private MediaPlayer player;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		player = MediaPlayer.create(this, R.raw.background_music);
		player.setLooping(true); // Set looping
	}

	@Override
	public void onDestroy() {
		player.release();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		player.start();
	}
}