package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

public class MainActivity extends BaseActivity {
    Button btnLogin;
    TextView txtSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.loginbutton);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(in);
            }
        });
        txtSignUp= (TextView) findViewById(R.id.signup_textview);
        txtSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(in);
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
