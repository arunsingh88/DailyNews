package com.arunpwc.newspaper.model;

/**
 * Created by aruns512 on 08/03/2017.
 */

public class LanguageModel {
    private String displayLangauge;
    private String langValue;

    public LanguageModel(String displayLangauge, String langValue) {
        this.displayLangauge = displayLangauge;
        this.langValue = langValue;
    }

    public String getDisplayLangauge() {
        return displayLangauge;
    }

    public void setDisplayLangauge(String displayLangauge) {
        this.displayLangauge = displayLangauge;
    }

    public String getLangValue() {
        return langValue;
    }

    public void setLangValue(String langValue) {
        this.langValue = langValue;
    }

}
