package com.example.rubricnewsapp;

/**
 * Created by root on 5/16/17.
 */

public class ArticleInfo {

    private String mTitile;
    private String mSectionName;
    private String publishedOn;
    private String mWebUrl;

    public ArticleInfo(String mTitile, String mSectionName, String publishedOn, String mWebUrl) {
        this.mTitile = mTitile;
        this.mSectionName = mSectionName;
        this.publishedOn = publishedOn;
        this.mWebUrl = mWebUrl;
    }

    public String getmTitile() {
        return mTitile;
    }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }
}
