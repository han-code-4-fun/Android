package com.example.android.didyoufeelit;

import android.os.AsyncTask;

import java.net.URL;

public class EarthquakeAsync extends AsyncTask<URL, Void, Event> {

    private String url;

    public EarthquakeAsync(String urlInput)
    {
        url = urlInput;
    }


    @Override
    protected Event doInBackground(URL... urls) {

        Event earthquake = Utils.fetchEarthquakeData(url);

        return earthquake;
    }

    @Override
    protected void onPostExecute(Event s) {
        if(s == null)
        {
            return;
        }

    }


}
