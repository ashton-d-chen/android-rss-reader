package com.ashtonchen.rssreader.reader.view.detail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class FeedDetailFragment extends DetailFragment {
    private Feed mFeed;

    public FeedDetailFragment() {

    }

    public static FeedDetailFragment newInstance(Feed feed) {
        FeedDetailFragment fragment = new FeedDetailFragment();
        fragment.mFeed = feed;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed_detail, container, false);
        if (mFeed != null) {
            int cellPadding = (int) (StyleSheet.CELL_PADDING * mScale + 0.5f);
            rootView.setPadding(cellPadding, cellPadding, cellPadding, cellPadding);


            TextView title = (TextView) rootView.findViewById(R.id.feed_detail_title);
            title.setText(mFeed.getTitle());
            title.setTextSize(StyleSheet.DETAIL_TITLE_FONT_SIZE);
            title.setTypeface(null, Typeface.BOLD);
            title.setOnClickListener(getOnClickListener());

            TextView description = (TextView) rootView.findViewById(R.id.feed_detail_description);
            description.setText(mFeed.getDescription());
            description.setTextSize(StyleSheet.DETAIL_DESCRIPTION_FONT_SIZE);
            description.setOnClickListener(getOnClickListener());

            //ImageView imageView = (ImageView) rootView.findViewById(R.id.feed_detail_thumbnail);
            //imageView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(imageView));
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getName(), "detail view clicked");
                WebViewFragment fragment = WebViewFragment.newInstance(mFeed.getUrl());
                mContext.displayFragment(fragment);
            }
        };
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
