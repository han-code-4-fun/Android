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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class Query {

    private static final String LOG_TAG="Query_Class";


    //block future creation of Query instance
    private Query(){  }

    public static List<Book> extractBookInfo(String urlInput)
    {
        Log.i(LOG_TAG,"start extractBookInfo");
        List<Book> books = new ArrayList<>();

        URL url = createUrl(urlInput);

        try
        {

            JSONObject rootObj = new JSONObject(makeHTTPRequest(url));
            JSONArray items = rootObj.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject current = items.getJSONObject(i);
                JSONObject volumeInfo = current.getJSONObject("volumeInfo");
                Log.i(LOG_TAG, "for looping");

                Book temp = new Book(
                        volumeInfo.getString("title"),
                        getMultipleAuthors(volumeInfo.getJSONArray("authors")),
                        volumeInfo.getString("description"),
                        volumeInfo.getString("infoLink")
                        );
                books.add(temp);

            }


        }catch(JSONException j)
        {
            Log.e(LOG_TAG, "error retrieving JSON from getMultipleAuthors()");

        }catch(IOException i)
        {
            Log.e(LOG_TAG,"cannot parsing to strings of JSON from makeHTTPRequest()");
        }
        if(books.isEmpty())
        {
            Log.e(LOG_TAG,"Book is empty");
        }else {
            Log.e(LOG_TAG, books.toString());
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

        Log.i(LOG_TAG,"finish create URL and return");
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
                JsonOutput=readFromSteam(inputStream);
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

    private static String readFromSteam(InputStream inputStream) throws IOException {
        StringBuilder outputString = new StringBuilder();
        if(inputStream != null)
        {
            //**** important to have Charset.forName("UTF-8")
            InputStreamReader sReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bReader = new BufferedReader(sReader);
            String line = bReader.readLine();
            while(line != null)
            {
                outputString.append(line);
                line = bReader.readLine();
            }

        }else
        {
            Log.e(LOG_TAG," inputStream is null in readFromSteam");
        }

        return outputString.toString();
    }

    private static String getMultipleAuthors(JSONArray inputJArray) throws JSONException
    {
        StringBuilder output = new StringBuilder();

        try{
            for (int i = 0; i < inputJArray.length(); i++) {
                output.append(inputJArray.getString(i));
                if(i<inputJArray.length()-2)
                {
                    output.append(", ");
                }else if(i<inputJArray.length()-1)
                {

                    output.append(" and ");
                }


            }
        }catch (Exception e)
        {
            Log.e(LOG_TAG,"something is wrong here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }




        return output.toString();
    }



}
