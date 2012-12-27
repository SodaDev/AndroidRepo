package pl.soda.productions.sweetieworldofkitty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

public class KittyFeederSpoon {
	private float positionX;
	private float positionY;
	private float targetPositionX;
	private float targetPositionY;
	private float cottageCheesePositionX;
	private float cottageCheesePositionY;
	private float lastPositionX;
	private float lastPositionY;
	private float accelerationX;
	private float accelerationY;
	private float friction;
	private float mass;
	private long lastChangeTime;
	private float lastDeltaTime;
	
	private Bitmap spoonFullImage;
	private Bitmap spoonEmptyImage;
	private Bitmap spoonCurrentImage;
	
	private int width;
	private int height;
	
	private PropertyChangeListener listener;
	
	public KittyFeederSpoon(Context context, int width, int height) {
		friction = 0.1f; //TODO: MO¯E UROZMAICIÆ!?
		mass = 2.5f;
		
		this.width = width;
		this.height = height;
		
		this.targetPositionX=0.015f;
		this.targetPositionY=0.03f;
		
		this.cottageCheesePositionX = -0.0175f;
		this.cottageCheesePositionY = -0.0055f;
		
		Options opts = new Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        spoonEmptyImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.spoon_empty, opts);
        spoonFullImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.spoon_full, opts);
        spoonCurrentImage = spoonEmptyImage;
	}

	public void updatePosition(float sx, float sy, long currentTime, float horizontalBound, float verticalBound){
		if(lastChangeTime == 0){
			lastChangeTime = currentTime;
		} else {
			final float deltaTime = (float) (currentTime - lastChangeTime) * (1.0f / 1000000000.0f);
			if(lastDeltaTime == 0){
				lastDeltaTime = deltaTime;
			} else {
				final float currentDeltaTime = deltaTime / lastDeltaTime;
				computePosition(sx, sy, currentDeltaTime, horizontalBound, verticalBound);
			}
		}
	}
	
	private void boundsAssertion(float horizontalBound, float verticalBound) {
		if(positionX > horizontalBound){
			positionX = horizontalBound;
		} else if (positionX < -horizontalBound){
			positionX = -horizontalBound;
		}
		
		if(positionY > verticalBound){
			positionY = verticalBound;
		} else if (positionY < -verticalBound){
			positionY = -verticalBound;
		}
	}

	private void computePosition(float sx, float sy, float currentDeltaTime, float horizontalBound, float verticalBound) {
		final float ax = -sx;
		final float ay = -sy;
		
		lastPositionX = positionX;
		lastPositionY = positionY;
		positionX = positionX + (friction * currentDeltaTime * (positionX - lastPositionX) + accelerationX * 0.1f)/100;
		positionY = positionY + (friction * currentDeltaTime * (positionY - lastPositionY) + accelerationY * 0.1f)/100;
		accelerationX = ax;
		accelerationY = ay;
		
		boundsAssertion(horizontalBound, verticalBound);
		
		Double distanceToKitty = Math.sqrt(Math.pow((positionX-0.0005) - targetPositionX, 2) + Math.pow((positionY+0.0025) - targetPositionY, 2));
		if(distanceToKitty < 0.00175){
			notifyListeners(false);
			spoonCurrentImage = spoonEmptyImage;
		} else {
			Log.v("JEDZ", "KITKA OTWIERA PASZCZE! Dystans: " + distanceToKitty);
		}
		
		Double distanceToCottageCheese = Math.sqrt(Math.pow((positionX-0.0075) - cottageCheesePositionX, 2) + Math.pow((positionY+0.01) - cottageCheesePositionY, 2));
		if(distanceToCottageCheese < 0.00275){
			notifyListeners(true);
			spoonCurrentImage = spoonFullImage;
		} else {
			Log.v("BIERZ SER", "### SER ERROR! Dystans: " + distanceToCottageCheese);
		}
	}

	public void addChangeListener(PropertyChangeListener newListener){
		listener = newListener;
	}
	
	private void notifyListeners(boolean isEating){
		listener.propertyChange(new PropertyChangeEvent(isEating, "currentSpoonState", null, isEating));
	}
	
	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
	
	public float getTargetPositionX() {
		return targetPositionX;
	}

	public void setTargetPositionX(float targetPositionX) {
		this.targetPositionX = targetPositionX;
	}

	public float getTargetPositionY() {
		return targetPositionY;
	}

	public void setTargetPositionY(float targetPositionY) {
		this.targetPositionY = targetPositionY;
	}
	
	public float getCottageCheesePositionX() {
		return cottageCheesePositionX;
	}

	public float getCottageCheesePositionY() {
		return cottageCheesePositionY;
	}

	public float getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(float accelerationX) {
		this.accelerationX = accelerationX;
	}

	public float getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(float accelerationY) {
		this.accelerationY = accelerationY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Bitmap getCurrentImage(){
		return spoonCurrentImage;
	}
}
