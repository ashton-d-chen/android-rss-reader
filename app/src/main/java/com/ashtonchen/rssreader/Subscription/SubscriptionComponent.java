package com.ashtonchen.rssreader.subscription;

import android.content.Context;
import android.widget.Toast;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.DatabaseComponent;
import com.ashtonchen.rssreader.reader.helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.model.Channel;
import com.ashtonchen.rssreader.reader.model.Channels;
import com.ashtonchen.rssreader.subscription.dao.SubscriptionDAO;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionComponent extends DatabaseComponent<SubscriptionDAO, Channel> {
    private boolean mNewSubscriptionAdded;
    private FeedNetworkHelper feedNetworkHelper;

    public SubscriptionComponent(Context context) {
        super(context);
        this.feedNetworkHelper = new FeedNetworkHelper(mContext);
    }

    @Override
    protected SubscriptionDAO getDAO() {
        return new SubscriptionDAO(mContext);
    }

    public boolean subscriptionExists(String url) {
        return Channels.find(mContext, url);
    }

    public void loadSubscriptionInfo(String url, FeedNetworkCallbackInterface callback) {
        this.feedNetworkHelper.getSubscriptionInfo(url, callback);
    }

    public boolean getNewSubscriptionAdded() {
        return mNewSubscriptionAdded;
    }

    public void setNewSubscriptionAdded(boolean value) {
        mNewSubscriptionAdded = value;
    }
}
