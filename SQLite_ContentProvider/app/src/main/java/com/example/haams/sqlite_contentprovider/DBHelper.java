package com.example.haams.sqlite_contentprovider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.example.haams.sqlite_contentprovider.NoteContractor.NoteColumns.DATE;
import static com.example.haams.sqlite_contentprovider.NoteContractor.NoteColumns.NAME;
import static com.example.haams.sqlite_contentprovider.NoteContractor.NoteColumns.WORDS;
import static com.example.haams.sqlite_contentprovider.NoteContractor.TABLE_NAME;


/**
 * Created by haams on 2017-07-18.
 */

public class DBHelper extends SQLiteOpenHelper {
    public final String TAG = "DBHelper";
    public static final String dbname = "noteDB";
    public static final int DB_Version = 1;

    public static final String CREATE_TABLE =
            String.format("CREATE TABLE %s (\n", TABLE_NAME) +
                    String.format("%s INTEGER PRIMARY KEY AUTOINCREMENT, \n", _ID) +
                    String.format("%s TEXT \n", NAME) +
                    String.format("%s TEXT \n", WORDS) +
                    String.format("%s TEXT);", DATE);

    private static final String[] SQL_INSERT_INITIAL_DATA = {
            String.format("INSERT INTO %s (%s, %s, %s)" + " VALUES('Date1','마음을 정리해야 한다','20170717')",
                    TABLE_NAME, NAME, WORDS, DATE),
            String.format("INSERT INTO %s (%s, %s, %s)" + " VALUES('Date2','오해를 풀려 한다','20170720')",
                    TABLE_NAME, NAME, WORDS, DATE)
    };

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreateDB");
        db.beginTransaction();
        try {
            execSQL(db, CREATE_TABLE);
            for (String sql : SQL_INSERT_INITIAL_DATA) {
                execSQL(db, sql);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private void execSQL(SQLiteDatabase db, String sql) {
        Log.d(TAG, "execSQL sql=" + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade oldVersion=" + oldVersion + ", newVersion=" + newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG,"ONOPNE");
    }
}
