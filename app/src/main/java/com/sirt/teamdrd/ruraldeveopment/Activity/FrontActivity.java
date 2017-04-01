package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.os.Bundle;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

/**
 * Created by Tanveer on 01-Apr-17.
 */

public class FrontActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_activity);
    }

    @Override
    public void showProgress(Boolean show, String tag) {
        return;
    }

    @Override
    public void onSuccess(JSONObject response, String tag) {
        return;
    }

    @Override
    public void onError(VolleyError error, String message, String tag) {

    }
}
