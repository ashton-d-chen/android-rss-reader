package com.ashtonchen.rssreader.Subscription;

import android.content.Context;
import android.widget.Toast;

import com.ashtonchen.rssreader.BaseComponent;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.Reader.Interface.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.Subscription.DAO.SubscriptionDbHelper;
import com.ashtonchen.rssreader.Subscription.Model.Subscription;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionComponent extends BaseComponent {
    private SubscriptionDbHelper mHelper;
    private boolean mNewSubscriptionAdded;
    private FeedNetworkHelper feedNetworkHelper;

    public SubscriptionComponent(Context context) {
        super(context);
        mHelper = new SubscriptionDbHelper(mContext);
        this.feedNetworkHelper = new FeedNetworkHelper(mContext);
    }

    public void addNewSubscription(Subscription subscription) {
        mHelper.addItem(subscription);
        //Subscriptions.add(subscription);
    }

    public void removeSubscriptoin(Subscription subscription) {
        mHelper.removeItem(subscription);
        Toast.makeText(mContext, R.string.subscription_removed, Toast.LENGTH_SHORT).show();
    }

    public void getSubscriptionInfo(String url, FeedNetworkCallbackInterface callback) {
        this.feedNetworkHelper.getSubscriptionInfo(url, callback);
    }

    public List<Subscription> getSubscriptions() {
        return mHelper.getAllItems();
    }

    public boolean getNewSubscriptionAdded() {
        return mNewSubscriptionAdded;
    }

    public void setNewSubscriptionAdded(boolean value) {
        mNewSubscriptionAdded = value;
    }
}
