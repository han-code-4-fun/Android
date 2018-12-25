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

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    private EarthquakeListAdapter myAdapter;

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?" +
            "format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.myListView);

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

        AsyncDataTask onLoadTask = new AsyncDataTask();
        onLoadTask.execute(URL);


    }


    public class AsyncDataTask extends AsyncTask<String, Void, List<EarthquakeInfo>> {

        //getting data in the background, from internet
        @Override
        protected List<EarthquakeInfo> doInBackground(String... strings) {

            List<EarthquakeInfo> earthquakes = QueryUtils.extractEarthquakes(strings[0]);

            return earthquakes;
        }


        //when data is ready, update existing views
        @Override
        protected void onPostExecute(List<EarthquakeInfo> earthquakeInfos) {
            PopulateDataToView(earthquakeInfos);
        }
    }

    public void PopulateDataToView(List<EarthquakeInfo> data)
    {
        // Clear the adapter of previous earthquake data
        myAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {

            myAdapter.addAll(data);
        }
    }

}
