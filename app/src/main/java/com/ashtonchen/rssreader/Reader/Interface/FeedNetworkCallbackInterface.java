package com.ashtonchen.rssreader.Reader.Interface;

import com.ashtonchen.rssreader.Reader.Model.Channel;

/**
 * Created by ashtonchen on 15-12-09.
 */
public interface FeedNetworkCallbackInterface {
    void onDownloadFinished(Channel channel);
}