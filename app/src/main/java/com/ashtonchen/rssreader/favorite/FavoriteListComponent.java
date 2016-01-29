package com.ashtonchen.rssreader.favorite;

import android.content.Context;

import com.ashtonchen.rssreader.base.ListDatabaseComponent;
import com.ashtonchen.rssreader.favorite.dao.FavoriteDAO;
import com.ashtonchen.rssreader.reader.model.Feed;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteListComponent extends ListDatabaseComponent<FavoriteDAO, Feed> {

    public FavoriteListComponent(Context context) {
        super(context);
    }

    @Override
    protected FavoriteDAO getListDAO() {
        return new FavoriteDAO(mContext);
    }
}
