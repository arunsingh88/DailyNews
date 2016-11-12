package com.arunpwc.newspaper.newsfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arunpwc.newspaper.R;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HindiNews extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int COLUMN_COUNT=2;

    public HindiNews() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        List<NewsObject> tamilNewsList = getAllItemList();

        lLayout = new GridLayoutManager(getActivity(), COLUMN_COUNT);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), tamilNewsList);
        recyclerView.setAdapter(recyclerViewAdapter);

        return rootView;
    }

    private List<NewsObject> getAllItemList() {
        List<NewsObject> allItems = new ArrayList<NewsObject>();
        allItems.add(new NewsObject(getResources().getString(R.string.dainik_jagran_hi), getResources().getString(R.string.dainik_jagran_hi_url), R.drawable.dainik_jagran_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewsObject(getResources().getString(R.string.dainik_bhaskar_hi), getResources().getString(R.string.dainik_bhaskar_hi_url), R.drawable.dainik_bhaskar_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));
        allItems.add(new NewsObject(getResources().getString(R.string.amar_ujala_hi), getResources().getString(R.string.amar_ujala_hi_url), R.drawable.amar_ujala_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewsObject(getResources().getString(R.string.hindustan_hi), getResources().getString(R.string.hindustan_hi_url), R.drawable.hindustan_hindi_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));
        allItems.add(new NewsObject(getResources().getString(R.string.navbahrat_times_hi), getResources().getString(R.string.navbahrat_times_hi_url), R.drawable.navbharat_times_icon, ContextCompat.getColor(getActivity(), R.color.tile5)));
        allItems.add(new NewsObject(getResources().getString(R.string.rajastahn_patrika), getResources().getString(R.string.rajastahn_patrika_url), R.drawable.rajasthan_patrika_icon, ContextCompat.getColor(getActivity(), R.color.tile6)));
        allItems.add(new NewsObject(getResources().getString(R.string.aajtak_hi), getResources().getString(R.string.aajtak_hi_url), R.drawable.aajtak_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewsObject(getResources().getString(R.string.ndtv_hi), getResources().getString(R.string.ndtv_hi_url), R.drawable.ndtvindi_hi_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));
        allItems.add(new NewsObject(getResources().getString(R.string.abp_hi), getResources().getString(R.string.abp_hi_url), R.drawable.abpnews_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewsObject(getResources().getString(R.string.zeenews_hi), getResources().getString(R.string.zeenews_hi_url), R.drawable.zeenews_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));

        return allItems;
    }

}
