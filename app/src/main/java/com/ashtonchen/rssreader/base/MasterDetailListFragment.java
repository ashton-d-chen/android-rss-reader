package com.ashtonchen.rssreader.base;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.StyleSheet;
import com.ashtonchen.rssreader.reader.view.widget.DecoratedItemRecyclerView;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public abstract class MasterDetailListFragment<T extends BaseRecyclerViewAdapter, S extends DatabaseComponent, U> extends DatabaseComponentFragment<S, U> {
    private static final String BUNDLE_RECYCLER_LAYOUT = "KeyForLayoutManagerState";
    private static final int MINIMUM_WINDOW_WIDTH = 900;
    private static Bundle mBundleRecyclerViewState;

    protected boolean mTwoPane;
    protected T mAdapter;
    protected EmptyRecyclerView mRecyclerView;
    protected SwipeRefreshLayout mListContainer;
    private int mLastClickedPosition;
    private View mDetailView;
    private SwipeRefreshLayout mEmptyListContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLastClickedPosition = -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.master_detail_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) view;

        Display display = mContext.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        final float density = getResources().getDisplayMetrics().density;
        final float dpWidth = outMetrics.widthPixels / density;

        if (dpWidth >= MINIMUM_WINDOW_WIDTH) {
            mTwoPane = true;

            layout.setBaselineAligned(false);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            mListContainer = getMasterViewContainer();
            layout.addView(mListContainer);
            layout.addView(getDivider());

            mDetailView = getDetailView();
            layout.addView(mDetailView);
        } else {
            mTwoPane = false;
            layout.setOrientation(LinearLayout.VERTICAL);
            mListContainer = getMasterViewContainer();
            layout.addView(mListContainer);
        }
        layout.addView(getEmptyViewContainer());

        mEmptyListContainer = getEmptyViewContainer();

        setupAdapter();

        mRecyclerView = new EmptyRecyclerView(mContext);
        mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //mRecyclerView.setBackgroundColor(Color.BLUE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DecoratedItemRecyclerView(30));
        mRecyclerView.setEmptyViewListener(getEmptyViewListener());
        mRecyclerView.setClickFirstItemListener(getClickFirstItemListener());
        mRecyclerView.setAdapter(mAdapter);

        int padding = (int) (StyleSheet.PADDING * mScale + 0.5f);
        mRecyclerView.setPadding(padding, padding, padding, padding);
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
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(this.getClass().getSimpleName(), "onPause()");
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(BUNDLE_RECYCLER_LAYOUT, listState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView.setEmptyViewListener(null);
        mRecyclerView.setClickFirstItemListener(null);
        mRecyclerView.setAdapter(null);
        mRecyclerView = null;
        mAdapter = null;
        mListContainer = null;
        mDetailView = null;
        mEmptyListContainer = null;
    }

    public final void setupAdapter() {
        Log.d(this.getClass().getName(), "setup adapter");
        mAdapter = getAdapter();
        mAdapter.setOnClickListener(getOnClickListener());
        mAdapter.setOnLongClickListener(getOnLongClickListener());
    }

    private final View getEmptyView() {
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
                mLastClickedPosition = mRecyclerView.getChildAdapterPosition(v);
                Log.d(this.getClass().getSimpleName(), "item clicked position =  " + mLastClickedPosition);
                if (mTwoPane) {
                    Log.d(this.getClass().getSimpleName(), "It's two panel");
                    DisplayDetailContent(mLastClickedPosition);
                } else {
                    Log.d(this.getClass().getSimpleName(), "It's single panel");
                    Fragment fragment = getDetailFragment(mLastClickedPosition);
                    mContext.displayFragment(fragment);
                }
            }
        };
    }

    protected final void DisplayDetailContent(int position) {
        Fragment fragment = getDetailFragment(position);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.detail_container, fragment)
                .commit();
    }

    protected void startOnPostExecute() {
        if (mAdapter != null) {
            mAdapter.setList(mList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private SwipeRefreshLayout getMasterViewContainer() {
        SwipeRefreshLayout layout = new SwipeRefreshLayout(mContext);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        if (mTwoPane) {
            width = (int) (400 * mScale + 0.5f);
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);
        layout.setId(R.id.swipe_refresh_layout_list);
        layout.setEnabled(false);
        //layout.setBackgroundColor(Color.GREEN);
        return layout;
    }

    private View getDivider() {
        ImageView divider = new ImageView(mContext);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, 0, 0, 0);
        divider.setLayoutParams(lp);
        divider.setBackgroundColor(Color.GRAY);
        return divider;
    }

    private FrameLayout getDetailView() {
        FrameLayout layout = new FrameLayout(mContext);
        layout.setId(R.id.detail_container);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);
        int padding = (int) (16 * mScale + 0.5f);
        layout.setPadding(padding, padding, padding, padding);
        //layout.setBackgroundColor(Color.RED);

        return layout;
    }

    private SwipeRefreshLayout getEmptyViewContainer() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        SwipeRefreshLayout layout = new SwipeRefreshLayout(mContext);
        layout.setLayoutParams(params);
        layout.setId(R.id.swipe_refresh_layout_list);
        layout.setEnabled(false);
        //layout.setBackgroundColor(Color.YELLOW);
        layout.addView(getEmptyView());
        return layout;
    }

    protected EmptyRecyclerView.EmptyViewListener getEmptyViewListener() {
        return new EmptyRecyclerView.EmptyViewListener() {
            public void setEmptyView(boolean value) {
                if (mListContainer != null && mEmptyListContainer != null) {
                    mListContainer.setVisibility(value ? View.GONE : View.VISIBLE);
                    mEmptyListContainer.setVisibility(value ? View.VISIBLE : View.GONE);
                }
                if (mDetailView != null) {
                    mDetailView.setVisibility(value ? View.GONE : View.VISIBLE);
                }
            }
        };
    }

    protected EmptyRecyclerView.ClickFirstItemListener getClickFirstItemListener() {
        return new EmptyRecyclerView.ClickFirstItemListener() {
            @Override
            public void clickFirstItem() {
                if (mTwoPane && mAdapter.getItemCount() > 0) {
                    if (mLastClickedPosition >= 0) {
                        DisplayDetailContent(mLastClickedPosition);
                    } else {
                        DisplayDetailContent(0);
                    }
                }
            }
        };
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
