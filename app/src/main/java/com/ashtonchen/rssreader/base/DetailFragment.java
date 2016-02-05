package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

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
    public void onDestroy() {
        super.onDestroy();
        mData = null;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext.getActionBarDrawerToggle().setToolbarNavigationClickListener(getToolbarNavigationClickListener());
    }

    private View.OnClickListener getToolbarNavigationClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onBackPressed();
            }
        };
    }

    protected boolean shouldDisplayDrawerIcon() {
        return mTwoPane;
    }
}
