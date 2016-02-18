package com.ashtonchen.rssreader.base;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class DatabaseComponentFragment<T extends DatabaseComponent, U> extends ComponentFragment<T> {
    protected List<U> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mList = null;
    }

    protected void startGetItemListsTask() {
        new AsyncTask<Void, String, List<U>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected List<U> doInBackground(Void... params) {
                if (mComponent != null) {
                    mComponent.loadData();
                    return mComponent.getData();
                } else {
                    return new ArrayList<U>();
                }
            }

            @Override
            protected void onCancelled() {

            }

            @Override
            protected void onPostExecute(List<U> result) {
                if (result.size() > 0) {
                    mList = result;
                    startOnPostExecute();
                }
            }
        }.execute();
    }

    protected abstract void startOnPostExecute();
}
