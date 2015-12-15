package com.ashtonchen.rssreader.Reader.View;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashtonchen.rssreader.BaseRecyclerViewHolder;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Model.Feed;
import com.ashtonchen.rssreader.StyleSheet;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class FeedViewHolder extends BaseRecyclerViewHolder {
    public final View mView;
    public final ImageView mThumbnail;
    public final TextView mTitle;
    public final TextView mDescription;
    public Feed mData;


    private int cellPadding;
    private int cellThumbnailSize;
    private int cellThumbnailTextSpacing;
    private int cellTextViewSpacing;

    public FeedViewHolder(View view) {
        super(view);
        mView = view;

        cellPadding = (int) (StyleSheet.CELL_PADDING * mScale + 0.5f);
        cellThumbnailSize = (int) (StyleSheet.CELL_THUMBNAIL_SIZE * mScale + 0.5f);
        cellThumbnailTextSpacing = (int) (StyleSheet.CELL_Thumbnail_TEXT_SPACING * mScale + 0.5f);
        cellTextViewSpacing = (int) (StyleSheet.CELL_TEXT_VIEW_SPACING * mScale + 0.5f);

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