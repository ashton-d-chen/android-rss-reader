package com.ashtonchen.rssreader.base;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Ashton Chen on 16-02-17.
 */
public abstract class AdapterObserverRecyclerView extends BaseRecyclerView {

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            onAdapterChange();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            onAdapterChange();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            onAdapterChange();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onAdapterChange();
        }
    };

    public AdapterObserverRecyclerView(Context context) {
        super(context);
    }

    public AdapterObserverRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdapterObserverRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        onAdapterChange();
    }

    protected abstract void onAdapterChange();
}
