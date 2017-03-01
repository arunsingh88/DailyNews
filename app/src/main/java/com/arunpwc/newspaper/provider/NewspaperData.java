package com.arunpwc.newspaper.provider;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.arunpwc.newspaper.R;
import com.arunpwc.newspaper.model.NewspaperModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aruns512 on 01/03/2017.
 */

public class NewspaperData {
    private Context context;
    private ArrayList<NewspaperModel> allItems;

    public NewspaperData(Context context) {
        this.context = context;
    }

    public ArrayList<NewspaperModel> getAllItemList(String language) {

        allItems = new ArrayList<NewspaperModel>();
        if (language.equalsIgnoreCase("english")) {
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.toi_en), context.getResources().getString(R.string.toi_en_url), R.drawable.toi_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.hindu_en), context.getResources().getString(R.string.hindu_en_url), R.drawable.hindu_icon, ContextCompat.getColor(context, R.color.tile2)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.indian_express_en), context.getResources().getString(R.string.indian_express_en_url), R.drawable.indian_express_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.timesnow_en), context.getResources().getString(R.string.timesnow_en_url), R.drawable.times_now_icon, ContextCompat.getColor(context, R.color.tile4)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.indiatoday_en), context.getResources().getString(R.string.indiatoday_en_url), R.drawable.india_today_icon, ContextCompat.getColor(context, R.color.tile5)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.ndtv_en), context.getResources().getString(R.string.ndtv_en_url), R.drawable.ndtv247_icon, ContextCompat.getColor(context, R.color.tile6)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.ibncnn_en), context.getResources().getString(R.string.ibncnn_en_url), R.drawable.cnn_ibn_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.hindustan_en), context.getResources().getString(R.string.hindustan_en_url), R.drawable.hindustan_times_icon, ContextCompat.getColor(context, R.color.tile2)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.economicTimes_en), context.getResources().getString(R.string.economicTimes_en_url), R.drawable.ecnomic_times_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.first_post_en), context.getResources().getString(R.string.first_post_en_url), R.drawable.firstpost_icon, ContextCompat.getColor(context, R.color.tile4)));

        } else if (language.equalsIgnoreCase("hindi")) {
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.dainik_jagran_hi), context.getResources().getString(R.string.dainik_jagran_hi_url), R.drawable.dainik_jagran_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.dainik_bhaskar_hi), context.getResources().getString(R.string.dainik_bhaskar_hi_url), R.drawable.dainik_bhaskar_icon, ContextCompat.getColor(context, R.color.tile2)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.amar_ujala_hi), context.getResources().getString(R.string.amar_ujala_hi_url), R.drawable.amar_ujala_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.hindustan_hi), context.getResources().getString(R.string.hindustan_hi_url), R.drawable.hindustan_hindi_icon, ContextCompat.getColor(context, R.color.tile4)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.navbahrat_times_hi), context.getResources().getString(R.string.navbahrat_times_hi_url), R.drawable.navbharat_times_icon, ContextCompat.getColor(context, R.color.tile5)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.rajastahn_patrika), context.getResources().getString(R.string.rajastahn_patrika_url), R.drawable.rajasthan_patrika_icon, ContextCompat.getColor(context, R.color.tile6)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.aajtak_hi), context.getResources().getString(R.string.aajtak_hi_url), R.drawable.aajtak_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.ndtv_hi), context.getResources().getString(R.string.ndtv_hi_url), R.drawable.ndtvindi_hi_icon, ContextCompat.getColor(context, R.color.tile2)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.abp_hi), context.getResources().getString(R.string.abp_hi_url), R.drawable.abpnews_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.zeenews_hi), context.getResources().getString(R.string.zeenews_hi_url), R.drawable.zeenews_icon, ContextCompat.getColor(context, R.color.tile4)));

        } else if (language.equalsIgnoreCase("tamil")) {
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.dinathanthi_tam), context.getResources().getString(R.string.dinathanthi_tam_url), R.drawable.dinathathi_icon, ContextCompat.getColor(context, R.color.tile2)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.vivegam_tam), context.getResources().getString(R.string.vivegam_tam_url), R.drawable.vivegamnews_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.tamilhindu_tam), context.getResources().getString(R.string.tamilhindu_tam_url), R.drawable.thehindhutamil_icon, ContextCompat.getColor(context, R.color.tile4)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.dinakaran_tam), context.getResources().getString(R.string.dinakaran_tam_url), R.drawable.dinakaran_icon, ContextCompat.getColor(context, R.color.tile5)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.dinamalar_tam), context.getResources().getString(R.string.dinamalar_tam_url), R.drawable.dinanmalar_icon, ContextCompat.getColor(context, R.color.tile6)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.dinamani_tam), context.getResources().getString(R.string.dinamani_tam_url), R.drawable.dinamani_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.dinaanchal_tam), context.getResources().getString(R.string.dinaanchal_tam_url), R.drawable.dinachaal_icon, ContextCompat.getColor(context, R.color.tile4)));
        } else if (language.equalsIgnoreCase("telugu")) {
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.eenadu_telu), context.getResources().getString(R.string.eenadu_telu_url), R.drawable.eenadu_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.andhranews_telu), context.getResources().getString(R.string.andhranews_telu_url), R.drawable.andhrabhoomi_icon, ContextCompat.getColor(context, R.color.tile2)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.andhraJyothi_telu), context.getResources().getString(R.string.andhraJyothi_telu_url), R.drawable.andhrajyothy_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.surayaa_telu), context.getResources().getString(R.string.surayaa_telu_url), R.drawable.surya_news_icon, ContextCompat.getColor(context, R.color.tile4)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.andrhaprabha_telu), context.getResources().getString(R.string.andrhaprabha_telu_url), R.drawable.andhraprabha_icon, ContextCompat.getColor(context, R.color.tile5)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.prajasakti_telu), context.getResources().getString(R.string.prajasakti_telu_url), R.drawable.prajasakthi_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.sakshi_telu), context.getResources().getString(R.string.sakshi_telu_url), R.drawable.sakshi_icon, ContextCompat.getColor(context, R.color.tile6)));

        } else if (language.equalsIgnoreCase("kannada")) {
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.kannadaprabha_kan), context.getResources().getString(R.string.kannadaprabha_kan_url), R.drawable.kannadaprabha_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.vijayakaranatka_kan), context.getResources().getString(R.string.vijayakaranatka_kan_url), R.drawable.vijaya_karanatka_icon, ContextCompat.getColor(context, R.color.tile2)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.vijayavani_kan), context.getResources().getString(R.string.vijayavani_kan_url), R.drawable.vijayvani_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.prajavani_kan), context.getResources().getString(R.string.prajavani_kan_url), R.drawable.prajavani_icon, ContextCompat.getColor(context, R.color.tile4)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.udayavani_kan), context.getResources().getString(R.string.udayavani_kan_url), R.drawable.udayavani_icon, ContextCompat.getColor(context, R.color.tile5)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.tv9kannada_kan), context.getResources().getString(R.string.tv9kannada_kan_url), R.drawable.tv9kannada_icon, ContextCompat.getColor(context, R.color.tile6)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.btvnews_kan), context.getResources().getString(R.string.btvnews_kan_url), R.drawable.btvnews_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.publictv_kan), context.getResources().getString(R.string.publictv_kan_url), R.drawable.publictv_icon, ContextCompat.getColor(context, R.color.tile2)));

        } else if (language.equalsIgnoreCase("malayalam")) {
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.manorama_mal), context.getResources().getString(R.string.manorama_mal_url), R.drawable.manorma_icon, ContextCompat.getColor(context, R.color.tile1)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.matrubhumi_mal), context.getResources().getString(R.string.matrubhumi_mal_url), R.drawable.mathrubhumi_icon, ContextCompat.getColor(context, R.color.tile2)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.deshabhimani_mal), context.getResources().getString(R.string.deshabhimani_mal_url), R.drawable.deshabhimani_icon, ContextCompat.getColor(context, R.color.tile3)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.madhyamam_mal), context.getResources().getString(R.string.madhyamam_mal_url), R.drawable.madhaym_icon, ContextCompat.getColor(context, R.color.tile4)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.keralaKaumudi_mal), context.getResources().getString(R.string.keralaKaumudi_mal_url), R.drawable.keralakamudi_icon, ContextCompat.getColor(context, R.color.tile5)));
            allItems.add(new NewspaperModel(context.getResources().getString(R.string.mangalam_mal), context.getResources().getString(R.string.mangalam_mal_url), R.drawable.mangalam_icon, ContextCompat.getColor(context, R.color.tile6)));

        }

        return allItems;
    }
}
