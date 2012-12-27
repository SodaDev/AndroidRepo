package pl.soda.productions.sweetieworldofkitty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class KittyMainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitty_main_menu);
        
        final Button buttonCreator = (Button) findViewById(R.id.buttonCreateKittyMenu);
        buttonCreator.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), KittyCreatorActivity.class);
				startActivity(intent);
			}
		});
        
        final Button buttonFeeder = (Button) findViewById(R.id.buttonFeed);
        buttonFeeder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), KittyFeederActivity.class);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_kitty_main_menu, menu);
        return true;
    }
}
