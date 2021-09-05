package com.example.velm.testlib.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.velm.testlib.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by velmmuru on 8/1/2017.
 */

public class PushNotificationUtils {

    String TAG = "PushNotificationUtils";

    public void handleFCMData(Class<?> activity,Context context,JSONObject jsonObject){
        {
            Log.e(TAG, "push json: " + jsonObject.toString());

            try {
                //JSONObject data = json.getJSONObject("data");

                String message = jsonObject.getString("title");
                String title = jsonObject.getString("title");
                //String message = data.getString("message");
                //boolean isBackground = data.getBoolean("is_background");
                String imageUrl = jsonObject.getString("image");
                //String timestamp = data.getString("timestamp");
                //JSONObject payload = data.getJSONObject("payload");

                // Log.e(TAG, "title: " + title);
                Log.e(TAG, "message: " + message);
                //Log.e(TAG, "isBackground: " + isBackground);
                //Log.e(TAG, "payload: " + payload.toString());
                Log.e(TAG, "imageUrl: " + imageUrl);
                //Log.e(TAG, "timestamp: " + timestamp);
                if(imageUrl == null || imageUrl.length() == 0){
                    pushNotificationWithoutImage(activity,context,title,message);
                } else {
                    pushNotificationWithImage(activity,context,title,message,imageUrl);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }}
    }


    public void pushNotificationWithImage(Class<?> activity,Context context,String title, String message, String image){
        Bitmap bitmap = getBitmapFromURL(image);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.profile);
        Intent intent = new Intent( context ,activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity( context , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setLargeIcon(icon)/*Notification icon image*/
                .setSmallIcon(R.drawable.profile)
                .setContentTitle(message)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap))/*Notification with Image*/
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

    public void pushNotificationWithoutImage(Class<?> activity,Context context,String title, String message) {
        Intent intent = new Intent(context,activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity(context , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.profile)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel( true )
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
