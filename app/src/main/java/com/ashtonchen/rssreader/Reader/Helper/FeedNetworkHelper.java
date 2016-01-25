package com.ashtonchen.rssreader.reader.helper;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.model.Channel;
import com.ashtonchen.rssreader.reader.model.Channels;
import com.ashtonchen.rssreader.reader.model.Feeds;
import com.ashtonchen.rssreader.reader.service.DownloadXmlTask;
import com.ashtonchen.rssreader.reader.service.NetworkReceiver;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class FeedNetworkHelper {

    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static final String SUCCESS = "success";

    // Whether the display should be refreshed.
    public static boolean refreshDisplay = true;
    // The user's current network preference setting.
    public static String sPref = null;
    // Whether there is a Wi-Fi connection.
    private static boolean wifiConnected = false;
    // Whether there is a mobile connection.
    private static boolean mobileConnected = false;
    private Context context;

    // The BroadcastReceiver that tracks network connectivity changes.
    private NetworkReceiver receiver;

    public FeedNetworkHelper(Context context) {
        this.context = context;
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver(this);
        this.context.registerReceiver(receiver, filter);
        // Gets the user's network preference settings
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.context);

        // Retrieves a string value for the preferences. The second parameter
        // is the default value to use if a preference value is not found.
        sPref = sharedPrefs.getString("listPref", "Wi-Fi");

        updateConnectedFlags();
    }

    private void updateConnectedFlags() {
        ConnectivityManager connMgr =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            Log.d("Network", "is connected");
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            Log.d("Network", "is not connected");
            wifiConnected = false;
            mobileConnected = false;
        }
    }

    public void getFeedList(FeedNetworkCallbackInterface callback) {
        Feeds.reset();
        for (Channel channel : Channels.getAll(this.context)) {
            new DownloadXmlTask(callback).execute(channel.getUrl());
        }
        /*
        if (((sPref.equals(ANY)) && (wifiConnected || mobileConnected))
                || ((sPref.equals(WIFI)) && (wifiConnected))) {
            Log.d("DownloadXmlTask()", "starts");
            new DownloadXmlTask().execute(URL);
        }
        */
    }

    public void getSubscriptionInfo(String url, FeedNetworkCallbackInterface callback) {
        new DownloadXmlTask(callback).execute(url);
    }
}
