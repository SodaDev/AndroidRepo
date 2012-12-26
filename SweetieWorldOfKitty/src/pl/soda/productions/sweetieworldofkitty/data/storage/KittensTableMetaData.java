package pl.soda.productions.sweetieworldofkitty.data.storage;

import android.net.Uri;
import android.provider.BaseColumns;

public class KittensTableMetaData implements BaseColumns{
	public static final String TABLE_NAME = "kittens";
	public static final Uri CONTENT_URI = Uri.parse("content://" + KittensProviderMetaData.AUTHORITY + "/" + TABLE_NAME);
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.androidkitty.kitty";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.androidkitty.kitty";
	public static final String DEFAULT_SORT_ORDER = "KITTY_NAME DESC";
	
	public static final String KITTY_NAME = "KITTY_NAME";	//String
	public static final String KITTY_CASH = "KITY_CASH";	//long	
	public static final String KITTY_HUNGER = "KITTY_HUNGER";	//int
	public static final String KITTY_SMOKE = "KITTY_SMOKE";	//int
	public static final String KITTY_HAPPINESS = "KITTY_HAPPINESS";	//int
	public static final String KITTY_EXPERIENCE = "KITTY_EXPERIENCE"; //int
	public static final String KITTY_LEVEL = "KITTY_LEVEL"; //int
	
	private KittensTableMetaData() {	}
}
