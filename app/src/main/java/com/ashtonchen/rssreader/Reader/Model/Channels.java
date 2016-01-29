package com.ashtonchen.rssreader.reader.model;

import android.content.Context;

import com.ashtonchen.rssreader.subscription.dao.SubscriptionDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 16-01-12.
 */
public class Channels {

    /**
     * An array of feed items.
     */
    private static List<Channel> channels = new ArrayList();

    public static boolean find(Context context, String url) {
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO(context);
        return subscriptionDAO.findItemExist(url);
    }

    public static void add(Channel item) {
        channels.add(item);
    }

    public static void remove(Context context, Channel item) {
        //channels = subscriptionDAO.getAllItems();
    }

    public static List<Channel> getAll(Context context) {
        if (channels == null) {
            refresh(context);
        }
        return channels;
    }

    public static void refresh(Context context) {

        //channels = subscriptionDAO.getAllItems();
    }

    public static int size() {
        if (channels != null) {
            return channels.size();
        } else {
            return 0;
        }
    }
}
