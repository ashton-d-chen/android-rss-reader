package com.ashtonchen.rssreader.Reader;

import android.content.Context;

import com.ashtonchen.rssreader.Reader.Callback.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.Reader.Helper.FeedNetworkHelper;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class ReaderComponent {
    private Context context;
    private FeedNetworkHelper feedNetworkHelper;

    public ReaderComponent(Context context) {
        this.context = context;
        this.feedNetworkHelper = new FeedNetworkHelper(this.context);
    }

    public void getFeedList(FeedNetworkCallbackInterface callback) {
        this.feedNetworkHelper.getFeedList(callback);
    }
}
