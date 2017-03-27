package com.sirt.teamdrd.ruraldeveopment.Activity.Util.Network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Constant;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aazam on 19/11/2016.
 */

public class RequestObjectJson extends JsonObjectRequest {

    private String acceptType;
    private int method;

    public RequestObjectJson(int method, String url, JSONObject jsonObject,
                             Response.Listener<JSONObject> listener, Response.ErrorListener errorListener,
                             String acceptType) {
        super(method, url, jsonObject, listener, errorListener);
        this.acceptType = acceptType;
        this.method = method;
        this.setRetryPolicy(getRetryPolicy());
    }

    public RequestObjectJson(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        this.acceptType = Constant.CONTENT_TYPE_JSON;
        this.method = Method.POST;
        this.setRetryPolicy(getRetryPolicy());
        Log.i("request","Volley constructor");
    }



    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
            headers.put("Accept", this.acceptType);
        //headers.put("Content-Type", Constant.CONTENT_TYPE_JSON);
         /*        if (this.method == Method.GET) {*/
             /* headers.put("Content-Type", Constant.CONTENT_TYPE_JSON);

           headers.put("charset", Constant.CHARSET);
        }*/
       /* String token = SharedPrefrencesManager.getStringPreference(Constant.TOKEN,Constant.DEFVALUE);
        if(token != null) {
            headers.put("Authorization", "Token " + token);
        }*/
        //headers.put(REQUEST_HEADER_DEVICE_ID,Constants.device_id);
        //	headers.put(REQUEST_HEADER_AUTH_TOKEN, IANappApplication.getInstance().SESSION_ID);
        //headers.put(REQUEST_HEADER_USERID,IANappApplication.getInstance().LOGGED_IN_USED_ID);
        return headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.i("Encoding error", "parseNetworkResponse: "+ response.toString());
            if (this.method == Method.GET) {
                return Response.success(
                        new JSONObject(jsonString),
                        enforceClientCaching(
                                HttpHeaderParser.parseCacheHeaders(response),
                                response));
            } else {
                return Response.success(new JSONObject(jsonString),
                        HttpHeaderParser.parseCacheHeaders(response));
            }

        } catch (UnsupportedEncodingException e) {

            Log.i("Encoding error", "parseNetworkResponse: "+ e.toString());
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            Log.i("json error", "parseNetworkResponse: "+ je.toString());
            return Response.error(new ParseError(je));
        }
    }

    //Cache request for atleast 24 hrs
    protected static final int defaultClientCacheExpiry = 1000 * 60 * 60 * 24;

    protected Cache.Entry enforceClientCaching(Cache.Entry entry,
                                               NetworkResponse response) {
        if (getClientCacheExpiry() == null)
            return entry;

        long now = System.currentTimeMillis();

        if (entry == null) {
            entry = new Cache.Entry();
            entry.data = response.data;
            entry.etag = response.headers.get("ETag");
            entry.softTtl = now + getClientCacheExpiry();
            entry.ttl = entry.softTtl;
            entry.serverDate = now;
            entry.responseHeaders = response.headers;
        } else if (entry.isExpired()) {
            entry.softTtl = now + getClientCacheExpiry();
            entry.ttl = entry.softTtl;
        }

        return entry;
    }

    protected Integer getClientCacheExpiry() {
        return defaultClientCacheExpiry;
    }

    /**
     * Overriding default timeout of Volley Network Socket Requests
     */
    //@Override
    public RetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy(Constant.NETWORK_SOCKET_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }
}
