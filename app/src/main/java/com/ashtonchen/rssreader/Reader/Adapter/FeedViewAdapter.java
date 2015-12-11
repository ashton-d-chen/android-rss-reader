package com.ashtonchen.rssreader.Reader.Adapter;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Callback.FeedListCallback;
import com.ashtonchen.rssreader.Reader.Model.Channel;
import com.ashtonchen.rssreader.Reader.Model.Feed;
import com.ashtonchen.rssreader.Reader.StyleSheet;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class FeedViewAdapter
        extends RecyclerView.Adapter<FeedViewAdapter.ViewHolder> {

    private Channel mData;
    private FeedListCallback mCallback;
    private int cellPadding;
    private int cellThumbnailSize;
    private int cellThumbnailTextSpacing;
    private int cellTextViewSpacing;

    public FeedViewAdapter(Context context, Channel data, FeedListCallback callback) {
        mData = data;
        mCallback = callback;

        final float scale = context.getResources().getDisplayMetrics().density;
        cellPadding = (int) (StyleSheet.CELL_PADDING * scale + 0.5f);
        cellThumbnailSize = (int) (StyleSheet.CELL_THUMBNAIL_SIZE * scale + 0.5f);
        cellThumbnailTextSpacing = (int) (StyleSheet.CELL_Thumbnail_TEXT_SPACING * scale + 0.5f);
        cellTextViewSpacing = (int) (StyleSheet.CELL_TEXT_VIEW_SPACING * scale + 0.5f);
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

        holder.mData = mData.getFeeds().get(position);

        holder.mTitle.setText(mData.getFeeds().get(position).getTitle());

        Log.d("FeedViewAdapter", "description = " + mData.getFeeds().get(position).getDescription());
        holder.mDescription.setText(mData.getFeeds().get(position).getDescription());
        //holder.mDescription.setText("test");

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
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mThumbnail;
        public final TextView mTitle;
        public final TextView mDescription;
        public Feed mData;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mView.setPadding(cellPadding, cellPadding, cellPadding, cellPadding);
            //mView.setBackgroundColor(Color.GREEN);

            mThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            mThumbnail.setPadding(0, 0, cellThumbnailTextSpacing, 0);
            mThumbnail.getLayoutParams().width = cellThumbnailSize;
            mThumbnail.getLayoutParams().height = cellThumbnailSize;

            mTitle = (TextView) view.findViewById(R.id.title);
            mTitle.setMaxLines(StyleSheet.CELL_TITLE_MAX_LINE);
            mTitle.setPadding(0, 0, 0, cellTextViewSpacing);
            mTitle.setLineSpacing(0, StyleSheet.CELL_TITLE_LINE_HEIGHT_MULTIPLIER);
            mTitle.setTextSize(StyleSheet.CELL_TITLE_FONT_SIZE);
            //mTitle.setBackgroundColor(Color.RED);

            mDescription = (TextView) view.findViewById(R.id.description);
            mDescription.setMaxLines(StyleSheet.CELL_DESCRIPTION_MAX_LINE);
            mDescription.setLineSpacing(1, StyleSheet.CELL_DESCRIPTION_LINE_HEIGHT_MULTIPLIER);
            mDescription.setTextSize(StyleSheet.CELL_DESCRIPTION_FONT_SIZE);
            //mDescription.setBackgroundColor(Color.BLUE);
        }

        @Override
        public String toString() {
            return ""; //super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}