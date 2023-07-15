package com.github.saintedlittle.stalkerapp.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemData {
    private String imei;
    private String deviceName;
    private String ip;
    private String country;
    private String timezone;

    @JsonProperty("IMEI")
    public String getImei() { return imei; }
    @JsonProperty("IMEI")
    public void setImei(String value) { this.imei = value; }

    @JsonProperty("DEVICE_NAME")
    public String getDeviceName() { return deviceName; }
    @JsonProperty("DEVICE_NAME")
    public void setDeviceName(String value) { this.deviceName = value; }

    @JsonProperty("IP")
    public String getIP() { return ip; }
    @JsonProperty("IP")
    public void setIP(String value) { this.ip = value; }

    @JsonProperty("COUNTRY")
    public String getCountry() { return country; }
    @JsonProperty("COUNTRY")
    public void setCountry(String value) { this.country = value; }

    @JsonProperty("TIMEZONE")
    public String getTimezone() { return timezone; }
    @JsonProperty("TIMEZONE")
    public void setTimezone(String value) { this.timezone = value; }
}
