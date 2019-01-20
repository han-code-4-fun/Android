package utils;

import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public final class AQURL {

    public static final String LOG_TAG =AQURL.class.getSimpleName();

    //following variables are used by Uri.parse
    public static final String BASE_URL = "https://api.openaq.org/v1/";

    public static final String PATH_COUNTRY = "countries";
    public static final String PATH_CITY = "cities";
    public static final String PATH_LOCATION = "locations";
    public static final String PATH_MEASURE = "measurements";

    public static final String PARAM_COUNTRY="country";
    public static final String PARAM_CITY="city";
    public static final String PARAM_LOCATION="location";


    public static URL getListCountriesURL()
    {
        Uri input = Uri.parse(BASE_URL).buildUpon()
                .appendPath(PATH_COUNTRY).build();

        return  URLCreator(input);
    }

    public static URL getListCitiesURL(String countryCode)
    {
        Uri input = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(PATH_CITY)
                    .appendQueryParameter(PARAM_COUNTRY,countryCode)
                    .build();

        return URLCreator(input);
    }

    public static URL getListLocationsURL(String countryCode, String cityName)
    {
        Uri input = Uri.parse(BASE_URL).buildUpon()
                .appendPath(PATH_LOCATION)
                .appendQueryParameter(PARAM_COUNTRY,countryCode)
                .appendQueryParameter(PARAM_CITY, cityName)
                .build();

        return URLCreator(input);
    }

    public static URL getDetailMeasurementURL(
            String countryCode, String cityName,String location)
    {
        Uri input = Uri.parse(BASE_URL).buildUpon()
                .appendPath(PATH_MEASURE)
                .appendQueryParameter(PARAM_COUNTRY,countryCode)
                .appendQueryParameter(PARAM_CITY, cityName)
                .appendQueryParameter(PARAM_LOCATION, location)
                .build();

        return URLCreator(input);
    }


    public static URL URLCreator(Uri inputUri)
    {
        URL output = null;
        try
        {
            output = new URL(inputUri.toString());

        }catch (MalformedURLException e)
        {
            Log.e(LOG_TAG, "error creating URL");
        }
        return output;
    }
}
