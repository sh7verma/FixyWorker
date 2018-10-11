package com.app.fixy_worker.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.LandingActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

    }
    // [END on_new_token]
    private void newRequestNotification(Map<String, String> messageBody, String mess, int notificationId) {
        int icon;
        long pattern[] = {100};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            icon = R.mipmap.ic_loc_pin;
        } else {
            icon = R.mipmap.ic_loc_pin;
        }

        Intent notificationIntent = new Intent(this, LandingActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent intent1 = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,String.valueOf(notificationId))
                .setSmallIcon(icon)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentTitle(messageBody.get("title"))
                .setContentText(mess)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mess))
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.black))
                .setSound(defaultSoundUri)
                .setVibrate(pattern)
                .setContentIntent(intent1);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(String.valueOf(notificationId),
                    "NOTIFICATION_CHANNEL_1", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));
            notificationChannel.enableVibration(true);
            notificationBuilder.setChannelId(String.valueOf(notificationId));
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(notificationId, notificationBuilder.build());

    }
    // [END on_new_token]
    private void defaultNotification(Map<String, String> messageBody, String mess, int notificationId) {
        int icon;
        long pattern[] = {100};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            icon = R.mipmap.ic_loc_pin;
        } else {
            icon = R.mipmap.ic_loc_pin;
        }

        Intent notificationIntent = new Intent(this, LandingActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent intent1 = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,String.valueOf(notificationId))
                .setSmallIcon(icon)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentTitle(messageBody.get("title"))
                .setContentText(mess)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mess))
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.black))
                .setSound(defaultSoundUri)
                .setVibrate(pattern)
                .setContentIntent(intent1);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(String.valueOf(notificationId),
                    "NOTIFICATION_CHANNEL_1", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));
            notificationChannel.enableVibration(true);
            notificationBuilder.setChannelId(String.valueOf(notificationId));
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(notificationId, notificationBuilder.build());

    }

}