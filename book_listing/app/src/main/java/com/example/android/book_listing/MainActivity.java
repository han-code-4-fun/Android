package com.example.android.book_listing;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

    private BookListAdapter myAdapter;

    private ConnectivityManager cm;

    private NetworkInfo myNetInfo;

    private ListView listView;

    private final static String url =
            "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=15";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        myNetInfo = cm.getActiveNetworkInfo();

        myAdapter = new BookListAdapter(this, 0, new ArrayList<Book>());

        listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book thisBook = myAdapter.getItem(position);

                Intent openUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(thisBook.getUrl()));
                startActivity(openUrl);

            }
        });

        if(myNetInfo != null && myNetInfo.isConnected())
        {

            //todo Loaders is deprecated in API 28, need to learn and
            //todo  replace this method with  ViewModel, LiveData and Observer*/
            getLoaderManager().initLoader(0,null,this).forceLoad();

        }


    }


    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        // Clear the adapter of previous earthquake data
        myAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {

            //comment out below 2 lines to test progress bar with empty view
            myAdapter.addAll(data);
            myAdapter.notifyDataSetChanged();
        }

    }
    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        myAdapter.clear();
    }


}


