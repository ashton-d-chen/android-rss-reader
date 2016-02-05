package com.ashtonchen.rssreader.reader;

import android.content.Context;

import com.ashtonchen.rssreader.base.DatabaseComponent;
import com.ashtonchen.rssreader.favorite.dao.FavoriteDAO;
import com.ashtonchen.rssreader.reader.helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.subscription.dao.SubscriptionDAO;
import com.ashtonchen.rssreader.subscription.model.Channel;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-09.
 */
public class ReaderComponent extends DatabaseComponent<SubscriptionDAO, Channel> {
    private FeedNetworkHelper mFeedNetworkHelper;
    private FavoriteDAO mFavoriteDAO;

    public ReaderComponent(Context context) {
        super(context);
        mFeedNetworkHelper = new FeedNetworkHelper();
        mFavoriteDAO = new FavoriteDAO(context);
    }

    @Override
    protected SubscriptionDAO getDAO(Context context) {
        return new SubscriptionDAO(context);
    }

    public void getFeedList(FeedNetworkCallbackInterface callback) {
        List<Channel> list = getData();
        mFeedNetworkHelper.getFeedList(list, callback);
    }

    public long addToFavorite(Feed data) {
        return mFavoriteDAO.addItem(data);
    }

    public boolean findFavorite(String id) {
        return mFavoriteDAO.findItem(id);
    }
}
