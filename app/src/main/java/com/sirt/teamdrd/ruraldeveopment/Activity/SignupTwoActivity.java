package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

/**
 * Created by Tanveer on 01-Apr-17.
 */

public class SignupTwoActivity extends BaseActivity {
    ImageButton imbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_two);
        imbtn = (ImageButton) findViewById(R.id.next_button_two);
        imbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SignupTwoActivity.this, FrontActivity.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void showProgress(Boolean show, String tag) {

    }

    @Override
    public void onSuccess(JSONObject response, String tag) {

    }

    @Override
    public void onError(VolleyError error, String message, String tag) {

    }

}
