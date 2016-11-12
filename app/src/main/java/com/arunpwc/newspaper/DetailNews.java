package com.arunpwc.newspaper;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.support.v7.widget.ShareActionProvider;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class DetailNews extends AppCompatActivity {

    private WebView mWebview;
    private String newsURL,newsTitle,currentURL;
    private String HASHTAG=" -via #DailyNewsApp";
    private ProgressBar progressBar;
    private Bundle bundle;
    private LinearLayout adViewTop;
    private AdView adView;
    private Intent sendIntent;
    private ShareActionProvider mShareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            newsURL= bundle.getString("NEWS_URL");
            newsTitle=bundle.getString("NEWS_TITLE");
        }
        currentURL=newsURL;

        mWebview = (WebView) findViewById(R.id.webView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        adView=(AdView)findViewById(R.id.adView);
        adViewTop=(LinearLayout)findViewById(R.id.adViewTop);
        adViewTop.bringToFront();

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-9708395996794900~7358033675");
        AdRequest adRequest=new AdRequest.Builder().addTestDevice("196FCE962C3DC7551A19FD25FC8543D0").build();
        adView.loadAd(adRequest);

        progressBar.setProgress(0);
        setTitle(newsTitle);

        mWebview.setWebViewClient(new NewsWebViewClient());
       // mShareActionProvider.setShareIntent(shareNews(currentURL));
        mWebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, final int progress)
            {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                    progressBar.setProgress(progress);
                }
            }
        });

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.loadUrl(newsURL);
        //mShareActionProvider.setShareIntent(shareNews(currentURL));
    }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate menu resource file.
       getMenuInflater().inflate(R.menu.detail_activity_menu, menu);
       // Locate MenuItem with ShareActionProvider
       MenuItem item = menu.findItem(R.id.share);
       // Fetch and store ShareActionProvider
       mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
       mShareActionProvider.setOnShareTargetSelectedListener(new ShareActionProvider.OnShareTargetSelectedListener() {
           @Override
           public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {
               return false;
           }
       });
       // Return true to display menu
       return true;
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
            case R.id.share:
               // shareNews(currentURL);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class NewsWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            currentURL=url;
            mShareActionProvider.setShareIntent(shareNews(currentURL));
            return true;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Intent shareNews(String currentURL)
    {
        sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentURL+HASHTAG);
        sendIntent.setType("text/plain");
        return sendIntent;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }



}
