package com.arunpwc.newspaper.model;

/**
 * Created by aruns512 on 24/02/2017.
 */

public class NewsModel {
    private String title;
    private String pubDate;
    private String description;
    private String newsURL;
    private String imageURL;

    /*public NewsModel(String title, String pubDate, String description, String newsURL, String imageURL) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.newsURL = newsURL;
        this.imageURL = imageURL;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
