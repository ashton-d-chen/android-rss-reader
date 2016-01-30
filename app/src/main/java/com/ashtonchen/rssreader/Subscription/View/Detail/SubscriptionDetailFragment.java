package com.ashtonchen.rssreader.subscription.view.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.DetailFragment;
import com.ashtonchen.rssreader.reader.model.Channel;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class SubscriptionDetailFragment extends DetailFragment {
    private Channel mChannel;

    public static SubscriptionDetailFragment newInstance(Channel channel) {
        SubscriptionDetailFragment fragment = new SubscriptionDetailFragment();
        fragment.mChannel = channel;
        return fragment;
    }

    public SubscriptionDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subscription_detail, container, false);
        if (mChannel != null) {
            ((TextView) rootView.findViewById(R.id.subscription_detail_title)).setText(mChannel.getTitle());
            ((TextView) rootView.findViewById(R.id.subscription_detail_description)).setText(mChannel.getDescription());
        }
        return rootView;
    }
}
