package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ashtonchen.rssreader.R;

/**
 * Created by Ashton Chen on 16-02-03.
 */
public abstract class ActionBarActivity extends BaseActivity {
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolBar = (Toolbar) findViewById(getToolBarView());
        setSupportActionBar(getToolBar());
    }

    protected int getToolBarView() {
        return R.id.toolbar;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }
}
