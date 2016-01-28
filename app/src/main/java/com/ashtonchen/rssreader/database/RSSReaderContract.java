package com.ashtonchen.rssreader.database;

import android.provider.BaseColumns;

/**
 * Created by Ashton Chen on 15-12-15.
 */
public final class RSSReaderContract {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "RSSReader.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public RSSReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class SubscriptionEntry implements BaseColumns {
        public static final String TABLE_NAME = "subscriptions";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_THUMBNAIL_URL = "thumbnail_url";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + SubscriptionEntry.TABLE_NAME + " (" +
                        SubscriptionEntry._ID + " INTEGER PRIMARY KEY," +
                        SubscriptionEntry.COLUMN_NAME_URL + TEXT_TYPE + COMMA_SEP +
                        SubscriptionEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        SubscriptionEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        SubscriptionEntry.COLUMN_NAME_THUMBNAIL_URL + TEXT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SubscriptionEntry.TABLE_NAME;
    }

    public static abstract class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_THUMBNAIL_URL = "thumbnail_url";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                        FavoriteEntry._ID + " INTEGER PRIMARY KEY," +
                        FavoriteEntry.COLUMN_NAME_URL + TEXT_TYPE + COMMA_SEP +
                        FavoriteEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        FavoriteEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        FavoriteEntry.COLUMN_NAME_THUMBNAIL_URL + TEXT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME;
    }
}
