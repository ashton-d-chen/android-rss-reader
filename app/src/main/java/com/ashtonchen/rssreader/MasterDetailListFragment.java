package com.ashtonchen.rssreader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ashtonchen.rssreader.Reader.Interface.OnListFragmentInteractionListener;
import com.ashtonchen.rssreader.Reader.View.Detail.FeedDetailActivity;
import com.ashtonchen.rssreader.Reader.View.Detail.FeedDetailFragment;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class MasterDetailListFragment extends Fragment implements OnListFragmentInteractionListener {
    protected Context mContext;
    protected boolean mTwoPane;


    protected OnListFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    /*
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

        }
    */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onItemClick(View v, String id) {
        if (mTwoPane) {
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
    }

    protected void setupAdapter() {

    }
}
