package com.example.velm.retrofitexample;

import com.google.gson.annotations.SerializedName;

/**
 * Created by velmmuru on 8/19/2017.
 */

class Movie {

    @SerializedName("name")
    private String name;
    @SerializedName("year")
    private String year;
    @SerializedName("actor")
    private String actor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", actor='" + actor + '\'' +
                '}';
    }
}
