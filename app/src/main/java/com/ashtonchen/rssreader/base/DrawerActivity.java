package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.ashtonchen.rssreader.R;

/**
 * Created by Ashton Chen on 16-02-03.
 */
public abstract class DrawerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawerLayout drawer = (DrawerLayout) findViewById(getDrawerLayout());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                getToolBar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(getNavigationView());
        navigationView.setNavigationItemSelectedListener(getOnNavigationItemSelectedListener());
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

    protected NavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                displayFragment(getContentFragment(item.getItemId()));
                return true;
            }
        };
    }

    protected int getDrawerLayout() {
        return R.id.drawer_layout;
    }

    protected int getNavigationView() {
        return R.id.nav_view;
    }

    protected abstract Fragment getContentFragment(int id);
}
