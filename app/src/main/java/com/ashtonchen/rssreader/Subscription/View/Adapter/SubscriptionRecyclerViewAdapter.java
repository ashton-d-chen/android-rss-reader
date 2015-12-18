package com.ashtonchen.rssreader.Subscription.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtonchen.rssreader.BaseRecyclerViewAdapter;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Subscription.Interface.SubscriptionNetworkCallbackInterface;
import com.ashtonchen.rssreader.Subscription.Interface.onSubscriptionListInteractionListener;
import com.ashtonchen.rssreader.Subscription.Model.Subscription;
import com.ashtonchen.rssreader.Subscription.View.Widget.SubscriptionViewHolder;
import com.ashtonchen.rssreader.Subscription.dummy.Subscriptions;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Subscription} and makes a call to the
 * specified {@link onSubscriptionListInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SubscriptionRecyclerViewAdapter extends BaseRecyclerViewAdapter<SubscriptionViewHolder> {

    private final SubscriptionNetworkCallbackInterface mListener;

    public SubscriptionRecyclerViewAdapter(Context context, SubscriptionNetworkCallbackInterface listener) {
        mListener = listener;
    }

    @Override
    public SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_list_cell, parent, false);
        return new SubscriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubscriptionViewHolder holder, int position) {
        holder.mItem = Subscriptions.get(position);
        holder.mTitleView.setText(Subscriptions.get(position).getTitle());
        holder.mDescriptionView.setText(Subscriptions.get(position).getDescription());

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
    }

    @Override
    public int getItemCount() {
        return Subscriptions.getSize();
    }
}
