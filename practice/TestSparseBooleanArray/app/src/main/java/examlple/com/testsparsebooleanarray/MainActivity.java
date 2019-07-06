package examlple.com.testsparsebooleanarray;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txf = findViewById(R.id.txtview);

        String temp = "";
        SparseBooleanArray a = new SparseBooleanArray();
//        a.put();
        txf.setText(temp);
    }
}
