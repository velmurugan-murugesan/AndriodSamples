package com.example.velm.testlib.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by velmmuru on 8/2/2017.
 */

public class FirebaseUtils {
    static DatabaseReference enquireDBreference;

    public static DatabaseReference getEnquiresDBreference() {

        if (enquireDBreference == null) {

            enquireDBreference = FirebaseDatabase.getInstance().getReference().child("enquires");
            enquireDBreference.keepSynced(true);
            return enquireDBreference;
        }

        return enquireDBreference;

    }


}
