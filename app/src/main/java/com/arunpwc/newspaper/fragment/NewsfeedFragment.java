package com.arunpwc.newspaper.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
public class NewsfeedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private NewsAdapter newsAdapter;
    private List<NewsModel> newsModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private RequestQueue requestQueue;
    private DataProvider dataProvider;
    private String topic;
    private String LANG;
    private Uri builtURI;
    private String REGION, CITY = "";
    private SwipeRefreshLayout swipeRefreshLayout;
    private final String NEWS_COUNT = "30";
    private final String TAG = NewsfeedFragment.class.getSimpleName();

    public NewsfeedFragment() {
        // Required empty public constructor
    }

    public static NewsfeedFragment newInstance(String topic, String language, String region, String city) {
        NewsfeedFragment myFragment = new NewsfeedFragment();
        Bundle args = new Bundle();
        args.putString("TOPIC", topic);
        args.putString("LANG", language);
        args.putString("REGION", region);
        args.putString("CITY", city);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topic = getArguments().getString("TOPIC");
        LANG = getArguments().getString("LANG");
        REGION = getArguments().getString("REGION");
        CITY = getArguments().getString("CITY");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        context = getActivity();
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        newsAdapter = new NewsAdapter(newsModelList, context);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerViewDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(newsAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        fetchGoogleNewsData();
        return rootView;
    }

    private void fetchGoogleNewsData() {
        dataProvider = new DataProvider(newsAdapter, newsModelList);
        String BASE_URL = VolleySingleton.BASE_URL;
        String LANGUAGE = "hl";
        String LANGUAGE_REGION = "ned";
        String TOPIC = "topic";
        String OUTPUT = "output";
        String NEWSCOUNT = "num";
        String GEO = "geo";

        if (CITY.equalsIgnoreCase("")) {
            builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(LANGUAGE, LANG)
                    .appendQueryParameter(LANGUAGE_REGION, LANG + "_" + REGION)
                    .appendQueryParameter(TOPIC, topic)
                    .appendQueryParameter(OUTPUT, "rss")
                    .appendQueryParameter(NEWSCOUNT, NEWS_COUNT)
                    .build();
        } else {
            builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(LANGUAGE, LANG)
                    .appendQueryParameter(LANGUAGE_REGION, LANG + "_" + REGION)
                    .appendQueryParameter(OUTPUT, "rss")
                    .appendQueryParameter(NEWSCOUNT, NEWS_COUNT)
                    .appendQueryParameter(GEO, CITY)
                    .build();
        }
        String newsurl = builtURI.toString();
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newsurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String encodedResponse = dataProvider.formatEncoding(response);
                        dataProvider.xmlParser(encodedResponse);
                        if (swipeRefreshLayout != null) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (swipeRefreshLayout != null) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });

        stringRequest.setTag(TAG);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onRefresh() {
        requestQueue.cancelAll(TAG);
        fetchGoogleNewsData();
    }

    @Override
    public void onStop() {
        requestQueue.cancelAll(TAG);
        super.onStop();
    }
}
