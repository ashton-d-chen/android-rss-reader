package com.ashtonchen.rssreader.subscription.view.detail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.StyleSheet;
import com.ashtonchen.rssreader.base.DetailFragment;
import com.ashtonchen.rssreader.subscription.model.Channel;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionDetailFragment extends DetailFragment<Channel> {

    public SubscriptionDetailFragment() {

    }

    public static Fragment newInstance(Channel channel, boolean twoPane) {
        Fragment fragment = new SubscriptionDetailFragment();
        fragment = DetailFragment.newInstance(fragment, twoPane);
        fragment.getArguments().putParcelable(Channel.ARG_CHANNEL, channel);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getArguments().getParcelable(Channel.ARG_CHANNEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.subscription_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mData != null) {
            int cellPadding = (int) (StyleSheet.PADDING * mScale + 0.5f);
            view.setPadding(cellPadding, cellPadding, cellPadding, cellPadding);

            TextView title = (TextView) view.findViewById(R.id.subscription_detail_title);
            title.setText(mData.getTitle());
            title.setTextSize(StyleSheet.DETAIL_TITLE_FONT_SIZE);
            title.setTypeface(null, Typeface.BOLD);

            TextView description = (TextView) view.findViewById(R.id.subscription_detail_description);
            description.setText(mData.getDescription());
            description.setTextSize(StyleSheet.DETAIL_DESCRIPTION_FONT_SIZE);
        }
    }

    protected String getSubtitle() {
        return getString(R.string.action_bar_subtitle_subscription_detail);
    }
}
