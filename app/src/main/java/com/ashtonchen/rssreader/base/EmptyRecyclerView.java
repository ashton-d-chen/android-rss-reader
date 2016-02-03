package com.ashtonchen.rssreader.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Ashton Chen on 16-02-01.
 */
public class EmptyRecyclerView extends BaseRecyclerView {
    protected View mContainerView;
    protected View mEmptyView;
    protected View mDetailView;

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            setEmptyView();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            setEmptyView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            setEmptyView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            setEmptyView();
        }
    };

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    void setEmptyView() {
        if (getAdapter() != null) {
            final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            if (mContainerView != null && mEmptyView != null) {
                mContainerView.setVisibility(emptyViewVisible ? View.GONE : View.VISIBLE);
                mEmptyView.setVisibility(emptyViewVisible ? View.VISIBLE : View.GONE);
            }

            if (mDetailView != null) {
                mDetailView.setVisibility(emptyViewVisible ? View.GONE : View.VISIBLE);
            }
        }
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
        setEmptyView();
    }

    final public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    final public void setContainerView(View view) {
        mContainerView = view;
    }

    final public void setDetailView(View view) {
        mDetailView = view;
    }
}