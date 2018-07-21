package com.example.a16023018.hydration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;


/**
 * Created by 16023018 on 17/12/2017.
 */

public class DataSource {
    // Database fields
    private DBHelper dbHelper;
    private String[] allColumns = {DBHelper.COL_ID,
            DBHelper.COL_CUPNUMBER,
            DBHelper.COL_DATE
    };

    public DataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean addData(String date, int cupnumber) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_CUPNUMBER, cupnumber);
        values.put(DBHelper.COL_DATE, date);
        long insertId = database.insert(DBHelper.TABLE_INFO, null, values);
        database.close();
        if (insertId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void updateData(String date, int cupnumber) {
        Log.d("UpdateData", "" + cupnumber + " " + date);
        //,String date
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_CUPNUMBER, cupnumber);

        String condition = DBHelper.COL_DATE + " = ?";
        String[] args = {date};
        long affectedRow = db.update(DBHelper.TABLE_INFO, values, condition, args);
        db.close();
        if (affectedRow != 1) {
            //insert
            addData(date, cupnumber);
        }
    }

    public boolean deleteData(long id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String condition = DBHelper.COL_ID + " = ?";
        String[] args = {String.valueOf(id)};
        long affectedRow = database.delete(DBHelper.TABLE_INFO, condition, args);
        database.close();
        if (affectedRow != 1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Data> getAllData() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ArrayList<Data> dataArrayList = new ArrayList<Data>();
        Cursor cursor = database.query(DBHelper.TABLE_INFO, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(0);
            Integer cupNumber = cursor.getInt(1);
            //String date = cursor.getString(2);

            Data dataobj = new Data(id, cupNumber);
            //date
            dataArrayList.add(dataobj);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return dataArrayList;
    }

    public ArrayList<Data> getAllData(String keyword) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ArrayList<Data> dataArrayList = new ArrayList<Data>();
        String condition = DBHelper.COL_DATE + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = database.query(DBHelper.TABLE_INFO, allColumns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                Integer cupNumber = cursor.getInt(1);
                Data data = new Data(id, cupNumber);
                dataArrayList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return dataArrayList;
    }

    public int getCupsByDate(String date){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String condition = DBHelper.COL_DATE + " = ?";
        String[] args = {date};
        Cursor cursor = database.query(DBHelper.TABLE_INFO, allColumns, condition, args,
                null, null, null, null);
        int cupNumber = 0;
        if (cursor.moveToFirst()) {
            do {
                cupNumber = cursor.getInt(1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return cupNumber;

    }
}
