package com.example.haams.sqlite_contentprovider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by haams on 2017-07-18.
 */

public interface NoteContractor {
    public static final String AUTHORITY = "com.example.haams.sqlite_contentprovider";
    public static final String TABLE_NAME = "note";
    public static Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT +
            "://" + AUTHORITY + "/" + TABLE_NAME);

    public static final String MIME_TYPE_DIR = "vnd.android.cursor.dir/" + AUTHORITY + "."
            + TABLE_NAME;
    public static final String MIME_TYPE_ITEM = "vnd.android.cursor.item/" + AUTHORITY + "."
            + TABLE_NAME;


    public interface NoteColumns extends BaseColumns {
        public static final String NAME = "name";
        public static final String WORDS = "word";
        public static final String DATE = "date";
    }
}
