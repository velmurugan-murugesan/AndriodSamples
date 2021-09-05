package com.example.velm.iot.model;

/**
 * Created by velmmuru on 8/21/2017.
 */

class WaterCtrl {

    private int sensorValue;

    public WaterCtrl(){}

    public WaterCtrl(int sensorValue) {
        this.sensorValue = sensorValue;
    }

    public int getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(int sensorValue) {
        this.sensorValue = sensorValue;
    }

    @Override
    public String toString() {
        return "WaterCtrl{" +
                "sensorValue=" + sensorValue +
                '}';
    }
}
