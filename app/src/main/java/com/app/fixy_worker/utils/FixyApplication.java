package com.app.fixy_worker.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDex;


/**
 * Created by Shubham verma on 14-08-2018.
 */


public class FixyApplication extends Application {

    private static FixyApplication instance;
    public static final String TAG = NassApplication.class
            .getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MultiDex.install(this);
        Foreground.init(this);

    }

    public static FixyApplication getInstance() {
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
