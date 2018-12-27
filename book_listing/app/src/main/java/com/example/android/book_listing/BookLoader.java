package com.example.android.book_listing;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String url;
    public BookLoader(@NonNull Context context, String inputUrl) {
        super(context);
        url = inputUrl;
    }

    @Override
    protected void onStartLoading() {
        // must have this line when inherited from AsyncTaskLoader
        super.onStartLoading();
    }

    public List<Book> loadInBackground() {
        if(url == null)
        {
            return null;
        }

        List<Book> output = Query.extractBookInfo(url);

        return output;
    }
}
