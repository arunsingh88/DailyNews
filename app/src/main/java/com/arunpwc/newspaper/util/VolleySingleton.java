package com.arunpwc.newspaper.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by aruns512 on 24/02/2017.
 */

public class VolleySingleton {
    private static VolleySingleton getInstance;
    public static final String BASE_URL="https://news.google.co.in/news";
    private static Context mContext;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public static synchronized VolleySingleton getInstance(Context context) {
        if (getInstance == null) {
            getInstance = new VolleySingleton(context);
        }

        return getInstance;
    }

    private VolleySingleton(Context context) {
        mContext = context;
        requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);

            }
        });

    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }
}

