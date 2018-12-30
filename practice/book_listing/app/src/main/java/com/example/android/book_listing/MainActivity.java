package com.example.android.book_listing;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    static private String searchKeyword;

    private ConnectivityManager cm;

    private NetworkInfo myNetInfo;

    private TextView emptyView;

    private ProgressBar progressBar;

    private ViewPager viewPager;

    private MainFragmentAdapter fragmentAdapter;

    private TabLayout tablayout;

    private static final String test="TEST_TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        myNetInfo = cm.getActiveNetworkInfo();

       // listView.setEmptyView(emptyView);

        if(myNetInfo != null && myNetInfo.isConnected())
        {

            emptyView = findViewById(R.id.emptyViewMain);

            progressBar = findViewById(R.id.progressViewMain);

            viewPager = findViewById(R.id.viewPager);

            fragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());

            tablayout = findViewById(R.id.tabView);

            viewPager.setAdapter(fragmentAdapter);

            tablayout.setupWithViewPager(viewPager);

            //Log.i("Be aware","there is internet connection!");

        }else{
            //handle completely no internet situation
            progressBar.setVisibility(View.GONE);
            emptyView.setText("No internet connection");
        }


    }


    public String getSearchKeyword(){
        return searchKeyword;
    }


}


