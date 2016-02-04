package com.ashtonchen.rssreader.reader.service;

import android.os.AsyncTask;
import android.util.Log;

import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.subscription.model.Channel;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ashton Chen on 15-12-09.
 */
public class DownloadXmlTask extends AsyncTask<String, Void, Channel> {
    FeedNetworkCallbackInterface callback;

    public DownloadXmlTask(FeedNetworkCallbackInterface callback) {
        this.callback = callback;
    }

    @Override
    protected Channel doInBackground(String... urls) {
        Log.d("background task", "started");
        try {
            return loadXmlFromNetwork(urls[0]);
        } catch (IOException e) {
            return null; // getResources().getString(R.string.connection_error);
        } catch (XmlPullParserException e) {
            return null; // getResources().getString(R.string.xml_error);
        }
    }

    @Override
    protected void onPostExecute(Channel result) {
        this.callback.onDownloadFinished(result);
    }

    private Channel loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        FeedXMLParser parser = new FeedXMLParser();

        // Checks whether the user set the preference to include summary text
        //SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //boolean pref = sharedPrefs.getBoolean("summaryPref", false);
        Log.d("url", urlString);
        try {
            stream = downloadUrl(urlString);
            return parser.parse(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        //return null; //htmlString.toString();
    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }
}
