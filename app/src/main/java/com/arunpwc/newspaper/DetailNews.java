package com.arunpwc.newspaper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class DetailNews extends AppCompatActivity {

    private WebView mWebview;
    private String newsURL, newsTitle, currentURL;
    private String HASHTAG = " -via " + "market://details?id=" + this.getPackageName();
    private ProgressBar progressBar;
    private Bundle bundle;
    private LinearLayout adViewTop;
    private AdView adView;
    private Intent sendIntent;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadNewsPaper();
        bundle = getIntent().getExtras();
        if (bundle != null) {
            newsURL = bundle.getString("NEWS_URL");
            newsTitle = bundle.getString("NEWS_TITLE");
        }
        /*Setting the current news url*/
        currentURL = newsURL;

        mWebview = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        adView = (AdView) findViewById(R.id.adView);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                adView.setVisibility(View.GONE);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                adView.setVisibility(View.VISIBLE);
            }
        });
        adViewTop = (LinearLayout) findViewById(R.id.adViewTop);
        adViewTop.bringToFront();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                shareNews(currentURL);
            }
        });

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-9708395996794900~7358033675");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("196FCE962C3DC7551A19FD25FC8543D0").build();
        adView.loadAd(adRequest);

        progressBar.setProgress(0);
        setTitle(newsTitle);
        mWebview.setWebViewClient(new NewsWebViewClient());
        mWebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, final int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setProgress(progress);
                }
            }
        });

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.loadUrl(newsURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.detail_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
            case R.id.action_refresh:
                mWebview.reload();
                return true;
            case R.id.action_zoom:
                zoomWebView();
                return true;
            case R.id.action_about:
                rateMe();
                return true;
            case R.id.action_check_updates:
                rateMe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class NewsWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            currentURL = url;
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

    private void shareNews(String currentURL) {
        sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentURL + HASHTAG);
        sendIntent.setType("text/plain");
        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        currentURL = newsURL;
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

    /*Dialog for Asking permission*/
    private void showDialogOK(String message, String title, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton(getString(R.string.dialog_OK), okListener)
                .create()
                .show();
    }

    /*Start the Activity */
    public void loadNewsPaper() {
        if (!checkNetworkStatus()) {
            showDialogOK(getString(R.string.error_msg), getString(R.string.app_name), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
    }

    public boolean checkNetworkStatus() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public void zoomWebView() {
        WebSettings settings = mWebview.getSettings();
        settings.setTextZoom(settings.getTextZoom() + 20);
    }

    public void rateMe() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }


}
