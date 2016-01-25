package com.ashtonchen.rssreader.subscription.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ashtonchen.rssreader.MasterDetailListFragment;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.view.widget.DecoratedItemRecyclerView;
import com.ashtonchen.rssreader.subscription.SubscriptionComponent;
import com.ashtonchen.rssreader.subscription.listener.SubscriptionNetworkCallbackInterface;
import com.ashtonchen.rssreader.subscription.model.Subscription;
import com.ashtonchen.rssreader.subscription.view.adapter.SubscriptionRecyclerViewAdapter;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionListFragment extends MasterDetailListFragment implements SubscriptionNetworkCallbackInterface {

    private SubscriptionComponent mSubscriptionComponent;
    private SubscriptionRecyclerViewAdapter mFeedViewAdapter;
    private RecyclerView mRecyclerView;
    private Subscription mSubscription;
    private SubscriptionNetworkCallbackInterface mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SubscriptionListFragment() {

    }

    @SuppressWarnings("unused")
    public static SubscriptionListFragment newInstance() {
        //Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        //fragment.setArguments(args);
        return new SubscriptionListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        this.mSubscriptionComponent = new SubscriptionComponent(mContext);

        mSubscription = new Subscription();

        //setupRecyclerView((RecyclerView) mRecyclerView);

        if (getActivity().findViewById(R.id.subscription_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        setSubtitle(R.string.action_bar_subtitle_subscriptions);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.subscription_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.subscription_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DecoratedItemRecyclerView(30));
        setupRecyclerView();
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.add(Menu.NONE, R.id.subscription_new, Menu.NONE, "Add");
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Fragment fragment = SubscriptionNewFragment.newInstance();
                mMainActivity.fragmentTransaction(fragment);
                return false;
            }
        });
        item.setIcon(R.drawable.ic_action_bar_add);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        super.onPrepareOptionsMenu(menu);
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
    public void onDownloadFinished(Subscription subscriptin) {
        mSubscription = subscriptin;
        setupRecyclerView();
        //mFeedViewAdapter.setData(mSubscription);
    }

    private void setupRecyclerView() {
        mFeedViewAdapter = new SubscriptionRecyclerViewAdapter(mContext, this, mSubscriptionComponent);
        mRecyclerView.setAdapter(mFeedViewAdapter);
    }

    public void onFeedItemClick(View v, String id) {
/*        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(FeedDetailFragment.ARG_ITEM_ID, id);
            FeedDetailFragment fragment = new FeedDetailFragment();
            fragment.setArguments(arguments);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.feed_detail_container, fragment)
                    .commit();
        } else {
            Context context = v.getContext();
            Intent intent = new Intent(context, FeedDetailActivity.class);
            intent.putExtra(FeedDetailFragment.ARG_ITEM_ID, id);

            context.startActivity(intent);
        }
*/
    }

    public void onListItemClicked(View view) {

    }
/*
    private void createContentFragment(int id) {
        setCurrentFragmentId(id);

        Fragment fragment;
        ActionBar actionBar = getSupportActionBar();
        String subtitle;
        if (id == R.id.nav_all) {
            subtitle = getString(R.string.action_bar_subtitle_feeds);
            fragment = FeedListFragment.newInstance();
        } else if (id == R.id.nav_favorite) {
            subtitle = getString(R.string.action_bar_subtitle_favorites);
            fragment = FeedListFragment.newInstance();
        } else if (id == R.id.nav_subscription) {
            subtitle = getString(R.string.action_bar_subtitle_subscriptions);
            fragment = SubscriptionListFragment.newInstance();
        } else if (id == R.id.subscription_new) {
            subtitle = getString(R.string.action_bar_subtitle_new_subscription);
            fragment = SubscriptionNewFragment.newInstance();
        } else {
            subtitle = getString(R.string.action_bar_subtitle_feeds);
            fragment = FeedListFragment.newInstance();
        }
        actionBar.setSubtitle(subtitle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).addToBackStack(null).commit();
    }
    */
}

