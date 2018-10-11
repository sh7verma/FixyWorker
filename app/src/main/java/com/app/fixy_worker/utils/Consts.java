package com.app.fixy_worker.utils;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Consts {

    public final static String FOURGROUND = "fourground";
    public final static String KILL = "killfkhfksas";
    public final static String REFRESH_TOKEN = "refreshedToken";
    public static final String FILE_NAME = "file_name";
    public static final String OPEN_FROM = "open_from";
    public static final int COMPLETE = 300;
    public static final int RECEIVE = 200;
    public static final int DISPATCH = 100;
    public static final String DOC_PATH = "doc_path";
    public static final String HINT = "hint";
    public static final java.lang.String RULES_REGEX = "-@-!-F-!-@-";

    public static final int FRAG_NULL = 0;
    public static final int FRAG_HOME = 1;
    public static final int FRAG_BOOKINGS = 2;
    public static final int FRAG_COINS = 3;
    public static final int FRAG_PROFILE = 4;



    ///

    public static void hideKeyboard(Activity mContext) {
        // Check if no view has focus:
        View view = mContext.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideKeyboardDialog(View view,Context mContext) {
        // Check if no view has focus:
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static long getUtcTime(long time) {
        long utcTime = 0;
        String dateValue = "";
        try {
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Calendar calUtc = Calendar.getInstance();

            SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            localFormat.setTimeZone(TimeZone.getDefault());
            Calendar calLocal = Calendar.getInstance();

            calLocal.setTimeInMillis(time);
            dateValue = localFormat.format(calLocal.getTime());
            Date value = localFormat.parse(dateValue);

            dateValue = utcFormat.format(value);
            calUtc.setTime(utcFormat.parse(dateValue));
            utcTime = calUtc.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return utcTime;
    }

    public static long getLocalTime(long time) {
        long localTime = 0;
        String dateValue = "";
        try {
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Calendar calUtc = Calendar.getInstance();

            SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            localFormat.setTimeZone(TimeZone.getDefault());
            Calendar calLocal = Calendar.getInstance();

            calUtc.setTimeInMillis(time);
            dateValue = utcFormat.format(calUtc.getTime());
            Date value = utcFormat.parse(dateValue);

            dateValue = localFormat.format(value);
            Date localvalue = localFormat.parse(dateValue);
            localTime = localvalue.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return localTime;
    }
    public static String getDateTime(String timeServer) {
        long utcTime = 0;
        String dateValue = "";
        try {

            SimpleDateFormat serverFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            serverFormat.setTimeZone(TimeZone.getDefault());
            Date value = serverFormat.parse(timeServer);

//            DateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
            DateFormat date = new SimpleDateFormat("dd-MMM");
//            date.setTimeZone(TimeZone.getTimeZone("UTC"));

            DateFormat time = new SimpleDateFormat(" hh:mm a");
//            time.setTimeZone(TimeZone.getTimeZone("UTC"));

            Calendar todayDate = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date.parse(date.format(value)));
            cal2.setTime(date.parse(date.format(todayDate.getTime())));

            dateValue = date.format(value)+" at "+time.format(value);
           /* if(cal1.after(cal2) || cal1.before(cal2)) {
                dateValue = date.format(value)+","+time.format(value);
                // In between
            }
            else {

                dateValue = "Today, "+time.format(value);
            }*/

            Log.d("get trime","utc time "+dateValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateValue;
    }

    public static long getMilisecond(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        try{
            //formatting the dateString to convert it into a Date
            Date date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static String convertMilisecondtoTime(long millis) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

    }
    public static long differnceServerToCurrentTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        Date expiryTIme = null;
        try {
            expiryTIme = dateFormat.parse(time);
            Calendar today = Calendar.getInstance();
            Date presentDate = today.getTime();
            long diffInMs = expiryTIme.getTime() - presentDate.getTime();
//             long leftTime= createdDate.getTime() - diffInMs;
            if (presentDate.getTime() < expiryTIme.getTime()){
                return diffInMs;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

  
}