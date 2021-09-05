package com.example.velm.iot.model;

import java.util.List;

/**
 * Created by velmmuru on 8/21/2017.
 */

public class Nodes {

    private String clientName;

    private List<LightCtrl> lightCtrl;

    private List<LightCtrl> motorCtrl;

    private List<WaterCtrl> waterLevel;

    public Nodes(){}

    public Nodes(String clientName, List<LightCtrl> lightCtrl, List<LightCtrl> motorCtrl, List<WaterCtrl> waterLevel) {
        this.clientName = clientName;
        this.lightCtrl = lightCtrl;
        this.motorCtrl = motorCtrl;
        this.waterLevel = waterLevel;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<LightCtrl> getLightCtrl() {
        return lightCtrl;
    }

    public void setLightCtrl(List<LightCtrl> lightCtrl) {
        this.lightCtrl = lightCtrl;
    }

    public List<LightCtrl> getMotorCtrl() {
        return motorCtrl;
    }

    public void setMotorCtrl(List<LightCtrl> motorCtrl) {
        this.motorCtrl = motorCtrl;
    }

    public List<WaterCtrl> getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(List<WaterCtrl> waterLevel) {
        this.waterLevel = waterLevel;
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "clientName='" + clientName + '\'' +
                ", lightCtrl=" + lightCtrl +
                ", motorCtrl=" + motorCtrl +
                ", waterLevel=" + waterLevel +
                '}';
    }
}
