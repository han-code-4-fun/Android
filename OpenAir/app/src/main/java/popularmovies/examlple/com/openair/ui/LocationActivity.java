package popularmovies.examlple.com.openair.ui;

import androidx.appcompat.app.AppCompatActivity;
import popularmovies.examlple.com.openair.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class LocationActivity extends AppCompatActivity {

    public static final String LOG_TAG = LocationActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Intent intent = getIntent();

        if(intent.hasExtra("countryCode") && intent.hasExtra("city"))
        {

        }else{
            Log.e(LOG_TAG, "error passing Intent data from CountryActivity");
        }

    }
}
