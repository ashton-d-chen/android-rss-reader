package com.ashtonchen.rssreader.reader.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.BaseRecyclerViewAdapter;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.listener.OnListFragmentInteractionListener;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.view.widget.FeedViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class FeedViewAdapter
        extends BaseRecyclerViewAdapter<FeedViewHolder> {

    private OnListFragmentInteractionListener mCallback;
    private Context mContext;
    public FeedViewAdapter(Context context, OnListFragmentInteractionListener callback) {
        mCallback = callback;
        mContext = context;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_list_cell, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder viewHolder, int position) {
        Log.d("FeedViewAdapter", "position = " + position);
        FeedViewHolder holder = viewHolder;
        holder.mData = Feeds.get(position);

        Context context = holder.mThumbnail.getContext();
        Log.d(this.getClass().getName(), "Thumbnail URL = " + holder.mData.getThumbnailURL());
        Log.d(this.getClass().getName(), "Web Thumbnail URL = " + holder.mData.getWebThumbnailURL());

        if (!holder.mData.getThumbnailURL().isEmpty()) {
            Picasso.with(context).load(holder.mData.getThumbnailURL()).into(holder.mThumbnail);
        } else if (!holder.mData.getWebThumbnailURL().isEmpty()) {
            Picasso.with(context).load(holder.mData.getWebThumbnailURL()).into(holder.mThumbnail);
        }

        holder.mTitle.setText(Feeds.get(position).getTitle());

        Log.d("FeedViewAdapter", "description = " + Feeds.get(position).getDescription());
        holder.mDescription.setText(Feeds.get(position).getDescription());

        holder.mView.setOnClickListener(getOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        Log.d(this.getClass().getName(), "data size = " + Feeds.size());
        return Feeds.size();
    }

    private View.OnClickListener getOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("URL clicked", "clicked");

                mCallback.onItemClick(position);
            }
        };
    }
}