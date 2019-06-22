package com.example.android.todolist.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class MyTestSMSReceiver extends BroadcastReceiver {

    private static final String TAG = "MyTestSMSReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Message comes innnnnnnnnnnnnnnnnnnnnnnnn");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[])bundle.get("pdus");
            final SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            }
            if (messages.length > -1) {
                Log.d(TAG, "Message recieved: " + messages[0].getMessageBody());
            }
        }

    }


}
