package com.ashtonchen.rssreader.subscription;

import android.content.Context;
import android.widget.Toast;

import com.ashtonchen.rssreader.BaseComponent;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.model.Channel;
import com.ashtonchen.rssreader.reader.model.Channels;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionComponent extends BaseComponent {
    private boolean mNewSubscriptionAdded;
    private FeedNetworkHelper feedNetworkHelper;

    public SubscriptionComponent(Context context) {
        super(context);
        this.feedNetworkHelper = new FeedNetworkHelper(mContext);
    }

    public boolean subscriptionExists(String url) {
        return Channels.find(mContext, url);
    }

    public void addNewSubscription(Channel subscription) {
        Channels.add(mContext, subscription);
    }

    public void removeSubscription(Channel subscription) {
        Channels.remove(mContext, subscription);
        Toast.makeText(mContext, R.string.subscription_removed, Toast.LENGTH_SHORT).show();
    }

    public void loadSubscriptionInfo(String url, FeedNetworkCallbackInterface callback) {
        this.feedNetworkHelper.getSubscriptionInfo(url, callback);
    }

    public List<Channel> getSubscriptions() {
        return Channels.getAll(mContext);
    }

    public boolean getNewSubscriptionAdded() {
        return mNewSubscriptionAdded;
    }

    public void setNewSubscriptionAdded(boolean value) {
        mNewSubscriptionAdded = value;
    }
}
