package com.ashtonchen.rssreader.Subscription;

import android.content.Context;

import com.ashtonchen.rssreader.BaseComponent;
import com.ashtonchen.rssreader.Subscription.DAO.SubscriptionDbHelper;
import com.ashtonchen.rssreader.Subscription.Model.Subscription;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionComponent extends BaseComponent {
    private SubscriptionDbHelper mHelper;
    private boolean mNewSubscriptionAdded;

    public SubscriptionComponent(Context context) {
        super(context);
        mHelper = new SubscriptionDbHelper(context);
    }

    public void addNewSubscription(Subscription subscription) {
        mHelper.addItem(subscription);
        //Subscriptions.add(subscription);
    }

    public void removeSubscriptoin(Subscription subscription) {
        mHelper.removeItem(subscription);
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
