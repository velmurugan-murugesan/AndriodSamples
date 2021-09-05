package com.example.velm.testlib.daoimpl;

import android.content.Context;
import android.util.Log;

import com.example.velm.testlib.asynctask.AsyCallback;
import com.example.velm.testlib.asynctask.AsyncTaskMain;
import com.example.velm.testlib.dao.EnquiresDao;
import com.example.velm.testlib.utils.FirebaseUtils;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by velmmuru on 8/2/2017.
 */

public class EnquiresDaoImpl implements EnquiresDao{




    String TAG = "EnquiresDaoImpl";

    public void getAllEnquires(AsyCallback callback, Context context) {

        Log.d(TAG,"getAllEnquires");
        Query query = FirebaseUtils.getEnquiresDBreference();
        AsyncTaskMain asyncTaskMain = new AsyncTaskMain(callback,context,query);
        asyncTaskMain.execute();

    }

    public void getFollowupSmsEnquires(AsyCallback callback, Context context){
        Query query = FirebaseUtils.getEnquiresDBreference().orderByChild("dateTime").startAt(getSmsDate()).endAt(getSmsDate()+"\uf8ff");
        AsyncTaskMain asyncTaskMain = new AsyncTaskMain(callback,context,query);
        asyncTaskMain.execute();
    }


    public void getFollowupCallsEnquires(AsyCallback callback, Context context){
        Query query = FirebaseUtils.getEnquiresDBreference().orderByChild("dateTime").startAt(getCallDate()).endAt(getCallDate()+"\uf8ff");
        AsyncTaskMain asyncTaskMain = new AsyncTaskMain(callback,context,query);
        asyncTaskMain.execute();
    }


    private String getSmsDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE, -1);
        Date date =  yesterday.getTime();

        return dateFormat.format(date);
    }


    private String getCallDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE, -2);
        Date date =  yesterday.getTime();

        return dateFormat.format(date);
    }



}
