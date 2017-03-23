package com.arunpwc.newspaper.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.arunpwc.newspaper.R;
import com.arunpwc.newspaper.model.LanguageModel;

import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by aruns512 on 02/03/2017.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private ArrayList<LanguageModel> languageList;
    private SharedPreferences sharedPreferences;
    private Context context;
    private LanguageViewHolder holder;
    private CheckBox selectedCheckBox = null;
    private OnItemCheckListener onItemCheckListener;
    private int selected_position;

    public interface OnItemCheckListener {
        void onItemCheck(String item,int selectedPosition);

        void onItemUncheck(String item);
    }

    public LanguageAdapter(ArrayList<LanguageModel> languageList, Context context, OnItemCheckListener onItemCheckListener) {
        this.languageList = languageList;
        this.onItemCheckListener = onItemCheckListener;
        this.context = context;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View languageView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.langauge_checkbox_item, parent, false);
        return new LanguageViewHolder(languageView);
    }

    @Override
    public void onBindViewHolder(LanguageViewHolder holder, final int position) {

        sharedPreferences=getDefaultSharedPreferences(context);
        selected_position=sharedPreferences.getInt("SELECTED_POSITION",-1);
        holder.textView.setText(languageList.get(position).getDisplayLangauge());
        if (selected_position == position) {
            holder.checkBox.setChecked(true);

        } else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    selected_position = position;
                    onItemCheckListener.onItemCheck(languageList.get(position).getLangValue(),position);
                } else {
                    selected_position = -1;
                    onItemCheckListener.onItemUncheck(languageList.get(position).getLangValue());

                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CheckBox checkBox;

        public LanguageViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.langText);
            checkBox = (CheckBox) view.findViewById(R.id.langCheck);
        }

    }
}
