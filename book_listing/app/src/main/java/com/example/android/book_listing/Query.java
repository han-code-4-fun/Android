package com.example.android.book_listing;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class Query {

    private static final String LOG_TAG="Query_Class";

    //block future creation of Query instance
    private Query(){  }

    public static List<Book> extractBookInfo(String urlInput)
    {
        List<Book> books = new ArrayList<>();

        URL url = createUrl(urlInput);

        try
        {
            JSONObject rootObj = new JSONObject(makeHTTPRequest(url));
            JSONArray items = rootObj.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject current = items.getJSONObject(i);
                JSONObject volumeInfo = current.getJSONObject("volumeInfo");


                Book temp = new Book(
                        volumeInfo.getString("title"),
                        getMultipleAuthors(volumeInfo.getJSONArray("author")),
                        volumeInfo.getString("description"),
                        volumeInfo.getString("infoLink")
                        );
                books.add(temp);

            }


        }catch(JSONException j)
        {
            Log.e(LOG_TAG, "error retrieving JSON from getMultipleAuthors()");

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

        return url;
    }

    private static String makeHTTPRequest(URL url)
    {
        String JsonOutput = "";
        if(url == null)
        {
            return JsonOutput;
        }
        HttpURLConnection urlConnection;
        InputStream inputStream;
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
                JsonOutput=readFromSteam(inputStream);
            }else
            {
                Log.e(LOG_TAG,"internet connection error: "+
                urlConnection.getResponseCode());
            }

        }catch (IOException e){
            Log.e(LOG_TAG,"error reading inputstream");
        }

        return JsonOutput;

    }

    private static String readFromSteam(InputStream inputStream) throws IOException {
        StringBuilder outputString = new StringBuilder();
        if(inputStream != null)
        {
            InputStreamReader sReader = new InputStreamReader(inputStream);
            BufferedReader bReader = new BufferedReader(sReader);
            String line = bReader.readLine();
            while(line != null)
            {
                outputString.append(line);
                bReader.readLine();
            }

        }

        return outputString.toString();
    }

    private static String getMultipleAuthors(JSONArray inputJArray) throws JSONException
    {
        StringBuilder output = new StringBuilder();

        try{
            for (int i = 0; i < inputJArray.length(); i++) {
                output.append(inputJArray.getString(i));
                if(i<inputJArray.length()-1)
                {
                    output.append(", ");
                }

            }
        }catch (Exception e)
        {
            Log.e(LOG_TAG,"something is wrong here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }




        return output.toString();
    }



}
