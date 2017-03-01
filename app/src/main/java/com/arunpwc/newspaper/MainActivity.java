package com.arunpwc.newspaper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.arunpwc.newspaper.firebase.Config;
import com.arunpwc.newspaper.firebase.NotificationUtils;
import com.arunpwc.newspaper.fragment.NewsFragment;
import com.arunpwc.newspaper.fragment.NewspaperFragment;
import com.arunpwc.newspaper.provider.NewspaperData;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout relativeLayout;
    private AdView adView;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static final String TAG = MainActivity.class.getSimpleName();
    private AdRequest adRequest;
    private PopupWindow popupWindow;
    private Fragment headlines, city, sports, entertainment, technology, business, health, world, science;

   /* private int REFRESH_RATE_IN_SECONDS = 5;
    private final Handler refreshHandler = new Handler();
    private final Runnable refreshRunnable = new RefreshRunnable();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        relativeLayout = (LinearLayout) findViewById(R.id.main_activity);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        adView = (AdView) findViewById(R.id.adViewActivity);
        adView.setVisibility(View.GONE);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, getResources().getString(R.string.admob_app_id));
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                adView.setVisibility(View.GONE);
                // adView.loadAd(adRequest);
                /*refreshHandler.removeCallbacks(refreshRunnable);
                refreshHandler.postDelayed(refreshRunnable, REFRESH_RATE_IN_SECONDS * 1000);*/
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);

            }
        });

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    String message = intent.getStringExtra("message");
                }
            }
        };
    }


    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            Toast.makeText(getApplicationContext(), "Firebase Reg Id: " + regId, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Firebase Reg Id not recvd", Toast.LENGTH_LONG).show();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        NewspaperData newspaperData = new NewspaperData(this);

        adapter.addFragment(NewspaperFragment.getInstance(newspaperData.getAllItemList("hindi")), getResources().getString(R.string.hindi));
        adapter.addFragment(NewspaperFragment.getInstance(newspaperData.getAllItemList("english")), getResources().getString(R.string.english));
        adapter.addFragment(NewspaperFragment.getInstance(newspaperData.getAllItemList("tamil")), getResources().getString(R.string.tamil));
        adapter.addFragment(NewspaperFragment.getInstance(newspaperData.getAllItemList("kannada")), getResources().getString(R.string.kannada));
        adapter.addFragment(NewspaperFragment.getInstance(newspaperData.getAllItemList("malayalam")), getResources().getString(R.string.malayalam));
        adapter.addFragment(NewspaperFragment.getInstance(newspaperData.getAllItemList("telugu")), getResources().getString(R.string.telugu));


        headlines = NewsFragment.newInstance("n");
        city = NewsFragment.newInstance("n");
        sports = NewsFragment.newInstance("s");
        entertainment = NewsFragment.newInstance("e");
        business = NewsFragment.newInstance("b");
        world = NewsFragment.newInstance("w");
        health = NewsFragment.newInstance("m");
        science = NewsFragment.newInstance("snc");
        technology = NewsFragment.newInstance("tc");


        adapter.addFragment(headlines, getResources().getString(R.string.headlines));
        adapter.addFragment(city, getResources().getString(R.string.city));
        adapter.addFragment(sports, getResources().getString(R.string.sports));
        adapter.addFragment(world, getResources().getString(R.string.world));
        adapter.addFragment(business, getResources().getString(R.string.business));
        adapter.addFragment(science, getResources().getString(R.string.science));
        adapter.addFragment(technology, getResources().getString(R.string.technology));
        adapter.addFragment(entertainment, getResources().getString(R.string.entertainment));
        adapter.addFragment(health, getResources().getString(R.string.health));
        //viewPager.setPageTransformer(false, new FadePageTransformer());
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_rate:
                rateMe();
                break;
            case R.id.action_close:
                MainActivity.this.finish();
                break;
            // action with ID action_settings was selected
            case R.id.action_check_updates:
                aboutApp();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
        /*Request for the Ad*/
        if (adView != null) {
            adView.resume();
        }

    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public class FadePageTransformer implements ViewPager.PageTransformer {
        public void transformPage(View view, float position) {
            final float normalizedposition = Math.abs(Math.abs(position) - 1);
            view.setScaleX(normalizedposition / 2 + 0.5f);
            view.setScaleY(normalizedposition / 2 + 0.5f);
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    public void rateMe() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }

    /*Description about app in popup window*/
    public void aboutApp() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.about_app, null);
        // Initialize a new instance of popup window
        popupWindow = new PopupWindow(
                customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

        TextView textView = (TextView) customView.findViewById(R.id.tv);
        textView.setText(Html.fromHtml(getResources().getString(R.string.about_app_desc)));
        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                popupWindow.dismiss();
            }
        });

        // Closes the popup window when touch outside.
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // Removes default background.
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
    }


    /*private class RefreshRunnable implements Runnable {
        @Override
        public void run() {
            adView.loadAd(adRequest);
        }
    }*/
}
