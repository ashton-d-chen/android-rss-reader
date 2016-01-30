package com.ashtonchen.rssreader.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.main.helper.ActionBarColorHelper;
import com.ashtonchen.rssreader.main.view.MainActivity;

/**
 * Created by Ashton Chen on 15-12-17.
 */
public abstract class BaseFragment extends Fragment {
    protected MainActivity mContext;
    protected float mScale;
    //protected MainActivity mMainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (MainActivity) getActivity();
        mScale = mContext.getResources().getDisplayMetrics().density;
        //mMainActivity = (MainActivity) mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container,
                savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        ActionBarColorHelper.colorizeToolbar(mContext.getToolbar(), Color.WHITE);
    }

    protected final void setSubtitle(int subtitle) {
        mContext.getSupportActionBar().setSubtitle(getString(subtitle));
    }
}
