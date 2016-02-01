package com.ashtonchen.rssreader.reader;

import android.content.Context;
import android.util.Log;

import com.ashtonchen.rssreader.base.DatabaseComponent;
import com.ashtonchen.rssreader.favorite.dao.FavoriteDAO;
import com.ashtonchen.rssreader.reader.helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.model.Channel;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.view.FeedListFragment;
import com.ashtonchen.rssreader.subscription.dao.SubscriptionDAO;
import com.ashtonchen.rssreader.utility.NetworkUtility;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-09.
 */
public class ReaderComponent extends DatabaseComponent<SubscriptionDAO, Channel> {
    private FeedListFragment mFragment;
    private FeedNetworkHelper mFeedNetworkHelper;
    private FavoriteDAO mFavoriteDAO;

    public ReaderComponent(Context context, FeedListFragment fragment) {
        super(context);
        mFeedNetworkHelper = new FeedNetworkHelper();
        mFragment = fragment;
        mFavoriteDAO = new FavoriteDAO(mContext);
    }

    @Override
    protected SubscriptionDAO getDAO() {
        return new SubscriptionDAO(mContext);
    }

    public void getFeedList(FeedNetworkCallbackInterface callback) {
        Log.d(this.getClass().getName(), "get feed list");
        if (NetworkUtility.isOnline(mContext)) {
            Log.d(this.getClass().getName(), "is online");
            List<Channel> list = getData();
            mFeedNetworkHelper.getFeedList(list, callback);
        } else if (Feeds.size() > 0) {
            Log.d(this.getClass().getName(), "is offline, get list from Feeds");
            mFragment.setupAdapter();
        }
    }

    public long addToFavorite(Feed data) {
        return mFavoriteDAO.addItem(data);
    }

    public boolean findFavorite(String id) {
        return mFavoriteDAO.findItem(id);
    }
}
