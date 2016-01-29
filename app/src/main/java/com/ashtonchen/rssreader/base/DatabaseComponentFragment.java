package com.ashtonchen.rssreader.base;

import android.util.Log;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class DatabaseComponentFragment<T extends DatabaseComponent> extends ComponentFragment<T> {
/*
    @Override
    public void onPause() {
        if (mComponent != null) {
            mComponent.closeDAO();
        } else {
            Log.d(this.getClass().getName(), "component is null. Can't close DAO.");
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mComponent != null) {
            mComponent.openDAO();
        }else {
            Log.d(this.getClass().getName(), "component is null. Can't open DAO.");
        }
        super.onResume();
    }
    */
}
