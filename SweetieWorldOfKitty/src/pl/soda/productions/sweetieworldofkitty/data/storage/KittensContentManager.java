package pl.soda.productions.sweetieworldofkitty.data.storage;

import android.content.ContentValues;
import android.net.Uri;

public class KittensContentManager{
	private static KittensContentProvider kittensContentProvider = new KittensContentProvider();
	
	public KittensContentManager() {}
	
	public static Uri createNewKitty(String kittyName){
      ContentValues values = new ContentValues();
      values.put(KittensTableMetaData.KITTY_NAME, kittyName);
      values.put(KittensTableMetaData.KITTY_CASH, 0);
      values.put(KittensTableMetaData.KITTY_HUNGER, 70);
      values.put(KittensTableMetaData.KITTY_SMOKE, 70);
      values.put(KittensTableMetaData.KITTY_HAPPINESS, 100);
      values.put(KittensTableMetaData.KITTY_EXPERIENCE, 0);
      values.put(KittensTableMetaData.KITTY_LEVEL, 0);
//      
//      int iterator = 0;
//      String[] columnsNames = new String[KittensContentProvider.kittensProjectionsMap.values().size()]; 
//      for(String column : KittensContentProvider.kittensProjectionsMap.values()){
//      	columnsNames[iterator] = column;
//      	iterator++;
//      }
//      
//      Cursor results = kittensProvider.query(KittensTableMetaData.CONTENT_URI,
//				  			  columnsNames, 
//				  			  null, 
//				  			  null, 
//				  			  null);
//       
//      if(results.moveToNext() == false){
//      	Log.v("cursorTag", "empty Query!");
//      }
//      
//      int nameColumnId = results.getColumnIndex(KittensTableMetaData.KITTY_NAME);
//      while(results.moveToNext()){
//      	Log.v("cursorTag", results.getString(nameColumnId));
//      }
		
      return kittensContentProvider.insert(KittensTableMetaData.CONTENT_URI, values);
	}
}
