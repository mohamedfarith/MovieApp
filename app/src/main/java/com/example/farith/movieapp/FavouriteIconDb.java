package com.example.farith.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.provider.BaseColumns;
import android.util.Log;

public class FavouriteIconDb extends SQLiteOpenHelper {

    public static final String TABLE_NAME="FavouriteIcon";
    public static final String DATABASE_NAME="FavouriteIcon.db";
    public static final String COLUMN_NAME="STATUS";
    public static final String TITLE = "TITLE";
    public static final String ID  = BaseColumns._ID;
    public static final String SQL_ENTRIES ="CREATE TABLE " +TABLE_NAME+ "("+ID  +" "+"INTEGER PRIMARY KEY AUTOINCREMENT ,"+TITLE+" "+"  TEXT , "+COLUMN_NAME+" "+"TEXT)";
    public static final String DELETE_ENTRIES ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String TAG = "FavouriteIconDb";
    FavouriteIconDb(Context context){
        super(context,DATABASE_NAME,null,1,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: Sql entries are"+SQL_ENTRIES);
        db.execSQL(SQL_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: "+DELETE_ENTRIES);
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);
    }
    public void insertData(SQLiteDatabase database,String title,String favIconStatus){
        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,title);
        contentValues.put(COLUMN_NAME,favIconStatus);
        database.replace(TABLE_NAME,null,contentValues);
    }
    public String readValues(SQLiteDatabase database,String title){
        database = getReadableDatabase();
        String status = "";
        String sql="select "+COLUMN_NAME+" from "+TABLE_NAME+" where "+TITLE+" = "+title;
        String[] columns = {ID,TITLE,COLUMN_NAME};
        Cursor cursor =  database.rawQuery("select "+COLUMN_NAME+" from "+TABLE_NAME+" where "+TITLE+" = "+"\""+title+"\"",null);
        while (cursor.moveToNext()){
            status = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            Log.d(TAG, "readValues: "+status);
            return status;
        }
       return status;
    }
    public void update(SQLiteDatabase database,String status,String title){
        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,status);
        database.update(TABLE_NAME,contentValues,TITLE+" "+"="+"\""+title+"\"",null);
    }
}
