package com.example.velm.testlib.model;

/**
 * Created by velmmuru on 7/28/2017.
 */

public class Dashboard {

    private String item;
    private int count;

    public Dashboard(String item, int count) {
        this.item = item;
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
