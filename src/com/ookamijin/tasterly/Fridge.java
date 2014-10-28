package com.ookamijin.tasterly;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/*
 * This class is a sqlite database adapter that 
 * holds the data associated with a beer
 */
public class Fridge extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "fridge.db";
	public final static String DATABASE_PATH = "/data/data/com.ookamijin.tasterly/databases/";

	private final Context context;
	private SQLiteDatabase db;

	public Fridge(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		if (checkDB()) {
			
		} else {
			try {
				this.getReadableDatabase();
				copyDB();
				this.close();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
			Toast.makeText(context, "Initial database is created",
					Toast.LENGTH_LONG).show();

		}
	}

	private boolean checkDB() {
		SQLiteDatabase checkDB = null;
	    boolean exist = false;
	    try {
	        String dbPath = DATABASE_PATH + DATABASE_NAME;
	        checkDB = SQLiteDatabase.openDatabase(dbPath, null,
	                SQLiteDatabase.OPEN_READONLY);
	    } catch (SQLiteException e) {
	        Log.v("db log", "database does't exist");
	    }

	    if (checkDB != null) {
	        exist = true;
	        checkDB.close();
	    }
	    return exist;
	}

	private void copyDB() throws IOException {
		InputStream myInput = context.getAssets().open(DATABASE_NAME);
	    String outFileName = DATABASE_PATH + DATABASE_NAME;
	    OutputStream myOutput = new FileOutputStream(outFileName);

	    byte[] buffer = new byte[1024];
	    int length;
	    while ((length = myInput.read(buffer))>0){
	        myOutput.write(buffer, 0, length);
	    }

	    myOutput.flush();
	    myOutput.close();
	    myInput.close();
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO does this need to do anything?
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void open() throws SQLException {
		String dbPath = DATABASE_PATH + DATABASE_NAME;
	    db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
	}

	
	@Override
	public synchronized void close() {
 
    	    if(db != null)
    		    db.close();
 
    	    super.close();
 
	}

	public long fillBeer(String name, String style, float rating,
			String pic_ids, String fValues, String id[]) {
		
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("style", style);
		cv.put("rating", rating);
		cv.put("pic_ids", pic_ids);
		cv.put("maj_val", fValues);
		
		for (int i = 0; i < id.length; ++i) {
			cv.put(id[i], 0);
		}
		return db.insert("BeerTable", null, cv);
	}

	public boolean refillBeer(String name, String style, float rating,
			String pic_ids, long rowId) {

		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("style", style);
		cv.put("rating", rating);
		cv.put("pic_ids", pic_ids);

		return db.update("BeerTable", cv, "_id" + "=" + rowId, null) > 0;

	}
	
	public boolean updateFlavor(long rowId, String fId, int value) {
		ContentValues cv = new ContentValues();
		cv.put(fId, value);
		return db.update("BeerTable", cv, "_id" + "=" + rowId, null) > 0;
	}

	public boolean refillBeer(long rowId, String fValues) {
		ContentValues cv = new ContentValues();
		cv.put("maj_val", fValues);
		return db.update("BeerTable", cv, "_id" + "=" + rowId, null) > 0;

	}

	public Cursor getList() {
		return db.query("BeerTable", new String[] { "_id", "name",
				"style", "rating", "pic_ids" }, null, null, null, null, null);
	}

	public Cursor getBeer(long rowId) throws SQLException {
		Cursor curse = db.query(true, "BeerTable", new String[] { "_id",
				"name", "style", "rating", "maj_val" }, "_id" + "="
				+ rowId, null, null, null, null, null);
		if (curse != null) {
			curse.moveToFirst();
		}
		return curse;
	}

	public Cursor getSeekValues(long rowId, String[] id) {
		Cursor curse = db.query(true, "BeerTable", id, "_id" + "="
				+ rowId, null, null, null, null, null);
		if (curse != null) {
			curse.moveToFirst();
		}
		return curse;
	}
	
	public boolean drinkBeer(long rowId) {

		return db.delete("BeerTable", "_id" + "=" + rowId, null) > 0;

	}

	

}
