package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.reader.view.widget.DecoratedItemRecyclerView;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public abstract class MasterDetailListFragment<T extends BaseRecyclerViewAdapter, S extends DatabaseComponent, U> extends DatabaseComponentFragment<S, U> {
    private static final String BUNDLE_RECYCLER_LAYOUT = "KeyForLayoutManagerState";
    private static Bundle mBundleRecyclerViewState;

    protected boolean mTwoPane;
    protected T mAdapter;
    protected EmptyRecyclerView mRecyclerView;
    protected SwipeRefreshLayout mListContainer;
    SwipeRefreshLayout mEmptyListContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.master_detail_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        View detailView = view.findViewById(R.id.detail_container);
        if (detailView != null) {
            mTwoPane = true;
            detailView.setVisibility(View.GONE);
        } else {
            mTwoPane = false;
        }

        mListContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_list);
        mListContainer.setEnabled(false);
        //mListContainer.setBackgroundColor(Color.GREEN);
        mListContainer.setVisibility(View.GONE);

        mEmptyListContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_empty_view);
        mEmptyListContainer.setEnabled(false);
        //mEmptyListContainer.setBackgroundColor(Color.YELLOW);
        mEmptyListContainer.addView(getEmptyView());
        mEmptyListContainer.setVisibility(View.GONE);

        setupAdapter();

        mRecyclerView = new EmptyRecyclerView(mContext);
        mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //mRecyclerView.setBackgroundColor(Color.BLUE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DecoratedItemRecyclerView(30));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setContainerView(mListContainer);
        mRecyclerView.setEmptyView(mEmptyListContainer);
        mRecyclerView.setDetailView(detailView);
        mListContainer.addView(mRecyclerView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startGetItemListsTask();
    }

    @Override
    public void onResume() {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(this.getClass().getSimpleName(), "onPause()");
        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(BUNDLE_RECYCLER_LAYOUT, listState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView = null;
    }

    public final void setupAdapter() {
        Log.d(this.getClass().getName(), "setup adapter");
        mAdapter = getAdapter();
        mAdapter.setOnClickListener(getOnClickListener());
        mAdapter.setOnLongClickListener(getOnLongClickListener());
    }

    protected final View getEmptyView() {
        TextView view = new TextView(mContext);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setText(getEmptyViewMessage());
        view.setGravity(Gravity.CENTER);
        view.setTextSize(18);
        //view.setBackgroundColor(Color.RED);
        return view;
    }

    protected RecyclerView.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);
                Log.d(this.getClass().getSimpleName(), "item clicked position =  " + position);
                if (mTwoPane) {
                    Log.d(this.getClass().getSimpleName(), "It's two panel");
                    DisplayDetailContent(position);
                } else {
                    Log.d(this.getClass().getSimpleName(), "It's single panel");
                    Fragment fragment = getDetailFragment(position);
                    mContext.displayFragment(fragment);
                }
            }
        };
    }

    protected final void DisplayDetailContent(int position) {
        Fragment fragment = getDetailFragment(position);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_container, fragment)
                .commit();
    }

    protected void startOnPostExecute() {
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
        if (mTwoPane && mAdapter.getItemCount() > 0) {
            DisplayDetailContent(0);
        }
    }

    protected abstract T getAdapter();

    protected abstract Fragment getDetailFragment(int position);

    protected abstract RecyclerView.OnLongClickListener getOnLongClickListener();

    protected String getEmptyViewMessage() {
        return getString(R.string.list_empty_list_message_no_data);
    }

    protected boolean shouldDisplayDrawerIcon() {
        return true;
    }
}
