package com.example.android.book_listing;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private static final String test="TEST_TAG";
    private String url;
    public BookLoader(Context context, String inputUrl) {
        super(context);
        url = inputUrl;
    }

    @Override
    protected void onStartLoading() {
        // must have this line when inherited from AsyncTaskLoader
        super.onStartLoading();
        //Log.i(test,"onstartloading finished");
    }

    @Override
    public List<Book> loadInBackground() {
        //Log.i(test,"begining of loadinbackground ");
        if(url == null)
        {
            //Log.i(test,"url null in loadinbackground ");
            return null;
        }

        //Log.i(test,"begining to Query.extractBookInfo(url) ");
        List<Book> output = Query.extractBookInfo(url);

        return output;
    }
}
