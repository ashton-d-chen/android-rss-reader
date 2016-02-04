package com.ashtonchen.rssreader.reader.listener;

import com.ashtonchen.rssreader.subscription.model.Channel;

/**
 * Created by Ashton Chen on 15-12-09.
 */
public interface FeedNetworkCallbackInterface {
    void onDownloadFinished(Channel channel);
}
