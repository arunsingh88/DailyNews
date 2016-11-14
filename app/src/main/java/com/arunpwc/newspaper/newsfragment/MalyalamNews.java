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
public class MalyalamNews extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int COLUMN_COUNT=2;

    public MalyalamNews() {
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
        allItems.add(new NewsObject(getResources().getString(R.string.manorama_mal), getResources().getString(R.string.manorama_mal_url), R.drawable.manorma_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewsObject(getResources().getString(R.string.matrubhumi_mal), getResources().getString(R.string.matrubhumi_mal_url), R.drawable.mathrubhumi_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));
        allItems.add(new NewsObject(getResources().getString(R.string.deshabhimani_mal), getResources().getString(R.string.deshabhimani_mal_url), R.drawable.deshabhimani_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewsObject(getResources().getString(R.string.madhyamam_mal), getResources().getString(R.string.madhyamam_mal_url), R.drawable.madhaym_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));
        allItems.add(new NewsObject(getResources().getString(R.string.keralaKaumudi_mal), getResources().getString(R.string.keralaKaumudi_mal_url), R.drawable.keralakamudi_icon, ContextCompat.getColor(getActivity(), R.color.tile5)));
        allItems.add(new NewsObject(getResources().getString(R.string.mangalam_mal), getResources().getString(R.string.mangalam_mal_url), R.drawable.mangalam_icon, ContextCompat.getColor(getActivity(), R.color.tile6)));

        return allItems;
    }

}
