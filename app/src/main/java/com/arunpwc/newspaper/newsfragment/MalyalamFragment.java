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


public class MalyalamFragment extends Fragment implements View.OnClickListener {

    private ImageView iManorama,iMatrubhumi,iDeshabhimani,iMadhyamam,iKerlakaumudi,iMangalam;
    private Intent intent;
    public MalyalamFragment() {
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
        View rootView=inflater.inflate(R.layout.fragment_malyalam, container, false);
        intent=new Intent(getActivity(),DetailNews.class);

        iManorama=(ImageView) rootView.findViewById(R.id.iManorama);
        iManorama.setOnClickListener(this);
        iMatrubhumi=(ImageView)rootView.findViewById(R.id.iMatrubhumi);
        iMatrubhumi.setOnClickListener(this);
        iDeshabhimani=(ImageView) rootView.findViewById(R.id.iDeshabhimani);
        iDeshabhimani.setOnClickListener(this);
        iMadhyamam=(ImageView)rootView.findViewById(R.id.iMadhyamam);
        iMadhyamam.setOnClickListener(this);
        iKerlakaumudi=(ImageView) rootView.findViewById(R.id.iKerlakaumudi);
        iKerlakaumudi.setOnClickListener(this);
        iMangalam=(ImageView)rootView.findViewById(R.id.iMangalam);
        iMangalam.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iManorama:
                intent.putExtra("NEWS_URL","http://www.manoramaonline.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.manorama_mal));
                startActivity(intent);
                // do your code
                break;

            case R.id.iMatrubhumi:
                intent.putExtra("NEWS_URL","http://www.mathrubhumi.com/mobile/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.matrubhumi_mal));
                startActivity(intent);
                // do your code
                break;
            case R.id.iDeshabhimani:
                intent.putExtra("NEWS_URL","http://www.deshabhimani.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.deshabhimani_mal));
                startActivity(intent);
                break;
            case R.id.iMadhyamam:
                intent.putExtra("NEWS_URL","http://www.madhyamam.com/en/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.madhyamam_mal));
                startActivity(intent);
                break;
            case R.id.iKerlakaumudi:
                intent.putExtra("NEWS_URL","http://news.keralakaumudi.com/beta/mobile/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.keralaKaumudi_mal));
                startActivity(intent);
                break;
            case R.id.iMangalam:
                intent.putExtra("NEWS_URL","http://www.mangalam.com/");
                intent.putExtra("NEWS_TITLE",getResources().getString(R.string.mangalam_mal));
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
