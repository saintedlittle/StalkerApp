package com.github.saintedlittle.stalkerapp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class PermissionDialog {

    private static final int PERMISSIONS_REQUEST_POST_NOTIFICATIONS = 101;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 102;
    private static final int PERMISSIONS_REQUEST_ACCESS_WIFI_STATE = 103;
    private static final int PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE = 104;
    private static final int PERMISSIONS_REQUEST_BIND_DEVICE_ADMIN = 105;
    private static final int PERMISSIONS_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = 106;
    private static final int PERMISSIONS_REQUEST_BATTERY_STATS = 107;
    private static final int PERMISSIONS_REQUEST_READ_PRIVILEGED_PHONE_STATE = 108;
    private static final int PERMISSIONS_REQUEST_ACCESS_BACKGROUND_LOCATION = 109;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 110;

    private Context context;

    public PermissionDialog(Context context) {
        this.context = context;
    }

    public void requestPermissions() {
        // Request individual permissions here
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission(Manifest.permission.POST_NOTIFICATIONS, PERMISSIONS_REQUEST_POST_NOTIFICATIONS);
        }

        requestPermission(Manifest.permission.READ_PHONE_STATE, PERMISSIONS_REQUEST_READ_PHONE_STATE);
        requestPermission(Manifest.permission.ACCESS_WIFI_STATE, PERMISSIONS_REQUEST_ACCESS_WIFI_STATE);
        requestPermission(Manifest.permission.ACCESS_NETWORK_STATE, PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE);
        requestPermission(Manifest.permission.BIND_DEVICE_ADMIN, PERMISSIONS_REQUEST_BIND_DEVICE_ADMIN);

        requestPermission(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, PERMISSIONS_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        requestPermission(Manifest.permission.BATTERY_STATS, PERMISSIONS_REQUEST_BATTERY_STATS);
        requestPermission(Manifest.permission.READ_PHONE_STATE, PERMISSIONS_REQUEST_READ_PRIVILEGED_PHONE_STATE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION, PERMISSIONS_REQUEST_ACCESS_BACKGROUND_LOCATION);
        }
        requestPermission(Manifest.permission.READ_CONTACTS, PERMISSIONS_REQUEST_READ_CONTACTS);
    }

    private void requestPermission(String permission, int requestCode) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, requestCode);
        }
    }
}