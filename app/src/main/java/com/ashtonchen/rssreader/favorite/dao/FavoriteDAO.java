package com.ashtonchen.rssreader.favorite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ashtonchen.rssreader.database.BaseDAO;
import com.ashtonchen.rssreader.database.RSSReaderContract;
import com.ashtonchen.rssreader.reader.model.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 16-01-26.
 */
public class FavoriteDAO extends BaseDAO<Feed> {

    public FavoriteDAO(Context context) {
        super(context);
    }

    @Override
    public boolean findItem(String url) {
        String[] projection = {
                RSSReaderContract.FavoriteEntry._ID,
                RSSReaderContract.FavoriteEntry.COLUMN_NAME_URL,
                RSSReaderContract.FavoriteEntry.COLUMN_NAME_TITLE,
                RSSReaderContract.FavoriteEntry.COLUMN_NAME_DESCRIPTION,
                RSSReaderContract.FavoriteEntry.COLUMN_NAME_THUMBNAIL_URL
        };

        String selection = RSSReaderContract.FavoriteEntry.COLUMN_NAME_URL + " = ?";
        String[] selection_args = {String.valueOf(url)};
        boolean itemExist = false;
        Cursor cursor = database.query(
                RSSReaderContract.FavoriteEntry.TABLE_NAME,
                projection,
                selection,
                selection_args,
                null,
                null,
                null);
        if (cursor != null) {
            Log.d(this.getClass().getSimpleName(), "DB Helper, cursor size: " + cursor.getCount());

            itemExist = cursor.getCount() > 0;
            cursor.close();
        }
        return itemExist;
    }

    @Override
    public List<Feed> getAllItems() {
        List<Feed> items = new ArrayList<Feed>();

        String[] projection = {
                RSSReaderContract.FavoriteEntry._ID,
                RSSReaderContract.FavoriteEntry.COLUMN_NAME_URL,
                RSSReaderContract.FavoriteEntry.COLUMN_NAME_TITLE,
                RSSReaderContract.FavoriteEntry.COLUMN_NAME_DESCRIPTION,
                RSSReaderContract.FavoriteEntry.COLUMN_NAME_THUMBNAIL_URL
        };

        Cursor cursor = database.query(
                RSSReaderContract.FavoriteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            Log.d(this.getClass().getSimpleName(), "DB Helper, cursor size: " + cursor.getCount());
            for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext()) {
                Feed item = new Feed();
                item.setUrl(cursor.getString(1));
                Log.d(this.getClass().getSimpleName(), "Select URl = " + cursor.getString(1));
                item.setTitle(cursor.getString(2));
                item.setDescription(cursor.getString(3));
                item.setThumbnailURL(cursor.getString(4));
                items.add(item);
            }
            cursor.close();
        }
        return items;
    }

    @Override
    public long addItem(Feed favorite) {
        open();
        ContentValues values = new ContentValues();
        values.put(RSSReaderContract.FavoriteEntry.COLUMN_NAME_TITLE, favorite.getTitle());
        values.put(RSSReaderContract.FavoriteEntry.COLUMN_NAME_DESCRIPTION, favorite.getDescription());
        values.put(RSSReaderContract.FavoriteEntry.COLUMN_NAME_URL, favorite.getUrl());
        values.put(RSSReaderContract.FavoriteEntry.COLUMN_NAME_THUMBNAIL_URL, favorite.getThumbnailURL());

        long newRowId = database.insert(RSSReaderContract.FavoriteEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    @Override
    public int removeItem(Feed favorite) {
        String selection = RSSReaderContract.FavoriteEntry.COLUMN_NAME_URL + " = ?";
        String[] selectionArgs = {String.valueOf(favorite.getUrl())};
        return database.delete(RSSReaderContract.FavoriteEntry.TABLE_NAME, selection, selectionArgs);
    }
}
