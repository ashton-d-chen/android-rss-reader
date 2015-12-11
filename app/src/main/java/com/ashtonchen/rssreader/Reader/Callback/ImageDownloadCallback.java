package com.ashtonchen.rssreader.Reader.Callback;

import android.graphics.Bitmap;

import com.ashtonchen.rssreader.Reader.Model.Channel;

/**
 * Created by ashtonchen on 15-12-10.
 */
public interface ImageDownloadCallback {
    void onDownloadFinished(Bitmap bitmap);
}
