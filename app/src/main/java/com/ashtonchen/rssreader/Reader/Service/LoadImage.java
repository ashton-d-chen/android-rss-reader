package com.ashtonchen.rssreader.Reader.Service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.ashtonchen.rssreader.Reader.Interface.ImageDownloadCallback;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by ashtonchen on 15-12-10.
 */
public class LoadImage extends AsyncTask<String, String, Bitmap> {
    Bitmap bitmap;
    ImageDownloadCallback mCallback;

    public LoadImage(ImageDownloadCallback callback) {
        mCallback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //pDialog = new ProgressDialog(MainActivity.this);
        //pDialog.setMessage("Loading Image ....");
        //pDialog.show();

    }

    protected Bitmap doInBackground(String... args) {
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap image) {

        if (image != null) {
            //img.setImageBitmap(image);
            //pDialog.dismiss();
            mCallback.onDownloadFinished(image);
        } else {

            // pDialog.dismiss();
            //Toast.makeText(MainActivity.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

        }
    }
}
