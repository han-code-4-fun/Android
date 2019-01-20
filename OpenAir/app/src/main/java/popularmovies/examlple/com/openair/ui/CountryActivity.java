package popularmovies.examlple.com.openair.ui;

import androidx.appcompat.app.AppCompatActivity;
import popularmovies.examlple.com.openair.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class CountryActivity extends AppCompatActivity {
    Spinner countries,cities;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        countries = findViewById(R.id.spinner_country);
        cities = findViewById(R.id.spinner_city);
        submit = findViewById(R.id.btn_submit);


    }
}
