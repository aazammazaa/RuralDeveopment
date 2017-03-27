package com.sirt.teamdrd.ruraldeveopment.Activity.Util;

/**
 * Created by Aazam on 19/11/2016.
 */

public class Constant {
    public static final int REQUEST_IMAGE_CAPTURE = 1 ;
    public static final int PICK_PHOTO_FOR_AVATAR =2 ;

    public static final String DEVICE_ID = "DEVICE_ID";
    public static String APP_INSTALLED_ALREADY = "app_installed_already";
    public static String DOWNLOAD_DONE = "download_done";
    public static String WHATSAPP_DONE = "whatsapp_done";
    public static String RECHARGE_DONE = "recharge_done";
    public static String NUMBER_RECHARGE = "number_recharge";
    public static String USER_NAME = "user_name";

    public static final String APP_CHECK = "com.uc.iflow";
    public static final String APP_CHECK2 = "mappstreet.silentmanager";
    public static final String USER_RETRYING = "USER_RETRYING";
    public static final String REDIRECT_LINK = "http://affiliates.icwonline.in/SH4xh";
    public static final String WhatsappMessage = "Dear user,\n We are happy to announce that we have completed a milestone of 50 million downloads of our PAYTM application So we are giving away some exciting prizes and paytm wallet money just by completing few steps.. Grab a chance to win exciting offers and win paytm balance upto 2500 rupees. Every user will definetly get rewarded. \n\nhttp://www.paytmwallet.tk/ \nTeam PAYTM. ";

    public static String API_BASE_URL = "http://paytmwallet.tk/sqldevtest/";//"http://192.168.42.15/sqldevtest/";//"http://192.168.0.100/usedbook/";
    public static String IMEI_DESCRIPTION = "imeidetail.php";
    public static String UPDATE_DETAIL = "update.php";




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
