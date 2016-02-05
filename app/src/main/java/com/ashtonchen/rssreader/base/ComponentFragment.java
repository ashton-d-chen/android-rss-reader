package com.ashtonchen.rssreader.base;

import android.os.Bundle;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class ComponentFragment<T extends BaseComponent> extends BaseFragment {
    protected T mComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = getComponent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mComponent = null;
    }

    protected abstract T getComponent();
}
