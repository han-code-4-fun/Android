package com.example.android.book_listing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String url;
    public BookLoader(@NonNull Context context, String inputUrl) {
        super(context);
        url = inputUrl;
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
