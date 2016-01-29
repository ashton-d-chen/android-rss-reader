package com.ashtonchen.rssreader.base;

import android.content.Context;

import com.ashtonchen.rssreader.database.BaseDAO;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class ListDatabaseComponent<T extends BaseDAO<S>, S> extends BaseComponent {
    protected T mDAO;

    public ListDatabaseComponent(Context context) {
        super(context);
        mDAO = getListDAO();
    }

    public void openListDAO() {
        mDAO.open();
    }

    public void closeListDAO() {
        mDAO.close();
    }

    protected abstract T getListDAO();

    public List<S> getListData() {
        return mDAO.getAllItems();
    }

    public void addListData(S data) {
        mDAO.addItem(data);
    }

    public void removeListData(S data) {
        mDAO.removeItem(data);
    }
}
