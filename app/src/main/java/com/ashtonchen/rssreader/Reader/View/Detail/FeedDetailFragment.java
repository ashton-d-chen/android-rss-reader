package com.ashtonchen.rssreader.reader.view.detail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.StyleSheet;
import com.ashtonchen.rssreader.base.DetailFragment;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.view.WebViewFragment;

/**
 * A fragment representing a single Feed detail screen.
 * This fragment is either contained in a {@link com.ashtonchen.rssreader.reader.view.FeedListFragment}
 * in two-pane mode (on tablets)
 * on handsets.
 */
public class FeedDetailFragment extends DetailFragment<Feed> {

    public static Fragment newInstance(Feed feed, boolean twoPane) {
        Fragment fragment = new FeedDetailFragment();
        fragment = DetailFragment.newInstance(fragment, twoPane);
        fragment.getArguments().putParcelable(Feed.ARG_FEED, feed);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getArguments().getParcelable(Feed.ARG_FEED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mData != null) {
            int cellPadding = (int) (StyleSheet.CELL_PADDING * mScale + 0.5f);
            view.setPadding(cellPadding, cellPadding, cellPadding, cellPadding);

            TextView title = (TextView) view.findViewById(R.id.feed_detail_title);
            title.setText(mData.getTitle());
            title.setTextSize(StyleSheet.DETAIL_TITLE_FONT_SIZE);
            title.setTypeface(null, Typeface.BOLD);
            title.setOnClickListener(getOnClickListener());
            TextView description = (TextView) view.findViewById(R.id.feed_detail_description);
            description.setText(mData.getDescription());
            description.setTextSize(StyleSheet.DETAIL_DESCRIPTION_FONT_SIZE);
            description.setOnClickListener(getOnClickListener());

            //ImageView imageView = (ImageView) rootView.findViewById(R.id.feed_detail_thumbnail);
            //imageView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(imageView));
        }
    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getSimpleName(), "detail view clicked");
                WebViewFragment fragment = WebViewFragment.newInstance(mData.getUrl());
                mContext.displayFragment(fragment);
            }
        };
    }

    protected String getSubtitle() {
        return getString(R.string.action_bar_subtitle_feed_detail);
    }

    protected boolean shouldDisplayDrawerIcon() {
        return false;
    }

 /*   protected ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final ImageView view) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = (int) (view.getWidth() * 0.5);
                if (!mFeed.getThumbnailURL().isEmpty()) {
                    Picasso
                            .with(mContext)
                            .load(mFeed.getThumbnailURL())
                            .resize(width, width)
                            .into(view);
                } else if (!mFeed.getWebThumbnailURL().isEmpty()) {
                    Picasso
                            .with(mContext)
                            .load(mFeed.getWebThumbnailURL())
                            .resize(width, width)
                            .into(view);
                }
            }
        };
    }*/
}
