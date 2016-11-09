package com.arunpwc.newspaper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class DetailNews extends AppCompatActivity {

    private WebView mWebview;
    private String newsURL,newsTitle,currentURL;
    private ProgressBar progressBar;
    private Bundle bundle;
    private LinearLayout adViewTop;
    private AdView adView;
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
        mWebview.setWebViewClient(new NewsWebViewClient());
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.loadUrl(newsURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
            case R.id.action_share:
                shareNews(currentURL);
                return true;
            case R.id.action_settings:
                shareNews(currentURL);
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
            return true;
        }
    }
    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void shareNews(String currentURL)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentURL+" -via DailyNews app");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,newsTitle));
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
