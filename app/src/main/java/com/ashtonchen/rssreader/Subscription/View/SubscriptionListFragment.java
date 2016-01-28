package com.ashtonchen.rssreader.subscription.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.BaseRecyclerViewAdapter;
import com.ashtonchen.rssreader.base.MasterDetailListFragment;
import com.ashtonchen.rssreader.reader.model.Channel;
import com.ashtonchen.rssreader.subscription.SubscriptionComponent;
import com.ashtonchen.rssreader.subscription.listener.SubscriptionNetworkCallbackInterface;
import com.ashtonchen.rssreader.subscription.view.adapter.SubscriptionRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionListFragment extends MasterDetailListFragment implements SubscriptionNetworkCallbackInterface {

    private SubscriptionComponent mSubscriptionComponent;
    private List<Channel> mList;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SubscriptionListFragment() {

    }

    public static SubscriptionListFragment newInstance() {
        return new SubscriptionListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        this.mSubscriptionComponent = new SubscriptionComponent(mContext);

        setSubtitle(R.string.action_bar_subtitle_subscriptions);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //LinearLayout view = (LinearLayout) inflater.inflate(R.layout.subscription_list, container, false);

        View view = super.onCreateView(inflater, container, savedInstanceState);
        // Set the adapter
        setupAdapter();
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
 /*   public interface RecyclerViewInteractionListener {

        //void onListFragmentInteraction(SubscriptionItem item);
    }
*/

    @Override
    protected BaseRecyclerViewAdapter getAdapter() {
        mList = mSubscriptionComponent.getSubscriptions();
        return new SubscriptionRecyclerViewAdapter(mList);
    }

    @Override
    protected View.OnClickListener getOnClickListener() {
        return null;
    }

    @Override
    protected View.OnLongClickListener getOnLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);

                mSubscriptionComponent.removeSubscription(mList.get(position));
                mList.remove(position);

                mAdapter.notifyItemRangeChanged(position, mList.size()

                );

                mAdapter.notifyItemRemoved(position);
                return false;
            }
        };
    }
}

