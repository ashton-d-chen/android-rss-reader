package com.ashtonchen.rssreader.Reader.Service;

import android.os.AsyncTask;
import android.util.Log;

import com.ashtonchen.rssreader.Reader.Callback.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.Reader.Model.Channel;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class DownloadXmlTask extends AsyncTask<String, Void, String> {
    Channel channel;
    FeedNetworkCallbackInterface callback;

    public DownloadXmlTask(FeedNetworkCallbackInterface callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... urls) {
        Log.d("background task", "started");
        try {
            return loadXmlFromNetwork(urls[0]);
        } catch (IOException e) {
            return ""; // getResources().getString(R.string.connection_error);
        } catch (XmlPullParserException e) {
            return ""; // getResources().getString(R.string.xml_error);
        }
    }

    @Override
    protected void onPostExecute(String result) {
        this.callback.onDownloadFinished(this.channel);
    }

    // Uploads XML from stackoverflow.com, parses it, and combines it with
    // HTML markup. Returns HTML string.
    private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        FeedXMLParser parser = new FeedXMLParser();

        // Checks whether the user set the preference to include summary text
        //SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //boolean pref = sharedPrefs.getBoolean("summaryPref", false);
        Log.d("url", urlString);
        try {
            stream = downloadUrl(urlString);
            this.channel = parser.parse(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return ""; //htmlString.toString();
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
