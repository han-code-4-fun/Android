/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.android.quakereport.data.EarthquakeInfo;
import com.example.android.quakereport.data.EarthquakeInfoLoader;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<EarthquakeInfo>>
        {

    private EarthquakeListAdapter myAdapter;

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?" +
            "format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private ListView earthquakeListView;

    private TextView emptyView;

    private ProgressBar progressBar;

    private ConnectivityManager cm;

    NetworkInfo myNetInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //get current network info
        myNetInfo = cm.getActiveNetworkInfo();


        // Find a reference to the {@link ListView} in the layout
         earthquakeListView = (ListView) findViewById(R.id.myListView);

         emptyView = (TextView)findViewById(R.id.emptyView);

        progressBar = (ProgressBar)findViewById(R.id.progressView);

        //set empty view
        earthquakeListView.setEmptyView(emptyView);

        // Create a new {@link ArrayAdapter} of earthquakes
        myAdapter = new EarthquakeListAdapter(
                this, 0, new ArrayList<EarthquakeInfo>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(myAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // navigate to a more detailed earthquake external webpage if user clicks
                EarthquakeInfo thisInfo = myAdapter.getItem(position);

                Intent browseURL = new Intent(Intent.ACTION_VIEW, Uri.parse(thisInfo.getUrl()));

                startActivity(browseURL);
            }
        });


        //if there is internet
        if(myNetInfo != null && myNetInfo.isConnected())
        {
            getLoaderManager().initLoader(0,null,this).forceLoad();
        }else
        {

            //handle completely no internet situation
            progressBar.setVisibility(View.GONE);
            emptyView.setText("No internet connection");
        }


    }





    @Override
    public Loader<List<EarthquakeInfo>> onCreateLoader(int id, Bundle args) {


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", "time");

        return new EarthquakeInfoLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeInfo>> loader, List<EarthquakeInfo> data)
    {
        progressBar.setVisibility(View.GONE);


        //following 4 lines was to test progress bar
        /*try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        emptyView.setText("No earthquakes found");
        Log.i("onLoadFinished","load finished, next is to populte data to view");


        PopulateDataToView(data);

    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeInfo>> loader) {
        Log.i("onLoaderReset","reset loader");
        myAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void PopulateDataToView(List<EarthquakeInfo> data)
    {
        // Clear the adapter of previous earthquake data
        myAdapter.clear();

        // update adapter if there is data
        if (data != null && !data.isEmpty()) {

            myAdapter.addAll(data);
            myAdapter.notifyDataSetChanged();
        }
    }

}
