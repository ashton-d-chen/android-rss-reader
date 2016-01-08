package com.ashtonchen.rssreader.Subscription.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.MasterDetailListFragment;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.View.Widget.DecoratedItemRecyclerView;
import com.ashtonchen.rssreader.Subscription.Interface.SubscriptionNetworkCallbackInterface;
import com.ashtonchen.rssreader.Subscription.Model.Subscription;
import com.ashtonchen.rssreader.Subscription.SubscriptionComponent;
import com.ashtonchen.rssreader.Subscription.View.Adapter.SubscriptionRecyclerViewAdapter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subscription_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.subscription_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DecoratedItemRecyclerView(30));
        setupRecyclerView();
        return view;
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
}
