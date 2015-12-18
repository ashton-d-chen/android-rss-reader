package com.ashtonchen.rssreader.Reader.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.MasterDetailListFragment;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Interface.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.Reader.Interface.OnListFragmentInteractionListener;
import com.ashtonchen.rssreader.Reader.Model.Channel;
import com.ashtonchen.rssreader.Reader.ReaderComponent;
import com.ashtonchen.rssreader.Reader.View.Adapter.FeedViewAdapter;
import com.ashtonchen.rssreader.Reader.View.Widget.DecoratedItemRecyclerView;

/**
 * Created by Ashton Chen on 15-12-12.
 */


/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FeedListFragment extends MasterDetailListFragment implements FeedNetworkCallbackInterface {


    private ReaderComponent mReaderComponent;
    private FeedViewAdapter mFeedViewAdapter;
    private RecyclerView mRecyclerView;
    private Channel mChannel;

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

        mChannel = new Channel();

        //setupRecyclerView((RecyclerView) mRecyclerView);

        if (getActivity().findViewById(R.id.feed_detail_container) != null) {
            Log.d(this.getClass().getName(), "two panes");
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
        View view = inflater.inflate(R.layout.feed_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.feed_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DecoratedItemRecyclerView(30));
        return view;
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
 /*   public interface OnListFragmentInteractionListener {

        //void onListFragmentInteraction(SubscriptionItem item);
    }
*/
    public void onDownloadFinished(Channel channel) {
        mChannel = channel;
        setupAdapter();
        //mFeedViewAdapter.setData(mChannel);
    }

    protected void setupAdapter() {
        mFeedViewAdapter = new FeedViewAdapter(getContext(), mChannel, this);
        mRecyclerView.setAdapter(mFeedViewAdapter);
    }

    public void onItemClick(View v, String id) {
    }
}

