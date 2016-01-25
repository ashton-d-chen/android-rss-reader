package com.ashtonchen.rssreader.reader.view.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ashtonchen on 15-12-10.
 */
public class DecoratedItemRecyclerView extends RecyclerView.ItemDecoration {
    private final int mVerticalSpaceHeight;

    public DecoratedItemRecyclerView(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }
}
