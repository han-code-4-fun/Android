package examlple.com.testviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
implements FragmentBottom.FragmentBottomListener,
        FragmentTop.FragmentTopListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        final CrossVM crossVM = ViewModelProviders.of(this).get(CrossVM.class);
        final Button btn = findViewById(R.id.button);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crossVM.countAddOne();
                crossVM.changeLocalSparseBooleanArray();
            }
        });

        final Button open = findViewById(R.id.buton_new_activity);


        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewActivity.class));
            }
        });


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_top, new FragmentTop())
                .replace(R.id.frame_bottom, new FragmentBottom())
                .commit();




    }


    @Override
    public void onBottomClicked() {

    }

    @Override
    public void onTopClicked() {

    }
}
