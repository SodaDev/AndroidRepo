package pl.soda.productions.sweetieworldofkitty;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;

public class KittyFeederController implements SensorEventListener{
	private Display display;
	private Bitmap kittyEatingImg;
	private Bitmap kittyNotEatingImg;
	private Bitmap kittyActiveImg;
	private Bitmap kittyFoodImg;
	
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private float accelerometerX;
	private float accelerometerY;
	private long accelerometerMovementTime;
	private long cpuMovementTime;
	

	public KittyFeederController(Display display, SensorManager systemServiceSensorManager, Resources resources) {
		this.display = display;
		sensorManager = systemServiceSensorManager;
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		Options opts = new Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        kittyEatingImg = BitmapFactory.decodeResource(resources, R.drawable.kitty_eating, opts);
        kittyNotEatingImg = BitmapFactory.decodeResource(resources, R.drawable.kitty_not_eating, opts);
        kittyFoodImg = BitmapFactory.decodeResource(resources, R.drawable.kitty_food, opts);
        kittyActiveImg = kittyNotEatingImg;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			switch(display.getOrientation()){
				case Surface.ROTATION_0:
					accelerometerX = event.values[0];
					accelerometerY = event.values[1];
					break;
				case Surface.ROTATION_90:
					accelerometerX = -event.values[1];
					accelerometerY = event.values[0];
					break;
				case Surface.ROTATION_180:
					accelerometerX = -event.values[0];
					accelerometerY = -event.values[1];
					break;
				case Surface.ROTATION_270:
					accelerometerX = event.values[1];
					accelerometerY = -event.values[0];
					break;
			}
			accelerometerMovementTime = event.timestamp;
			cpuMovementTime = System.nanoTime();
		}
		return;
	}

	public void start() {
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
	}

	public void stop() {
		sensorManager.unregisterListener(this);
	}

	public float getAccelerometerX() {
		return accelerometerX;
	}

	public void setAccelerometerX(float accelerometerX) {
		this.accelerometerX = accelerometerX;
	}

	public float getAccelerometerY() {
		return accelerometerY;
	}

	public void setAccelerometerY(float accelerometerY) {
		this.accelerometerY = accelerometerY;
	}

	public long getCurrentTime(){
		return accelerometerMovementTime + (System.nanoTime() - cpuMovementTime);
	}

	public Bitmap getKittyActiveImg() {
		return kittyActiveImg;
	}

	public void setKittyActiveImg(Bitmap kittyActiveImg) {
		this.kittyActiveImg = kittyActiveImg;
	}

	public Bitmap getKittyEatingImg() {
		return kittyEatingImg;
	}

	public Bitmap getKittyNotEatingImg() {
		return kittyNotEatingImg;
	}

	public Bitmap getKittyFoodImg() {
		return kittyFoodImg;
	}
	
	
}
