package com.example.onnews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    /** Adapter for the list of news */
    private NewsAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    private static final int NEWS_LOADER_ID = 1;


    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String THE_GUARDIAN = "https://content.guardianapis.com/search?page-size=25&show-tags=contributor&show-fields=thumbnail&api-key=64a2d235-3760-49a5-84e6-d3994d445dc2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.onn);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current news that was clicked on
                News currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getUrl());

                // Create a new intent to view the news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loading = findViewById(R.id.loading_spinner);
            loading.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText("No internet connection");
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, THE_GUARDIAN);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        ProgressBar loading = (ProgressBar) findViewById(R.id.loading_spinner);
        loading.setVisibility(View.GONE);
        mEmptyStateTextView.setText("No News Found");

        mAdapter.clear();

        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }

}