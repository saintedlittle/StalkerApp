package com.github.saintedlittle.stalkerapp.fetcher;

import android.content.Context;
import android.os.Build;

import com.github.saintedlittle.stalkerapp.data.SystemData;

import java.util.Date;

public class SystemDataFetcher {
    private final Context context;

    public SystemDataFetcher(Context context) {
        this.context = context;

    }

    public SystemData fetchSystemData() {
        SystemData systemData = new SystemData();

        String deviceName = Build.MODEL;
        systemData.setDeviceName(deviceName);

        String country = getCountry();
        systemData.setCountry(country);

        String timezone = getTimezone();
        systemData.setTimezone(timezone);

        systemData.setTimestamp(new Date());

        return systemData;
    }

    private String getCountry() {
        return context.getResources().getConfiguration().locale.getDisplayCountry();
    }

    private String getTimezone() {
        return java.util.TimeZone.getDefault().getID();
    }
}