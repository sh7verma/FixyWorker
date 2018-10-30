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
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.utils.Consts;
import com.app.fixy_worker.utils.Utils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Utils utils;

    @Override
    public void onCreate() {
        super.onCreate();
        utils = new Utils(getApplicationContext());
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            sendNotification(data);
        }
    }

    private void sendNotification(Map<String, String> data) {

        Intent notificationIntent = null;
        if (data.get("push_type").equals("1")) {
            if (utils.getBoolean(Consts.FOURGROUND, false)) {
                // if app open

                sendBroadcast(new Intent(InterConst.NEW_REQUEST_HIT_API_BROADCAST));
            } else {
                //app closed

                notificationIntent = new Intent(this, LandingActivity.class);
                notificationIntent.putExtra(InterConst.PUSH, InterConst.NEW_REQUEST_PUSH_TYPE);
                generateNotification(data, 1, notificationIntent);// 1 for new request
            }
        }


    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        utils.setString(InterConst.DEVICE_ID, token);

    }

    // [END on_new_token]
    private void generateNotification(Map<String, String> messageBody, int notificationId, Intent notificationIntent) {
        int icon;
        long pattern[] = {100};
        String mess = messageBody.get("message");
        String title = messageBody.get("title");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            icon = R.mipmap.ic_splash_logo;
        } else {
            icon = R.mipmap.ic_splash_logo;
        }


        PendingIntent intent1 = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, String.valueOf(notificationId))
                .setSmallIcon(icon)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentTitle(title)
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
            icon = R.mipmap.ic_splash_logo;
        } else {
            icon = R.mipmap.ic_splash_logo;
        }

        Intent notificationIntent = new Intent(this, LandingActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent intent1 = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, String.valueOf(notificationId))
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