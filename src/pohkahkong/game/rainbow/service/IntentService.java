package pohkahkong.game.rainbow.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class IntentService {
	private static IntentService instance = null;

	private Activity activity;

	private IntentService() {	
	}

	public synchronized static IntentService getInstance() {
		if (instance==null) {
			instance = new IntentService();
		}
		return instance;
	}

	public void init(Activity activity) {
		this.activity = activity;
	}
	
	public void startActivity(Class<?> cls) {
		Intent intent = new Intent(activity, cls);
		activity.startActivity(intent);    	    	
    }
	
	public void startActivityWithExtra(Class<?> cls, Map<String, Integer> extra) {
		Intent intent = new Intent(activity, cls);
		Iterator<Entry<String, Integer>> iterator = extra.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			intent.putExtra(entry.getKey(), entry.getValue());
		}
		activity.startActivity(intent);    	    	
    }
	
	public void startUrlActivity(String url) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri data = Uri.parse(url);
		intent.setData(data);
		activity.startActivity(intent);		
	}

	public void startService(Class<?> cls) {
		Intent intent = new Intent(activity, cls);
		activity.startService(intent);
	}

	public void stopService(Class<?> cls) {
		Intent intent = new Intent(activity, cls);
		activity.stopService(intent);
	}
}
