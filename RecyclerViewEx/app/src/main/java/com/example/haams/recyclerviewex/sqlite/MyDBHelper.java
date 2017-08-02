package com.example.haams.recyclerviewex.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by haams on 2017-07-21.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String dbname = "noteDB";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NoteDay (_id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , contents TEXTAREA);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String title, String contents) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO NoteDay VALUES(null,'" +title+ "','" +contents+ "');");
   //     db.close();
    }

    public String loadData(String title) {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM NoteDay" + " WHERE title="+"'"+title+"'", null);
        while (cursor.moveToNext()) {
            result += "[ " +cursor.getString(1) + " ] " + "\n" // column 1 == title
                    + "// 일정 : " + cursor.getString(2) +" // ";  // column2 == contents
        }
        return result;
    }
}
