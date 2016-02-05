package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.favorite.view.FavoriteListFragment;
import com.ashtonchen.rssreader.reader.view.FeedListFragment;
import com.ashtonchen.rssreader.subscription.view.SubscriptionListFragment;

/**
 * Created by Ashton Chen on 16-02-03.
 */
public abstract class DrawerActivity extends ActionBarActivity {
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private int mMenuItemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawerLayout drawer = (DrawerLayout) findViewById(getDrawerLayout());
        mActionBarDrawerToggle = getActionBarDrawerToggle(drawer);
        drawer.setDrawerListener(mActionBarDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(getNavigationView());
        navigationView.setNavigationItemSelectedListener(getOnNavigationItemSelectedListener());
    }

    @Override
    protected Fragment getContentFragment() {
        Fragment fragment = super.getContentFragment();
        if (fragment == null) {
            if (mMenuItemID == R.id.nav_all) {
                fragment = FeedListFragment.newInstance();
            } else if (mMenuItemID == R.id.nav_favorite) {
                fragment = FavoriteListFragment.newInstance();
            } else if (mMenuItemID == R.id.nav_subscription) {
                fragment = SubscriptionListFragment.newInstance();
            } else {
                fragment = FeedListFragment.newInstance();
            }
        }
        return fragment;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private ActionBarDrawerToggle getActionBarDrawerToggle(DrawerLayout drawer) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawer,
                getToolBar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();
        return actionBarDrawerToggle;
    }

    protected NavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                mMenuItemID = item.getItemId();
                displayFragment(getContentFragment());
                return true;
            }
        };
    }

    public final ActionBarDrawerToggle getActionBarDrawerToggle() {
        return mActionBarDrawerToggle;
    }

    protected int getDrawerLayout() {
        return R.id.drawer_layout;
    }

    protected int getNavigationView() {
        return R.id.nav_view;
    }
}
