package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class JsonUtils {

    private final static String LOG_TAG = "JsonUtilis";

    public static Sandwich parseSandwichJson(String input)
    {
        if(input == null){return null;}
        Sandwich output = null;
        try{
            JSONObject elementRoot = new JSONObject(input);
            JSONObject name = elementRoot.getJSONObject("name");

            output = new Sandwich(
                    name.getString("mainName"),
                    fromJSONObjToList(name, "alsoKnownAs"),
                    elementRoot.getString("placeOfOrigin"),
                    elementRoot.getString("description"),
                    elementRoot.getString("image"),
                    fromJSONObjToList(elementRoot, "ingredients")
            );
        }catch(JSONException j)
        {
            Log.e(LOG_TAG,"error parsing JSON");
        }
        return output;
    }


    //handling datamodel which use List<String>
    public static List<String> fromJSONObjToList(JSONObject inputObj,
                                                 String property) throws JSONException
    {
        List<String> output = new ArrayList<>();

        JSONArray temp = inputObj.getJSONArray(property);
        if(temp!=null){
            for (int i = 0; i < temp.length(); i++) {
                  output.add(temp.getString(i));
            }
        }else
        {
           output.add("N/A");
        }
        return output;
    }

}

