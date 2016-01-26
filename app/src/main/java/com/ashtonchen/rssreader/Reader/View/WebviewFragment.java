package com.ashtonchen.rssreader.reader.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ashtonchen.rssreader.base.BaseFragment;
import com.ashtonchen.rssreader.R;

/**
 * Created by Ashton Chen on 16-01-22.
 */
public class WebviewFragment extends BaseFragment {

    private static final String ARG_URL = "url";

    private String currentURL;

    public static Fragment newInstance(String url) {
        Fragment fragment = new WebviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        currentURL = getArguments().getString(ARG_URL);
        Log.d("SwA", "WVF onCreateView");
        View view = inflater.inflate(R.layout.webview_layout, container, false);
        if (currentURL != null) {
            Log.d("SwA", "Current URL  1[" + currentURL + "]");

            WebView webView = (WebView) view.findViewById(R.id.webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(currentURL);

        }
        return view;
    }


/*    public void updateUrl(String url) {
        Log.d("SwA", "Update URL [" + url + "] - View [" + getView() + "]");
        currentURL = url;

        WebView wv = (WebView) getView().findViewById(R.id.webPage);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);

    }*/

    private class SwAWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

    }

}