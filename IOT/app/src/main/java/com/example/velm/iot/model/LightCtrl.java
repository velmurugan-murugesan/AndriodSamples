package com.example.velm.iot.model;

/**
 * Created by velmmuru on 8/21/2017.
 */

public class LightCtrl {

    private boolean onOff;


    private String ctrlName;

    private int sensorValue;

    private int nodeNumber;

    public LightCtrl(){}

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }




    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }

    public String getCtrlName() {
        return ctrlName;
    }

    public void setCtrlName(String ctrlName) {
        this.ctrlName = ctrlName;
    }

    public int getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(int sensorValue) {
        this.sensorValue = sensorValue;
    }

    @Override
    public String toString() {
        return "LightCtrl{" +
                "onOff=" + onOff +
                ", ctrlName='" + ctrlName + '\'' +
                ", sensorValue=" + sensorValue +
                '}';
    }
}
