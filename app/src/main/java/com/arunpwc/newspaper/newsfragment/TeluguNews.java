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
public class TeluguNews extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int COLUMN_COUNT=2;

    public TeluguNews() {
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
        allItems.add(new NewsObject(getResources().getString(R.string.eenadu_telu), getResources().getString(R.string.eenadu_telu_url), R.drawable.eenadu_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewsObject(getResources().getString(R.string.andhranews_telu), getResources().getString(R.string.andhranews_telu_url), R.drawable.andhrabhoomi_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));
        allItems.add(new NewsObject(getResources().getString(R.string.andhraJyothi_telu), getResources().getString(R.string.andhraJyothi_telu_url), R.drawable.andhrajyothy_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewsObject(getResources().getString(R.string.surayaa_telu), getResources().getString(R.string.surayaa_telu_url), R.drawable.surya_news_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));
        allItems.add(new NewsObject(getResources().getString(R.string.andrhaprabha_telu), getResources().getString(R.string.andrhaprabha_telu_url), R.drawable.andhraprabha_icon, ContextCompat.getColor(getActivity(), R.color.tile5)));
        allItems.add(new NewsObject(getResources().getString(R.string.sakshi_telu), getResources().getString(R.string.sakshi_telu_url), R.drawable.sakshi_icon, ContextCompat.getColor(getActivity(), R.color.tile6)));
        allItems.add(new NewsObject(getResources().getString(R.string.prajasakti_telu), getResources().getString(R.string.prajasakti_telu_url), R.drawable.prajasakthi_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));


        return allItems;
    }

}
