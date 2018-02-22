package com.example.android.news;

public class NewsInfo {
    private String mTitle;
    private String mTime;
    private String mUrl;

    public NewsInfo(String title, String author, String url){
        this.mTime = author;
        this.mTitle = title;
        this.mUrl = url;
    }

    public String getmTitle(){
        return mTitle;
    }

    public String getmTime(){
        return mTime;
    }

    public String getmUrl(){
        return mUrl;
    }
}
