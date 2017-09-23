package com.ashtonchen.rssreader.reader.helper;

import android.util.Log;

import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.service.DownloadXmlTask;
import com.ashtonchen.rssreader.subscription.model.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 15-12-09.
 */
public class FeedNetworkHelper {
    private int mDownloadChannelCount;
    private ArrayList<Feed> mFeeds;

    private NetworkHelperCallback mNetworkHelperCallback;

    public FeedNetworkHelper() {

    }

    public void getFeedList(List<Channel> channels, NetworkHelperCallback callback) {
        mNetworkHelperCallback = callback;
        mFeeds = new ArrayList<>();
        mDownloadChannelCount = 0;
        for (Channel channel : channels) {
            new DownloadXmlTask(getDownloadFeedXmlTaskCallback(channels.size())).execute(channel.getUrl());
        }
    }

    public void getSubscriptionInfo(String url, NetworkHelperCallback callback) {
        mNetworkHelperCallback = callback;
        new DownloadXmlTask(getDownloadChannelXmlTaskCallback()).execute(url);
    }

    protected DownloadXmlTaskCallback getDownloadFeedXmlTaskCallback(final int size) {
        return new DownloadXmlTaskCallback() {
            @Override
            public void onDownloadFinished(Channel channel) {
                mDownloadChannelCount++;

                if (channel != null) {
                    mFeeds.addAll(channel.getFeeds());
                }

                Log.d(this.getClass().getSimpleName(), "Downloaded Channel Count: " + mDownloadChannelCount);
                if (mDownloadChannelCount >= size) {
                    Feeds.reset();
                    Feeds.addAll(new ArrayList<Feed>(mFeeds));
                    if (mNetworkHelperCallback != null) {
                        mNetworkHelperCallback.onNetworkHelperFinished(channel);
                    }
                }
            }
        };
    }

    protected DownloadXmlTaskCallback getDownloadChannelXmlTaskCallback() {
        return new DownloadXmlTaskCallback() {
            @Override
            public void onDownloadFinished(Channel channel) {
                if (mNetworkHelperCallback != null) {
                    mNetworkHelperCallback.onNetworkHelperFinished(channel);
                }
            }
        };
    }

    public interface NetworkHelperCallback {
        void onNetworkHelperFinished(Channel channel);
    }

    public interface DownloadXmlTaskCallback {
        void onDownloadFinished(Channel channel);
    }
}
