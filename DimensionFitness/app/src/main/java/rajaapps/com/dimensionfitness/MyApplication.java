package rajaapps.com.dimensionfitness;

import android.app.Application;

import com.example.velm.testlib.daoimpl.EnquiresDaoImpl;
import com.example.velm.testlib.utils.PushNotificationUtils;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by velmmuru on 7/26/2017.
 */

public class MyApplication extends Application {


    static PushNotificationUtils pushNotificationUtils;
    static EnquiresDaoImpl enquiresDaoImpl;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //initSingleton();

    }

    public static PushNotificationUtils getPushNotificationInstance(){

        if(pushNotificationUtils == null){
            return new PushNotificationUtils();
        }

        return pushNotificationUtils;
    }

    public static EnquiresDaoImpl getEnquiresDaoImpl(){

        if(enquiresDaoImpl == null){
            return new EnquiresDaoImpl();
        }

        return enquiresDaoImpl;
    }



}
