package com.example.velm.taskmanager.models;

/**
 * Created by velm on 9/8/2017.
 */

public class User {

    private int id;
    private String name ;
    private int pin;

    User(){}

    public User(int id, String name, int pin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
    }
    public User( String name, int pin) {
        this.name = name;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pin=" + pin +
                '}';
    }
}
