package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.view.widget.DecoratedItemRecyclerView;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public abstract class MasterDetailListFragment extends BaseFragment {

    protected RecyclerView mRecyclerView;
    protected boolean mTwoPane;
    protected BaseRecyclerViewAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mater_detail_list, container, false);
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
    }

    protected abstract BaseRecyclerViewAdapter getAdapter();

    protected abstract RecyclerView.OnClickListener getOnClickListener();

    protected abstract RecyclerView.OnLongClickListener getOnLongClickListener();
}
