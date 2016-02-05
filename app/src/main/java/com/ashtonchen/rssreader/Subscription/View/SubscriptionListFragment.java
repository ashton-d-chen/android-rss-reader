package com.ashtonchen.rssreader.subscription.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.MasterDetailListFragment;
import com.ashtonchen.rssreader.subscription.SubscriptionComponent;
import com.ashtonchen.rssreader.subscription.listener.SubscriptionNetworkCallbackInterface;
import com.ashtonchen.rssreader.subscription.model.Channel;
import com.ashtonchen.rssreader.subscription.view.adapter.SubscriptionRecyclerViewAdapter;
import com.ashtonchen.rssreader.subscription.view.detail.SubscriptionDetailFragment;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionListFragment extends MasterDetailListFragment<SubscriptionRecyclerViewAdapter, SubscriptionComponent, Channel> implements SubscriptionNetworkCallbackInterface {
    private static final int NEW_SUBSCRIPTION = 1;

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
        setSubtitle(R.string.action_bar_subtitle_subscriptions);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //LinearLayout view = (LinearLayout) inflater.inflate(R.layout.subscription_list, container, false);

        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.add(Menu.NONE, R.id.subscription_new, Menu.NONE, "Add");
        item.setOnMenuItemClickListener(getMenuItemClickListener(this));
        item.setIcon(R.drawable.ic_action_bar_add);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
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
    protected SubscriptionComponent getComponent() {
        return new SubscriptionComponent(mContext);
    }

    @Override
    protected SubscriptionRecyclerViewAdapter getAdapter() {
        return new SubscriptionRecyclerViewAdapter(mComponent.getData());
    }

    @Override
    protected Fragment getDetailFragment(int position) {
        List<Channel> list = mAdapter.getList();
        return SubscriptionDetailFragment.newInstance(list.get(position), mTwoPane);
    }

    @Override
    protected View.OnLongClickListener getOnLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);
                int result = mComponent.removeData(position);
                if (result > 0) {
                    mAdapter.notifyItemRemoved(position);
                    Toast.makeText(mContext, R.string.subscription_removed, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
    }

    @Override
    protected String getEmptyViewMessage() {
        return getString(R.string.list_empty_list_message_no_subscription);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case NEW_SUBSCRIPTION:
                if (resultCode == Activity.RESULT_OK) {
                    //Log.d(this.getClass().getSimpleName(), "get new subscription");
                    //Log.d(this.getClass().getSimpleName(), "new subscription list size = " + mComponent.getData().size());
                    //mAdapter.notifyItemInserted(mComponent.getData().size() - 1);
                    //mAdapter.setList(mComponent.getData());
                    //mAdapter.notifyDataSetChanged();
                    startGetItemListsTask();
                }
                break;
        }
    }

    private MenuItem.OnMenuItemClickListener getMenuItemClickListener(final SubscriptionListFragment listFragment) {
        return new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Fragment fragment = SubscriptionNewFragment.newInstance();
                fragment.setTargetFragment(listFragment, NEW_SUBSCRIPTION);
                mContext.displayFragment(fragment);
                return true;
            }
        };
    }
}

