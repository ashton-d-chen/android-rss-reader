package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ashton Chen on 16-01-30.
 */
public abstract class DetailFragment<T> extends BaseFragment {
    private static final String ARG_TWO_PANE = "argumentTwoPane";
    protected T mData;
    protected boolean mTwoPane;

    public static Fragment newInstance(Fragment fragment, boolean twoPane) {
        Bundle arguments = new Bundle();
        arguments.putBoolean(ARG_TWO_PANE, twoPane);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTwoPane = getArguments().getBoolean(ARG_TWO_PANE);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mTwoPane) {
            Log.d(this.getClass().getName(), "Toggle enabled");
            mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mContext.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        } else {
            Log.d(this.getClass().getName(), "Toggle disabled");
            mContext.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mContext.getActionBarDrawerToggle().setToolbarNavigationClickListener(getToolbarNavigationClickListener());
        return inflater.inflate(getLayout(), container, false);
    }

    private View.OnClickListener getToolbarNavigationClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getName(), "Onclick listener triggered");
                mContext.onBackPressed();
            }
        };
    }

    protected abstract int getLayout();
}
