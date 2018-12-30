package com.example.android.book_listing;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public final class JSONProcessor
{
    private static final String LOG_TAG = "JSONProcessor";
    //block futher creation of this class instance
    private JSONProcessor(){};



    public static void assignJSONDataToBookList(List<Book> books, JSONObject rootObj) throws JSONException
    {
        JSONArray items = rootObj.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject current = items.getJSONObject(i);
            JSONObject volumeInfo = current.getJSONObject("volumeInfo");

            //Log.i(LOG_TAG, "for looping");

            Book temp = new Book(
                    handlePropertyName(volumeInfo,"title"),
                    getMultipleAuthors(handleArrayName(volumeInfo,"authors")),
                    handlePropertyName(volumeInfo,"description"),
                    handlePropertyName(volumeInfo,"infoLink")
            );
            books.add(temp);

        }
    }

    //use BufferedReader to read inputstream
    public static String readFromSteam(InputStream inputStream) throws IOException {
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


    //extract and concatenate multiple authors
    private static String getMultipleAuthors(JSONArray inputJArray) throws JSONException
    {
        if(inputJArray != null)
        {
            StringBuilder output = new StringBuilder();


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

            return  output.toString();
        }else{

            return "";
        }

    }


    //handle JSON property
    private static String handlePropertyName(JSONObject inputObj, String inputProperty) throws JSONException
    {
        if(inputObj.has(inputProperty))
        {
            return inputObj.getString(inputProperty);
        }
        Log.i(LOG_TAG, "no such property: '"+ inputProperty+"'");
        return "";
    }


    //handle JSONArray property
    private static JSONArray handleArrayName(JSONObject inputObj, String inputArrayName) throws JSONException
    {
        if(inputObj.has(inputArrayName))
        {
            return inputObj.getJSONArray(inputArrayName);
        }
        Log.e(LOG_TAG,"no such JSON array name, check handleArrayName()");
        return null;
    }

}
