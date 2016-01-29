package com.ashtonchen.rssreader.reader.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.BaseRecyclerViewAdapter;
import com.ashtonchen.rssreader.base.MasterDetailFeedListFragment;
import com.ashtonchen.rssreader.favorite.dao.FavoriteDAO;
import com.ashtonchen.rssreader.favorite.model.Favorites;
import com.ashtonchen.rssreader.reader.ReaderListComponent;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.listener.RecyclerViewInteractionListener;
import com.ashtonchen.rssreader.reader.model.Channel;
import com.ashtonchen.rssreader.reader.model.Channels;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.view.adapter.FeedViewAdapter;

/**
 * Created by Ashton Chen on 15-12-12.
 */


/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link RecyclerViewInteractionListener}
 * interface.
 */
public class FeedListFragment extends MasterDetailFeedListFragment implements FeedNetworkCallbackInterface {

    private ReaderListComponent mReaderComponent;
    private int downloadChannelCount = 0;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FeedListFragment() {

    }

    public static FeedListFragment newInstance() {
        return new FeedListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReaderComponent = new ReaderListComponent(mContext, this);

        setSubtitle(R.string.action_bar_subtitle_feeds);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        addFloatingActionButton(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(this.getClass().getName(), "Start getting feed list");
        mReaderComponent.getFeedList(this);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
 /*   public interface RecyclerViewInteractionListener {

        //void onListFragmentInteraction(SubscriptionItem item);
    }
*/

    private void addFloatingActionButton(View view) {
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

        Log.d(this.getClass().getName(), "try to find two panes");
        if (view.findViewById(R.id.feed_detail_container) != null) {
            Log.d(this.getClass().getName(), "two panes");
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.feed_detail_container);
            frameLayout.addView(fab);
        } else {
            ((LinearLayout)view).addView(fab);
        }
    }


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

    @Override
    protected BaseRecyclerViewAdapter getAdapter() {
        return new FeedViewAdapter(Feeds.getFeeds());
    }

    @Override
    protected RecyclerView.OnLongClickListener getOnLongClickListener() {
        return new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);
                Log.d(this.getClass().getName(), "Long click on position = " + position);
                Favorites.add(Feeds.get(position));
                FavoriteDAO helper = new FavoriteDAO(mContext);
                helper.addItem(Feeds.get(position));

                return true;
            }
        };
    }

}

