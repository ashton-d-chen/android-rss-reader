package com.ashtonchen.rssreader.base;

import android.content.Context;

import com.ashtonchen.rssreader.database.BaseDAO;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class DatabaseComponent<T extends BaseDAO<S>, S> extends BaseComponent {
    protected T mDAO;
    protected List<S> mList;

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
        if (mList == null) {
            mList = mDAO.getAllItems();
        }
        return mList;
    }

    public long addData(S data) {
        if (mList == null) {
            mList = mDAO.getAllItems();
        }
        mList.add(data);
        return mDAO.addItem(data);
    }

    public int removeData(int position) {
        if (mList == null) {
            mList = mDAO.getAllItems();
        }
        S data = mList.get(position);
        mList.remove(position);
        return mDAO.removeItem(data);
    }

    public boolean findData(String id) {
        return mDAO.findItem(id);
    }
}
