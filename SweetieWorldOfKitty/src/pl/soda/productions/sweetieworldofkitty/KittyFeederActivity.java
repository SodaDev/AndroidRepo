package pl.soda.productions.sweetieworldofkitty;

import android.app.Activity;
import android.os.Bundle;

public class KittyFeederActivity extends Activity {
	private KittyFeederView view;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new KittyFeederView(this);
		setContentView(view);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		view.startFeeding();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		view.stopFeeding();
	}
}
