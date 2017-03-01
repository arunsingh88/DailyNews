package com.arunpwc.newspaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arunpwc.newspaper.DetailNews;
import com.arunpwc.newspaper.R;
import com.arunpwc.newspaper.model.NewspaperModel;

import java.util.List;

/**
 * Created by aruns512 on 10/11/2016.
 */

public class NewspaperAdapter extends RecyclerView.Adapter<NewspaperAdapter.RecyclerViewHolders> {

    private List<NewspaperModel> itemList;
    private Context context;
    private String newsURL;
    private Intent intent;

    public NewspaperAdapter(Context context, List<NewspaperModel> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView, context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.newsName.setText(itemList.get(position).getNewsName());
        holder.newsLogo.setImageResource(itemList.get(position).getNewsLogo());
        holder.cardView.setCardBackgroundColor(itemList.get(position).getTilesBgColor());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView newsName;
        public ImageView newsLogo;
        public CardView cardView;
        public RelativeLayout relativeLayout;
        private Context context;

        public RecyclerViewHolders(View itemView, Context context
        ) {
            super(itemView);
            newsName = (TextView) itemView.findViewById(R.id.news_name);
            newsLogo = (ImageView) itemView.findViewById(R.id.news_logo);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.card_view_relative);
            this.context = context;
            itemView.setOnClickListener(this);
            relativeLayout.setOnClickListener(this);
            newsLogo.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            intent = new Intent(context, DetailNews.class);
            newsURL = itemList.get(getLayoutPosition()).getNewsURL();
            intent.putExtra("NEWS_URL", newsURL);
            intent.putExtra("NEWS_TITLE", itemList.get(getLayoutPosition()).getNewsName());
            context.startActivity(intent);
        }
    }
}
