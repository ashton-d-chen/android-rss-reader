package com.ashtonchen.rssreader.base;

import android.content.Context;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public abstract class BaseComponent {
    protected Context mContext;

    public BaseComponent(Context context) {
        mContext = context;
    }
}
