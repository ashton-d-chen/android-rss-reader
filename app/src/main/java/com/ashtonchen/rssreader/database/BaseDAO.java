package com.ashtonchen.rssreader.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ashton Chen on 16-01-26.
 */
public class BaseDAO {
    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public BaseDAO(Context context) {
        this.mContext = context;
        dbHelper = DatabaseHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        if (dbHelper == null) {
            dbHelper = DatabaseHelper.getHelper(mContext);
        }
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database = null;
    }
}
