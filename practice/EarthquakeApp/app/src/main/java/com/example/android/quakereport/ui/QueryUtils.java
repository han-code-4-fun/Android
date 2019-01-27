package com.example.android.quakereport.ui;

import android.util.Log;

import com.example.android.quakereport.data.EarthquakeInfo;

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

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;


public final class QueryUtils {

    //class that handles network activity eg. inputstream, HttpURLConnection
    //and returning a List of the datamodel objects

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    // Create a private constructor so it cannot be an instance anymore
    private QueryUtils() {
    }



    public static List<EarthquakeInfo> extractEarthquakes(String inputUrl) {

        Log.i(LOG_TAG, "extractEarthquakes begin");

        // Create an empty ArrayList that we can start adding earthquakes to
        List<EarthquakeInfo> earthquakes = new ArrayList<>();

        URL createdURL = createUrl(inputUrl);


        // Try to parse the JSON response into our datamodel: EarthquakeInfo
        try {

            // build up a list of Earthquake objects with the corresponding data.
            JSONObject rootObj = new JSONObject(makeHTTPRequest(createdURL));
            JSONArray features = rootObj.getJSONArray("features");

            Log.i("extractEarthquakes", "method in try");

            for (int i = 0; i < features.length(); i++) {
                JSONObject current = features.getJSONObject(i);
                JSONObject properties = current.getJSONObject("properties");

                EarthquakeInfo thisInfo = new EarthquakeInfo(
                        properties.getDouble("mag"),
                        properties.getString("place"),
                        properties.getLong("time"),
                        properties.getString("url")

                );
                earthquakes.add(thisInfo);


                Log.i(LOG_TAG, "method finish one loop");

            }


        } catch (JSONException e) {

            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }catch(IOException i)
        {
            Log.e(LOG_TAG,"cannot parsing to strings of JSON");
        }

        // Return the list of earthquakes
        return earthquakes;
    }


    //create URL from String
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }


    //return Json string from an URL
    private static String makeHTTPRequest(URL inputUrl) throws IOException{

        String JsonOutput = "";
        if(inputUrl == null)
        {
            return JsonOutput;
        }

        HttpURLConnection urlConnect = null;
        InputStream inputStream = null;

            urlConnect = (HttpURLConnection) inputUrl.openConnection();
            urlConnect.setRequestMethod("GET");
            urlConnect.setReadTimeout(10000);
            urlConnect.setConnectTimeout(15000);
            urlConnect.connect();
            if(urlConnect.getResponseCode() == 200)
            {

                inputStream = urlConnect.getInputStream();
                JsonOutput = readFromStream(inputStream);
            }else
            {
                Log.e(LOG_TAG,"internet connection error: " +
                        urlConnect.getResponseCode());
            }


            if (urlConnect != null)
            {
                urlConnect.disconnect();
            }
            if(inputStream != null)
            {

                inputStream.close();
            }



        return JsonOutput;
    }


    //return string from inputStream using bufferedReader
    private static String readFromStream(InputStream stream)throws IOException
    {
        StringBuilder outputString= new StringBuilder();
        if(stream != null)
        {
            InputStreamReader sReader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            BufferedReader buffReader = new BufferedReader(sReader);
            String line = buffReader.readLine();
            while(line!=null)
            {
                outputString.append(line);
                line = buffReader.readLine();
            }
        }


        return outputString.toString();
    }


}