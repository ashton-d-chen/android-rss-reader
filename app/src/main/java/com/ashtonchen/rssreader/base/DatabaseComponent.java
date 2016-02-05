package com.ashtonchen.rssreader.base;

import android.content.Context;
import android.util.Log;

import com.ashtonchen.rssreader.database.BaseDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class DatabaseComponent<T extends BaseDAO<S>, S> extends BaseComponent {
    protected T mDAO;
    protected List<S> mList;

    public DatabaseComponent(Context context) {
        mDAO = getDAO(context);
        mList = new ArrayList<S>();
    }

    public void openDAO() {
        mDAO.open();
    }

    public void closeDAO() {
        mDAO.close();
    }

    public void loadData() {
        mList = mDAO.getAllItems();
    }

    public List<S> getData() {
        Log.d(this.getClass().getSimpleName(), "component get data size = " + mList.size());
        return new ArrayList<S>(mList);
    }

    public long addData(S data) {
        long result = mDAO.addItem(data);
        mList.add(data);
        return result;
    }

    public int removeData(int position) {
        S data = mList.get(position);
        mList.remove(position);
        return mDAO.removeItem(data);
    }

    public boolean findData(String id) {
        return mDAO.findItem(id);
    }

    protected abstract T getDAO(Context context);
}
