package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.Activity.Adapter.QueryFoundAdapter;
import com.sirt.teamdrd.ruraldeveopment.Activity.BaseActivity;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Constant;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.SharedPrefrencesManager;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MachedQuries extends BaseActivity {
    RecyclerView queryRecycler;
    QueryFoundAdapter queryFoundAdapter;
    Button btnSubmit;
    JSONObject searchJsonObject;
    TextView queryAmount;
    JSONObject jsonObjectPost;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mached_quries);
        queryRecycler = (RecyclerView) findViewById(R.id.query_recycler);
        jsonArray = new JSONArray();
        queryFoundAdapter = new QueryFoundAdapter(MachedQuries.this,jsonArray);

        RecyclerView.LayoutManager rlm= new LinearLayoutManager(this);
        queryRecycler.setLayoutManager(rlm);
        queryRecycler.setItemAnimator(new DefaultItemAnimator());
        queryRecycler.setAdapter(queryFoundAdapter);

        btnSubmit= (Button) findViewById(R.id.submit);
        btnSubmit.setOnClickListener(submitButton());

        searchJsonObject = new JSONObject();
        jsonObjectPost = new JSONObject();

        onSearchQueries(searchJsonObject);
        queryAmount = (TextView) findViewById(R.id.query_amount);
    }

    private View.OnClickListener submitButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postQuery(jsonObjectPost, SharedPrefrencesManager.getStringPreference(Constant.CURRENT_QUESTION_DETAILS, null));
                /*Intent in = new Intent(MachedQuries.this, DiscussionForum.class);
                startActivity(in);*/
            }
        };
    }

    @Override
    public void showProgress(Boolean show, String tag) {

    }
    int status = 0;
    @Override
    public void onSuccess(JSONObject response, String tag) {
        if(tag.equals(Constant.SEARCH_RURAL+"1")){
            Log.e("ERROR", response.toString());
            try {
                status = response.getInt("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(status == 200){
                try {
                    jsonArray = new JSONArray(response.getString("data"));
                    queryAmount.setText(String.valueOf(jsonArray.length())+" ");
                    queryFoundAdapter = new QueryFoundAdapter(MachedQuries.this, jsonArray);
                    queryRecycler.setAdapter(queryFoundAdapter);
                    //JSONObject jobj = new JSO(response.getString("data"));
                    Log.e("ERROR", jsonArray.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else  {
                try {
                    Toast.makeText(MachedQuries.this,response.getString("msg"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        if (tag.equals(Constant.POST_RURAL + "1")){
            Log.e("ERROR", response.toString());
            try {
                status = response.getInt("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(status == 200){
                try {
                    //queryText.setText("");
                    SharedPrefrencesManager.setPreference(Constant.CURRENT_QUESTION_ID, response.getString("query_id").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent in = new Intent(MachedQuries.this, DiscussionForum.class);
                startActivity(in);
            }else {
                try {
                    Log.e("ERROR", response.getString("msg"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    @Override
    public void onError(VolleyError error, String message, String tag) {

    }
    public  void onSearchQueries(JSONObject searchJsonObject){
        try {
            searchJsonObject.put("user_id", SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));
            searchJsonObject.put("query_detail", "%"+SharedPrefrencesManager.getStringPreference(Constant.CURRENT_QUESTION_DETAILS, null)+ "%");
            searchJsonObject.put("search_type", "1");
            //searchJsonObject.put("user_id", SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));

            loadJsonData(Constant.SEARCH_RURAL, searchJsonObject.toString(), Constant.SEARCH_RURAL+"1");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void postQuery(JSONObject jsonObjectPost, String queryDetails){
        try {

            jsonObjectPost.put("query_detail",queryDetails);
            jsonObjectPost.put("query_type","0");
            jsonObjectPost.put("query_id","");
            jsonObjectPost.put("user_id", SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));

            loadJsonData(Constant.POST_RURAL, jsonObjectPost.toString(), Constant.POST_RURAL + "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
