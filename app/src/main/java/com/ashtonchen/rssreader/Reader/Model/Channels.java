package com.ashtonchen.rssreader.reader.model;

import android.content.Context;

import com.ashtonchen.rssreader.subscription.dao.SubscriptionDbHelper;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-12.
 */
public class Channels {

    /**
     * An array of feed items.
     */
    private static List<Channel> channels;

    public static boolean find(Context context, String url) {
        SubscriptionDbHelper subscriptionDbHelper = new SubscriptionDbHelper(context);
        return subscriptionDbHelper.findItemExist(url);
    }

    public static void add(Context context, Channel item) {
        SubscriptionDbHelper subscriptionDbHelper = new SubscriptionDbHelper(context);
        subscriptionDbHelper.addItem(item);
        channels = subscriptionDbHelper.getAllItems();
    }

    public static void remove(Context context, Channel item) {
        SubscriptionDbHelper subscriptionDbHelper = new SubscriptionDbHelper(context);
        subscriptionDbHelper.removeItem(item);
        channels = subscriptionDbHelper.getAllItems();
    }

    public static List<Channel> getAll(Context context) {
        if (channels == null) {
            refresh(context);
        }
        return channels;
    }

    public static void refresh(Context context) {
        SubscriptionDbHelper subscriptionDbHelper = new SubscriptionDbHelper(context);
        channels = subscriptionDbHelper.getAllItems();
    }

    public static int size() {
        if (channels != null) {
            return channels.size();
        } else {
            return 0;
        }
    }
}
