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
public class TamilNews extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int COLUMN_COUNT=2;

    public TamilNews() {
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
        allItems.add(new NewsObject(getResources().getString(R.string.paperkaran_tam), getResources().getString(R.string.paperkaran_tam_url), R.drawable.paperkaran_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewsObject(getResources().getString(R.string.dinathanthi_tam), getResources().getString(R.string.dinathanthi_tam_url), R.drawable.dinathathi_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));
        allItems.add(new NewsObject(getResources().getString(R.string.vivegam_tam), getResources().getString(R.string.vivegam_tam_url), R.drawable.vivegamnews_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewsObject(getResources().getString(R.string.tamilhindu_tam), getResources().getString(R.string.tamilhindu_tam_url), R.drawable.thehindhutamil_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));
        allItems.add(new NewsObject(getResources().getString(R.string.dinakaran_tam), getResources().getString(R.string.dinakaran_tam_url), R.drawable.dinakaran_icon, ContextCompat.getColor(getActivity(), R.color.tile5)));
        allItems.add(new NewsObject(getResources().getString(R.string.dinamalar_tam), getResources().getString(R.string.dinamalar_tam_url), R.drawable.dinanmalar_icon, ContextCompat.getColor(getActivity(), R.color.tile6)));
        allItems.add(new NewsObject(getResources().getString(R.string.dinamani_tam), getResources().getString(R.string.dinamani_tam_url), R.drawable.dinamani_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewsObject(getResources().getString(R.string.dinaanchal_tam), getResources().getString(R.string.dinaanchal_tam_url), R.drawable.dinachaal_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));

        return allItems;
    }

}