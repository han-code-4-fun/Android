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
package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

//(8) Implement GreenAdapter.ListItemClickListener from the MainActivity
public class MainActivity extends AppCompatActivity implements GreenAdapter.MyListItemClickListener{

    private static final int NUM_LIST_ITEMS = 100;


    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;

    // (9) Create a Toast variable called mToast to store the current Toast
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);

        mNumbersList.setHasFixedSize(true);

        //(13) Pass in this as the ListItemClickListener to the GreenAdapter constructor

        mAdapter = new GreenAdapter(NUM_LIST_ITEMS,this);
        mNumbersList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {

            case R.id.action_refresh:
                // (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
                mAdapter = new GreenAdapter(NUM_LIST_ITEMS,this);
                mNumbersList.setAdapter(mAdapter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // (10) Override ListItemClickListener's onListItemClick method
    @Override
    public void myOnListItemClick(int itemClicked) {
        if(mToast != null)
        {
            mToast.cancel();
        }
        String toastMSG = "item number"+ itemClicked+ " clicked.";
        mToast = Toast.makeText(this,toastMSG,Toast.LENGTH_LONG);
        mToast.show();
    }
    // (11) In the beginning of the method, cancel the Toast if it isn't null
    // (12) Show a Toast when an item is clicked, displaying that item number that was clicked
}
