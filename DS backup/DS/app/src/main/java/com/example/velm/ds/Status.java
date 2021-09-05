package com.example.velm.ds;

import com.google.gson.annotations.SerializedName;

/**
 * Created by velmmuru on 9/17/2017.
 */

public class Status {

    @SerializedName("name")
    private String name;

    @SerializedName("available")
    private boolean availability;


    public Status(String name, boolean availability) {
        this.name = name;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                ", availability=" + availability +
                '}';
    }
}
