package com.example.rubricnewsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 5/16/17.
 */

public class ArticlesAdapter extends ArrayAdapter<ArticleInfo> {

    private Context mContext;

    public ArticlesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ArticleInfo> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_row , parent , false);
            mHolder = new ViewHolder();
            mHolder.mArticleNameTextView = (TextView)  convertView.findViewById(R.id.textView_article_row_name);
            mHolder.mArticleSectionTextView = (TextView)  convertView.findViewById(R.id.textView_article_row_section);
            mHolder.mArticleDateView = (TextView)  convertView.findViewById(R.id.textView_article_row_date);
            convertView.setTag(mHolder);
        }else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        // updates the Data UI

        ArticleInfo mArticleInfo = getItem(position);

        mHolder.mArticleNameTextView.setText(mArticleInfo.getmTitile());
        mHolder.mArticleSectionTextView.setText(mArticleInfo.getmSectionName());
        mHolder.mArticleDateView.setText(mArticleInfo.getPublishedOn());

        return convertView;
    }


    static class ViewHolder{
        private TextView mArticleNameTextView;
        private TextView mArticleSectionTextView;
        private TextView mArticleDateView;
    }
}
