package examlple.com.testappsmssender;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    TextView textView_result;

    String one = "_id -> 10 thread_id -> 3 address -> +15555215558 person -> null date -> 1564090158181 date_sent -> 1564036156000 protocol -> 0 read -> 1 status -> -1 type -> 1 reply_path_present -> 0 subject -> null body -> RBC: Withdrawal of $2496.56 from RBC acct 4784 made APR02. Stop-txt STOP/Help-txt HELP service_center -> null locked -> 0 error_code -> 0 seen -> 1 \n" +
            "\n";
    String two = "_id -> 8 thread_id -> 3 address -> +15555215554 person -> null date -> 1564092538895 date_sent -> 1564038538000 protocol -> 0 read -> 1 status -> -1 type -> 1 reply_path_present -> 0 subject -> null body -> RBC: Deposit of $1686.98 to RBC acct 4784 made FEB18. Stop-txt STOP/Help-txt HELP service_center -> null locked -> 0 sub_id -> -1 error_code -> 0 creator -> com.android.mms seen -> 1 ";
    String three = " _id -> 10 thread_id -> 3 address -> +15555215554 person -> null date -> 1564092543887 date_sent -> 1564038543000 protocol -> 0 read -> 1 status -> -1 type -> 1 reply_path_present -> 0 subject -> null body -> Purchase of $381.69 from RBC credit card 1121 made MAY07 at Costco. Stop-txt STOP/Help-txt HELP service_center -> null locked -> 0 sub_id -> -1 error_code -> 0 creator -> com.android.mms seen -> 1 ";
    String four = " _id -> 15 thread_id -> 4 address -> +15555215554 person -> null date -> 1564093030184 date_sent -> 1564039031000 protocol -> 0 read -> 0 status -> -1 type -> 1 reply_path_present -> 0 subject -> null body -> Purchase of $1102.0 from RBC credit card 1121 made FEB19 at STARBUCKS. Stop-txt STOP/Help-txt HELP service_center -> null locked -> 0 sub_id -> 1 error_code -> 0 creator -> com.google.android.apps.messaging seen -> 1 ";
    String five = " _id -> 547 thread_id -> 7 address -> +15555215558 person -> null date -> 1564093892768 date_sent -> 1564039893000 protocol -> 0 read -> 0 status -> -1 type -> 1 reply_path_present -> 0 subject -> null body -> Purchase of $1165.49 from RBC credit card 1121 made MAR02 at Avis rental. Stop-txt STOP/Help-txt HELP service_center -> null locked -> 0 sub_id -> 1 error_code -> 0 creator -> com.google.android.apps.messaging seen -> 1 ";
    String six = "_id -> 466 thread_id -> 15 address -> +15555215554 person -> null date -> 1564050611092 date_sent -> 1563996613000 protocol -> 0 read -> 0 status -> -1 type -> 1 reply_path_present -> 0 subject -> null body -> Purchase of $354.01 from RBC credit card 1121 made JUL24 at BC Hydro. Stop-txt STOP/Help-txt HELP service_center -> null locked -> 0 sub_id -> 1 error_code -> 0 creator -> com.google.android.apps.messaging seen -> 1 \n" +
            "     ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView_result = findViewById(R.id.sms_result);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("txt_result", "readMSG:   get 11111");
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS},
                    REQUEST_CODE_ASK_PERMISSIONS);
        } else {

            readMSG();

        }

        Button button = findViewById(R.id.button_log);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("txt_result1", one);
                Log.d("txt_result1", two);
                Log.d("txt_result1", three);
                Log.d("txt_result1", four);
                Log.d("txt_result1", five);
                Log.d("txt_result1", six);





            }
        });

        Button androidTime = findViewById(R.id.button_android_date_gettime);
        androidTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date today = new Date();

                textView_result.setText(String.valueOf(today.getTime()));
            }
        });

        final Button jodaTime = findViewById(R.id.joda_get_time);
        jodaTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = "";
                if(textView_result.getText()!= null){
                     t = textView_result.getText().toString();
                }

//                String time = "1564096366968";
//                DateTime jTime = new DateTime(Long.parseLong(t));

//                String format =jTime.toString();

//                DateTime now = new DateTime();
//                String f = now.toString();


                DateTime now = LocalTime.now().toDateTimeToday();


                textView_result.setText(String.valueOf(now.getMillis()));

            }
        });

    }

    private void readMSG() {

        StringBuilder result;


        // Permission is granted
        Log.d("txt_result", "readMSG:   get 3333");

        Cursor cursor = getContentResolver().query(
                Uri.parse("content://sms/inbox"),
                null,
                null,
                null,
                null);

        if (cursor != null) {

            if (cursor.moveToFirst()) { // must check the result to prevent exception
                do {
                    result = new StringBuilder();
                    for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                        result.append(" ").append(cursor.getColumnName(idx)).append(" -> ").append(cursor.getString(idx));
                    }
                    result.append(" \n \n");
                    Log.d("txt_result", "readMSG: \n" + result.toString());

                    // use msgData
                } while (cursor.moveToNext());

            } else {
                // empty box, no SMS
            }


            cursor.close();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("txt_result", "readMSG:   get hhhhhhhhhhherererere " +
                "");



        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("txt_result", "readMSG:   get 222222");

                readMSG();
            }
        }
    }
}
