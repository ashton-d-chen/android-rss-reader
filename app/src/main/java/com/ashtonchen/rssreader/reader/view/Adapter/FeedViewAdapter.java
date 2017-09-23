package com.ashtonchen.rssreader.reader.view.adapter;

import com.ashtonchen.rssreader.base.RSSRecyclerViewAdapter;
import com.ashtonchen.rssreader.reader.model.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 16-01-26.
 */
public class FeedViewAdapter extends RSSRecyclerViewAdapter {

    public FeedViewAdapter() {
        super(new ArrayList<Feed>());
    }

    public FeedViewAdapter(List<Feed> list) {
        super(list);
    }
}
