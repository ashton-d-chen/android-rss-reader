package com.ashtonchen.rssreader.base;

import android.content.Context;

import com.ashtonchen.rssreader.database.BaseDAO;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class DatabaseComponent<T extends BaseDAO<S>, S> extends BaseComponent {
    protected T mDAO;

    public DatabaseComponent(Context context) {
        super(context);
        mDAO = getDAO();
    }

    public void openDAO() {
        mDAO.open();
    }

    public void closeDAO() {
        mDAO.close();
    }

    protected abstract T getDAO();

    public List<S> getData() {
        return mDAO.getAllItems();
    }

    public long addData(S data) {
        return mDAO.addItem(data);
    }

    public int removeData(S data) {
        return mDAO.removeItem(data);
    }
}
