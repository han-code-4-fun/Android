package popularmovies.examlple.com.popularmovies.Utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public final class MyURL {

    //class that returns url

    private static final String LOG_TAG = MyURL.class.getSimpleName();

    //todo, first step, put your own API key here
    private static final String MOVIEDB_API_KEY = "";

    private static final String BASE_URL_MOVIE = "https://api.themoviedb.org/3/discover/";

    private static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/";

    private static final String IMAGE_SIZE = "w185";

    private static final String PATH = "movie";

    private static final String API_PARAM = "api_key";


    private static final String SORT_PARAM = "sort_by";

    private static final String BY_POPULARITY_DESC = "popularity.desc";


    private static final String BY_RATING_DESC = "vote_average.desc";

    public static String getPopFilter() {  return BY_POPULARITY_DESC; }

    public static String getRatingFilter() { return BY_RATING_DESC; }




    public static void errorLogURL(Exception e)
    {
        Log.e(LOG_TAG, "error creating URL: "+ e.getStackTrace().toString());
    }

    public static URL createURL( String sortingFilter)
    {
        Uri temp = Uri.parse(BASE_URL_MOVIE).buildUpon()
                .appendPath(PATH)
                .appendQueryParameter(API_PARAM,MOVIEDB_API_KEY)
                .appendQueryParameter(SORT_PARAM, sortingFilter)
                .build();
        URL output = null;
        try
        {
            output = new URL(temp.toString());
        }catch(MalformedURLException e)
        {
           errorLogURL(e);
        }

        return output;
    }


    public static URL createImageURL(String imagePartialUrl)
    {
        URL output = null;
        try {
            output = new URL(BASE_URL_IMAGE+IMAGE_SIZE+imagePartialUrl);
        } catch (MalformedURLException e) {
            errorLogURL(e);
        }
        return output;
    }



}
