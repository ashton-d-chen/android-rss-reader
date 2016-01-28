package com.ashtonchen.rssreader.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.view.widget.FeedViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ashtonchen on 15-12-09.
 */
public abstract class RSSRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<Feed, FeedViewHolder> {

    public RSSRecyclerViewAdapter(List<Feed> list) {
        super(list);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_list_cell, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder viewHolder, int position) {
        Log.d(this.getClass().getName(), "position = " + position);
        FeedViewHolder holder = viewHolder;
        holder.mData = mList.get(position);

        holder.itemView.setOnClickListener(mOnClickListener);
        holder.itemView.setOnLongClickListener(mOnLongClickListener);

        getThumbnail(holder);

        holder.mTitle.setText(mList.get(position).getTitle());

        Log.d(this.getClass().getName(), "description = " + mList.get(position).getDescription());
        holder.mDescription.setText(mList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        Log.d(this.getClass().getName(), "data size = " + mList.size());
        return mList.size();
    }

    //protected abstract List<Feed> getData();

    protected void getThumbnail(FeedViewHolder holder) {
        Context context = holder.mThumbnail.getContext();
        Log.d(this.getClass().getName(), "Thumbnail URL = " + holder.mData.getThumbnailURL());
        Log.d(this.getClass().getName(), "Web Thumbnail URL = " + holder.mData.getWebThumbnailURL());

        if (!holder.mData.getThumbnailURL().isEmpty()) {
            Picasso.with(context).load(holder.mData.getThumbnailURL()).into(holder.mThumbnail);
        } else if (!holder.mData.getWebThumbnailURL().isEmpty()) {
            Picasso.with(context).load(holder.mData.getWebThumbnailURL()).into(holder.mThumbnail);
        }
    }
}