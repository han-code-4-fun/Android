package popularmovies.examlple.com.openair.ui;

import Data.City;
import Data.Country;

import androidx.appcompat.app.AppCompatActivity;


import asynctask.CityAsyncTask;
import asynctask.CountryAsyncTask;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import popularmovies.examlple.com.openair.R;
import utils.JSONUtils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {
    Spinner countriesSpinner, citiesSpinner;
    Button submit;
    ArrayList<String> countriesCode;
    ArrayAdapter<String> countriesAdapter;
    ArrayAdapter<String> citiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        countriesCode = new ArrayList<>();

        countriesSpinner = findViewById(R.id.spinner_country);

        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = countriesCode.get(position);
                CityAsyncTask updateCities = new CityAsyncTask(selected, citiesAdapter);
                updateCities.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citiesSpinner = findViewById(R.id.spinner_city);



        countriesAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(countriesAdapter);

        citiesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citiesSpinner.setAdapter(citiesAdapter);

        CountryAsyncTask initialLoadCountries = new CountryAsyncTask(countriesAdapter, citiesAdapter, countriesCode);
        initialLoadCountries.execute();




        submit = findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(citiesSpinner.getSelectedItem().toString() == "unused")
                {
                    Toast.makeText(
                            CountryActivity.this,
                            "Please choose cities that has current data",
                            Toast.LENGTH_LONG).show();
                }else{
                    Intent locationActivity = new Intent(CountryActivity.this, LocationActivity.class);
                    locationActivity.putExtra(
                            "countryCode",
                            countriesCode.get(countriesSpinner.getSelectedItemPosition()));
                    locationActivity.putExtra("city", citiesSpinner.getSelectedItem().toString());
                    startActivity(locationActivity);
                }
            }
        });

    }




}
