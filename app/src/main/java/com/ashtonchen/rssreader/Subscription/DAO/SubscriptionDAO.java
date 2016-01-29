package com.ashtonchen.rssreader.subscription.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ashtonchen.rssreader.database.BaseDAO;
import com.ashtonchen.rssreader.database.RSSReaderContract;
import com.ashtonchen.rssreader.reader.model.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 15-12-15.
 */
public class SubscriptionDAO extends BaseDAO<Channel> {

    public SubscriptionDAO(Context context) {
        super(context);
    }

    public boolean findItemExist(String url) {
        String[] projection = {
                RSSReaderContract.SubscriptionEntry._ID,
                RSSReaderContract.SubscriptionEntry.COLUMN_NAME_URL,
                RSSReaderContract.SubscriptionEntry.COLUMN_NAME_TITLE,
                RSSReaderContract.SubscriptionEntry.COLUMN_NAME_DESCRIPTION,
                RSSReaderContract.SubscriptionEntry.COLUMN_NAME_THUMBNAIL_URL
        };

        String selection = RSSReaderContract.SubscriptionEntry.COLUMN_NAME_URL + " = ?";
        String[] selection_args = {String.valueOf(url)};
        boolean itemExist = false;
        Cursor cursor = database.query(
                RSSReaderContract.SubscriptionEntry.TABLE_NAME,
                projection,
                selection,
                selection_args,
                null,
                null,
                null);
        if (cursor != null) {
            Log.d(this.getClass().getName(), "DB Helper, cursor size: " + cursor.getCount());

            itemExist = cursor.getCount() > 0;
            cursor.close();
        }
        return itemExist;
    }

    public List<Channel> getAllItems() {
        List<Channel> items = new ArrayList<Channel>();

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
            Log.d(this.getClass().getName(), "DB Helper, cursor size: " + cursor.getCount());
            for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext()) {
                Channel item = new Channel();
                item.setUrl(cursor.getString(1));
                Log.d(this.getClass().getName(), "Select URl = " + cursor.getString(1));
                item.setTitle(cursor.getString(2));
                item.setDescription(cursor.getString(3));
                item.setThumbnailURL(cursor.getString(4));
                items.add(item);
            }
            cursor.close();
        }
        return items;
    }

    public long addItem(Channel subscription) {

        ContentValues values = new ContentValues();
        values.put(RSSReaderContract.SubscriptionEntry.COLUMN_NAME_TITLE, subscription.getTitle());
        values.put(RSSReaderContract.SubscriptionEntry.COLUMN_NAME_DESCRIPTION, subscription.getDescription());
        values.put(RSSReaderContract.SubscriptionEntry.COLUMN_NAME_URL, subscription.getUrl());
        values.put(RSSReaderContract.SubscriptionEntry.COLUMN_NAME_THUMBNAIL_URL, subscription.getThumbnailURL());

        return database.insert(RSSReaderContract.SubscriptionEntry.TABLE_NAME, null, values);
    }

    public int removeItem(Channel subscription) {
        String selection = RSSReaderContract.SubscriptionEntry.COLUMN_NAME_URL + " = ?";
        String[] selectionArgs = {String.valueOf(subscription.getUrl())};
        return database.delete(RSSReaderContract.SubscriptionEntry.TABLE_NAME, selection, selectionArgs);
    }
}
