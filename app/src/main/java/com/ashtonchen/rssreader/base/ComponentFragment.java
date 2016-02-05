package com.ashtonchen.rssreader.base;

import android.content.Context;

/**
 * Created by Ashton Chen on 16-01-28.
 */
public abstract class ComponentFragment<T extends BaseComponent> extends BaseFragment {
    protected T mComponent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mComponent = getComponent();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mComponent = null;
    }

    protected abstract T getComponent();
}
