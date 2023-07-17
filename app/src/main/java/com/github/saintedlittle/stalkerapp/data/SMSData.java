package com.github.saintedlittle.stalkerapp.data;

import com.google.firebase.Timestamp;

import java.util.Date;

public class SMSData {

    private final String sender;
    private final String message;
    private final Timestamp timestamp;

    public SMSData(String sender, String message, long timestamp) {
        this.sender = sender;
        this.message = message;
        this.timestamp = new Timestamp(new Date(timestamp));
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
