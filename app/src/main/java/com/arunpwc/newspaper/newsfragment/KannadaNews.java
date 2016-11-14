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
public class KannadaNews extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int COLUMN_COUNT=2;

    public KannadaNews() {
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
        allItems.add(new NewsObject(getResources().getString(R.string.kannadaprabha_kan), getResources().getString(R.string.kannadaprabha_kan_url), R.drawable.kannadaprabha_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewsObject(getResources().getString(R.string.vijayakaranatka_kan), getResources().getString(R.string.vijayakaranatka_kan_url), R.drawable.vijaya_karanatka_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));
        allItems.add(new NewsObject(getResources().getString(R.string.vijayavani_kan), getResources().getString(R.string.vijayavani_kan_url), R.drawable.vijayvani_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewsObject(getResources().getString(R.string.prajavani_kan), getResources().getString(R.string.prajavani_kan_url), R.drawable.prajavani_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));
        allItems.add(new NewsObject(getResources().getString(R.string.udayavani_kan), getResources().getString(R.string.udayavani_kan_url), R.drawable.udayavani_icon, ContextCompat.getColor(getActivity(), R.color.tile5)));
        allItems.add(new NewsObject(getResources().getString(R.string.tv9kannada_kan), getResources().getString(R.string.tv9kannada_kan_url), R.drawable.tv9kannada_icon, ContextCompat.getColor(getActivity(), R.color.tile6)));
        allItems.add(new NewsObject(getResources().getString(R.string.btvnews_kan), getResources().getString(R.string.btvnews_kan_url), R.drawable.btvnews_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewsObject(getResources().getString(R.string.publictv_kan), getResources().getString(R.string.publictv_kan_url), R.drawable.publictv_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));


        return allItems;
    }

}
