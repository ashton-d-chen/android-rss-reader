package com.ashtonchen.rssreader.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.DrawerActivity;
import com.ashtonchen.rssreader.favorite.view.FavoriteListFragment;
import com.ashtonchen.rssreader.reader.view.FeedListFragment;
import com.ashtonchen.rssreader.subscription.view.SubscriptionListFragment;

public class MainActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("RSS Reader");
        displayFragment(getContentFragment(R.id.nav_all));
    }

    protected Fragment getContentFragment(int id) {
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
       return fragment;
    }
}
