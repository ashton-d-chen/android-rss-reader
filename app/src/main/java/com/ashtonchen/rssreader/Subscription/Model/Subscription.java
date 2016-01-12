package com.ashtonchen.rssreader.Subscription.Model;

/**
 * Created by Ashton Chen on 15-12-14.
 */
public class Subscription {

    private String title = "";
    private String description = "";
    private String url = "";
    private String thumbnailURL = "";

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
}
