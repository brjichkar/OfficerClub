package com.android.officersclub.ui_section.home_section.home_section.model;

public class NewsModel {
    String imageBg;
    String title;

    public NewsModel() {
    }

    public NewsModel(String imageBg, String title) {
        this.imageBg = imageBg;
        this.title = title;
    }

    public String getImageBg() {
        return imageBg;
    }

    public void setImageBg(String imageBg) {
        this.imageBg = imageBg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
