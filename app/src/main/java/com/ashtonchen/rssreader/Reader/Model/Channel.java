package com.ashtonchen.rssreader.Reader.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class Channel {
    private String title;
    private String url;
    private String description;
    private String thumbnailURL;
    private List<Feed> feeds;

    public Channel() {
        feeds = new ArrayList();
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
