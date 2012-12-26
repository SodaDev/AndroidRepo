package pl.soda.productions.sweetieworldofkitty.data.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, KittensProviderMetaData.DATABASE_NAME, null, KittensProviderMetaData.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE " + KittensTableMetaData.TABLE_NAME + " (" + KittensTableMetaData._ID + " INTEGER PRIMARY KEY,"
																				+ KittensTableMetaData.KITTY_NAME + " TEXT,"
																				+ KittensTableMetaData.KITTY_CASH + " INTEGER,"
																				+ KittensTableMetaData.KITTY_HUNGER + " INTEGER,"
																				+ KittensTableMetaData.KITTY_SMOKE + " INTEGER,"
																				+ KittensTableMetaData.KITTY_HAPPINESS + " INTEGER,"
																				+ KittensTableMetaData.KITTY_EXPERIENCE + " INTEGER,"
																				+ KittensTableMetaData.KITTY_LEVEL + " INTEGER" 
																		 + " );";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("DB_UPDATE", "### Zmiana bazy wersji: " + oldVersion + " -> " + newVersion + "###\n### Dane zostan¹ utracone ###");
		db.execSQL("DROP TABLE IF EXISTS " + KittensTableMetaData.TABLE_NAME);
		onCreate(db);
	}

}
