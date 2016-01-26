package com.ashtonchen.rssreader.favorite.view.adapter;

import com.ashtonchen.rssreader.base.RSSRecyclerViewAdapter;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.view.widget.FeedViewHolder;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteViewAdapter extends RSSRecyclerViewAdapter {

    protected List<Feed> getData() {
        return Feeds.getFeeds();
    }
}
