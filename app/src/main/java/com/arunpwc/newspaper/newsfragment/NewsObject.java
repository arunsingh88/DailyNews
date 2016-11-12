package com.arunpwc.newspaper.newsfragment;

/**
 * Created by aruns512 on 10/11/2016.
 */
public class NewsObject {
    private String newsName;
    private String newsURL;
    private int newsLogo;
    private int tilesBgColor;

    public NewsObject(String newsName, String newsURL, int newsLogo, int tilesBgColor) {
        this.newsName = newsName;
        this.newsURL = newsURL;
        this.newsLogo=newsLogo;
        this.tilesBgColor=tilesBgColor;

    }
    public int getTilesBgColor() {
        return tilesBgColor;
    }

    public void setTilesBgColor(int tilesBgColor) {
        this.tilesBgColor = tilesBgColor;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public int getNewsLogo() {
        return newsLogo;
    }

    public void setNewsLogo(int newsLogo) {
        this.newsLogo = newsLogo;
    }
}
