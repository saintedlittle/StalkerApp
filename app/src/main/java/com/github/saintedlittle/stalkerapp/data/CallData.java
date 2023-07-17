package com.github.saintedlittle.stalkerapp.data;

import com.google.firebase.Timestamp;

import java.util.Date;

public class CallData {
    private final String caller;

    private final String phone;
    private final Timestamp timestamp;
    private final int duration;

    public CallData(String caller, String phone, long timestamp, int duration) {
        this.caller = caller;
        this.phone = phone;
        this.timestamp = new Timestamp(new Date(timestamp));
        this.duration = duration;
    }

    public String getCaller() {
        return caller;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getDuration() {
        return duration;
    }

    public String getPhone() {
        return phone;
    }
}

