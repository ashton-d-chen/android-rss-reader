package com.ashtonchen.rssreader.Reader;

import android.content.Context;

import com.ashtonchen.rssreader.BaseComponent;
import com.ashtonchen.rssreader.Reader.Helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.Reader.Interface.FeedNetworkCallbackInterface;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class ReaderComponent extends BaseComponent {

    private FeedNetworkHelper feedNetworkHelper;

    public ReaderComponent(Context context) {
        super(context);
        this.feedNetworkHelper = new FeedNetworkHelper(mContext);
    }

    public void getFeedList(FeedNetworkCallbackInterface callback) {
        this.feedNetworkHelper.getFeedList(callback);
    }
}
