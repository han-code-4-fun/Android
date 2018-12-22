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
package com.example.android.miwok;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);


        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);


        MyFragmentPageAdapter myFAdapter = new MyFragmentPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(myFAdapter);

        TabLayout tabLayout =(TabLayout)findViewById(R.id.sliding_tabs);

        /*
        //shorter way to visual polish tabs without add style information in the xml file,
        //it's fast but LESS organized!!!!!
        tabLayout.setTabTextColors(Color.parseColor("#A8A19E"), Color.parseColor("#FFFFFF"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setBackgroundColor(Color.parseColor("#4A312A"));
        */

        //connect the tab layout with the view pager
        //and be able to set tab name according to position of view pager
        tabLayout.setupWithViewPager(viewPager);
    }
}
