package com.github.saintedlittle.stalkerapp.data;

import com.google.firebase.Timestamp;

import java.util.Date;

public class SystemData {
    private String imei;
    private String deviceName;
    private String ip;
    private String country;
    private String timezone;
    private String simOperatorName;
    private String simSerialNumber;
    private String networkOperatorName;
    private String networkCountryIso;
    private String networkType;

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


    public void setNetworkCountryIso(String networkCountryIso) {
        this.networkCountryIso = networkCountryIso;
    }

    public void setNetworkOperatorName(String networkOperatorName) {
        this.networkOperatorName = networkOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public void setSimSerialNumber(String simSerialNumber) {
        this.simSerialNumber = simSerialNumber;
    }

}
