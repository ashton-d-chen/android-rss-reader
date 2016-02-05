package com.ashtonchen.rssreader.subscription;

import android.content.Context;

import com.ashtonchen.rssreader.base.DatabaseComponent;
import com.ashtonchen.rssreader.reader.helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.subscription.dao.SubscriptionDAO;
import com.ashtonchen.rssreader.subscription.model.Channel;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionComponent extends DatabaseComponent<SubscriptionDAO, Channel> {
    private FeedNetworkHelper feedNetworkHelper;

    public SubscriptionComponent(Context context) {
        super(context);
        this.feedNetworkHelper = new FeedNetworkHelper();
    }

    @Override
    protected SubscriptionDAO getDAO(Context context) {
        return new SubscriptionDAO(context);
    }

    public void loadSubscriptionInfo(String url, FeedNetworkCallbackInterface callback) {
        this.feedNetworkHelper.getSubscriptionInfo(url, callback);
    }
}
