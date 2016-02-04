package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.model.Feed;

/**
 * Created by Ashton Chen on 16-01-30.
 */
public abstract class DetailFragment<T> extends BaseFragment {
    protected static final String ARG_TWO_PANE = "argumentTwoPane";
    protected T mData;
    protected boolean mTwoPane;

    public static Fragment newInstance(Fragment fragment, boolean twoPane) {
        Bundle arguments = new Bundle();
        arguments.putBoolean(Feed.ARG_FEED, twoPane);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTwoPane = getArguments().getBoolean(ARG_TWO_PANE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(!mTwoPane);
        return inflater.inflate(R.layout.feed_detail, container, false);
    }
}
