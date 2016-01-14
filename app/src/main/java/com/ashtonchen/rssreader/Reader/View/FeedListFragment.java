package com.ashtonchen.rssreader.Reader.View;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ashtonchen.rssreader.MasterDetailListFragment;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Interface.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.Reader.Interface.OnListFragmentInteractionListener;
import com.ashtonchen.rssreader.Reader.Model.Channel;
import com.ashtonchen.rssreader.Reader.Model.Channels;
import com.ashtonchen.rssreader.Reader.Model.Feeds;
import com.ashtonchen.rssreader.Reader.ReaderComponent;
import com.ashtonchen.rssreader.Reader.View.Adapter.FeedViewAdapter;
import com.ashtonchen.rssreader.Reader.View.Widget.DecoratedItemRecyclerView;

/**
 * Created by Ashton Chen on 15-12-12.
 */


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FeedListFragment extends MasterDetailListFragment implements FeedNetworkCallbackInterface {

    private ReaderComponent mReaderComponent;
    private FeedViewAdapter mFeedViewAdapter;
    private RecyclerView mRecyclerView;
    private int downloadChannelCount = 0;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FeedListFragment() {

    }

    @SuppressWarnings("unused")
    public static FeedListFragment newInstance() {
        //Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        //fragment.setArguments(args);
        return new FeedListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mReaderComponent = new ReaderComponent(mContext);
        this.mReaderComponent.getFeedList(this);

        //setupRecyclerView((RecyclerView) mRecyclerView);

        if (getActivity().findViewById(R.id.feed_detail_container) != null) {
            Log.d(this.getClass().getName(), "two panes");
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        setSubtitle(R.string.action_bar_subtitle_feeds);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.feed_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.feed_list);


        //FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        FloatingActionButton fab = new FloatingActionButton(getActivity());
        fab.setImageResource(R.drawable.ic_menu_favorite);
        fab.setColorFilter(Color.WHITE);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        Float scale = view.getContext().getResources().getDisplayMetrics().density;
        int size = (int) (72 * scale + 0.5f);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        fab.setLayoutParams(params);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(this.getClass().getName(), "Floating action button clicked!");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        view.addView(fab);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DecoratedItemRecyclerView(30));
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.add(Menu.NONE, R.id.action_refresh_feed, Menu.NONE, "Refresh");
        item.setIcon(R.drawable.ic_refresh);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh_feed:
                mReaderComponent.getFeedList(this);
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
 /*   public interface OnListFragmentInteractionListener {

        //void onListFragmentInteraction(SubscriptionItem item);
    }
*/
    public void onDownloadFinished(Channel channel) {
        if (channel != null) {
            Feeds.addAll(channel.getFeeds());
        }
        downloadChannelCount++;
        Log.d(this.getClass().getName(), "Downloaded Channel Count: " + downloadChannelCount);
        if (downloadChannelCount >= Channels.size()) {
            downloadChannelCount = 0;
            setupAdapter();
        }
        //mFeedViewAdapter.setData(mChannel);
    }

    protected void setupAdapter() {
        mFeedViewAdapter = new FeedViewAdapter(getContext(), this);
        mRecyclerView.setAdapter(mFeedViewAdapter);
    }

    public void onItemClick(View v, String id) {
    }
}

