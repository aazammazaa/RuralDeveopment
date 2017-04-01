package com.sirt.teamdrd.ruraldeveopment.Activity.Util;

import android.content.Context;
import android.content.SharedPreferences;


import com.sirt.teamdrd.ruraldeveopment.Activity.Util.app.RuralDevelopment;

/**
 * Created by Aazam on 19/11/2016.
 */

public class SharedPrefrencesManager {
    private SharedPrefrencesManager(){

    }
    private static SharedPrefrencesManager manager ;
    private SharedPreferences sharedPreferences ;

    public static SharedPreferences getSharedPreferencesInstances(){
        if(manager == null){
            manager = new SharedPrefrencesManager();
            Context context = RuralDevelopment.getInstance();
            manager.sharedPreferences = context.getSharedPreferences(Constant.PAYTM_WALLET_PREFERENCE_FILE_KEY, context.MODE_PRIVATE);

        }
        return manager.sharedPreferences;
    }

    public void deleteAll() {
        getSharedPreferencesInstances().edit().clear().commit();
    }
    /*
    Save string data in Sharedprefrences
     */
    public static void setPreference(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferencesInstances().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public  void setPreference(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferencesInstances().edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setPreference(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferencesInstances().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void removePreference(String key) {
        SharedPreferences.Editor editor = getSharedPreferencesInstances().edit();
        editor.remove(key);
        editor.commit();
    }

    /*
Fetching saved string data from Sharedprefrences
 */
    public static String getStringPreference(String key, String defValue) {
        return getSharedPreferencesInstances().getString(key, defValue);
    }

    public static int getIntPreference(String key, int defValue) {
        return getSharedPreferencesInstances().getInt(key, defValue);
    }

    public static Boolean getBooleanPreference(String key, boolean defValue) {
        return getSharedPreferencesInstances().getBoolean(key, defValue);
    }

}
