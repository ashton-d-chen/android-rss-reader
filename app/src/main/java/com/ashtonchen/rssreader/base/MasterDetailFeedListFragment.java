package com.ashtonchen.rssreader.base;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.view.detail.FeedDetailFragment;

/**
 * Created by Ashton Chen on 16-01-26.
 */
public abstract class MasterDetailFeedListFragment<T extends BaseRecyclerViewAdapter, S extends DatabaseComponent> extends MasterDetailListFragment<T, S> {
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
                Log.d(this.getClass().getName(), "item clicked position =  " + position);
                if (mTwoPane) {
                    Log.d(this.getClass().getName(), "It's two panel");
                    Fragment fragment = FeedDetailFragment.newInstance(position);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.feed_detail_container, fragment)
                            .commit();
                } else {
                    Log.d(this.getClass().getName(), "It's single panel");
                    Fragment fragment = FeedDetailFragment.newInstance(position);
                    mContext.fragmentTransaction(fragment);
                }
            }
        };
    }

    @Override
    protected RecyclerView.OnLongClickListener getOnLongClickListener() {
        return null;
    }
}
