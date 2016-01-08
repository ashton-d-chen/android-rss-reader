package com.ashtonchen.rssreader.Subscription.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.BaseRecyclerViewAdapter;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Subscription.Interface.SubscriptionNetworkCallbackInterface;
import com.ashtonchen.rssreader.Subscription.Interface.onSubscriptionListInteractionListener;
import com.ashtonchen.rssreader.Subscription.Model.Subscription;
import com.ashtonchen.rssreader.Subscription.SubscriptionComponent;
import com.ashtonchen.rssreader.Subscription.View.Widget.SubscriptionViewHolder;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Subscription} and makes a call to the
 * specified {@link onSubscriptionListInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SubscriptionRecyclerViewAdapter extends BaseRecyclerViewAdapter<SubscriptionViewHolder> {

    private final SubscriptionNetworkCallbackInterface mListener;
    private final List<Subscription> mList;
    private final SubscriptionComponent mSubscriptionComponent;

    public SubscriptionRecyclerViewAdapter(Context context, SubscriptionNetworkCallbackInterface listener, SubscriptionComponent component) {
        mListener = listener;
        mSubscriptionComponent = component;
        mList = mSubscriptionComponent.getSubscriptions();
    }

    @Override
    public SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_list_cell, parent, false);
        return new SubscriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubscriptionViewHolder holder, int position) {
        Log.d(this.getClass().getName(), "adapter subscription: " + position);

        holder.mTitleView.setText(mList.get(position).getUrl());
        holder.mDescriptionView.setText(mList.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListItemClicked(holder);
                }
            }
        });

        holder.mView.setOnLongClickListener(getOnLongClickListener(mList.get(position)));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private View.OnLongClickListener getOnLongClickListener(final Subscription subscription) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mSubscriptionComponent.removeSubscriptoin(subscription);
                return false;
            }
        };
    }
}