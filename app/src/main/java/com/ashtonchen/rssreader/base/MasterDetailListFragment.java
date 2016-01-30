package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.view.detail.FeedDetailFragment;
import com.ashtonchen.rssreader.reader.view.widget.DecoratedItemRecyclerView;

import java.util.List;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public abstract class MasterDetailListFragment<T extends BaseRecyclerViewAdapter, S extends DatabaseComponent> extends DatabaseComponentFragment<S> {

    protected RecyclerView mRecyclerView;
    protected boolean mTwoPane;
    protected T mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_detail_list, container, false);
        Log.d(this.getClass().getName(), "try to find two panes");

        if (view.findViewById(R.id.detail_container) != null) {
            mTwoPane = true;
        }

        setupRecyclerView(view);
        return view;
    }

    protected void setupRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);

        if (mRecyclerView == null) {
            Log.d(this.getClass().getName(), "recycler view is null");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DecoratedItemRecyclerView(30));
    }

    public void setupAdapter() {
        mAdapter = getAdapter();
        mAdapter.setOnClickListener(getOnClickListener());
        mAdapter.setOnLongClickListener(getOnLongClickListener());
        mRecyclerView.setAdapter(mAdapter);
        if (mTwoPane && mAdapter.getItemCount() > 0) {
            setDetailContent(0);
        }
    }

    protected RecyclerView.OnClickListener getOnClickListener() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);
                Log.d(this.getClass().getName(), "item clicked position =  " + position);
                if (mTwoPane) {
                    Log.d(this.getClass().getName(), "It's two panel");
                    setDetailContent(position);
                } else {
                    Log.d(this.getClass().getName(), "It's single panel");
                    Fragment fragment = getDetailFragment(position);
                    mContext.fragmentTransaction(fragment);
                }
            }
        };
    }

    protected void setDetailContent(int position) {
        Fragment fragment = getDetailFragment(position);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_container, fragment)
                .commit();
    }

    protected abstract T getAdapter();

    protected abstract DetailFragment getDetailFragment(int position);

    protected abstract RecyclerView.OnLongClickListener getOnLongClickListener();
}
