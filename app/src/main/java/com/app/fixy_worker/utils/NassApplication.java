package com.app.fixy_worker.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDex;

/**
 * Created by dev on 17/11/17.
 */

public class NassApplication extends Application {

    private static NassApplication instance;
    public static final String TAG = NassApplication.class
            .getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
//        Foreground.init(this);
        instance = this;
        MultiDex.install(this);

    }

    public static NassApplication getInstance() {
        return instance;
    }

    public static boolean hasNetwork() {
        return instance.checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
