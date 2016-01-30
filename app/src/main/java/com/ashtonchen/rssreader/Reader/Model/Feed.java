package com.ashtonchen.rssreader.reader.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ashton Chen on 15-12-09.
 */
public class Feed implements Comparable<Feed> {
    private String webTitle = "";
    private String webThumbnailURL = "";
    private String webDescription = "";
    private String title = "";
    private String description = "";
    private String url = "";
    private String thumbnailURL = "";
    private String pubDate = "";

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebThumbnailURL() {
        return webThumbnailURL;
    }

    public void setWebThumbnailURL(String webThumbnailURL) {
            this.webThumbnailURL = webThumbnailURL;
    }

    public String getWebDescription() {
        return webDescription;
    }

    public void setWebDescription(String webDescription) {
        this.webDescription = webDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public int compareTo(Feed another) {
        Log.d(this.getClass().getName(), "first date time: " + this.getPubDate());
        Log.d(this.getClass().getName(), "second date time: " + another.getPubDate());

        return convertDate(another.getPubDate()).compareTo(convertDate(this.getPubDate()));
    }

    private String convertDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        long timeInMilliseconds = 0;
        try {
            Date mDate = sdf.parse(date);
            timeInMilliseconds = mDate.getTime();
            //System.out.println("Date in milli :: " + timeInMilliseconds);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        return String.valueOf(timeInMilliseconds);
    }
}
