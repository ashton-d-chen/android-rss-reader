package com.ashtonchen.rssreader.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ashton Chen on 16-01-26.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, RSSReaderContract.DATABASE_NAME, null, RSSReaderContract.DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RSSReaderContract.SubscriptionEntry.SQL_CREATE_ENTRIES);
        db.execSQL(RSSReaderContract.FavoriteEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(RSSReaderContract.SubscriptionEntry.SQL_DELETE_ENTRIES);
        db.execSQL(RSSReaderContract.FavoriteEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
