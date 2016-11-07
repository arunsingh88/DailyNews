package com.arunpwc.newspaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton toiBtn,hinduBtn,firstpostBtn,indianexpressBtn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent=new Intent(MainActivity.this,DetailNews.class);

        /*Define View*/
        toiBtn=(ImageButton)findViewById(R.id.toi);
        toiBtn.setOnClickListener(this);
        hinduBtn=(ImageButton)findViewById(R.id.hindu);
        hinduBtn.setOnClickListener(this);
        firstpostBtn=(ImageButton)findViewById(R.id.firstPost);
        firstpostBtn.setOnClickListener(this);
        indianexpressBtn=(ImageButton)findViewById(R.id.indianExpress);
        indianexpressBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.toi:
                // do your code
                intent.putExtra("NEWS_URL","http://timesofindia.indiatimes.com/");
                startActivity(intent);
                break;

            case R.id.hindu:
                intent.putExtra("NEWS_URL","http://newsite.thehindu.com/");
                startActivity(intent);
                // do your code
                break;

            case R.id.firstPost:
                intent.putExtra("NEWS_URL","http://timesofindia.indiatimes.com/");
                startActivity(intent);
                // do your code
                break;
            case R.id.indianExpress:
                intent.putExtra("NEWS_URL","http://timesofindia.indiatimes.com/");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
