package com.ashtonchen.rssreader.Reader.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Callback.FeedListCallback;
import com.ashtonchen.rssreader.Reader.Model.Channel;
import com.ashtonchen.rssreader.Reader.Model.Feed;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class FeedViewAdapter
        extends RecyclerView.Adapter<FeedViewAdapter.ViewHolder> {

    //private final List<FeedContent.FeedItem> mValues;
    private Channel mData;
    private FeedListCallback mCallback;

    public FeedViewAdapter(Channel data, FeedListCallback callback) {
        mData = data;
        mCallback = callback;
    }

    public void setData(Channel data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("FeedViewAdapter", "position = " + position);
        final int index = position;
        holder.mData = mData.getFeeds().get(position);
        holder.mIdView.setText(mData.getFeeds().get(position).getTitle());
        //holder.mContentView.setText(mData.getFeeds().get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callback.onFeedItemClick(v, String.valueOf(index));
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("FeedViewAdapter", "data size = " + mData.getFeeds().size());
        return mData.getFeeds().size();
        //return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Feed mData;

        public  ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}