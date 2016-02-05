package com.ashtonchen.rssreader.reader.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.MasterDetailFeedListFragment;
import com.ashtonchen.rssreader.favorite.view.FavoriteListFragment;
import com.ashtonchen.rssreader.reader.ReaderComponent;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.listener.RecyclerViewInteractionListener;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.view.adapter.FeedViewAdapter;
import com.ashtonchen.rssreader.subscription.model.Channel;
import com.ashtonchen.rssreader.subscription.view.SubscriptionNewFragment;
import com.ashtonchen.rssreader.utility.NetworkUtility;

/**
 * Created by Ashton Chen on 15-12-12.
 */


/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link RecyclerViewInteractionListener}
 * interface.
 */
public class FeedListFragment extends MasterDetailFeedListFragment<FeedViewAdapter, ReaderComponent> implements FeedNetworkCallbackInterface {
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListContainer.setEnabled(true);
        mListContainer.setOnRefreshListener(getSwipeRefreshLayoutListener(this));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem addItem = menu.add(Menu.NONE, R.id.subscription_new, Menu.NONE, "Add");
        addItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Fragment fragment = SubscriptionNewFragment.newInstance();
                mContext.displayFragment(fragment);
                return true;
            }
        });
        addItem.setIcon(R.drawable.ic_action_bar_add);
        addItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        MenuItem refreshItem = menu.add(Menu.NONE, R.id.action_refresh_feed, Menu.NONE, "Refresh");
        refreshItem.setIcon(R.drawable.ic_refresh);
        refreshItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh_feed:
                mListContainer.setRefreshing(true);
                getFeedList();
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
                Log.d(this.getClass().getSimpleName(), "Floating action button clicked!");
                Fragment fragment = FavoriteListFragment.newInstance();
                mContext.displayFragment(fragment);
            }
        });

 /*       if (mTwoPane) {
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.detail_container);
            frameLayout.addView(fab);
        } else {
            mListContainer.addView(fab);
        }*/
    }


    public void onDownloadFinished(Channel channel) {
        if (channel != null) {
            Feeds.addAll(channel.getFeeds());
        }
        downloadChannelCount++;
        Log.d(this.getClass().getSimpleName(), "Downloaded Channel Count: " + downloadChannelCount);
        if (downloadChannelCount >= mComponent.getData().size()) {
            downloadChannelCount = 0;
            mListContainer.setRefreshing(false);
            mAdapter.setList(Feeds.getFeeds());
            mAdapter.notifyDataSetChanged();
            if (mTwoPane && mAdapter.getItemCount() > 0) {
                DisplayDetailContent(0);
            }
        }

        //mFeedViewAdapter.setData(mChannel);
    }

    @Override
    protected ReaderComponent getComponent() {
        return new ReaderComponent(mContext);
    }

    @Override
    protected FeedViewAdapter getAdapter() {
        return new FeedViewAdapter(Feeds.getFeeds());
    }

    @Override
    protected RecyclerView.OnLongClickListener getOnLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);
                Log.d(this.getClass().getSimpleName(), "Long click on position = " + position);
                Feed feed = mAdapter.getList().get(position);

                if (!mComponent.findFavorite(feed.getUrl())) {
                    long result = mComponent.addToFavorite(feed);
                    if (result > 0) {
                        Toast.makeText(mContext, R.string.toast_added_to_favorite, Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        };
    }


    private SwipeRefreshLayout.OnRefreshListener getSwipeRefreshLayoutListener(final FeedListFragment fragment) {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mListContainer.setRefreshing(true);
                getFeedList();
            }
        };
    }

    @Override
    protected String getEmptyViewMessage() {
        return getString(R.string.list_empty_list_message_no_feed);
    }

    private void getFeedList() {
        Log.d(this.getClass().getSimpleName(), "get feed list");
        if (NetworkUtility.isOnline(mContext)) {
            mComponent.getFeedList(this);
        } else if (Feeds.size() > 0) {
            Log.d(this.getClass().getSimpleName(), "is offline, get list from Feeds");
            setupAdapter();
            //mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void startOnPostExecute() {
        getFeedList();
    }
}

