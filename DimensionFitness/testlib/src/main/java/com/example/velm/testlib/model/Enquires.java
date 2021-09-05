package com.example.velm.testlib.model;

import android.support.annotation.NonNull;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Velmurugan on 7/17/2017.
 */

public class Enquires implements Comparable{
    private String enq_name;
    private String enq_email;
    private String enq_address;
    private String enq_phone;
    private String enq_source;
    private String enq_comments;
    private String enq_followup;
    private String enq_trainer;
    private String dateTime;


    public Enquires(){}

    public Enquires(String enq_name, String enq_email, String enq_address, String enq_phone, String enq_source, String enq_comments, String enq_followup, String enq_trainer, String dateTime) {
        this.enq_name = enq_name;
        this.enq_email = enq_email;
        this.enq_address = enq_address;
        this.enq_phone = enq_phone;
        this.enq_source = enq_source;
        this.enq_comments = enq_comments;
        this.enq_followup = enq_followup;
        this.enq_trainer = enq_trainer;
        this.dateTime = dateTime;
    }

    public String getEnq_name() {
        return enq_name;
    }

    public void setEnq_name(String enq_name) {
        this.enq_name = enq_name;
    }

    public String getEnq_email() {
        return enq_email;
    }

    public void setEnq_email(String enq_email) {
        this.enq_email = enq_email;
    }

    public String getEnq_address() {
        return enq_address;
    }

    public void setEnq_address(String enq_address) {
        this.enq_address = enq_address;
    }

    public String getEnq_phone() {
        return enq_phone;
    }

    public void setEnq_phone(String enq_phone) {
        this.enq_phone = enq_phone;
    }

    public String getEnq_source() {
        return enq_source;
    }

    public void setEnq_source(String enq_source) {
        this.enq_source = enq_source;
    }

    public String getEnq_comments() {
        return enq_comments;
    }

    public void setEnq_comments(String enq_comments) {
        this.enq_comments = enq_comments;
    }

    public String getEnq_followup() {
        return enq_followup;
    }

    public void setEnq_followup(String enq_followup) {
        this.enq_followup = enq_followup;
    }

    public String getEnq_trainer() {
        return enq_trainer;
    }

    public void setEnq_trainer(String enq_trainer) {
        this.enq_trainer = enq_trainer;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String enq_dateTime) {
        this.dateTime = enq_dateTime;
    }


    @Override
    public String toString() {
        return "Name ="+enq_name;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Enquires s1=(Enquires) o;

        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");

        Calendar c = Calendar.getInstance();

        try {
            Date d1 = df3.parse(s1.getDateTime());
            Date d2 = df3.parse(this.getDateTime());
            //Log.d("RETURN = ",d1.compareTo(d2)+"");
            return d1.compareTo(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }
}
