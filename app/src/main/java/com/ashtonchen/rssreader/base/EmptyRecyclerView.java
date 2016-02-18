package com.ashtonchen.rssreader.base;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Ashton Chen on 16-02-01.
 */
public class EmptyRecyclerView extends AdapterObserverRecyclerView {
    protected EmptyViewListener mEmptyViewListener;
    protected ClickFirstItemListener mClickFirstItemListener;

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onAdapterChange() {
        if (getAdapter() != null) {
            final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            if (mEmptyViewListener != null) {
                mEmptyViewListener.setEmptyView(emptyViewVisible);
            }

            if (mClickFirstItemListener != null) {
                mClickFirstItemListener.clickFirstItem();
            }
        }
    }

    final public void setEmptyViewListener(EmptyViewListener listener) {
        mEmptyViewListener = listener;
    }

    final public void setClickFirstItemListener(ClickFirstItemListener listener) {
        mClickFirstItemListener = listener;
    }

    public interface EmptyViewListener {
        void setEmptyView(boolean value);
    }

    public interface ClickFirstItemListener {
        void clickFirstItem();
    }
}