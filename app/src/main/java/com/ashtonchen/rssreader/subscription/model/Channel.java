package com.ashtonchen.rssreader.subscription.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ashtonchen.rssreader.reader.model.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashton Chen on 15-12-09.
 */
public class Channel implements Parcelable {
    public static final String ARG_CHANNEL = "argumentChannel";
    private String title = "";
    private String url = "";
    private String webURL = "";
    private String description = "";
    private String thumbnailURL = "";
    private List<Feed> feeds;

    public Channel() {
        feeds = new ArrayList();
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        public Channel createFromParcel(Parcel in) {
            return new Channel(in);
        }

        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };

    public Channel(Parcel in) {
        title = in.readString();
        description = in.readString();
        url = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(thumbnailURL);
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUrl() {
        return webURL;
    }

    public void setWebUrl(String url) {
        this.webURL = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
