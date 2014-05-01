package pohkahkong.game.rainbow.service;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class AccelerometerService implements SensorEventListener {
	private static AccelerometerService instance = null;
	
	private final float calibrateY = 6.0f;
	private final float sensitivity = 1.0f;	
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	private float deltaX = 0.0f;
	private float deltaY = 0.0f;	
	
	private AccelerometerService() {
	}

	public static AccelerometerService getInstance() {
		if (instance==null) {
			instance = new AccelerometerService();
		}
		return instance;
	}
	
	public void init(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);        		
	}
	
	public void onAccuracyChanged(Sensor arg0, int arg1) {	
	}

	public void onSensorChanged(SensorEvent event) {
		float x = event.values[0];
		float y = event.values[1]-calibrateY;
		
		if (Math.abs(x)>sensitivity) {
			if (x<0)
				deltaX = -(x+sensitivity);
			else
				deltaX = -(x-sensitivity);
		} else
			deltaX = 0.0f;		
		if (Math.abs(y)>sensitivity) {
			if (y<0)
				deltaY = y+sensitivity;
			else
				deltaY = y-sensitivity;
		} else
			deltaY = 0.0f;				
	}
	
	public void register() {
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void unregister() {
		mSensorManager.unregisterListener(this);
	}

	public float getDeltaX() {
		return deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}	
}
