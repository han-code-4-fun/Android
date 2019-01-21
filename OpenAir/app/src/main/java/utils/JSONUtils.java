package utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import Data.City;
import Data.Country;

public class JSONUtils {
    private static final String LOG_TAG = JSONUtils.class.getSimpleName();



    public static ArrayList<Country> getCounties()
    {
        URL url = AQURL.getListCountriesURL();
        ArrayList<Country> output = new ArrayList<>();
        try {
            Log.i(LOG_TAG, url.toString());
            String stringResult = NetworkUtils.getJSONFromURL(url);
            JSONObject root = new JSONObject(stringResult);
            JSONArray results = getJSONArrayFromJSONObj(root,"results");
            if(results!=null)
            {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject current = results.getJSONObject(i);
                    Country temp = new Country(
                            getPropertyInString(current,"name"),
                            getPropertyInString(current,"code")
                    );
                    output.add(temp);
                }
            }
        } catch (JSONException j) {
          Log.e(LOG_TAG,  j.getStackTrace().toString());
        } catch (IOException e) {
            Log.e(LOG_TAG,  e.getStackTrace().toString());
        }
        return output;
    }

    public static ArrayList<City> getCities(String countryCode)
    {
        URL url = AQURL.getListCitiesURL(countryCode);
        ArrayList<City> output = new ArrayList<>();
        try {
            Log.i(LOG_TAG, url.toString());
            String stringResult = NetworkUtils.getJSONFromURL(url);
            JSONObject root = new JSONObject(stringResult);
            JSONArray results = getJSONArrayFromJSONObj(root,"results");
            if(results!=null)
            {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject current = results.getJSONObject(i);
                    City temp = new City(
                            getPropertyInString(current, "city"),
                            getPropertyInString(current, "country")
                    );
                    output.add(temp);
                }
            }
        } catch (JSONException j) {
            Log.e(LOG_TAG,  j.getStackTrace().toString());
        } catch (IOException e) {
            Log.e(LOG_TAG,  e.getStackTrace().toString());
        }
        return output;
    }


    //handle JSON property
    private static String getPropertyInString
        (JSONObject inputObj, String inputProperty)throws JSONException
    {
        if(inputObj.has(inputProperty))
        {
            return inputObj.getString(inputProperty);
        }
        Log.i(LOG_TAG, "no such property: '"+ inputProperty+"'");
        return "";
    }


    //handle JSONArray property
    private static JSONArray getJSONArrayFromJSONObj
    (JSONObject inputObj, String arrayName)throws JSONException
    {
        if(inputObj.has(arrayName))
        {
            return inputObj.getJSONArray(arrayName);
        }
        Log.e(LOG_TAG,"no such JSON array: '"+ arrayName+"'");
        return null;
    }
}
