package com.ashtonchen.rssreader.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    protected float mScale;

    public BaseRecyclerViewHolder(View view) {
        super(view);
        mScale = view.getContext().getResources().getDisplayMetrics().density;
    }

    @Override
    public String toString() {
        return ""; //super.toString() + " '" + mDescription.getText() + "'";
    }
}