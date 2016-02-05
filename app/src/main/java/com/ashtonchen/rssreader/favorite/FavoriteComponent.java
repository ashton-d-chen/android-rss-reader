package com.ashtonchen.rssreader.favorite;

import android.content.Context;

import com.ashtonchen.rssreader.base.DatabaseComponent;
import com.ashtonchen.rssreader.favorite.dao.FavoriteDAO;
import com.ashtonchen.rssreader.reader.model.Feed;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteComponent extends DatabaseComponent<FavoriteDAO, Feed> {

    public FavoriteComponent(Context context) {
        super(context);
    }

    @Override
    protected FavoriteDAO getDAO(Context context) {
        return new FavoriteDAO(context);
    }
}
