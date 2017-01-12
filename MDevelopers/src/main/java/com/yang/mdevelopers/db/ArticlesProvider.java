package com.yang.mdevelopers.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.yang.mdevelopers.Utils.Articles;

import java.net.IDN;
import java.util.HashMap;

/**
 * Created by linusyang on 17-1-12.
 */

public class ArticlesProvider extends ContentProvider {

    private static final String DB_NAME = "Articles.db";
    private static final String DB_TABLE = "ArticlesTable";
    private static final int DB_VERSION = 1;

    private DBHelper dbHelper = null;
    private ContentResolver resolver = null;

    private static final String DB_CREATE = "create table " + DB_TABLE +  "( " + Articles.ID  + " integer primary key autoincrement, "
               + Articles.TITLE + " text not null, "
               + Articles.ABSTRACT + " text not null "
               + Articles.URL + " text not null) ";

    private static final UriMatcher mURI_MATCHER;

    static {
        mURI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        mURI_MATCHER.addURI(Articles.AUTHORITY, "item", Articles.ITEM);
        mURI_MATCHER.addURI(Articles.AUTHORITY, "item/#", Articles.ITEM_ID);
        mURI_MATCHER.addURI(Articles.AUTHORITY, "pos/#", Articles.ITEM_POS);
    }

    private static final HashMap<String, String> articleProjectionMap;

    static {
        articleProjectionMap = new HashMap<>();
        articleProjectionMap.put(Articles.ID, Articles.ID);
        articleProjectionMap.put(Articles.TITLE, Articles.TITLE);
        articleProjectionMap.put(Articles.ABSTRACT, Articles.ABSTRACT);
        articleProjectionMap.put(Articles.URL, Articles.URL);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        resolver = context.getContentResolver();
        dbHelper = new DBHelper(context, DB_NAME, null , DB_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (mURI_MATCHER.match(uri)) {
            case Articles.ITEM:
                return Articles.CONTENT_TYPE;
            case Articles.ITEM_ID:
            case Articles.ITEM_POS:
                return Articles.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Error Uri: + uri");
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }
}
