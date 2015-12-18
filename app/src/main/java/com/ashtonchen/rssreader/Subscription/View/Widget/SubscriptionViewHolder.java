package com.ashtonchen.rssreader.Subscription.View.Widget;

import android.view.View;
import android.widget.TextView;

import com.ashtonchen.rssreader.BaseRecyclerViewHolder;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Subscription.Model.Subscription;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionViewHolder extends BaseRecyclerViewHolder {
    public final View mView;
    public final TextView mTitleView;
    public final TextView mDescriptionView;
    public Subscription mItem;

    public SubscriptionViewHolder(View view) {
        super(view);
        mView = view;
        mTitleView = (TextView) view.findViewById(R.id.title);
        mDescriptionView = (TextView) view.findViewById(R.id.description);
    }

    @Override
    public String toString() {
        return ""; //super.toString() + " '" + mContentView.getText() + "'";
    }
}