package examlple.com.testviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        CrossVM crossVM  = ViewModelProviders.of(this).get(CrossVM.class);

        TextView tv = findViewById(R.id.textView_new_activity);
        tv.setText(String.valueOf(crossVM.getCountLiveData().getValue()));
    }
}
