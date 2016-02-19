package com.ashtonchen.rssreader.network;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashton Chen on 16-02-18.
 */
public class DataRequest {
    private static final String URL = "https://app.goclio.com/api/v2/matters";
    private static final String AUTHORIZATION = "Bearer Xzd7LAtiZZ6HBBjx0DVRqalqN8yjvXgzY5qaD15a";
    private static final String CHARSET = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";
    private static final String ACCEPT = "application/json";
    private final Map<String, String> headers;
    Context mContext;
    RequestQueue mRequestQueue;
    Response.Listener<JSONObject> mCallback;

    public DataRequest(Context context, Response.Listener<JSONObject> callback) {
        mContext = context;
        mCallback = callback;

        Cache cache = new DiskBasedCache(mContext.getCacheDir(), 1024 * 1024); // 1MB cap

        Network network = new BasicNetwork(new HurlStack());

        mRequestQueue = new RequestQueue(cache, network);

        mRequestQueue.start();

        headers = new HashMap<String, String>();
        headers.put("Authorization", AUTHORIZATION);
        headers.put("Charset", CHARSET);
        headers.put("Content-Type", CONTENT_TYPE);
        headers.put("Accept", ACCEPT);
    }

    public void getMatterData() {

        DataJsonObjectRequest jsObjRequest = new DataJsonObjectRequest
                (Request.Method.GET, URL, mCallback, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, headers);
        mRequestQueue.add(jsObjRequest);
    }
}
