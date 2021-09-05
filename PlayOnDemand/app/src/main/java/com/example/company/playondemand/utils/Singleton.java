package com.example.company.playondemand.utils;

/**
 * Created by velmmuru on 9/7/2016.
 */
public class Singleton {


    private static Utils utilInstance = null;


    public static Utils getUtilInstance(){

        if (utilInstance == null){
            return utilInstance = new Utils();
        } else {
            return utilInstance;
        }
    }
}
