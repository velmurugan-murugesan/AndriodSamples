package com.example.velm.iot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by velmmuru on 8/21/2017.
 */

public class LightHistory {

    @SerializedName("value")
    private String value;
    @SerializedName("last_inform_date")
    private String lastDate;

    public LightHistory(String value, String lastDate) {
        this.value = value;
        this.lastDate = lastDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public String toString() {
        return "LightHistory{" +
                "value='" + value + '\'' +
                ", lastDate='" + lastDate + '\'' +
                '}';
    }
}
