package com.ashtonchen.rssreader.base;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.main.view.MainActivity;
import com.ashtonchen.rssreader.reader.view.detail.FeedDetailFragment;

/**
 * Created by Ashton Chen on 16-01-26.
 */
public abstract class MasterDetailFeedListFragment extends MasterDetailListFragment {
    @Override
    protected void setupRecyclerView(View view) {
        super.setupRecyclerView(view);
    }

    @Override
    protected RecyclerView.OnClickListener getOnClickListener() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);
                Log.d(this.getClass().getName(), "clicked position =  " + position);
                if (mTwoPane) {
                    Fragment fragment = FeedDetailFragment.newInstance(position);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.feed_detail_container, fragment)
                            .commit();
                } else {
                    Fragment fragment = FeedDetailFragment.newInstance(position);
                    ((MainActivity) mContext).fragmentTransaction(fragment);
                }
            }
        };
    }

    @Override
    protected RecyclerView.OnLongClickListener getOnLongClickListener() {
        return null;
    }
}
