package com.ashtonchen.rssreader.Subscription.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ashtonchen.rssreader.RSSReaderContract;
import com.ashtonchen.rssreader.Subscription.Model.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 15-12-15.
 */
public class SubscriptionDbHelper extends SQLiteOpenHelper {

    public SubscriptionDbHelper(Context context) {
        super(context, RSSReaderContract.DATABASE_NAME, null, RSSReaderContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RSSReaderContract.SubscriptionEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(RSSReaderContract.SubscriptionEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<Subscription> getAllItems() {
        List<Subscription> items = new ArrayList<Subscription>();

        SQLiteDatabase database = getReadableDatabase();

        String[] projection = {
                RSSReaderContract.SubscriptionEntry._ID,
                RSSReaderContract.SubscriptionEntry.COLUMN_NAME_URL,
                RSSReaderContract.SubscriptionEntry.COLUMN_NAME_TITLE,
                RSSReaderContract.SubscriptionEntry.COLUMN_NAME_DESCRIPTION,
                RSSReaderContract.SubscriptionEntry.COLUMN_NAME_THUMBNAIL_URL
        };

        Cursor cursor = database.query(
                RSSReaderContract.SubscriptionEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext()) {
                Subscription item = new Subscription();
                item.setUrl(cursor.getString(1));
                item.setTitle(cursor.getString(2));
                item.setDescription(cursor.getString(3));
                item.setThumbnailURL(cursor.getString(4));
                items.add(item);
            }
            cursor.close();
        }

        database.close();
        return items;
    }

    public long addItem(Subscription subscription) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RSSReaderContract.SubscriptionEntry.COLUMN_NAME_TITLE, subscription.getTitle());
        values.put(RSSReaderContract.SubscriptionEntry.COLUMN_NAME_DESCRIPTION, subscription.getDescription());
        values.put(RSSReaderContract.SubscriptionEntry.COLUMN_NAME_URL, subscription.getUrl());
        values.put(RSSReaderContract.SubscriptionEntry.COLUMN_NAME_THUMBNAIL_URL, subscription.getThumbnailURL());


        long newRowId = database.insert(RSSReaderContract.SubscriptionEntry.TABLE_NAME, null, values);
        database.close();
        return newRowId;
    }

    public void removeItem(Subscription subscription) {
        SQLiteDatabase database = getWritableDatabase();
        String selection = RSSReaderContract.SubscriptionEntry.COLUMN_NAME_URL + " = ?";
        String[] selectionArgs = {String.valueOf(subscription.getUrl())};
        database.delete(RSSReaderContract.SubscriptionEntry.TABLE_NAME, selection, selectionArgs);
        database.close();
    }
}
