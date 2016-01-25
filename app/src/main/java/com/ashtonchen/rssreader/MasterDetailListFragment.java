package com.ashtonchen.rssreader;

import android.content.Context;

import com.ashtonchen.rssreader.reader.listener.OnListFragmentInteractionListener;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public abstract class MasterDetailListFragment extends BaseFragment implements OnListFragmentInteractionListener {

    protected boolean mTwoPane;
    protected OnListFragmentInteractionListener mListener;

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

    public void onItemClick(int id) {
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
        }*/

    }

    protected void setupAdapter() {

    }
}
