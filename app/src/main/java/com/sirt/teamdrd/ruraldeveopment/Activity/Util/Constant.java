package com.sirt.teamdrd.ruraldeveopment.Activity.Util;

/**
 * Created by Aazam on 19/11/2016.
 */

public class Constant {
    public static final int REQUEST_IMAGE_CAPTURE = 1 ;
    public static final int PICK_PHOTO_FOR_AVATAR =2 ;

    public static final String DEVICE_ID = "DEVICE_ID";



    public static String API_BASE_URL = "http:/10.45.6.1/ruraldevelopment/";//"http://192.168.42.15/sqldevtest/";//"http://192.168.0.100/usedbook/";
    public static String LOGIN_RURAL = "ruralsignin.php";




    //http://192.168.10.235:8000/v1/api/deals/<dealId>/comments/

    public final static String API_BASE_URL_KEY = "API_BASE_URL_KEY";
    public final static String PAYTM_WALLET_PREFERENCE_FILE_KEY = "PAYTM_WALLET_PREFERENCE_FILE_KEY";
    public final static String CHARSET = "utf-8";
    public final static String CONTENT_TYPE_JSON = "application/json";
    public final static int NETWORK_SOCKET_TIMEOUT = 120000;
    public final static String TOKEN = "token";
    public final static String DEFVALUE = "USEDBOOK";
    public static String device_id = null;
    //no off boos should be requested -1
    public static final int BOOKS_PER_REQUEST = 2;

    public static String getDeviceId() {

        return (device_id == null || device_id.isEmpty()) ? "1" : device_id;
    }
    public static void updateAPIUrl(String apiUrl) {

        API_BASE_URL = apiUrl;
        SharedPrefrencesManager.setPreference(API_BASE_URL_KEY, API_BASE_URL);
    }

    public static String getBaseAPIUrl() {

        return API_BASE_URL;
    }

    public static String getAPIUrl(String apiUrl) {

        return API_BASE_URL + apiUrl;
    }
}
