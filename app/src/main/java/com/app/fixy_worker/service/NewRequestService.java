package com.app.fixy_worker.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Consts;
import com.app.fixy_worker.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewRequestService extends Service {


    Utils utils;
    int RECALL =10000;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(runnable,RECALL);
            hitIncomingRequest();
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        if (utils == null){
            utils = new Utils(getApplicationContext());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
            Notification notification =
                    new Notification.Builder(this,"9989")
                            .setSmallIcon(R.mipmap.ic_splash_logo)
                            .setContentIntent(pendingIntent)
                            .setColor(ContextCompat.getColor(getApplicationContext(),R.color.black))
                            .build();
            startForeground(9989, notification);
        }
        Log.e("New request s","service call hit");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 2000);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    public void hitIncomingRequest() {
        if (utils.getBoolean(Consts.FOURGROUND,false)) {
            ApiInterface apiInterface = RetrofitClient.getInstance();

            Call<RequestModel> call = apiInterface.incomming_request(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    utils.getString(InterConst.DEVICE_ID, ""));
            call.enqueue(new Callback<RequestModel>() {
                @Override
                public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {

                    if (utils.getBoolean(Consts.FOURGROUND, false) &&
                            utils.getInt(InterConst.ON_BOOKING, InterConst.ZERO) == InterConst.ZERO) {

                        Intent intent = new Intent(InterConst.INCOMING_BROADCAST);
                        intent.putExtra(InterConst.EXTRA, InterConst.SHOW);
                        // call broadcast of NewIncomingPopoupActivity
                        if (response.body().getResponse().size() > 0) {
                            sendBroadcast(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<RequestModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("service", "In onDestroy");
//        Toast.makeText(this, "Service Detroyed!", Toast.LENGTH_SHORT).show();
    }


}
