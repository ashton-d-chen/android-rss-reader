package com.ashtonchen.rssreader.reader.view.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.model.Feeds;

/**
 * A fragment representing a single Feed detail screen.
 * This fragment is either contained in a {@link com.ashtonchen.rssreader.reader.view.FeedListFragment}
 * in two-pane mode (on tablets)
 * on handsets.
 */
public class FeedDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_POSITION = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Feed mFeed;

    public static FeedDetailFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_POSITION, position);
        FeedDetailFragment fragment = new FeedDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_POSITION)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mFeed = Feeds.get(getArguments().getInt(ARG_ITEM_POSITION));

/*            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                //appBarLayout.setTitle(mItem.content);
            }*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mFeed != null) {
            ((TextView) rootView.findViewById(R.id.feed_detail_title)).setText(mFeed.getTitle());
            ((TextView) rootView.findViewById(R.id.feed_detail_description)).setText(mFeed.getDescription());
        }
        return rootView;
    }
}
