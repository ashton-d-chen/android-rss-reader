package com.ashtonchen.rssreader.base;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    @Override
    public int getItemCount() {
        Log.d(this.getClass().getSimpleName(), "adapter list size = " + mList.size());
        return mList.size();
    }

    public final void setOnClickListener(View.OnClickListener listener) {
        mOnClickListener = listener;
    }

    public final void setOnLongClickListener(View.OnLongClickListener listener) {
        mOnLongClickListener = listener;
    }

    public final List<S> getList() {
        return mList;
    }

    public final void setList(List<S> list) {
        mList = list;
    }
}
