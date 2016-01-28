package com.ashtonchen.rssreader.main.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.favorite.view.FavoriteListFragment;
import com.ashtonchen.rssreader.reader.view.FeedListFragment;
import com.ashtonchen.rssreader.subscription.view.SubscriptionListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("RSS Reader");
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createContentFragment(R.id.nav_all);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count > 1) {
                getSupportFragmentManager().popBackStack();
            } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        createContentFragment(item.getItemId());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createContentFragment(int id) {

        Fragment fragment;
        if (id == R.id.nav_all) {
            fragment = FeedListFragment.newInstance();
        } else if (id == R.id.nav_favorite) {
            fragment = FavoriteListFragment.newInstance();
        } else if (id == R.id.nav_subscription) {
            fragment = SubscriptionListFragment.newInstance();
        } else {
            fragment = FeedListFragment.newInstance();
        }
        fragmentTransaction(fragment);
    }

    public void fragmentTransaction(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).addToBackStack(null).commit();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
