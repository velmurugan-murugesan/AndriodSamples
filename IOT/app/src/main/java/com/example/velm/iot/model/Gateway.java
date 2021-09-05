package com.example.velm.iot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by velmmuru on 8/20/2017.
 */

public class Gateway {


    @SerializedName("clientName")
    private String clientName;

    @SerializedName("clientId")
    private String clientId;

    @SerializedName("lifetime")
    private long lifetime;

    @SerializedName("version")
    private String version;

    @SerializedName("ip")
    private String ip;

    @SerializedName("mac")
    private String mac;

    @SerializedName("post")
    private int post;

    @SerializedName("status")
    private String status;

    public Gateway(){}

    public Gateway(String clientName, String clientId, long lifetime, String version, String ip, String mac, int post, String status) {
        this.clientName = clientName;
        this.clientId = clientId;
        this.lifetime = lifetime;
        this.version = version;
        this.ip = ip;
        this.mac = mac;
        this.post = post;
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "clientName='" + clientName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", lifetime=" + lifetime +
                ", version='" + version + '\'' +
                ", ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                ", post=" + post +
                ", status='" + status + '\'' +
                '}';
    }
}
