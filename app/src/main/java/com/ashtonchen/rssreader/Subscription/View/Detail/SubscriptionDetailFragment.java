package com.ashtonchen.rssreader.subscription.view.detail;

import android.graphics.Typeface;
import android.os.Bundle;
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
public class SubscriptionDetailFragment extends DetailFragment {
    private Channel mChannel;

    public static SubscriptionDetailFragment newInstance(Channel channel) {
        SubscriptionDetailFragment fragment = new SubscriptionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Channel.ARG_CHANNEL, channel);
        fragment.setArguments(bundle);
        return fragment;
    }

    public SubscriptionDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChannel = getArguments().getParcelable(Channel.ARG_CHANNEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subscription_detail, container, false);
        if (mChannel != null) {
            int cellPadding = (int) (StyleSheet.CELL_PADDING * mScale + 0.5f);
            rootView.setPadding(cellPadding, cellPadding, cellPadding, cellPadding);

            TextView title = (TextView) rootView.findViewById(R.id.subscription_detail_title);
            title.setText(mChannel.getTitle());
            title.setTextSize(StyleSheet.DETAIL_TITLE_FONT_SIZE);
            title.setTypeface(null, Typeface.BOLD);

            TextView description = (TextView) rootView.findViewById(R.id.subscription_detail_description);
            description.setText(mChannel.getDescription());
            description.setTextSize(StyleSheet.DETAIL_DESCRIPTION_FONT_SIZE);
        }
        return rootView;
    }
}
