package com.ashtonchen.rssreader.favorite.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.BaseRecyclerViewAdapter;
import com.ashtonchen.rssreader.base.MasterDetailFeedListFragment;
import com.ashtonchen.rssreader.favorite.FavoriteComponent;
import com.ashtonchen.rssreader.favorite.view.adapter.FavoriteViewAdapter;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteListFragment extends MasterDetailFeedListFragment {

    private FavoriteComponent mFavoriteComponent;

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFavoriteComponent = new FavoriteComponent(mContext);
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
        return new FavoriteViewAdapter(mFavoriteComponent.getFavorites());
    }

    @Override
    protected View.OnLongClickListener getOnLongClickListener() {
        return null;
    }
}
