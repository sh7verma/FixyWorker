package com.app.fixy_worker.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressLint("NewApi")
public class Foreground implements Application.ActivityLifecycleCallbacks {
    public static final long CHECK_DELAY = 500;
    public static final String TAG = Foreground.class.getName();
    public static SharedPreferences sp;
    static Context mContext;

    public interface Listener {

        public void onBecameForeground();

        public void onBecameBackground();

    }

    private static Foreground instance;

    private boolean foreground = false, paused = true;
    private Handler handler = new Handler();
    private List<Listener> listeners = new CopyOnWriteArrayList<Listener>();
    private Runnable check;

    /**
     * Its not strictly necessary to use this method - _usually_ invoking get
     * with a Context gives us a path to retrieve the Application and
     * initialise, but sometimes (e.g. in test harness) the ApplicationContext
     * is != the Application, and the docs make no guarantees.
     *
     * @param application
     * @return an initialised Foreground instance
     */
    @SuppressLint("NewApi")
    public static Foreground init(Application application) {

        sp = application.getSharedPreferences(application.getPackageName(),Context.MODE_PRIVATE);
        if (instance == null) {
            instance = new Foreground();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static Foreground get(Application application) {
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    public static Foreground get(Context ctx) {
        if (instance == null) {
            Context appCtx = ctx.getApplicationContext();
            mContext = ctx.getApplicationContext();
            if (appCtx instanceof Application) {
                init((Application) appCtx);
            }
            throw new IllegalStateException(
                    "Foreground is not initialised and "
                            + "cannot obtain the Application object");
        }
        return instance;
    }

    public static Foreground get() {
        if (instance == null) {
            throw new IllegalStateException(
                    "Foreground is not initialised - invoke "
                            + "at least once with parameterised init/get");
        }
        return instance;
    }

    public boolean isForeground() {
        return foreground;
    }

    public boolean isBackground() {
        return !foreground;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mContext = activity;

        paused = false;
        boolean wasBackground = !foreground;
        foreground = true;

        if (check != null)
            handler.removeCallbacks(check);

        if (wasBackground) {
            Log.i(TAG, "went foreground");
            setOnline();

            for (Listener l : listeners) {
                try {
                    l.onBecameForeground();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.i(TAG, "still foreground");
            setOnline();
        }
    }

    @Override
    public void onActivityPaused(final Activity activity) {
        sp = activity.getSharedPreferences(activity.getPackageName(),
                Context.MODE_PRIVATE);
        mContext = activity;

        paused = true;

        if (check != null)
            handler.removeCallbacks(check);

        handler.postDelayed(check = new Runnable() {
            @Override
            public void run() {
                if (foreground && paused) {
                    foreground = false;
                    Log.i(TAG, "went background");
                    setOffline();
                    for (Listener l : listeners) {
                        try {
                            l.onBecameBackground();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.i(TAG, "still foreground");
                    setOnline();
                }
            }
        }, CHECK_DELAY);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        setkill(false);
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        setkill(true);
    }

    void setOnline() {
        if (mContext != null) {
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
        }
        sp.edit().putBoolean(Consts.FOURGROUND, true).commit();
    }

    void setOffline() {
        sp.edit().putBoolean(Consts.FOURGROUND, false).commit();
    }
    void setkill(boolean flag) {
        sp.edit().putBoolean(Consts.KILL, flag).apply();
    }
}
