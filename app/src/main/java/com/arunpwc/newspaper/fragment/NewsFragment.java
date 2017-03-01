package com.arunpwc.newspaper.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.arunpwc.newspaper.R;
import com.arunpwc.newspaper.provider.DataProvider;
import com.arunpwc.newspaper.adapter.NewsAdapter;
import com.arunpwc.newspaper.model.NewsModel;
import com.arunpwc.newspaper.util.RecyclerViewDecoration;
import com.arunpwc.newspaper.util.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private NewsAdapter newsAdapter;
    private List<NewsModel> newsModelList=new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private RequestQueue requestQueue;
    private DataProvider dataProvider;
    private String topic;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String topic) {
        NewsFragment myFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("TOPIC", topic);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topic=getArguments().getString("TOPIC");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_news_layout, container, false);
        context=getActivity();
        newsAdapter = new NewsAdapter(newsModelList, getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerViewDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(newsAdapter);
        fetchGoogleNewsData();
        return rootView;
    }

    private void fetchGoogleNewsData() {
        dataProvider=new DataProvider(newsAdapter,newsModelList);
        String BASE_URL= VolleySingleton.BASE_URL;
        String LANGUAGE="hl";
        String LANGUAGE_REGION="ned";
        String TOPIC="topic";
        String OUTPUT="output";
        String NEWSCOUNT="num";

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(LANGUAGE, "en")
                .appendQueryParameter(LANGUAGE_REGION, "en_in")
                .appendQueryParameter(TOPIC, topic)
                .appendQueryParameter(OUTPUT, "rss")
                .appendQueryParameter(NEWSCOUNT,"30")
                .build();
        String newsurl=builtUri.toString();
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newsurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String encodedResponse=dataProvider.formatEncoding(response);
                        dataProvider.xmlParser(encodedResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue.add(stringRequest);
    }

}
