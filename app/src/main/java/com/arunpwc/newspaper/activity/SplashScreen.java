package com.arunpwc.newspaper.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.arunpwc.newspaper.R;
import com.arunpwc.newspaper.util.TrackGPS;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class SplashScreen extends AppCompatActivity {

    private ImageView imageView;
    private int SPLASH_TIME_OUT = 2000;
    private int PERMISSION_CHECK = 1212;
    private Location location;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getDefaultSharedPreferences(this);

        /*Request for Location permission*/
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_CHECK);
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView) findViewById(R.id.imageView);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(SPLASH_TIME_OUT);
        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        imageView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                location = new TrackGPS(SplashScreen.this).getLocation();
                getCityName(location, new OnGeocoderFinishedListener() {
                    @Override
                    public void onFinished(List<Address> results) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("CITY", results.get(0).getLocality() + "," + results.get(0).getAdminArea());
                        editor.putString("COUNTRY_CODE", results.get(0).getCountryCode().toLowerCase());
                        Toast.makeText(SplashScreen.this, results.get(0).getLocality() + "," + results.get(0).getAdminArea(), Toast.LENGTH_LONG).show();
                        editor.commit();

                        Intent intent = new Intent(SplashScreen.this, LanguageSetting.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, SPLASH_TIME_OUT);

        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.progressBar);
        circularProgressBar.setProgressWithAnimation(100, SPLASH_TIME_OUT);

    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void getCityName(final Location location, final OnGeocoderFinishedListener listener) {
        new AsyncTask<Void, Integer, List<Address>>() {
            @Override
            protected List<Address> doInBackground(Void... arg0) {
                Geocoder coder = new Geocoder(SplashScreen.this, Locale.ENGLISH);
                List<Address> results = null;
                try {
                    results = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                } catch (IOException e) {
                    // nothing
                }
                return results;
            }

            @Override
            protected void onPostExecute(List<Address> results) {
                if (results != null && listener != null) {
                    listener.onFinished(results);
                }
            }
        }.execute();
    }


    public abstract class OnGeocoderFinishedListener {
        public abstract void onFinished(List<Address> results);
    }
}
