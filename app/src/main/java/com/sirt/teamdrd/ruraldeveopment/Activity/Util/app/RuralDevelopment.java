package com.sirt.teamdrd.ruraldeveopment.Activity.Util.app;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Aazam on 26/03/2017.
 */

public class RuralDevelopment extends Application {
    public static final String TAG = RuralDevelopment.class.getSimpleName();
    private static RuralDevelopment mInstance = null;
    private RequestQueue mRequestQueue;
    private File cacheDir;
    private File filesDir;

    public static synchronized RuralDevelopment getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //Toast.makeText(getApplicationContext(),"this is TAG ="+TAG,Toast.LENGTH_LONG).show();
    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        Log.i("request","request queue is called");
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public boolean isNetworkAvailable() {

        final ConnectivityManager connectivityManager = (ConnectivityManager) getInstance()
                .getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public  boolean convertToBoolean(String string){
        if(string.trim().equals("1")){
            return true;
        }else {

            return false;
        }
    }

    @Override
    public File getCacheDir() {
        if (cacheDir == null) {
            cacheDir = (getExternalCacheDir() != null) ? getExternalCacheDir()
                    : super.getCacheDir();
        }

        return cacheDir;
    }

    @Override
    public File getFilesDir() {
        if (filesDir == null) {
            filesDir = (getExternalFilesDir(null) != null) ? getExternalFilesDir(null)
                    : super.getFilesDir();
        }

        return filesDir;
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;*/
    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

}
