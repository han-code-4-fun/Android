package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeInfoLoader extends AsyncTaskLoader<List<EarthquakeInfo>> {

    private String Url;

    public EarthquakeInfoLoader(Context context,String inputUrl) {
        super(context);
        Url = inputUrl;
    }

    @Override
    public List<EarthquakeInfo> loadInBackground() {
        if (Url == null)
        {
            return null;
        }

        List<EarthquakeInfo> output = QueryUtils.extractEarthquakes(Url);

        return output;
    }
}
