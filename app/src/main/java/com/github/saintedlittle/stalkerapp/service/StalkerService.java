package com.github.saintedlittle.stalkerapp.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StalkerService extends Service {

    private final StalkerUtils stalkerUtils = new StalkerUtils();

    private NotificationManager mNM;

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private final int NOTIFICATION = 123456;

    private final IBinder mBinder = new LocalBinder();

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        StalkerService getService() {
            return StalkerService.this;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        stalkerUtils.sendCreateNotify(this);

        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        startForeground(NOTIFICATION, showNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stalkerUtils.sendSuccessNotify(this);


        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stalkerUtils.sendExitNotify(this);
    }


    private static class StalkerUtils {

        public void sendCreateNotify(Service service) {
            Toast.makeText(service, "Служба создана!",
                    Toast.LENGTH_SHORT).show();
        }

        public void sendSuccessNotify(Service service) {
            Toast.makeText(service, "Служба запущена!",
                    Toast.LENGTH_SHORT).show();
        }

        public void sendExitNotify(Service service) {
            Toast.makeText(service, "Служба остановлена!",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private Notification showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = "work..";

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setTicker(text)  // the status text
                .build();

        // Send the notification.
        mNM.notify(NOTIFICATION, notification);

        return notification;
    }

}