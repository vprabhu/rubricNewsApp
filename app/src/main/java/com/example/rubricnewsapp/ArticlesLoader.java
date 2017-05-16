package com.example.rubricnewsapp;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by root on 5/16/17.
 */

public class ArticlesLoader extends AsyncTaskLoader<List<ArticleInfo>> {

    private Context mContext;
    private String mQueryUrl;

    public ArticlesLoader(Context context , String url) {
        super(context);
        mContext = context;
        mQueryUrl = url;
    }

    @Override
    public List<ArticleInfo> loadInBackground() {
        List<ArticleInfo> mArticleInfoList = QueryUtils.fetchArticles(mQueryUrl);
        return mArticleInfoList;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
