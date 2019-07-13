package examlple.com.testtoolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    int count;
    Toolbar toolbar;
    String originTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt1 = findViewById(R.id.button1);
        Button bt2 = findViewById(R.id.button2);
        Button bt3 = findViewById(R.id.button3);
        count = 2342;

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set to origin toolbar

                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.menu_1);
                toolbar.
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set to different toolbar

                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.menu_2);
                toolbar.setTitle("ah222222222a2");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toolbar.setTitle("" + (++count));
                toolbar.getMenu().clear();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        return true;
    }
}
