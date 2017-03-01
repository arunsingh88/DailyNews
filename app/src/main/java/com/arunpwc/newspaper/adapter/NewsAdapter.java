package com.arunpwc.newspaper.adapter;

/**
 * Created by aruns512 on 25/02/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.arunpwc.newspaper.DetailNews;
import com.arunpwc.newspaper.R;
import com.arunpwc.newspaper.model.NewsModel;
import com.arunpwc.newspaper.util.VolleySingleton;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.arunpwc.newspaper.R.mipmap.ic_launcher;

/**
 * Created by aruns512 on 24/02/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {


    private List<NewsModel> newsModelList;
    private Context mContext;
    private ImageLoader imageLoader;

    public NewsAdapter(List<NewsModel> newsModelList,Context mContext) {
        this.newsModelList = newsModelList;
        this.mContext=mContext;
        imageLoader= VolleySingleton.getInstance(mContext).getImageLoader();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_news_item,parent,false);
        return new MyViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsModel newsModel=newsModelList.get(position);
        String imageURL="http:"+newsModel.getImageURL();
        holder.title.setText(newsModel.getTitle());
        holder.pubDate.setText(newsModel.getPubDate());
        Picasso.with(mContext)
                .load(imageURL)
                .placeholder(ic_launcher)
                .error(ic_launcher)
                .into(holder.imageView);

      /*  holder.imageView.setAlpha(0f);
        holder.imageView.setImageUrl("http:"+newsModel.getImageURL(),imageLoader);
        holder.imageView.setDefaultImageResId(ic_launcher);
        holder.imageView.setErrorImageResId(ic_launcher);
        holder.imageView.animate().alpha(1f).setDuration(1000);*/

    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView title, description,pubDate;
        public ImageView imageView;
        public String newsURL;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            pubDate = (TextView) view.findViewById(R.id.pubDate);
            imageView=(ImageView)view.findViewById(R.id.imageView);
            title.setOnClickListener(this);
            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DetailNews.class);
            newsURL = newsModelList.get(getLayoutPosition()).getNewsURL();
            intent.putExtra("NEWS_URL", newsURL);
            //intent.putExtra("NEWS_TITLE", itemList.get(getLayoutPosition()).getNewsName());
            mContext.startActivity(intent);
        }
    }

}

