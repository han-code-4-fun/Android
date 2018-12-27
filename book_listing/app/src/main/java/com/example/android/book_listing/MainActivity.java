package com.example.android.book_listing;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.*;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private BookListAdapter myAdapter;
    private final static String url = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=15";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myAdapter = new BookListAdapter(this, 0, new ArrayList<Book>());

        ListView listView = this.findViewById(R.id.listView);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book thisBook = myAdapter.getItem(position);

                Intent openUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(openUrl);

            }
        });


    }


    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        // Clear the adapter of previous earthquake data
        myAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {

            //comment out below 2 lines to test progress bar with empty view
            myAdapter.addAll(books);
            myAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public void onLoaderReset (Loader < List < Book >> loader) {
        myAdapter.clear();
    }
}


