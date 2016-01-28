package com.ashtonchen.rssreader.favorite;

import android.content.Context;

import com.ashtonchen.rssreader.base.BaseComponent;
import com.ashtonchen.rssreader.favorite.dao.FavoriteDAO;
import com.ashtonchen.rssreader.reader.model.Feed;

import java.util.List;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteComponent extends BaseComponent {
    private FavoriteDAO mFavoriteDAO;

    public FavoriteComponent(Context context) {
        super(context);
        mFavoriteDAO = new FavoriteDAO(context);
    }


    public List<Feed> getFavorites() {
        return mFavoriteDAO.getAllItems();
    }

}
