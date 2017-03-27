package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.Activity.Adapter.QueryFoundAdapter;
import com.sirt.teamdrd.ruraldeveopment.Activity.BaseActivity;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

public class MachedQuries extends BaseActivity {
    RecyclerView queryRecycler;
    QueryFoundAdapter queryFoundAdapter;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mached_quries);
        queryRecycler = (RecyclerView) findViewById(R.id.query_recycler);
        queryFoundAdapter = new QueryFoundAdapter(MachedQuries.this);

        RecyclerView.LayoutManager rlm= new LinearLayoutManager(this);
        queryRecycler.setLayoutManager(rlm);
        queryRecycler.setItemAnimator(new DefaultItemAnimator());
        queryRecycler.setAdapter(queryFoundAdapter);

        btnSubmit= (Button) findViewById(R.id.submit);
        btnSubmit.setOnClickListener(submitButton());

    }

    private View.OnClickListener submitButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MachedQuries.this, DiscussionForum.class);
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
