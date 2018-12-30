package com.example.android.book_listing;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class Query {

    private static final String LOG_TAG="Query_Class";


    //block future creation of Query instance
    private Query(){  }


    //method that call the rest of methods in the class
    public static List<Book> extractBookInfo(String urlInput)
    {
        //Log.i(LOG_TAG,"start extractBookInfo");
        List<Book> books = new ArrayList<>();

        URL url = createUrl(urlInput);

        try
        {

            JSONObject rootObj = new JSONObject(makeHTTPRequest(url));
            JSONProcessor.assignJSONDataToBookList(books,rootObj);


        }catch(JSONException j)
        {
            Log.e(LOG_TAG, "error retrieving JSON");

        }catch(IOException i)
        {
            Log.e(LOG_TAG,"cannot parsing to strings of JSON from makeHTTPRequest()");
        }


        return books;
    }

    private static URL createUrl(String urlString)
    {
        URL url = null;
        try{
            url = new URL(urlString);
        }catch(MalformedURLException exception)
        {
            Log.e(LOG_TAG, "error creating URL");
            return null;
        }

        //Log.i(LOG_TAG,"finish create URL and return");
        return url;
    }



    private static String makeHTTPRequest(URL url) throws IOException
    {
        String JsonOutput = "";
        if(url == null)
        {
            return JsonOutput;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try
        {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                JsonOutput=JSONProcessor.readFromSteam(inputStream);
            }else
            {
                Log.e(LOG_TAG,"internet connection error: "+
                urlConnection.getResponseCode());
            }

        }catch (IOException e){
            Log.e(LOG_TAG,"error reading inputstream");
        }finally {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if(inputStream != null)
            {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }

        return JsonOutput;

    }




}
