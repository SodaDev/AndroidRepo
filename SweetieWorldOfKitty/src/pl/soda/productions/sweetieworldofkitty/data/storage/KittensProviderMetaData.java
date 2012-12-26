package pl.soda.productions.sweetieworldofkitty.data.storage;

public class KittensProviderMetaData {
	public static final String AUTHORITY = "pl.soda.productions.KittensProvider";
	public static final String DATABASE_NAME = "kittens.db";
	public static final String KITTENS_TABLE_NAME = "KITTENS";
	public static final int VERSION= 2;
	
	public static KittensTableMetaData kittensTable;
	
	
	private KittensProviderMetaData(){}
}
