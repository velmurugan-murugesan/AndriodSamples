package rajaapps.com.dimensionfitness.firebase;

import android.util.Log;

import com.example.velm.testlib.utils.PushNotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import rajaapps.com.dimensionfitness.activities.MainActivity;
import rajaapps.com.dimensionfitness.MyApplication;

/**
 * Created by Velmurugan on 7/14/2017.
 */



public class AppFirebaseMsgService extends FirebaseMessagingService {
    private static final String TAG = "AppFirebaseMsgService";
    PushNotificationUtils pushNotificationUtils;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Log data to Log Cat
        pushNotificationUtils = MyApplication.getPushNotificationInstance();
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        String data = remoteMessage.getData().toString();
        Log.d(TAG, "Notification Message Body: " + data);

        //Log.d("title",remoteMessage.getData().toString());

        //createNotification(remoteMessage.getNotification().getBody());
        //create notification
        if (remoteMessage.getData().size() > 0) {
            try {
                JSONObject json = new JSONObject(data);

                pushNotificationUtils.handleFCMData(MainActivity.class,getApplicationContext(),json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

    }



}