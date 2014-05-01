package pohkahkong.game.rainbow.service;

import android.os.Handler;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class HandlerService {
	private static HandlerService instance = null;	
	private final Handler handler = new Handler();
	
	private HandlerService() {
	}

	public static HandlerService getInstance() {
		if (instance==null) {
			instance = new HandlerService();
		}		
		return instance;
	}
	
	public void run(Runnable runnable) {
		handler.post(runnable);
	}
	
	public void run(Runnable runnable, long delay) {
		handler.postDelayed(runnable, delay);
	}
	
	public void release() {
		handler.removeCallbacksAndMessages(null);
	}
}
