package com.ashtonchen.rssreader.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-26.
 */
public abstract class BaseDAO<T> {
    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context mContext;


    public BaseDAO(Context context) {
        this.mContext = context.getApplicationContext();
        dbHelper = DatabaseHelper.getInstance(mContext);
        open();
    }

    public void open() throws SQLException {
        if (dbHelper == null) {
            dbHelper = DatabaseHelper.getInstance(mContext);
        }
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database = null;
    }

    public abstract List<T> getAllItems();

    public abstract long addItem(T item);

    public abstract int removeItem(T item);

    public abstract boolean findItem(String id);
}
