package pl.soda.productions.sweetieworldofkitty.data.storage;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class KittensContentProvider extends ContentProvider {
	public static HashMap<String, String> kittensProjectionsMap = new HashMap<String, String>(5);
	private static DatabaseHelper databaseHelper;
	
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int KITTENS_SINGLE = 1;
	private static final int KITTENS_COLLECTION = 2;
	
	public KittensContentProvider() {
		kittensProjectionsMap.put(KittensTableMetaData.KITTY_NAME, KittensTableMetaData.KITTY_NAME);
		kittensProjectionsMap.put(KittensTableMetaData.KITTY_CASH, KittensTableMetaData.KITTY_CASH);
		kittensProjectionsMap.put(KittensTableMetaData.KITTY_HUNGER, KittensTableMetaData.KITTY_HUNGER);
		kittensProjectionsMap.put(KittensTableMetaData.KITTY_SMOKE, KittensTableMetaData.KITTY_SMOKE);
		kittensProjectionsMap.put(KittensTableMetaData.KITTY_HAPPINESS, KittensTableMetaData.KITTY_HAPPINESS);
		kittensProjectionsMap.put(KittensTableMetaData.KITTY_EXPERIENCE, KittensTableMetaData.KITTY_EXPERIENCE);
		kittensProjectionsMap.put(KittensTableMetaData.KITTY_LEVEL, KittensTableMetaData.KITTY_LEVEL);
		
		uriMatcher.addURI(KittensProviderMetaData.AUTHORITY, KittensTableMetaData.TABLE_NAME, KITTENS_COLLECTION);
		uriMatcher.addURI(KittensProviderMetaData.AUTHORITY, KittensTableMetaData.TABLE_NAME + "/#", KITTENS_SINGLE);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count;
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		
		switch(uriMatcher.match(uri)){
		case KITTENS_SINGLE:
			String rowId = uri.getPathSegments().get(1);
			count = database.delete(KittensTableMetaData.TABLE_NAME, 
									KittensTableMetaData._ID + "=" + rowId + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""), 
									selectionArgs);
			break;
		case KITTENS_COLLECTION:
			count = database.delete(KittensTableMetaData.TABLE_NAME,
									selection, 
									selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("### Nieznany typ URI :" + uri);
	}
		
		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch(uriMatcher.match(uri)){
			case KITTENS_SINGLE:
				return KittensTableMetaData.CONTENT_ITEM_TYPE;
			case KITTENS_COLLECTION:
				return KittensTableMetaData.CONTENT_TYPE;
			default:
				throw new IllegalArgumentException("### Nieznany typ URI :" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(uriMatcher.match(uri) != KITTENS_COLLECTION){
			throw new IllegalArgumentException("### Nieznany typ URI :" + uri);
		}
		
		if(values.containsKey(KittensTableMetaData.KITTY_NAME) == false){
			throw new SQLException("### B³¹d w trakcie operacji INSERT -> brak: " + "NAME" + "###");
		}
		if(values.containsKey(KittensTableMetaData.KITTY_CASH) == false){
			throw new SQLException("### B³¹d w trakcie operacji INSERT -> brak: " + "CASH" + "###");
		}
		if(values.containsKey(KittensTableMetaData.KITTY_HUNGER) == false){
			throw new SQLException("### B³¹d w trakcie operacji INSERT -> brak: " + "HUNGER" + "###");
		}
		if(values.containsKey(KittensTableMetaData.KITTY_SMOKE) == false){
			throw new SQLException("### B³¹d w trakcie operacji INSERT -> brak: " + "SMOKE" + "###");
		}
		if(values.containsKey(KittensTableMetaData.KITTY_EXPERIENCE) == false){
			throw new SQLException("### B³¹d w trakcie operacji INSERT -> brak: " + "EXPERIENCE" + "###");
		}
		if(values.containsKey(KittensTableMetaData.KITTY_LEVEL) == false){
			throw new SQLException("### B³¹d w trakcie operacji INSERT -> brak: " + "LEVEL" + "###");
		}
		if(values.containsKey(KittensTableMetaData.KITTY_HAPPINESS) == false){
			throw new SQLException("### B³¹d w trakcie operacji INSERT -> brak: " + "HAPPINESS" + "###");
		}
		
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		long rowId = database.insert(KittensTableMetaData.TABLE_NAME, KittensTableMetaData.KITTY_NAME, values);
		
		if(rowId > 0){
			Uri newKittenRecord = ContentUris.withAppendedId(KittensTableMetaData.CONTENT_URI,
															 rowId);
//			getContext().getContentResolver().notifyChange(newKittenRecord, null);
			return newKittenRecord;
		}
		
		throw new SQLException("### B³¹d w trakcie operacji INSERT -> uri: " + uri + "###");
	}

	@Override
	public boolean onCreate() {
		databaseHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(KittensTableMetaData.TABLE_NAME);
		queryBuilder.setProjectionMap(kittensProjectionsMap);
		
		switch(uriMatcher.match(uri)){
			case KITTENS_SINGLE:
				queryBuilder.appendWhere(KittensTableMetaData._ID + "=" + uri.getPathSegments().get(1));
				break;
			case KITTENS_COLLECTION:
				break;
			default:
				throw new IllegalArgumentException("### Nieznany typ URI :" + uri);
		}
		
		if(TextUtils.isEmpty(sortOrder)){
			sortOrder = KittensTableMetaData.DEFAULT_SORT_ORDER;
		}
		
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
		
//		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int count;
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		
		switch(uriMatcher.match(uri)){
			case KITTENS_SINGLE:
				String rowId = uri.getPathSegments().get(1);
				count = database.update(KittensTableMetaData.TABLE_NAME,
										values, 
										KittensTableMetaData._ID + "=" + rowId + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""), 
										selectionArgs);
				break;
			case KITTENS_COLLECTION:
				count = database.update(KittensTableMetaData.TABLE_NAME,
						values, 
						selection, 
						selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("### Nieznany typ URI :" + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
