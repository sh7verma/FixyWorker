package com.app.fixy_worker.interfaces;

public interface InterConst {

    public int PICKUP_ADD = 1;
    public int DELIVERY_ADD = 2;
    public int ANT_GO = 1;
    public int ANT_EXPRESS = 2;
    public int ZERO = 0;
    public int ONE = 1;
    public int TWO = 2;
    public String NUMBER_REGISTERED = "number_registered";
    public String STARTFOREGROUND_ACTION = "STARTFOREGROUND_ACTION";
    public String STOPFOREGROUND_ACTION = "STOPFOREGROUND_ACTION";
    public String MAIN_ACTION = "MAIN_ACTION";
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
    String PROFILE_STATUS = "profile_status";
    String PROFILE_IMAGE = "profile_image";

    String ACCESS_TOKEN = "access_token";
    String COUNTRY_CODE = "country_code";
    String PHONE_NUMBER = "phone_number";
    String GENDER = "gender";
    String EMAIL = "email";
    String USER_NAME = "username";
    String USER_ID = "user_id";
    int ERROR_RESULT =405 ;
    int INVALID_ACCESS_TOKEN =404 ;
    int CAMERA_PERMISSION_REQUEST_CODE = 1;
    String SHOW_PIC = "show_pic";
    String NULL = "null";
    String TYPE = "type";
    String EDIT_PROFILE = "1";
    String CREATE_PROFILE = "0";
    String MALE = "male";
    String FEMALE = "female";
    String USER_ROLE = "1";
    String WORKER_ROLE = "2";
    String DEVICE_ID= "device_id";
    String PHONE_STATE = "phone_state";

    // for api purpose
    String USER_TYPE = "2";
    String PLATFORM_TYPE= "1";
    String APPLICATION_MODE = "1";
    String CITY_NAME = "city_name";
    String CITY_ID = "city_id";
    int VERIFY = 1;
    int PROFILE_VERIFY = 4;
    int NOT_VERIFY = 0;
    String NUMBER_VERIFY = "number_verify";
    String AADHAR = "AADHAR";
    String LICENCE= "LICENCE";
    String REFFERAL_CODE = "refferal_code";
    String REFFERAL_COIN = "refferal_coin";
    String NEW_USER_COIN = "new_user_coin";
    String PASS_CONGRATULATION = "PASS_CONGRATULATION";
    int CONNECT = 0;
    int DISCONNECT = 1;
    int SHOW = 1;
    int DISMISS = 0;
    //
    String PUSH = "push_message_from_notification";
    int NEW_REQUEST_PUSH_TYPE = 1;
    // broadcast receiver
    String INCOMING_BROADCAST_OPEN_POPUP = "incomingINCOMING_BROADCAST_OPEN_POPUP";
    String NEW_REQUEST_HIT_API_BROADCAST = "new_request_broadcastNEW_REQUEST_HIT_API_BROADCAST";
//    ---------------------
    String ON_BOOKING = "onbooking";
    String SERVICE_RUNNING = "service_running";
    String ACCEPT_REQUEST = "1";
    String ON_THE_WAY= "2";
    String CONFRIM= "3";
    String COMPLETE = "4";
    String DECLINE_REQUEST = "-1";
    String NEW_REQUEST = "new_request_shared";
    String SCHEDULED = "scheduled_request_shared";
    int SHOW_ADAPTER_SNACKBAR = -2;
    int CANCEL = 1;
    int CONFIRM = 2;

    public enum WEBVIEW{
        TERM_CONDITION;
    }

    public enum GOOGLE{
        NEARBY,AUTOCOMPLETE
    }

    public enum Address{
        HOME,WORK,OTHER
    }
    public enum Docs{
        AADHAR,LICENCE;
    }
}
