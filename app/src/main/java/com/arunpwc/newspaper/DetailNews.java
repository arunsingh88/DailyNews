package com.arunpwc.newspaper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.support.v7.widget.ShareActionProvider;
import android.widget.SeekBar;
import android.widget.TextView;

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
    private TextView textView;
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
        //textView=(TextView)findViewById(R.id.textView);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-9708395996794900~7358033675");
        AdRequest adRequest=new AdRequest.Builder().addTestDevice("196FCE962C3DC7551A19FD25FC8543D0").build();
        adView.loadAd(adRequest);

        progressBar.setProgress(0);
        setTitle(newsTitle);

        mWebview.setWebViewClient(new NewsWebViewClient());
       /* WebSettings settings = mWebview.getSettings();
        settings.setTextZoom(settings.getTextZoom() + 20);*/
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
        /*Alert Dialog Box*/
        //callDialog();
    }

    public void callDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailNews.this);
        LayoutInflater inflater = DetailNews.this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.text_font_size, (ViewGroup) findViewById(R.id.linearSeekBar));
        builder.setView(inflater.inflate(R.layout.text_font_size, null))
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        builder.setTitle("TEXT SIZE");
        builder.create();
        builder.show();

        SeekBar sb = (SeekBar)layout.findViewById(R.id.seekBar);
        textView=(TextView)layout.findViewById(R.id.textView) ;
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                //Do something here with new value
                //textView.setText(progress);
                textView.setText("Value of : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
        currentURL=newsURL;
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
