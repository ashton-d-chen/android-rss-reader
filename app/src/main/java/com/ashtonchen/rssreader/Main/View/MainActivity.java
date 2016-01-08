package com.ashtonchen.rssreader.Main.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ashtonchen.rssreader.Main.Helper.ActionBarColorHelper;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.View.FeedListFragment;
import com.ashtonchen.rssreader.Subscription.View.SubscriptionListFragment;
import com.ashtonchen.rssreader.Subscription.View.SubscriptionNewFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Menu mActionMenu;
    private Toolbar mToolbar;
    private int mCurrentFragmentId = -1;
    private int mPreviousFragmentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("RSS Reader");
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createContentFragment(mCurrentFragmentId);

        mToolbar.setBackgroundColor(Color.RED);

    }

    @Override
    public void onBackPressed() {
        if (mPreviousFragmentId > 0) {
            mCurrentFragmentId = mPreviousFragmentId;
            mPreviousFragmentId = -1;
            invalidateOptionsMenu();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mActionMenu = menu;


        //Drawable icon = getResources().getDrawable(R.drawable.ic_action_bar_add);
        //icon.setColorFilter(new
        //      PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN));
        //icon.set
        //mActionMenu.add(Menu.NONE, 0, Menu.NONE, "Refresh").setIcon(icon).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();

        Log.d(this.getClass().getName(), "current id is " + mCurrentFragmentId);
        if (mCurrentFragmentId == R.id.nav_subscription) {
            Log.d(this.getClass().getName(), "current id is nav_subscription");
            MenuItem item = mActionMenu.add(Menu.NONE, R.id.subscription_new, Menu.NONE, "Refresh");
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    createContentFragment(item.getItemId());
                    return false;
                }
            });
            item.setIcon(R.drawable.ic_action_bar_add);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }

        ActionBarColorHelper.colorizeToolbar(mToolbar, Color.BLUE, this);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        createContentFragment(item.getItemId());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createContentFragment(int id) {
        setCurrentFragmentId(id);

        Fragment fragment;
        if (id == R.id.nav_all) {
            fragment = FeedListFragment.newInstance();
        } else if (id == R.id.nav_favorite) {
            fragment = FeedListFragment.newInstance();
        } else if (id == R.id.nav_subscription) {
            fragment = SubscriptionListFragment.newInstance();
        } else if (id == R.id.subscription_new) {
            fragment = SubscriptionNewFragment.newInstance();
        } else {
            fragment = FeedListFragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).addToBackStack(null).commit();
    }

    public void setCurrentFragmentId(int id) {
        if (mCurrentFragmentId != id) {
            Log.d(this.getClass().getName(), "invalidate options menu");
            mPreviousFragmentId = mCurrentFragmentId;
            mCurrentFragmentId = id;
            invalidateOptionsMenu();
        }
    }
}
