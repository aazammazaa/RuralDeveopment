package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.Activity.Adapter.QueryFoundAdapter;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

public class SearchQuery extends BaseActivity {
    RecyclerView queryRecycler;
    QueryFoundAdapter queryAdapter;

    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_query);
        Bundle bundle = getIntent().getExtras();
        Boolean myQuery = bundle.getBoolean("MY_QUERIES");
        if(myQuery){
            rl = (RelativeLayout) findViewById(R.id.myquestion);
            rl.setVisibility(View.VISIBLE);
        }
        queryRecycler = (RecyclerView) findViewById(R.id.query_recycler);
        queryAdapter = new QueryFoundAdapter(SearchQuery.this);

        RecyclerView.LayoutManager rlm= new LinearLayoutManager(this);
        queryRecycler.setLayoutManager(rlm);
        queryRecycler.setItemAnimator(new DefaultItemAnimator());
        queryRecycler.setAdapter(queryAdapter);
        //queryRecycler.setOn

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
