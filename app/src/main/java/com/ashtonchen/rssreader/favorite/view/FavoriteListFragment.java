package com.ashtonchen.rssreader.favorite.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.BaseRecyclerViewAdapter;
import com.ashtonchen.rssreader.base.MasterDetailFeedListFragment;
import com.ashtonchen.rssreader.favorite.FavoriteListComponent;
import com.ashtonchen.rssreader.favorite.view.adapter.FavoriteViewAdapter;
import com.ashtonchen.rssreader.reader.model.Feed;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteListFragment extends MasterDetailFeedListFragment {

    private FavoriteListComponent mFavoriteComponent;

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFavoriteComponent = new FavoriteListComponent(mContext);
        setSubtitle(R.string.action_bar_subtitle_favorites);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setupAdapter();
        return view;
    }


    @Override
    protected BaseRecyclerViewAdapter getAdapter() {
        return new FavoriteViewAdapter(mFavoriteComponent.getListData());
    }

    @Override
    protected View.OnLongClickListener getOnLongClickListener() {
        return new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);
                Log.d(this.getClass().getName(), "Long click on position = " + position);
                List<Feed> list = mAdapter.getList();
                Feed feed = list.get(position);
                mFavoriteComponent.removeListData(feed);
                return true;
            }
        };
    }
}
