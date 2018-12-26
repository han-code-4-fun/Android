package com.example.android.book_listing;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>
{
    private BookListAdapter myAdapter;
    private final static String url = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=15";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         myAdapter = new BookListAdapter(this, 0 , new ArrayList<Book>());

        ListView listView = this.findViewById(R.id.listView);

        listView.setAdapter(myAdapter);


    }


    @Override
    public Loader<List<Book>> onCreateLoader(int i,  Bundle bundle) {
        BookLoader myLoader = new BookLoader(this,url);

        return myLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {


    }

    @Override
    public void onLoaderReset( Loader<List<Book>> loader) {

    }
}
