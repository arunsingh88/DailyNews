package com.arunpwc.newspaper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arunpwc.newspaper.R;
import com.arunpwc.newspaper.adapter.NewspaperAdapter;
import com.arunpwc.newspaper.model.NewspaperModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewspaperFragment extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView recyclerView;
    private NewspaperAdapter newspaperAdapter;
    private ArrayList<NewspaperModel> newspaperModels;
    private int COLUMN_COUNT=2;

    public NewspaperFragment() {
        // Required empty public constructor
    }

    public static NewspaperFragment getInstance(ArrayList<NewspaperModel> newspaperModels){

        NewspaperFragment myFragment = new NewspaperFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("newsPaperArrayList",newspaperModels);
        myFragment.setArguments(args);
        return myFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newspaperModels=getArguments().getParcelableArrayList("newsPaperArrayList");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        List<NewspaperModel> tamilNewsList =newspaperModels;

        lLayout = new GridLayoutManager(getActivity(), COLUMN_COUNT);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        newspaperAdapter = new NewspaperAdapter(getActivity(), tamilNewsList);
        recyclerView.setAdapter(newspaperAdapter);

        return rootView;
    }

    private List<NewspaperModel> getAllItemList() {
        List<NewspaperModel> allItems = new ArrayList<NewspaperModel>();
        allItems.add(new NewspaperModel(getResources().getString(R.string.toi_en), getResources().getString(R.string.toi_en_url), R.drawable.toi_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.hindu_en), getResources().getString(R.string.hindu_en_url), R.drawable.hindu_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.indian_express_en), getResources().getString(R.string.indian_express_en_url), R.drawable.indian_express_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.timesnow_en), getResources().getString(R.string.timesnow_en_url), R.drawable.times_now_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.indiatoday_en), getResources().getString(R.string.indiatoday_en_url), R.drawable.india_today_icon, ContextCompat.getColor(getActivity(), R.color.tile5)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.ndtv_en), getResources().getString(R.string.ndtv_en_url), R.drawable.ndtv247_icon, ContextCompat.getColor(getActivity(), R.color.tile6)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.ibncnn_en), getResources().getString(R.string.ibncnn_en_url), R.drawable.cnn_ibn_icon, ContextCompat.getColor(getActivity(), R.color.tile1)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.hindustan_en), getResources().getString(R.string.hindustan_en_url), R.drawable.hindustan_times_icon, ContextCompat.getColor(getActivity(), R.color.tile2)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.economicTimes_en), getResources().getString(R.string.economicTimes_en_url), R.drawable.ecnomic_times_icon, ContextCompat.getColor(getActivity(), R.color.tile3)));
        allItems.add(new NewspaperModel(getResources().getString(R.string.first_post_en), getResources().getString(R.string.first_post_en_url), R.drawable.firstpost_icon, ContextCompat.getColor(getActivity(), R.color.tile4)));

        return allItems;
    }

}
