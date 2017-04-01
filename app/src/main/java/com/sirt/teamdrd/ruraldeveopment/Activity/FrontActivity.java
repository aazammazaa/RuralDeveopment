package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

/**
 * Created by Tanveer on 01-Apr-17.
 */

public class FrontActivity extends BaseActivity {
    public ImageView view1, view2, view3, view4, view5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_activity);
        view1 = (ImageView) findViewById(R.id.ask_question);
        view2 = (ImageView) findViewById(R.id.my_asked_question);
        view3 = (ImageView) findViewById(R.id.public_discussion);
        view4 = (ImageView) findViewById(R.id.govt_policies);
        view5 = (ImageView) findViewById(R.id.profile_imageview);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Intent in = new Intent(getApplicationContext(), SignupTwoActivity.class);
                // startActivity(in);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_in);
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent in = new Intent(getApplicationContext(), SignupTwoActivity.class);
                // startActivity(in);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_in);
            }
        });
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent in = new Intent(getApplicationContext(), SignupTwoActivity.class);
                // startActivity(in);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_in);
            }
        });
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent in = new Intent(getApplicationContext(), SignupTwoActivity.class);
                // startActivity(in);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_in);
            }
        });
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_in);
            }
        });
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
