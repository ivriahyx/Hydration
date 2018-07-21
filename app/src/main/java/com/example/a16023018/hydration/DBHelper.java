package com.example.a16023018.hydration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 16023018 on 17/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "data.db";
    public static final String TABLE_INFO = "information";
    // Columns names
    public static final String COL_ID = "_id";
    public static final String COL_CUPNUMBER = "cupnumber";
    public static final String COL_DATE = "date";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_create_table = "CREATE TABLE " + TABLE_INFO +
                "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_CUPNUMBER + " INTEGER,"+ COL_DATE +" TEXT"+")";
        //
        db.execSQL(sql_create_table);
        Log.v("Hydration","Database created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);
        // Create tables
        onCreate(db);
    }
}
