package com.ashtonchen.rssreader.Reader.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Adapter.FeedViewAdapter;
import com.ashtonchen.rssreader.Reader.Callback.FeedListCallback;
import com.ashtonchen.rssreader.Reader.Model.Channel;
import com.ashtonchen.rssreader.Reader.ReaderComponent;
import com.ashtonchen.rssreader.Reader.Callback.FeedNetworkCallbackInterface;

/**
 * An activity representing a list of Feeds. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link FeedDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class FeedListActivity extends AppCompatActivity implements FeedNetworkCallbackInterface, FeedListCallback {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private ReaderComponent mReaderComponent;
    private FeedViewAdapter mFeedViewAdapter;
    private RecyclerView recyclerView;
    private Channel mChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mReaderComponent = new ReaderComponent(this);
        this.mReaderComponent.getFeedList(this);
        setContentView(R.layout.activity_feed_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mChannel = new Channel();

        recyclerView = (RecyclerView) findViewById(R.id.feed_list);
        assert recyclerView != null;
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

    private void setupRecyclerView() {
        mFeedViewAdapter = new FeedViewAdapter(mChannel, this);
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
