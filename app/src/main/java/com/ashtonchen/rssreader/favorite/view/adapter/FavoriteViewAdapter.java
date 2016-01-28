package com.ashtonchen.rssreader.favorite.view.adapter;

import com.ashtonchen.rssreader.base.RSSRecyclerViewAdapter;
import com.ashtonchen.rssreader.reader.model.Feed;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteViewAdapter extends RSSRecyclerViewAdapter {

    public FavoriteViewAdapter(List<Feed> list) {
        super(list);
    }
}
