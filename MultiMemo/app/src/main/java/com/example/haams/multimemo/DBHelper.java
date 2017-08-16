package com.example.haams.multimemo;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by haams on 2017-08-15.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static String createSQL = "CREATE TABLE memoTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, memo TEXT, image VARCHAr);";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSQL);
    }

    public void insertMemoWithImage(String title, String memo, String image) {
        SQLiteDatabase db = getWritableDatabase();
        // getWritableDatabase() --> DB 생성 하는데 쓰기 모드로 생성
        db.execSQL("INSERT INTO memoTable VALUES(null, '" + title + "','" + memo + "','" + image + "');");
        db.close(); // 생성 후 명령어 입력 한 뒤 db는 닫아주세요!
    }


    public void insertOnlyMemo(String title, String memo) {
        SQLiteDatabase db = getWritableDatabase();
        // getWritableDatabase() --> DB 생성 하는데 쓰기 모드로 생성
        db.execSQL("INSERT INTO memoTable VALUES(null, '" + title + "','" + memo + "','" + null + "');");
        db.close(); // 생성 후 명령어 입력 한 뒤 db는 닫아주세요!
    }

    public void upgrade(String title, String memo) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE memoTable SET memo=" + memo + "WHERE title='" + title + "';");
        db.close();
    }

    public void delete(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM memoTable WHERE title='" + title + "';");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getMemoOnly(String title) {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM memoTable WHERE title='" + title + "'", null);
        while (cursor.moveToNext()) {
            result += "제목: " + cursor.getString(1) + '\n'
                    + "내용:" + cursor.getString(2) + '\n';
        }
        return result;
    }

    public String getMemoAllThings(String title) {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM memoTable WHERE title='" + title + "'", null);
        while (cursor.moveToNext()) {
            result += "제목: " + cursor.getString(1) + '\n'
                    + "내용:" + cursor.getString(2) + '\n'
                    + "이미지 경로:" + cursor.getString(3) + '\n';
        }
        return result;
    }
}
