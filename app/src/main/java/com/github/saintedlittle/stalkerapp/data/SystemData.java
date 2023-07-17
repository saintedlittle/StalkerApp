package com.github.saintedlittle.stalkerapp.data;

import com.google.firebase.Timestamp;

import java.util.Date;

public class SystemData {
    private String imei;
    private String deviceName;
    private String ip;
    private String country;
    private String timezone;
    private Timestamp timestamp;

    public String getImei() { return imei; }
    public void setImei(String value) { this.imei = value; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String value) { this.deviceName = value; }

    public String getIP() { return ip; }
    public void setIP(String value) { this.ip = value; }

    public String getCountry() { return country; }
    public void setCountry(String value) { this.country = value; }

    public String getTimezone() { return timezone; }
    public void setTimezone(String value) { this.timezone = value; }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date date) {
        timestamp = new Timestamp(date);
    }

}
