package com.example.velm.iot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by velmmuru on 8/9/2017.
 */

public class WaterLevelHistory {

    @SerializedName("value")
    private int value;
    @SerializedName("last_inform_date")
    private String lastDate;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
        return "WaterLevelHistory{" +
                "value='" + value + '\'' +
                ", lastDate=" + lastDate +
                '}';
    }
}
