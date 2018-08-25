package com.app.fixy.interfaces;

public interface InterConst {

    public int PICKUP_ADD = 1;
    public int DELIVERY_ADD = 2;
    public int ANT_GO = 1;
    public int ANT_EXPRESS = 2;
    public int ZERO = 0;
    public int ONE = 1;
    public int TWO = 2;
    public String NUMBER_REGISTERED = "number_registered";
    public String EXTRA = "extra";
    public String EXTRA2 = "extra2";
    String READ_PHONE_STATE_PERMISSION= "phone_state";
    String LOCATION_DATA_EXTRA="location_extra";
    String RECEIVER ="reciver_location";
    String RESULT_DATA_KEY = ".RESULT_DATA_KEY";
    int SUCCESS_RESULT = 200;
    int HOME = 1;
    int WORK= 2;
    int DEFAULT= -1;
    int OTHER = 3;

    public enum WEBVIEW{
        TERM_CONDITION;
    }

    public enum GOOGLE{
        NEARBY,AUTOCOMPLETE
    }

    public enum Address{
        HOME,WORK,OTHER
    }
}
