package com.arunpwc.newspaper.newsfragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arunpwc.newspaper.DetailNews;
import com.arunpwc.newspaper.R;


public class EnglishFragment extends Fragment implements View.OnClickListener {

    private ImageView toi,theHindu,firstpost,indianexpress,hindustanTimes,thEconomicTimes;
    private Intent intent;
    public EnglishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_english, container, false);
        intent=new Intent(getActivity(),DetailNews.class);

        toi=(ImageView) rootView.findViewById(R.id.toi);
        toi.setOnClickListener(this);
        theHindu=(ImageView)rootView.findViewById(R.id.theHindu);
        theHindu.setOnClickListener(this);
        firstpost=(ImageView) rootView.findViewById(R.id.firstpost);
        firstpost.setOnClickListener(this);
        indianexpress=(ImageView)rootView.findViewById(R.id.indianexpress);
        indianexpress.setOnClickListener(this);
        hindustanTimes=(ImageView) rootView.findViewById(R.id.hindustanTimes);
        hindustanTimes.setOnClickListener(this);
        thEconomicTimes=(ImageView)rootView.findViewById(R.id.thEconomicTimes);
        thEconomicTimes.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toi:
                intent.putExtra("NEWS_URL","http://m.timesofindia.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.toi_en));
                startActivity(intent);
                // do your code
                break;

            case R.id.theHindu:
                intent.putExtra("NEWS_URL","http://m.thehindu.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.hindu_en));
                startActivity(intent);
                // do your code
                break;
            case R.id.indianexpress:
                intent.putExtra("NEWS_URL","http://indianexpress.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.indian_express_en));
                startActivity(intent);
                break;
            case R.id.hindustanTimes:
                intent.putExtra("NEWS_URL","http://m.hindustantimes.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.hindustan_en));
                startActivity(intent);
                break;
            case R.id.firstpost:
                intent.putExtra("NEWS_URL","http://m.firstpost.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.first_post_en));
                startActivity(intent);
                break;
            case R.id.thEconomicTimes:
                intent.putExtra("NEWS_URL","http://m.economictimes.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.economicTimes_en));
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
