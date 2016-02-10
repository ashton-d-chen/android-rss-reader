package com.ashtonchen.rssreader.reader.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.BaseFragment;

/**
 * Created by Ashton Chen on 16-01-22.
 */
public class WebViewFragment extends BaseFragment {
    private static final String ARG_URL = "url";
    private String mCurrentURL;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentURL = getArguments().getString(ARG_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.webview_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) view;
        WebView webView = new WebView(mContext);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mCurrentURL);
        rootView.addView(webView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAppLayoutExpandable(true);
    }

    protected String getSubtitle() {
        return getString(R.string.action_bar_subtitle_website);
    }

    protected boolean shouldDisplayDrawerIcon() {
        return false;
    }
}