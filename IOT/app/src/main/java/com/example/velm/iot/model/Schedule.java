package com.example.velm.iot.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by velmmuru on 8/17/2017.
 */

public class Schedule {

    @SerializedName("start_time")
    private String startTime;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("node_name")
    private String nodeName;
    @SerializedName("resource")
    private String resource;
    @SerializedName("enabled")
    private String enaled;
    @SerializedName("day")
    private String[] days;

    public Schedule(String startTime, String endTime, String nodeName, String resource, String enaled, String[] days) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.nodeName = nodeName;
        this.resource = resource;
        this.enaled = enaled;
        this.days = days;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getEnaled() {
        return enaled;
    }

    public void setEnaled(String enaled) {
        this.enaled = enaled;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", resource='" + resource + '\'' +
                ", enaled='" + enaled + '\'' +
                ", days=" + Arrays.toString(days) +
                '}';
    }
}
