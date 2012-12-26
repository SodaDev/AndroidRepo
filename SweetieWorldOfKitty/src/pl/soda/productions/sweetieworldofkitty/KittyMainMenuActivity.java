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
        
        final Button buttonCreator = (Button) findViewById(R.id.buttonFeed);
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
				buttonFeeder.setText(buttonFeeder.getText() + "8==>");
				finish();
			}
		});
//        KittensContentProvider kittensProvider = new KittensContentProvider();
//        ContentValues values = new ContentValues();
//        values.put(KittensTableMetaData.KITTY_NAME, "Ciusia");
//        values.put(KittensTableMetaData.KITTY_CASH, 100);
//        values.put(KittensTableMetaData.KITTY_HUNGER, 100);
//        values.put(KittensTableMetaData.KITTY_SMOKE, 100);
//        values.put(KittensTableMetaData.KITTY_HAPPINESS, 100);
//        values.put(KittensTableMetaData.KITTY_EXPERIENCE, 100);
//        values.put(KittensTableMetaData.KITTY_LEVEL, 0);
//        
//        int iterator = 0;
//        String[] columnsNames = new String[KittensContentProvider.kittensProjectionsMap.values().size()]; 
//        for(String column : KittensContentProvider.kittensProjectionsMap.values()){
//        	columnsNames[iterator] = column;
//        	iterator++;
//        }
//        
//        Cursor results = kittensProvider.query(KittensTableMetaData.CONTENT_URI,
//				  			  columnsNames, 
//				  			  null, 
//				  			  null, 
//				  			  null);
//         
//        if(results.moveToNext() == false){
//        	Log.v("cursorTag", "empty Query!");
//        }
//        
//        int nameColumnId = results.getColumnIndex(KittensTableMetaData.KITTY_NAME);
//        while(results.moveToNext()){
//        	Log.v("cursorTag", results.getString(nameColumnId));
//        }
        
//        System.out.println("new record uri is: " + kittensProvider.insert(KittensTableMetaData.CONTENT_URI, values));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_kitty_main_menu, menu);
        return true;
    }
}
