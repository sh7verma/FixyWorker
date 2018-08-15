package com.app.fixy.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Consts {

    public final static String FOURGROUND = "fourground";
    public final static String REFRESH_TOKEN = "refreshedToken";
    public static final String FILE_NAME = "file_name";
    public static final String OPEN_FROM = "open_from";
    public static final int COMPLETE = 300;
    public static final int RECEIVE = 200;
    public static final int DISPATCH = 100;
    public static final String DOC_PATH = "doc_path";
    public static final String HINT = "hint";
    public static final java.lang.String RULES_REGEX = "-@-!-N-!-@-";

    public static final int FRAG_NULL = 0;
    public static final int FRAG_DASHBOARD = 1;
    public static final int FRAG_BILLS = 2;
    public static final int FRAG_COMMITTEE = 3;
    public static final int FRAG_ACTS = 4;
    public static final int FRAG_LIVE = 5;
    public static final int FRAG_SENATE_LEADERSHIP = 11;
    public static final int FRAG_RECENT_ACTIVITIES = 12;
    public static final int FRAG_ABOUT_SENATE = 13;
    public static final int FRAG_PRINCIPAL_OFFICERS = 14;
    public static final int FRAG_CONSTITUTIONAL_ROLES = 15;
    public static final int FRAG_COMPOSITIONS = 16;
    public static final int FRAG_PROFILE = 21;

    public static final int PRESIDENT = 0;
    public static final int DEPUTY = 1;
    public static String DETAIL_ID="detail_id";


    ///


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

  
}