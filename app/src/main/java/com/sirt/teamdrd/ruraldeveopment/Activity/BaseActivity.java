package com.sirt.teamdrd.ruraldeveopment.Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Constant;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Network.RequestObjectJson;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Network.Volley.VolleyErrorListener;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.app.RuralDevelopment;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Aazam on 19/11/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected FragmentManager fm;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
    }
    ///////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.item1:

                Intent in = new Intent(BaseActivity.this, ProfileActivity.class);
                startActivity(in);
                return true;

        case R.id.item2:
            Toast.makeText(BaseActivity.this,"Logout",Toast.LENGTH_LONG).show();
    }
        return super.onOptionsItemSelected(item);
    }
    /////////
    public abstract void showProgress(Boolean show, String tag);

    public abstract void onSuccess(JSONObject response, String tag);

    public abstract void onError(VolleyError error, String message, String tag);

    protected void loadJsonData(String url, String formattedJson, final String reqTag) {
        loadJsonData(Request.Method.POST, url, formattedJson, reqTag, true);
    }

    protected void loadJsonData(String url, String formattedJson, final String reqTag, Boolean shouldCache) {

    }

    protected void loadJsonData(int type, String url, String formattedJson,
                                final String reqTag, boolean shouldCache) {
        url = Constant.getBaseAPIUrl() + url;
        //url = "http://www.admin.faizkolkata.com/assets/global/php/login.php";
        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        showProgress(true, reqTag);
        Cache cache = RuralDevelopment.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(url);
        if (entry != null) {
            try {
//                String data = new String(entry.data, Constants.CHARSET);
                String data = new String(entry.data, Constant.CONTENT_TYPE_JSON);
                JSONObject response = new JSONObject(data);
                Log.i("Cache :", "data cached fron the catche");
                onSuccess(response, reqTag);
            } catch (JSONException je) {
                je.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            showProgress(false, reqTag);

        } else {
            JSONObject reqParams = null;
            try {
                reqParams = TextUtils.isEmpty(formattedJson) ? null
                        : new JSONObject(formattedJson);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            Log.i("request", "Request for Json network");
            RequestObjectJson jsonObjReq = new RequestObjectJson(type, url, reqParams,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("request", "Request recieved 1");
                            onSuccess(response, reqTag);
                            showProgress(false, reqTag);
                        }
                    }, new VolleyErrorListener(getApplicationContext()) {

                @Override
                public void handleVolleyError(VolleyError error,
                                              String message) {
                    onError(error, message, reqTag);
                    showProgress(false, reqTag);
                }
            });
            Log.i("request", "code is ready to hit");
            //jsonObjReq.setShouldCache(shouldCache);
            // Adding request to request queue
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(500,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            RuralDevelopment.getInstance().addToRequestQueue(jsonObjReq);
            //requestQueue.add(jsonObjReq);

        }
    }


}
