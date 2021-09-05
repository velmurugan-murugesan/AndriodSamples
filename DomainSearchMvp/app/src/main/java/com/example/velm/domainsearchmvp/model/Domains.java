package com.example.velm.domainsearchmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by velmmuru on 9/17/2017.
 */

public class Domains {

    @SerializedName("status")
    private List<Status> status;

    public Domains(List<Status> status) {
        this.status = status;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Domains{" +
                "status=" + status +
                '}';
    }
}
