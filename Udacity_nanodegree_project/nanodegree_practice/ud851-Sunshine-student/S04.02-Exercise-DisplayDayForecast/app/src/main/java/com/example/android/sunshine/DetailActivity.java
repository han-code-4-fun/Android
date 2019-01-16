package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent whoeverCallsThis = getIntent();
        //  (2) Display the weather forecast that was passed from MainActivity
        if(whoeverCallsThis.hasExtra("data1"))
        {
            Toast.makeText(this,whoeverCallsThis.getStringExtra("data1"), Toast.LENGTH_LONG).show();
        }
    }
}