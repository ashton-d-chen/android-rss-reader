package com.ashtonchen.rssreader.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Adapter.FeedViewAdapter;
import com.ashtonchen.rssreader.Reader.Callback.FeedListCallback;
import com.ashtonchen.rssreader.Reader.Callback.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.Reader.Cell.DecoratedItemRecyclerView;
import com.ashtonchen.rssreader.Reader.Model.Channel;
import com.ashtonchen.rssreader.Reader.ReaderComponent;
import com.ashtonchen.rssreader.Reader.View.FeedDetailActivity;
import com.ashtonchen.rssreader.Reader.View.FeedDetailFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FeedNetworkCallbackInterface, FeedListCallback {

    private boolean mTwoPane;

    private ReaderComponent mReaderComponent;
    private FeedViewAdapter mFeedViewAdapter;
    private RecyclerView recyclerView;
    private Channel mChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Master/detail view
        this.mReaderComponent = new ReaderComponent(this);
        this.mReaderComponent.getFeedList(this);
        //setContentView(R.layout.activity_feed_list);


        mChannel = new Channel();

        recyclerView = (RecyclerView) findViewById(R.id.feed_list);

        assert recyclerView != null;

        recyclerView.addItemDecoration(new DecoratedItemRecyclerView(30));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.feed_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            // Handle the camera action
        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupRecyclerView() {
        mFeedViewAdapter = new FeedViewAdapter(this, mChannel, this);
        recyclerView.setAdapter(mFeedViewAdapter);
    }

    public void onDownloadFinished(Channel channel) {
        mChannel = channel;
        setupRecyclerView();
        //mFeedViewAdapter.setData(mChannel);
    }

    public void onFeedItemClick(View v, String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(FeedDetailFragment.ARG_ITEM_ID, id);
            FeedDetailFragment fragment = new FeedDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.feed_detail_container, fragment)
                    .commit();
        } else {
            Context context = v.getContext();
            Intent intent = new Intent(context, FeedDetailActivity.class);
            intent.putExtra(FeedDetailFragment.ARG_ITEM_ID, id);

            context.startActivity(intent);
        }
    }
}
