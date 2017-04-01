package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

/**
 * Created by Tanveer on 01-Apr-17.
 */

public class SignupActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ImageButton btn = (ImageButton) findViewById(R.id.next_button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SignupTwoActivity.class);
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
