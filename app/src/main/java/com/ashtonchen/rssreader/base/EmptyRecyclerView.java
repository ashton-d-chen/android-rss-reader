package com.ashtonchen.rssreader.base;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Ashton Chen on 16-02-01.
 */
public class EmptyRecyclerView extends BaseRecyclerView {
    protected EmptyViewListener mListener;

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
            if (mListener != null) {
                mListener.setEmptyView(emptyViewVisible);
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

    final public void setEmptyViewListener(EmptyViewListener listener) {
        mListener = listener;
    }

    public interface EmptyViewListener {
        void setEmptyView(boolean value);
    }
}