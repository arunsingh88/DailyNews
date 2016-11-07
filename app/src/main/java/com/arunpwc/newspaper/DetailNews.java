package com.arunpwc.newspaper;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailNews extends AppCompatActivity {

    private WebView mWebview;
    private String newsURL;
    private ProgressDialog progressBar;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mWebview=(WebView)findViewById(R.id.webView);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            newsURL= bundle.getString("NEWS_URL");
        }
        mWebview  = new WebView(this);
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        progressBar = ProgressDialog.show(DetailNews.this, "NEWS DETAILS", "Loading...");

        mWebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }
        });
        mWebview .loadUrl(newsURL);
        setContentView(mWebview );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
