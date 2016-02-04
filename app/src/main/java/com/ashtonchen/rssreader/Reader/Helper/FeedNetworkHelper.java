package com.ashtonchen.rssreader.reader.helper;

import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.subscription.model.Channel;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.service.DownloadXmlTask;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-09.
 */
public class FeedNetworkHelper {

    public FeedNetworkHelper() {

    }

    public void getFeedList(List<Channel> channels, FeedNetworkCallbackInterface callback) {
        Feeds.reset();
        for (Channel channel : channels) {
            new DownloadXmlTask(callback).execute(channel.getUrl());
        }
    }

    public void getSubscriptionInfo(String url, FeedNetworkCallbackInterface callback) {
        new DownloadXmlTask(callback).execute(url);
    }
}
