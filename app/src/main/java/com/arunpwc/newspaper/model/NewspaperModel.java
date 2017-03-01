package com.arunpwc.newspaper.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aruns512 on 10/11/2016.
 */
public class NewspaperModel implements Parcelable {
    private String newsName;
    private String newsURL;
    private int newsLogo;
    private int tilesBgColor;

    public NewspaperModel(String newsName, String newsURL, int newsLogo, int tilesBgColor) {
        this.newsName = newsName;
        this.newsURL = newsURL;
        this.newsLogo=newsLogo;
        this.tilesBgColor=tilesBgColor;

    }
    public static final Parcelable.Creator<NewspaperModel> CREATOR
            = new Parcelable.Creator<NewspaperModel>() {
        public NewspaperModel createFromParcel(Parcel in) {
            return new NewspaperModel(in);
        }

        public NewspaperModel[] newArray(int size) {
            return new NewspaperModel[size];
        }
    };
    public NewspaperModel(Parcel in)
    {
        super();
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        newsName=in.readString();
        newsURL=in.readString();
        newsLogo=in.readInt();
        tilesBgColor=in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newsURL);
        dest.writeString(newsName);
        dest.writeInt(newsLogo);
        dest.writeInt(tilesBgColor);
    }
}
