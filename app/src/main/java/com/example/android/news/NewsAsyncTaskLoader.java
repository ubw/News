package com.example.android.news;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsAsyncTaskLoader extends AsyncTaskLoader<List<NewsInfo>> {
    private String mUrl;

    public NewsAsyncTaskLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
        Log.d("NewsAsyncTaskLoader","==========NewsAsyncTaskLoader==============");
    }

    @Override
    public List<NewsInfo> loadInBackground() {
        Log.d("NewsAsyncTaskLoader","===========loadInBackground=================");
        return QueryUtils.fetchEarthquakeData(mUrl);
    }
}
