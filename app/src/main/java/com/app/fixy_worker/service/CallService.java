package com.app.fixy_worker.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class CallService extends Service {

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e("service call","hit CallService");
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.postDelayed(runnable,3000);
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
