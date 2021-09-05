package com.example.velm.testlib.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.example.velm.testlib.model.Enquires;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by velmmuru on 8/2/2017.
 */

public class AsyncTaskMain extends AsyncTask<Object, Object, Object> {

    private AsyCallback callback;

    Context context;

    List<Enquires> enquiresList = new ArrayList<>();
    ProgressDialog progressDialog;

    Query query ;


    String TAG = "AsyncTaskMain";

    public AsyncTaskMain(AsyCallback callback, Context context, Query query){
        Log.d(TAG,"AsyncTaskMain");

        this.callback =  callback;
        this.context = context;
        this.query = query;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        enquiresList = new ArrayList<>();

        progressDialog = ProgressDialog.show(context,
                "ProgressDialog",
                "Wait!");

        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
    }

    @Override
    protected List<Enquires> doInBackground(Object... strings) {
        Log.d(TAG,"doInBackground");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                enquiresList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Enquires enquires = dataSnapshot1.getValue(Enquires.class);

                    enquiresList.add(enquires);

                    Log.d(TAG,"Enquire added"+enquires.toString());

                }
                Collections.sort(enquiresList);
                callback.onTaskComplete(enquiresList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return null;

    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        progressDialog.setMessage(String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d(TAG,"onPostExecute = "+enquiresList.toString());

        progressDialog.dismiss();
    }

}
