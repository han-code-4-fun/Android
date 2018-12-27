package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeInfoLoader extends AsyncTaskLoader<List<EarthquakeInfo>> {

    private String Url;

    public EarthquakeInfoLoader(Context context,String inputUrl) {
        super(context);
        Url = inputUrl;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.i("onStartLoading","loader class start loading");
    }

    @Override
    public List<EarthquakeInfo> loadInBackground() {
        if (Url == null)
        {
            return null;
        }
        Log.i("loadInBackground", "loader class load in background");

        List<EarthquakeInfo> output = QueryUtils.extractEarthquakes(Url);

        return output;
    }
}
