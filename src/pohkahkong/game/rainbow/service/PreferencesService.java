package pohkahkong.game.rainbow.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class PreferencesService {
	private static PreferencesService instance;
	
	private final String STAGE_KEY = "stage";	
	private final String COMPLETED_KEY = "isCompleted";
	private final String SOUNDENABLED_KEY = "isSoundEnabled";
	private final String VIBRATIONENABLED_KEY = "isVibrationEnabled";
	private final String ACCXINVERTED_KEY = "isAccXInverted";
	private final String ACCYINVERTED_KEY = "isAccYInverted";
	private final String RATINGPLAYCOUNT_KEY = "playCountToRate";
	
	private final String STAGE_DEFAULTVALUE = "1-1";
	private final boolean COMPLETED_DEFAULTVALUE = false;
	private final boolean SOUNDENABLED_DEFAULTVALUE = true;
	private final boolean VIBRATIONENABLED_DEFAULTVALUE = true;
	private final boolean ACCXINVERTED_DEFAULTVALUE = false;
	private final boolean ACCYINVERTED_DEFAULTVALUE = false;
	private final int RATINGPLAYCOUNT_DEFAULTVALUE = 0;

	private SharedPreferences sharedPreferences;

	private PreferencesService() {	
	}

	public synchronized static PreferencesService getInstance() {
		if (instance==null) {
			instance = new PreferencesService();
		}
		return instance;
	}
	
	public void init(Context context) {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public int getSeason() {
		return Integer.parseInt(getString(STAGE_KEY, STAGE_DEFAULTVALUE).split("-")[0]);
	}
	
	public int getLevel() {
		return Integer.parseInt(getString(STAGE_KEY, STAGE_DEFAULTVALUE).split("-")[1]);
	}
	
	public int getUnlockNum() {
		int season = Integer.parseInt(getString(STAGE_KEY, STAGE_DEFAULTVALUE).split("-")[0]);
		int level = Integer.parseInt(getString(STAGE_KEY, STAGE_DEFAULTVALUE).split("-")[1]);
		int unlockNum = 0;
    	if (season==1) {
    		if (level>=11)
    			unlockNum = 2;
    		else if (level>=1)
    			unlockNum = 1;    		
    	} else if (season==2) {
    		if (level>=16)
    			unlockNum = 4;
    		else if (level>=5)
    			unlockNum = 3;
    		else
    			unlockNum = 2;
    	} else if (season==3) {
    		if (level>=6)
    			unlockNum = 5;
    		else
    			unlockNum = 4;
    	} else {
    		if (level>=16)
    			unlockNum = 7;
    		else if (level>=6)
    			unlockNum = 6;
    		else
    			unlockNum = 5;
    	}
    	return unlockNum;
	}
	
	public void setStage(int season, int level) {
		setString(STAGE_KEY, season + "-" + level);
	}
	
	public boolean isCompleted() {
		return getBoolean(COMPLETED_KEY, COMPLETED_DEFAULTVALUE);
	}

	public void setCompleted(boolean completed) {
		setBoolean(COMPLETED_KEY, completed);
	}
	
	public boolean isSoundEnabled() {
		return getBoolean(SOUNDENABLED_KEY, SOUNDENABLED_DEFAULTVALUE);
	}

	public void setSoundEnabled(boolean soundEnabled) {
		setBoolean(SOUNDENABLED_KEY, soundEnabled);
	}

	public boolean isVibrationEnabled() {
		return getBoolean(VIBRATIONENABLED_KEY, VIBRATIONENABLED_DEFAULTVALUE);
	}

	public void setVibrationEnabled(boolean vibrationEnabled) {
		setBoolean(VIBRATIONENABLED_KEY, vibrationEnabled);
	}

	public boolean isAccXInverted() {
		return getBoolean(ACCXINVERTED_KEY, ACCXINVERTED_DEFAULTVALUE);
	}

	public void setAccXInverted(boolean soundEnabled) {
		setBoolean(ACCXINVERTED_KEY, soundEnabled);
	}

	public boolean setAccYInverted() {
		return getBoolean(ACCYINVERTED_KEY, ACCYINVERTED_DEFAULTVALUE);
	}

	public void setAccYInverted(boolean soundEnabled) {
		setBoolean(ACCYINVERTED_KEY, soundEnabled);
	}
	
	public void setRatingPlayCount(int ratingPlayCount) {
		setInt(RATINGPLAYCOUNT_KEY, ratingPlayCount);
	}
	
	public int getRatingPlayCount() {
		return getInt(RATINGPLAYCOUNT_KEY, RATINGPLAYCOUNT_DEFAULTVALUE);
	}
	
	private boolean getBoolean(String key, boolean defaultValue) {
		return sharedPreferences.getBoolean(key, defaultValue);
	}
	
	private void setBoolean(String key, boolean value) {
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	private int getInt(String key, int defaultValue) {
		return sharedPreferences.getInt(key, defaultValue);
	}
	
	private void setInt(String key, int value) {
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	private String getString(String key, String defaultValue) {
		return sharedPreferences.getString(key, defaultValue);
	}
	
	private void setString(String key, String value) {
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}  	
}
