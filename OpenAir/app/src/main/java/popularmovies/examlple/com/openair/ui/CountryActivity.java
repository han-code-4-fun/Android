package popularmovies.examlple.com.openair.ui;

import androidx.appcompat.app.AppCompatActivity;
import popularmovies.examlple.com.openair.R;
import utils.AQURL;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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


        /*test URL
        TextView title = findViewById(R.id.tv_country_title);
        title.setTextSize(20);
        title.setAllCaps(false);
        title.setText(AQURL.getDetailMeasurementURL("CN","台州市", "台州环保大楼").toString());
        */

    }
}
