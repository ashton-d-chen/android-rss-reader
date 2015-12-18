package com.ashtonchen.rssreader;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public abstract class BaseRecyclerViewAdapter<T extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<T> {

    public BaseRecyclerViewAdapter() {

    }

    @Override
    public void onBindViewHolder(T viewHolder, int position) {

    }

    @Override
    public int getItemCount() {

        return 0;
    }
}
