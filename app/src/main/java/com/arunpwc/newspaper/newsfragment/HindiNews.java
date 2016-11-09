package com.arunpwc.newspaper.newsfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arunpwc.newspaper.DetailNews;
import com.arunpwc.newspaper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HindiNews extends Fragment implements View.OnClickListener {

    //private RelativeLayout toiBtn,hinduBtn,firstpostBtn,indianexpressBtn;
    private ImageView dainikJagran,dainikBhaskar,amarUjala,hindustan,navbharatTimes,rajasthanPatrika;
    private Intent intent;
    public HindiNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_hindi_news, container, false);
        intent=new Intent(getActivity(),DetailNews.class);


        dainikJagran=(ImageView) rootView.findViewById(R.id.dainikJagran);
        dainikJagran.setOnClickListener(this);
        dainikBhaskar=(ImageView)rootView.findViewById(R.id.dainikBhaskar);
        dainikBhaskar.setOnClickListener(this);
        amarUjala=(ImageView) rootView.findViewById(R.id.amarUjala);
        amarUjala.setOnClickListener(this);
        hindustan=(ImageView)rootView.findViewById(R.id.hindustan);
        hindustan.setOnClickListener(this);
        navbharatTimes=(ImageView) rootView.findViewById(R.id.navbharatTimes);
        navbharatTimes.setOnClickListener(this);
        rajasthanPatrika=(ImageView)rootView.findViewById(R.id.rajasthanPatrika);
        rajasthanPatrika.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dainikJagran:
                intent.putExtra("NEWS_URL","http://www.jagran.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.dainik_jagran_hi));
                startActivity(intent);
                // do your code
                break;

            case R.id.dainikBhaskar:
                intent.putExtra("NEWS_URL","http://m.bhaskar.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.dainik_bhaskar_hi));
                startActivity(intent);
                // do your code
                break;
            case R.id.hindustan:
                intent.putExtra("NEWS_URL","http://m.livehindustan.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.hindustan_hi));
                startActivity(intent);
                break;
            case R.id.amarUjala:
                intent.putExtra("NEWS_URL","http://www.amarujala.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.amar_ujala_hi));
                startActivity(intent);
                break;
            case R.id.navbharatTimes:
                intent.putExtra("NEWS_URL","http://m.navbharattimes.indiatimes.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.navbahrat_times_hi));
                startActivity(intent);
                break;
            case R.id.rajasthanPatrika:
                intent.putExtra("NEWS_URL","http://m.rajasthanpatrika.patrika.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.rajastahn_patrika));
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
