package com.ashtonchen.rssreader.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.main.helper.ActionBarColorHelper;
import com.ashtonchen.rssreader.main.view.MainActivity;

/**
 * Created by Ashton Chen on 15-12-17.
 */
public abstract class BaseFragment extends Fragment {
    protected MainActivity mContext;
    protected float mScale;
    protected AppBarLayout mAppBarLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) getActivity();
        mScale = mContext.getResources().getDisplayMetrics().density;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mContext.getSupportActionBar() != null && !getSubtitle().isEmpty()) {
            mContext.getSupportActionBar().setSubtitle(getSubtitle());
        }
        mAppBarLayout = (AppBarLayout) mContext.findViewById(R.id.app_bar);
        setNavigationMenuIcon(shouldDisplayDrawerIcon());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mScale = 0f;
        mContext = null;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        ActionBarColorHelper.colorizeToolbar(mContext.getToolBar(), Color.WHITE);
    }

    protected abstract String getSubtitle();

    private void setNavigationMenuIcon(boolean isDrawerIcon) {
        if (isDrawerIcon) {
            Log.d(this.getClass().getSimpleName(), "Toggle enabled");
            mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mContext.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        } else {
            Log.d(this.getClass().getSimpleName(), "Toggle disabled");
            mContext.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mContext.getActionBarDrawerToggle().setToolbarNavigationClickListener(getToolbarNavigationClickListener());

        }
    }

    private final View.OnClickListener getToolbarNavigationClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onBackPressed();
            }
        };
    }

    public final void setAppLayoutExpandable(boolean expandable) {
        if (mAppBarLayout != null) {
            Log.d(this.getClass().getSimpleName(), "Set app layout expandable = " + expandable);
            mAppBarLayout.setExpanded(expandable, true);
        }
    }

    protected abstract boolean shouldDisplayDrawerIcon();
}
