package com.ashtonchen.rssreader.reader.listener;

import com.ashtonchen.rssreader.reader.model.Channel;

/**
 * Created by ashtonchen on 15-12-09.
 */
public interface FeedNetworkCallbackInterface {
    void onDownloadFinished(Channel channel);
}
