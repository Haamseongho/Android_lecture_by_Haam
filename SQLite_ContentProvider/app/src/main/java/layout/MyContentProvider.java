package layout;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;

import com.example.haams.sqlite_contentprovider.DBHelper;

import static android.provider.BaseColumns._ID;
import static com.example.haams.sqlite_contentprovider.DBHelper.DB_Version;
import static com.example.haams.sqlite_contentprovider.DBHelper.dbname;
import static com.example.haams.sqlite_contentprovider.NoteContractor.AUTHORITY;
import static com.example.haams.sqlite_contentprovider.NoteContractor.MIME_TYPE_DIR;
import static com.example.haams.sqlite_contentprovider.NoteContractor.MIME_TYPE_ITEM;
import static com.example.haams.sqlite_contentprovider.NoteContractor.TABLE_NAME;

public class MyContentProvider extends ContentProvider {
    private static final String TAG = "MyContentProvider";
    private static final UriMatcher sUriMatcher;
    private static final int ROW_DIR = 1;
    private static final int ROW_ITEM = 2;
    private static int sLastId = 0;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, ROW_DIR);
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", ROW_ITEM);
    }

    private DBHelper mDBHelper;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (sUriMatcher.match(uri)) {
            case ROW_ITEM:
                int id = (int) ContentUris.parseId(uri);
                synchronized (mDBHelper) {
                    Log.d(TAG, "delete(item) id=" + id);
                    SQLiteDatabase db = mDBHelper.getWritableDatabase(); // 추가하기
                    count = db.delete(TABLE_NAME, _ID + "=?", new String[]{Long.toString(id)});
                    if (count > 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                }
                break;
            case ROW_DIR:
                synchronized (mDBHelper) {
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    count = db.delete(TABLE_NAME, selection, selectionArgs);
                    if (count > 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                }
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        // Implement this to handle requests to delete one or more rows.
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ROW_DIR:
                return MIME_TYPE_DIR;
            case ROW_ITEM:
                return MIME_TYPE_ITEM;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Uri resultUri = null;
        switch (sUriMatcher.match(uri)) {
            case ROW_DIR:
                synchronized (mDBHelper) {
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    long lastId = db.insert(TABLE_NAME, null, values);
                    resultUri = ContentUris.withAppendedId(uri, lastId);
                    getContext().getContentResolver().notifyChange(resultUri, null);
                }
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return resultUri;
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext(), dbname, null, DB_Version, new DatabaseErrorHandler() {
            @Override
            public void onCorruption(SQLiteDatabase dbObj) {
                Log.d(TAG, "충돌 !!");
                String path = dbObj.getPath();
                getContext().deleteFile(path);
            }
        });
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {
            case ROW_DIR:
                synchronized (mDBHelper) {
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                }
                return cursor;
            case ROW_ITEM:
                synchronized (mDBHelper) {
                    long id = ContentUris.parseId(uri);
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    cursor = db.query(TABLE_NAME, projection, _ID
                            , new String[]{Long.toString(id)}, null, null, null);

                }
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (sUriMatcher.match(uri)) {
            case ROW_ITEM:
                int id = (int) ContentUris.parseId(uri);
                synchronized (mDBHelper) {
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    count = db.update(TABLE_NAME, values, _ID + "=?", new String[]{Long.toString(id)});
                    if (count > 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                }
                break;

            case ROW_DIR:
                synchronized (mDBHelper) {
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    count = db.update(TABLE_NAME, values, selection, selectionArgs);
                    if (count > 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                }
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return count;
    }
}
