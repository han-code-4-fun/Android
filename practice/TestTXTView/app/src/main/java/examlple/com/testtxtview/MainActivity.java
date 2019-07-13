package examlple.com.testtxtview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView upTXT;
    TextView lowerTXT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upTXT = findViewById(R.id.tv1);
        lowerTXT = findViewById(R.id.tv2);

        upTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lowerTXT.setBackgroundColor(getResources().getColor(R.color.deactive));
                upTXT.setBackgroundColor(getResources().getColor(R.color.active));
            }
        });

        lowerTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upTXT.setBackgroundColor(getResources().getColor(R.color.deactive));
                lowerTXT.setBackgroundColor(getResources().getColor(R.color.active));
            }
        });

    }
}
