package com.ashtonchen.rssreader.base;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.view.detail.FeedDetailFragment;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-26.
 */
public abstract class MasterDetailFeedListFragment<T extends BaseRecyclerViewAdapter, S extends DatabaseComponent> extends MasterDetailListFragment<T, S, Feed> {

    @Override
    protected Fragment getDetailFragment(int position) {
        List<Feed> list = mAdapter.getList();
        return FeedDetailFragment.newInstance(list.get(position), mTwoPane);
    }

    @Override
    protected RecyclerView.OnLongClickListener getOnLongClickListener() {
        return null;
    }
}
