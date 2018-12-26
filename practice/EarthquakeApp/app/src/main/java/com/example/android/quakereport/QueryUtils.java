package com.example.android.quakereport;

import android.os.AsyncTask;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link EarthquakeInfo} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<EarthquakeInfo> extractEarthquakes(String inputUrl) {

        Log.i("extractEarthquakes", "method begin");

        // Create an empty ArrayList that we can start adding earthquakes to
        List<EarthquakeInfo> earthquakes = new ArrayList<>();

        URL createdURL = createUrl(inputUrl);


        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
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
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }catch(IOException i)
        {
            Log.e("makeHTTPRequest()","cannot parsing to strings of JSON");
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    /**
     * Returns new URL object from the given string URL.
     */
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
        try
        {
            urlConnect = (HttpURLConnection) inputUrl.openConnection();
            urlConnect.setRequestMethod("GET");
            urlConnect.setReadTimeout(10000 /* milliseconds */);
            urlConnect.setConnectTimeout(15000 /* milliseconds */);
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

        }catch (IOException e)
        {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        }finally {
            if (urlConnect != null)
            {
                urlConnect.disconnect();
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