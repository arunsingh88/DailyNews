package com.arunpwc.newspaper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.arunpwc.newspaper.R;
import com.arunpwc.newspaper.adapter.LanguageAdapter;
import com.arunpwc.newspaper.model.LanguageModel;
import com.arunpwc.newspaper.util.RecyclerViewDecoration;

import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class LanguageSetting extends AppCompatActivity {

    private LanguageAdapter languageAdapter;
    private RecyclerView recyclerView;
    private ArrayList<LanguageModel> languageList = new ArrayList<LanguageModel>();
    private SharedPreferences sharedPreferences;
    private Button button;
    private Spinner countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);
        sharedPreferences = getDefaultSharedPreferences(this);

        button = (Button) findViewById(R.id.next);
        countryList = (Spinner) findViewById(R.id.spinnerLang);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.country_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryList.setAdapter(adapter);
        countryList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (sharedPreferences.getInt("SELECTED_POSITION", -1) == -1) {
            button.setEnabled(false);
        }

        languageAdapter = new LanguageAdapter(languageList, this, new LanguageAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(String item, int position) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("LANGUAGE", item);
                editor.putInt("SELECTED_POSITION", position);
                editor.commit();
                button.setEnabled(true);
                Toast.makeText(LanguageSetting.this, "Language Selected: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemUncheck(String item) {
                button.setEnabled(false);
                Toast.makeText(LanguageSetting.this, "Please select atleast one language", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerViewDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(languageAdapter);

        fetchLanguage();
    }

    private void fetchLanguage() {

        languageList.add(new LanguageModel("English", "en"));
        languageList.add(new LanguageModel("Hindi", "hi"));
        languageList.add(new LanguageModel("Tamil", "ta"));
        languageList.add(new LanguageModel("Telugu", "te"));
        languageList.add(new LanguageModel("Malayalam", "ml"));

        languageAdapter.notifyDataSetChanged();
    }

    public void prepareNewsFeed(View view) {
        Intent intent = new Intent(LanguageSetting.this, MainActivity.class);
        startActivity(intent);
    }
}
