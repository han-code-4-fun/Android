package popularmovies.examlple.com.popularmovies.Utils;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import popularmovies.examlple.com.popularmovies.AppRecyclerView.MyAdapter;
import popularmovies.examlple.com.popularmovies.Data.Movie;
import popularmovies.examlple.com.popularmovies.MyAsyncTask.AppExecutors;


public final class JSONUtils {

    //class that implment multiple classes to extract JSON data from url
    //and return an ArrayList of our datamodel class: Movie

    private static final String LOG_TAG = JSONUtils.class.getSimpleName();


    public static void getMovies(String sortFilter, final MyAdapter myAdapter) {






            URL url = MyURL.createURL(sortFilter);



            final OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .build();
            final Response[] responses = {null};
            final ArrayList<Movie> output = new ArrayList<>();

            AppExecutors.getInstance().networkIO().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        responses[0] = client.newCall(request).execute();
                        String stringResult = responses[0].body().toString();

                        JSONObject root = new JSONObject(stringResult);
                        JSONArray results = getJSONArrayFromJSONObj(root, "results");
                        if (results != null) {
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject current = results.getJSONObject(i);
                                Movie temp = new Movie(
                                        getPropertyInString(current, "original_title"),
                                        getPropertyInString(current, "poster_path"),
                                        getPropertyInString(current, "overview"),
                                        getPropertyInDouble(current, "popularity"),
                                        getPropertyInDouble(current, "vote_average"),
                                        getPropertyInString(current, "release_date")

                                );
                                output.add(temp);
                            }
                        }


                        myAdapter.setData(output);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (JSONException j) {
                        Log.e(LOG_TAG, j.getStackTrace().toString());
                    }
                }
            });






//        try {
//            Log.i(LOG_TAG, url.toString());
//
//
//            String stringResult = NetworkUtils.getJSONFromURL(url);
//
//            JSONObject root = new JSONObject(stringResult);
//            JSONArray results = getJSONArrayFromJSONObj(root, "results");
//            if (results != null) {
//                for (int i = 0; i < results.length(); i++) {
//                    JSONObject current = results.getJSONObject(i);
//                    Movie temp = new Movie(
//                            getPropertyInString(current, "original_title"),
//                            getPropertyInString(current, "poster_path"),
//                            getPropertyInString(current, "overview"),
//                            getPropertyInDouble(current, "popularity"),
//                            getPropertyInDouble(current, "vote_average"),
//                            getPropertyInString(current, "release_date")
//
//                    );
//                    output.add(temp);
//                }
//            }
//        } catch (JSONException j) {
//            Log.e(LOG_TAG, j.getStackTrace().toString());
//        } catch (IOException e) {
//            Log.e(LOG_TAG, e.getStackTrace().toString());
//        }
//        return output;
    }


    //handle JSON property
    private static String getPropertyInString
    (JSONObject inputObj, String inputProperty) throws JSONException {
        if (inputObj.has(inputProperty)) {
            return inputObj.getString(inputProperty);
        }
        Log.i(LOG_TAG, "no such property: '" + inputProperty + "'");
        return "";
    }


    //re-usedable method that handle JSONArray property
    private static JSONArray getJSONArrayFromJSONObj
    (JSONObject inputObj, String arrayName) throws JSONException {
        if (inputObj.has(arrayName)) {
            return inputObj.getJSONArray(arrayName);
        }
        Log.e(LOG_TAG, "no such JSON array: '" + arrayName + "'");
        return null;
    }


    //re-usedable method that  handle JSON object
    private static JSONObject getJSONObj
    (JSONObject inputObj, String objName) throws JSONException {
        if (inputObj.has(objName)) {
            return inputObj.getJSONObject(objName);
        }
        Log.e(LOG_TAG, "no such property: '" + objName + "'");
        return null;
    }


    //re-usedable method that handle JSON double property
    private static Double getPropertyInDouble
    (JSONObject inputObj, String inputProperty) throws JSONException {
        if (inputObj.has(inputProperty)) {
            return inputObj.getDouble(inputProperty);
        }
        Log.i(LOG_TAG, "no such property: '" + inputProperty + "'");
        return null;
    }
}
