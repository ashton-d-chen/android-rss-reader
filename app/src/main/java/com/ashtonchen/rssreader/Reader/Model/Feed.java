package com.ashtonchen.rssreader.Reader.Model;

/**
 * Created by ashtonchen on 15-12-09.
 */
public class Feed {
    private String webTitle;
    private String webThumbnailURL;
    private String webDescription;
    private String title;
    private String description;
    private String url;
    private String thumbnailURL;
    private String pubDate;

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
}
