package com.arunpwc.newspaper.provider;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.arunpwc.newspaper.adapter.NewsAdapter;
import com.arunpwc.newspaper.model.NewsModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by aruns512 on 25/02/2017.
 */

public class DataProvider {
    private final String TAG = DataProvider.class.getSimpleName();
    private NewsAdapter newsAdapter;
    private NewsModel newsModel;
    private List<NewsModel> newsModelList;
    private RequestQueue requestQueue;
    private String title, description, imageURL, newsURL, pubDate;

    public DataProvider(NewsAdapter newsAdapter, List<NewsModel> newsModelList) {
        this.newsAdapter = newsAdapter;
        this.newsModelList = newsModelList;
    }

    public void xmlParser(String xmlResponse) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlResponse));
            int eventType = xmlPullParser.getEventType();

            boolean insideItem = false;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xmlPullParser.getName().equalsIgnoreCase("item")) {
                        newsModel = new NewsModel();
                        insideItem = true;
                    } else if (xmlPullParser.getName().equalsIgnoreCase("title")) {

                        if (insideItem) {
                            title = xmlPullParser.nextText();
                            newsModel.setTitle(title);
                        }
                    } else if (xmlPullParser.getName().equalsIgnoreCase("link")) {
                        if (insideItem) {
                            newsURL = xmlPullParser.nextText();
                            newsModel.setNewsURL(newsURL);
                        }
                    } else if (xmlPullParser.getName().equalsIgnoreCase("pubDate")) {
                        if (insideItem) {
                            pubDate = xmlPullParser.nextText();
                            newsModel.setPubDate(pubDate);
                        }
                    } else if (xmlPullParser.getName().equalsIgnoreCase("description")) {
                        if (insideItem) {
                            description = xmlPullParser.nextText();
                            Document doc = Jsoup.parse(description);
                            Elements img = doc.select("img");
                            String url = img.attr("src");
                            newsModel.setDescription(url);
                            newsModel.setImageURL(url);
                        }
                    }

                } else if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")) {
                    insideItem = false;
                    Log.e(TAG, "News model size" + newsModelList.size());
                    newsModelList.add(newsModel);
                    newsAdapter.notifyDataSetChanged();
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            Log.e(TAG, e.getMessage().toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage().toString());

        }
    }

    public String formatEncoding(String response) {
        try {
            byte[] u = response.toString().getBytes(
                    "ISO-8859-1");
            response = new String(u, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        return response;

    }
}
