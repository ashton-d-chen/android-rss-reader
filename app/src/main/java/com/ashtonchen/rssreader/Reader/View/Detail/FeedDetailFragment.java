package com.ashtonchen.rssreader.reader.view.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.DetailFragment;
import com.ashtonchen.rssreader.reader.model.Feed;

/**
 * A fragment representing a single Feed detail screen.
 * This fragment is either contained in a {@link com.ashtonchen.rssreader.reader.view.FeedListFragment}
 * in two-pane mode (on tablets)
 * on handsets.
 */
public class FeedDetailFragment extends DetailFragment {
    private Feed mFeed;

    public static FeedDetailFragment newInstance(Feed feed) {
        FeedDetailFragment fragment = new FeedDetailFragment();
        fragment.mFeed = feed;
        return fragment;
    }

    public FeedDetailFragment() {

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
            ((TextView) rootView.findViewById(R.id.feed_detail_title)).setText(mFeed.getTitle());
            ((TextView) rootView.findViewById(R.id.feed_detail_description)).setText(mFeed.getDescription());
        }
        return rootView;
    }
}
