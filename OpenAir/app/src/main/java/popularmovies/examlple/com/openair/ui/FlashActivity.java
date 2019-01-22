package popularmovies.examlple.com.openair.ui;

import androidx.appcompat.app.AppCompatActivity;
import popularmovies.examlple.com.openair.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        //countriesAdapter button that can be used to skip flash, which in the future, this can be countriesAdapter dynamical image such as an ad
        Button skipButton = findViewById(R.id.skip_button);

        ImageView flash = findViewById(R.id.flash_iv);
        //dynamically assign image, potentially can use for internet image instead of local's
        flash.setImageResource(R.drawable.flash);

        //in the future, this can be used for navigate user to browsers if user clicked the flash image.
        flash.setOnClickListener(null);

        final Thread fromFlashToMain = new Thread()
        {
            @Override
            public void run() {
                try {
                    // make flash show 3 seconds before go to main screen
                    sleep(3000);
                    startMainActivity();
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //a skip button allows user enter mainactivity directly
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromFlashToMain.isAlive()) {fromFlashToMain.interrupt();}
                startMainActivity();
            }
        });

        fromFlashToMain.start();
    }

    private void startMainActivity()
    {
        Intent openMain = new Intent(FlashActivity.this, CountryActivity.class);
        startActivity(openMain);
    }
}
