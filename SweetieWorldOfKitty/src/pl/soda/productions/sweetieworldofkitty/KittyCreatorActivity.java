package pl.soda.productions.sweetieworldofkitty;

import pl.soda.productions.sweetieworldofkitty.data.storage.KittensContentManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class KittyCreatorActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kitty_creator);
		
		Button createKittyButton = (Button) findViewById(R.id.buttonCreateKitty);
		createKittyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				KittensContentManager.createNewKitty(((EditText) findViewById(R.id.editKittyNameInput)).getText().toString());
				finish();
			}
		});
		
	}
}
