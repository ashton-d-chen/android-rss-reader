package com.ashtonchen.rssreader.reader;

import android.content.Context;
import android.util.Log;

import com.ashtonchen.rssreader.base.BaseComponent;
import com.ashtonchen.rssreader.reader.helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.view.FeedListFragment;
import com.ashtonchen.rssreader.utility.NetworkUtility;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class ReaderComponent extends BaseComponent {

    private FeedListFragment mFragment;

    private FeedNetworkHelper mFeedNetworkHelper;

    public ReaderComponent(Context context, FeedListFragment fragment) {
        super(context);
        mFeedNetworkHelper = new FeedNetworkHelper(mContext);
        mFragment = fragment;
    }

    public void getFeedList(FeedNetworkCallbackInterface callback) {
        Log.d(this.getClass().getName(), "get feed list");
        if (NetworkUtility.isOnline(mContext)) {
            Log.d(this.getClass().getName(), "is online");
            mFeedNetworkHelper.getFeedList(callback);
        } else if (Feeds.size() > 0) {
            Log.d(this.getClass().getName(), "is offline, get list from Feeds");
            mFragment.setupAdapter();
        }
    }
}
