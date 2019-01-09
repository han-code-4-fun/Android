package examlple.com.test_edit_text;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         et = findViewById(R.id.username);


    }

    public void onClick(View view)
    {
        try{

            String temp = et.getText().toString();
            Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {
            Log.e("thisClass", e.getMessage().toString());
        }
    }
}
