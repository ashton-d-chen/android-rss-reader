package com.ashtonchen.rssreader.reader.view.widget;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.StyleSheet;
import com.ashtonchen.rssreader.base.BaseRecyclerViewHolder;
import com.ashtonchen.rssreader.reader.model.Feed;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class FeedViewHolder extends BaseRecyclerViewHolder {
    public final ImageView mThumbnail;
    public final TextView mTitle;
    public final TextView mDescription;
    public Feed mData;

    public FeedViewHolder(View view) {
        super(view);

        int cellTopPadding = (int) (StyleSheet.CELL_TOP_PADDING * mScale + 0.5f);
        int cellBottomPadding = (int) (StyleSheet.CELL_BOTTOM_PADDING * mScale + 0.5f);

        int cellLeftPadding = (int) (StyleSheet.CELL_LEFT_PADDING * mScale + 0.5f);
        int cellRightPadding = (int) (StyleSheet.CELL_RIGHT_PADDING * mScale + 0.5f);

        int cellThumbnailSize = (int) (StyleSheet.CELL_THUMBNAIL_SIZE * mScale + 0.5f);
        int cellThumbnailTextSpacing = (int) (StyleSheet.CELL_Thumbnail_TEXT_SPACING * mScale + 0.5f);
        int cellTextViewSpacing = (int) (StyleSheet.CELL_TEXT_VIEW_SPACING * mScale + 0.5f);

        this.itemView.setPadding(cellLeftPadding, cellTopPadding, cellRightPadding, cellBottomPadding);
        //mView.setBackgroundColor(Color.GREEN)

        mThumbnail = (ImageView) view.findViewById(R.id.feed_thumbnail);
        mThumbnail.setPadding(0, 0, cellThumbnailTextSpacing, 0);
        mThumbnail.getLayoutParams().width = cellThumbnailSize;
        mThumbnail.getLayoutParams().height = cellThumbnailSize;

        mTitle = (TextView) view.findViewById(R.id.feed_title);
        mTitle.setMaxLines(StyleSheet.CELL_TITLE_MAX_LINE);
        mTitle.setPadding(0, 0, 0, cellTextViewSpacing);
        mTitle.setTextSize(StyleSheet.CELL_TITLE_FONT_SIZE);
        mTitle.setTypeface(null, Typeface.BOLD);
        //mTitle.setBackgroundColor(Color.RED);

        mDescription = (TextView) view.findViewById(R.id.feed_description);
        mDescription.setTextSize(StyleSheet.CELL_DESCRIPTION_FONT_SIZE);
        mDescription.setPadding(0, 0, 0, 0);
        mDescription.setMaxLines(StyleSheet.CELL_DESCRIPTION_MAX_LINE);
        //mDescription.setBackgroundColor(Color.BLUE);
    }

    @Override
    public String toString() {
        return ""; //super.toString() + " '" + mDescription.getText() + "'";
    }
}