package com.ashtonchen.rssreader.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public abstract class BaseRecyclerViewAdapter<S, T extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<T> {
    protected View.OnClickListener mOnClickListener;
    protected View.OnLongClickListener mOnLongClickListener;

    protected List<S> mList;

    public BaseRecyclerViewAdapter(List<S> list) {
        mList = list;
    }

    @Override
    public void onBindViewHolder(T viewHolder, int position) {

    }

    public final void setOnClickListener(View.OnClickListener listener) {
        mOnClickListener = listener;
    }

    public final void setOnLongClickListener(View.OnLongClickListener listener) {
        mOnLongClickListener = listener;
    }
}
