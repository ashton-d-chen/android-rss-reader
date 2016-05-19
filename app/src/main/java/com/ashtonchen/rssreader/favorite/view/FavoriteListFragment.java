package com.ashtonchen.rssreader.favorite.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.MasterDetailFeedListFragment;
import com.ashtonchen.rssreader.favorite.FavoriteComponent;
import com.ashtonchen.rssreader.favorite.view.adapter.FavoriteViewAdapter;

/**
 * Created by Ashton Chen on 16-01-25.
 */
public class FavoriteListFragment extends MasterDetailFeedListFragment<FavoriteViewAdapter, FavoriteComponent> {

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getSubtitle();
    }

    @Override
    protected FavoriteComponent getComponent() {
        return new FavoriteComponent(mContext);
    }

    @Override
    protected FavoriteViewAdapter getAdapter() {
        return new FavoriteViewAdapter(mList);
    }

    @Override
    protected View.OnLongClickListener getOnLongClickListener() {
        return new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                final int position = mRecyclerView.getChildAdapterPosition(v);
                int result = mComponent.removeData(position);
                if (result > 0) {
                    mList.remove(position);
                    mAdapter.notifyItemRemoved(position);
                    mAdapter.notifyItemRangeChanged(position, mList.size());
                    Toast.makeText(mContext, R.string.toast_removed_from_favorite, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
    }

    @Override
    protected String getEmptyViewMessage() {
        return getString(R.string.list_empty_list_message_no_favorite);
    }

    protected String getSubtitle() {
        return getString(R.string.action_bar_subtitle_favorites);
    }

    protected void retrieveDataForDatabase() {
        // This fragment can only retrieve data from database, do nothing
    }
}
