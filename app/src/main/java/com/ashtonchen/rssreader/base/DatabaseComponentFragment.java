package com.ashtonchen.rssreader.base;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class DatabaseComponentFragment<T extends DatabaseComponent> extends ComponentFragment<T> {

    @Override
    public void onPause() {
        if (mComponent != null) {
            mComponent.closeDAO();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mComponent != null) {
            mComponent.openDAO();
        }
        super.onResume();
    }
}
