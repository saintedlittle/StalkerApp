package com.github.saintedlittle.stalkerapp.events;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.github.saintedlittle.stalkerapp.service.StalkerService;

public class ReceiverCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Service Stops", "Ohhhhhhh");
        context.startService(new Intent(context, StalkerService.class));;
    }
}
