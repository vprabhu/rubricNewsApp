package com.example.rubricnewsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements android.app.LoaderManager.LoaderCallbacks<List<ArticleInfo>> {


    private ListView mArticlesListView;
    private ArticlesAdapter mArticlesAdapter;
    private TextView mEmptyTextView;
    private ProgressBar mProgressBar;


    private String url = "http://content.guardianapis.com/search?q=debates&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ui typecasting
        mArticlesListView = (ListView) findViewById(R.id.listView_articles);
        mEmptyTextView = (TextView) findViewById(R.id.textView_empty_list);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        // if internet is connected initialize the loader
        // if not , show "No Internet Connection" to user
        if(isInternetConnected(MainActivity.this)){
            // change the visibility to show progressbar
            mProgressBar.setVisibility(View.VISIBLE);
            mEmptyTextView.setVisibility(View.GONE);
            mArticlesListView.setVisibility(View.GONE);
            // starts the loader to fetch the articles
            getLoaderManager().initLoader(1 , null  , MainActivity.this);
        }else {
            mArticlesListView.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.VISIBLE);
            mEmptyTextView.setText(getResources().getString(R.string.info_no_internet));
        }

        // initialize the adapter with dummy list
        mArticlesAdapter = new ArticlesAdapter(
                MainActivity.this ,
                R.layout.layout_row ,
                new ArrayList<ArticleInfo>()
        );
        mArticlesListView.setAdapter(mArticlesAdapter);


        mArticlesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // loads the URL in a web browser respectively
                String url = mArticlesAdapter.getItem(position).getmWebUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

    }

    @Override
    public android.content.Loader<List<ArticleInfo>> onCreateLoader(int id, Bundle args) {
        return new ArticlesLoader(MainActivity.this , url);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<ArticleInfo>> loader, List<ArticleInfo> data) {
       // dismiss the progressbar to show the listview
        mProgressBar.setVisibility(View.GONE);
        mArticlesListView.setVisibility(View.VISIBLE);
        mEmptyTextView.setVisibility(View.VISIBLE);

        // the textview is displayed when the listview is empty
        mArticlesListView.setEmptyView(mEmptyTextView);
        mEmptyTextView.setText(getResources().getString(R.string.info_empty_text));
        // checks the new data is not empty
        if(!data.isEmpty()){
            // add the new data into the adapter
            mArticlesAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<ArticleInfo>> loader) {
        // clears the data 
        mArticlesAdapter.clear();
    }


    /**
     * checks if the mobile is connected to internet
     * @param context to obtaind the system service
     * @return boolean
     * returns true if network is connected
     * returns false if network is not connected
     */
    private boolean isInternetConnected(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }

}
