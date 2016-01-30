package com.ashtonchen.rssreader.reader.listener;

import android.graphics.Bitmap;

/**
 * Created by Ashton Chen on 15-12-10.
 */
public interface ImageDownloadCallback {
    void onDownloadFinished(Bitmap bitmap);
}
