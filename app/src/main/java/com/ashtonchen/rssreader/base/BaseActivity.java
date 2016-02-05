package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashtonchen.rssreader.R;

import java.util.List;

/**
 * Created by Ashton Chen on 16-02-03.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String STATE_CURRENT_FRAGMENT_TAG = "currentFragmentTag";
    private String mCurrentFragmentTag;
    private String mRestoredFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        if (savedInstanceState != null) {
            Log.d(this.getClass().getSimpleName(), "savedInstanceState found");
            mRestoredFragmentTag = savedInstanceState.getString(STATE_CURRENT_FRAGMENT_TAG, null);
            mCurrentFragmentTag = mRestoredFragmentTag;
        }
        displayFragment(getContentFragment());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_CURRENT_FRAGMENT_TAG, mCurrentFragmentTag);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            List<Fragment> list = getSupportFragmentManager().getFragments();
            Fragment fragment = list.get(list.size() - 2);
            if (fragment != null) {
                mCurrentFragmentTag = fragment.getClass().getSimpleName();
            }
            getSupportFragmentManager().popBackStackImmediate();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    protected final int getContentLayout() {
        return R.layout.activity_main;
    }

    protected final int getContentView() {
        return R.id.content_main;
    }

    protected Fragment getContentFragment() {
        return getSupportFragmentManager().findFragmentByTag(mRestoredFragmentTag);
    }

    public final void displayFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        String fragmentTag = fragment.getClass().getSimpleName();
        if (mCurrentFragmentTag != fragmentTag) {
            mCurrentFragmentTag = fragmentTag;
        }
        fragmentTransaction.replace(getContentView(), fragment, mCurrentFragmentTag);
        if (mRestoredFragmentTag == null) {
            fragmentTransaction.addToBackStack(mCurrentFragmentTag);
        } else {
            mRestoredFragmentTag = null;
        }
        fragmentTransaction.commit();
    }
}
