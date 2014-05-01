package pohkahkong.game.rainbow.service;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class ToastService {
	private static ToastService instance = null;
	private Context context;
	
	private ToastService() {
	}

	public static ToastService getInstance() {
		if (instance==null) {
			instance = new ToastService();
		}
		return instance;
	}

	public void init(Context context) {
		this.context = context;
	}
	
	public void showMessage(String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
