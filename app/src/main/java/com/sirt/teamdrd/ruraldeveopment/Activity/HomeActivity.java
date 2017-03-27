package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

public class HomeActivity extends BaseActivity {
    Button btnOtherQueries;
    Button btnMyQueries;
    Button btnAskQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnAskQuery = (Button) findViewById(R.id.queryask);
        btnAskQuery.setOnClickListener(askQueryButton());

        btnMyQueries = (Button) findViewById(R.id.myqueries);
        btnMyQueries.setOnClickListener(myQueryiesButton());

        btnOtherQueries = (Button) findViewById(R.id.otherqueries);
        btnOtherQueries.setOnClickListener(otherQueriesButton());

    }

    private View.OnClickListener otherQueriesButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this, SearchQuery.class);
                in.putExtra("MY_QUERIES",false);
                startActivity(in);
            }
        };
    }

    private View.OnClickListener myQueryiesButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this, SearchQuery.class);
                in.putExtra("MY_QUERIES",true);
                startActivity(in);
            }
        };
    }

    private View.OnClickListener askQueryButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this, MachedQuries.class);
                startActivity(in);
            }
        };
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
