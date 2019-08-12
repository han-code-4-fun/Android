package examlple.com.testappsmssender;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
/*
 *  send message between two emulator android
 *  Create an application to send message between two emulators.
 */
import android.app.Activity;
import android.app.PendingIntent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final int REQUEST_READ_PHONE_STATE = 1;
    PendingIntent pi;
    SmsManager sms;

    TextView result;
    EditText numberToSend;
    String receiverNumber;
    String message;
    EditText editText;
    String senderNumber;
    EditText etTestShowEmpty;

    private static String[] remarks = {
            "STARBUCKS",
            "Shoppers Drug Mart",
            "TNT supermarket",
            "Random Sushi",
            "Super Ramen",
            "Levis",
            "Costco",
            "Movie",
            "Hilton Hotel",
            "Canada air",
            "Avis rental",
            "Fido",
            "BC Hydro"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        JodaTimeAndroid.init(this);

        editText = findViewById(R.id.editText);
        numberToSend = findViewById(R.id.editText2);
        etTestShowEmpty = findViewById(R.id.ed_test_empty);


        final EditText editText_custom_body = findViewById(R.id.et_custom_msg);

        Button btnSendCustomMSG = findViewById(R.id.custom_msg_btn);
        btnSendCustomMSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCustomMESSAGE(editText_custom_body.getText().toString());
            }
        });

        Button btnShowEditText = findViewById(R.id.button_show_edittext);
        btnShowEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(
                        MainActivity.this,
                        "The content in edittext is ->" + etTestShowEmpty.getText().toString() + "<-",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
//        String msg = "android.telephony.SmsManager.STATUS_ON_ICC_SENT";
//        PendingIntent piSent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent(msg), 0);
//
//        sms = SmsManager.getDefault();
//        sms.sendTextMessage("5556", null, "This is sample test message", piSent, null);
        result = findViewById(R.id.textViewResult);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }


        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                } else {
                    sendSMSMessage("general");
                }

            }
        });

        Button btn1msg = findViewById(R.id.button_one_msg);
        btn1msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                } else {
                    sendSMSOneMessage();
                }
            }
        });


        Button openSmsActivity = findViewById(R.id.button_openresult);
        openSmsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        Button creditcardMSG = findViewById(R.id.button_credit_msg);
        creditcardMSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                } else {
                    sendSMSMessage("credit");
                }
            }
        });
    }

    private void sendCustomMESSAGE(String customMSG){

        receiverNumber = editText.getText().toString();
        senderNumber = "754456";
        try {

            SmsManager smsManager = SmsManager.getDefault();


            smsManager.sendTextMessage(
                    receiverNumber, returnRandomSenderNumber(), customMSG, null, null);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendSMSOneMessage() {
        receiverNumber = editText.getText().toString();
        message = "hello world xxxxxxxxxxxxxxxxxxxxx";
        senderNumber = "754456";
        Log.d("test", "sendSMSMessage:  goes here");
        try {

            SmsManager smsManager = SmsManager.getDefault();


            smsManager.sendTextMessage(
                    receiverNumber, returnRandomSenderNumber(), returnRandomMessage(), null, null);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void sendSMSMessage(String input) {
        receiverNumber = editText.getText().toString();
        message = "hello world xxxxxxxxxxxxxxxxxxxxx";
        senderNumber = "754456";
        Log.d("test", "sendSMSMessage:  goes here");
        try {

            SmsManager smsManager = SmsManager.getDefault();
            sendMSGBatch(smsManager,input);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void sendMSGBatch(final SmsManager smsManager,String input) {

        result.setText("Start sending");
        int number = Integer.parseInt(numberToSend.getText().toString());
        final ArrayList<ArrayList<String>> cases = getTestingCases(number, input);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < cases.size(); i++) {
                    ArrayList<String> temp = cases.get(i);
                    smsManager.sendTextMessage(
                            receiverNumber, temp.get(0), temp.get(1), null, null);
                    Log.d("test_sms", temp.get(0) + "(((())))" + temp.get(1));

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                result.setText("FINISHED SENDING");

                Toast.makeText(MainActivity.this, "FINISHED", Toast.LENGTH_LONG).show();
            }
        }.execute();


    }

    private ArrayList<ArrayList<String>> getTestingCases(int number,String input) {
        ArrayList<ArrayList<String>> output = new ArrayList<>();


        for (int i = 0; i < number; i++) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(returnRandomSenderNumber());
            if(input.equals("general")){

                temp.add(returnRandomMessage());
            }else{
                temp.add(returnCreditCardMessage());
            }

            output.add(temp);
        }


        return output;
    }


    private String returnRandomSenderNumber() {
        String rbcNumber = "722258";

        String[] numbers = {"754456", rbcNumber,"7788636348"};
        return numbers[(int) (Math.random() * numbers.length)];

    }

    private String returnRandomMessage() {
        String[] s1 = {"Purchase of $", "RBC: Withdrawal of $", "RBC: Deposit of $"};
        String[] s2 = {" from RBC credit card 1121 made ", " from RBC acct 4784 made ", " to RBC acct 4784 made "};
        String amount = String.valueOf(getRandomAmountBetween3000());
        StringBuilder builder = new StringBuilder();


        int tempPos = (int) (Math.random() * s1.length);


        Log.d("test_sms", "returnRandomMessage: tempPos -> " + tempPos);
        builder.append(s1[tempPos]);
        builder.append(amount);
        builder.append(s2[tempPos]);
        int days = (int) (Math.random() * 180);
        String time = DateTimeFormat.forPattern("MMMdd").print(LocalDate.now().minusDays(days));
        time = time.toUpperCase();
        builder.append(time);
        if (tempPos == 0) {
            builder.append(" at ");
            builder.append(remarks[(int) (Math.random() * remarks.length)]);
        }

        builder.append(". Stop-txt STOP/Help-txt HELP");


        return builder.toString();

    }

    private String returnCreditCardMessage() {
        String[] s1 = {"Purchase of $", "RBC: Withdrawal of $", "RBC: Deposit of $"};
        String[] s2 = {" from RBC credit card 1121 made ", " from RBC acct 4784 made ", " to RBC acct 4784 made "};
        String amount = String.valueOf(getRandomAmountBetween3000());
        StringBuilder builder = new StringBuilder();


        int tempPos = 0;


        Log.d("test_sms", "returnRandomMessage: tempPos -> " + tempPos);
        builder.append(s1[tempPos]);
        builder.append(amount);
        builder.append(s2[tempPos]);
        int days = (int) (Math.random() * 180);
        String time = DateTimeFormat.forPattern("MMMdd").print(LocalDate.now().minusDays(days));
        time = time.toUpperCase();
        builder.append(time);
        if (tempPos == 0) {
            builder.append(" at ");
            builder.append(remarks[(int) (Math.random() * remarks.length)]);
        }

        builder.append(". Stop-txt STOP/Help-txt HELP");


        return builder.toString();
    }

    private static float getRandomAmountBetween3000() {
        float output = 0;

        Random random = new Random();
        output = random.nextFloat() * 3000;
        output = Math.round(output * 100);
        output = output / 100;

        return output;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;
        }

    }

}