package com.ashtonchen.rssreader.favorite.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ashtonchen.rssreader.base.MasterDetailListFragment;
import com.ashtonchen.rssreader.favorite.FavoriteComponent;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteListFragment extends MasterDetailListFragment {

    private FavoriteComponent mFavoriteComponent;

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return null;
    }

    @Override
    protected void setupRecyclerView(View view) {
        super.setupRecyclerView(view);
    }

    @Override
    protected View.OnClickListener getOnClickListener() {
        return null;
    }

    @Override
    protected View.OnLongClickListener getOnLongClickListener() {
        return null;
    }
}
