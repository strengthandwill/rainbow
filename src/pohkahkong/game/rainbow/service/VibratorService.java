package pohkahkong.game.rainbow.service;

import android.content.Context;
import android.os.Vibrator;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class VibratorService {
	private static VibratorService instance = null;
	
	private Vibrator vibrator;
	private boolean isEnabled = true;
	
	private long[] stunPattern = {0l, 500l, 100l, 500l, 100l, 500l};
	private long[] gameOverEnemyPattern = {0l, 800l, 200l, 800l};
	
	private VibratorService() {
	}

	public static VibratorService getInstance() {
		if (instance==null) {
			instance = new VibratorService();
		}
		return instance;
	}
	
	public void init(Context context) {
		vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);				
	}
		
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void playerNormal() {
		if (isEnabled)
			vibrator.vibrate(10);
	}
	
	public void playerSlow() {
		if (isEnabled)
			vibrator.vibrate(100);
	}	
	
	public void playerStun() {
		if (isEnabled)
			vibrator.vibrate(stunPattern, -1);
	}	
	
	public void playerSlip() {
		if (isEnabled)
			vibrator.vibrate(1000);
	}
	
	public void playerGameOverEnemy() {
		if (isEnabled)
			vibrator.vibrate(gameOverEnemyPattern, -1);
	}		
	
	public void release() {
		vibrator.cancel();
	}
}
