package pl.soda.productions.sweetieworldofkitty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class KittyFeederView extends View{
	private KittyFeederController controller;
	private KittyFeederSpoon kittySpoon;
	private float screenOriginalX;
	private float screenOriginalY;
	private float screenXInPixels;
	private float screenYInPixels;
	private float horizontalBound;
	private float verticalBound;
//	private Bitmap background;
	
	public KittyFeederView(Context context) {
		super(context);
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		controller = new KittyFeederController(display,			
											   sensorManager,
											   context.getResources());
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);
		screenXInPixels = displayMetrics.xdpi / 0.0254f;
		screenYInPixels = displayMetrics.ydpi / 0.0254f;
		
		kittySpoon = new KittyFeederSpoon(context, 2, 1);
		kittySpoon.addChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				setKittyImage((Boolean) event.getNewValue());
			}
		});
		
		Options opts = new Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
//        background = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, opts);
	}

	public void startFeeding(){
		controller.start();
	}
	
	public void stopFeeding(){
		controller.stop();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		screenOriginalX = (w - kittySpoon.getCurrentImage().getWidth()/2) * 0.5f; //[px]
		screenOriginalY = (h - kittySpoon.getCurrentImage().getHeight()/2) * 0.5f; //[px]
		horizontalBound = ((w / screenXInPixels) * 0.5f); //[m]
		verticalBound = ((h / screenYInPixels) * 0.5f); //[m]
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(controller.getKittyFoodImg(),
						  screenOriginalX + kittySpoon.getCottageCheesePositionX() * screenXInPixels, 
				  		  screenOriginalY - kittySpoon.getCottageCheesePositionY() * screenYInPixels,
				  		  null);
		canvas.drawBitmap(controller.getKittyActiveImg(),
						  screenOriginalX + kittySpoon.getTargetPositionX() * screenXInPixels, 
						  screenOriginalY - kittySpoon.getTargetPositionY() * screenYInPixels,
						  null);
		kittySpoon.updatePosition(controller.getAccelerometerX(),
								  controller.getAccelerometerY(),
								  controller.getCurrentTime(),
								  horizontalBound,
								  verticalBound);
		canvas.drawBitmap(kittySpoon.getCurrentImage(),
						  screenOriginalX + kittySpoon.getPositionX() * screenXInPixels, 
						  screenOriginalY - kittySpoon.getPositionY() * screenYInPixels,
						  null);
		invalidate();
	}
	
	private void setKittyImage(boolean isEating) {
		if(isEating){
			controller.setKittyActiveImg(controller.getKittyEatingImg());
		} else {
			controller.setKittyActiveImg(controller.getKittyNotEatingImg());
		}
	}
}
