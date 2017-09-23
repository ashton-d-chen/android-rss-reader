package com.ashtonchen.rssreader.subscription.view.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.StyleSheet;
import com.ashtonchen.rssreader.base.BaseRecyclerViewHolder;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionViewHolder extends BaseRecyclerViewHolder {
    public final TextView mTitle;
    public final TextView mDescription;
    public final ImageView mThumbnail;

    public SubscriptionViewHolder(View view) {
        super(view);

        int cellTopPadding = (int) (StyleSheet.CELL_TOP_PADDING * mScale + 0.5f);
        int cellBottomPadding = (int) (StyleSheet.CELL_BOTTOM_PADDING * mScale + 0.5f);

        int cellLeftPadding = (int) (StyleSheet.CELL_LEFT_PADDING * mScale + 0.5f);
        int cellRightPadding = (int) (StyleSheet.CELL_RIGHT_PADDING * mScale + 0.5f);

        //int cellThumbnailSize = StyleSheet.CELL_THUMBNAIL_SIZE;
        int cellThumbnailSize = (int) (StyleSheet.CELL_THUMBNAIL_SIZE * mScale + 0.5f);
        int cellThumbnailTextSpacing = (int) (StyleSheet.CELL_Thumbnail_TEXT_SPACING * mScale + 0.5f);
        int cellTextViewSpacing = (int) (StyleSheet.CELL_TEXT_VIEW_SPACING * mScale + 0.5f);

        this.itemView.setPadding(cellLeftPadding, cellTopPadding, cellRightPadding, cellBottomPadding);

        mThumbnail = (ImageView) view.findViewById(R.id.feed_thumbnail);
        mThumbnail.setPadding(0, 0, cellThumbnailTextSpacing, 0);
        mThumbnail.getLayoutParams().width = cellThumbnailSize;
        mThumbnail.getLayoutParams().height = cellThumbnailSize;

        mTitle = (TextView) view.findViewById(R.id.feed_title);
        mTitle.setMaxLines(StyleSheet.CELL_TITLE_MAX_LINE);
        mTitle.setPadding(0, 0, 0, cellTextViewSpacing);
        mTitle.setTextSize(StyleSheet.CELL_TITLE_FONT_SIZE);

        mDescription = (TextView) view.findViewById(R.id.feed_description);
        mDescription.setMaxLines(StyleSheet.CELL_DESCRIPTION_MAX_LINE);
        mDescription.setTextSize(StyleSheet.CELL_DESCRIPTION_FONT_SIZE);
    }

    @Override
    public String toString() {
        return ""; //super.toString() + " '" + mContentView.getText() + "'";
    }
}