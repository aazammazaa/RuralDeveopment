package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Constant;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.SharedPrefrencesManager;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends BaseActivity {
//    Button btnOtherQueries;
//    Button btnMyQueries;
    Button btnAskQuery;
    JSONObject jsonObjectPost;
    EditText queryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnAskQuery = (Button) findViewById(R.id.queryask);
        btnAskQuery.setOnClickListener(askQueryButton());

        jsonObjectPost = new JSONObject();
        queryText = (EditText) findViewById(R.id.querytext);
        /*btnMyQueries = (Button) findViewById(R.id.myqueries);
        btnMyQueries.setOnClickListener(myQueryiesButton());

        btnOtherQueries = (Button) findViewById(R.id.otherqueries);
        btnOtherQueries.setOnClickListener(otherQueriesButton());*/

    }

    /*private View.OnClickListener otherQueriesButton() {
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
    }*/

    private View.OnClickListener askQueryButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!queryText.getText().toString().equals("")){

                    SharedPrefrencesManager.setPreference(Constant.CURRENT_QUESTION_DETAILS, queryText.getText().toString());
                    queryText.setText("");
                    Intent in = new Intent(HomeActivity.this, MachedQuries.class);
                    startActivity(in);

                }else{
                    Toast.makeText(HomeActivity.this, "Please fill the Query Box", Toast.LENGTH_SHORT).show();
                }



            }
        };
    }

    @Override
    public void showProgress(Boolean show, String tag) {

    }
    int status = 0;
    @Override
    public void onSuccess(JSONObject response, String tag) {
        /*Log.e("ERROR", response.toString());
        try {
            status = response.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(status == 200){
            try {
                queryText.setText("");
                SharedPrefrencesManager.setPreference(Constant.CURRENT_QUESTION_ID, response.getString("query_id").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent in = new Intent(HomeActivity.this, MachedQuries.class);
            startActivity(in);
        }else {
            try {
                Log.e("ERROR", response.getString("msg"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/


    }

    @Override
    public void onError(VolleyError error, String message, String tag) {
        Log.e("ERROR", message + SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));
    }

    /*public void postQuery(JSONObject jsonObjectPost, String queryDetails){
        try {
            SharedPrefrencesManager.setPreference(Constant.CURRENT_QUESTION_DETAILS, queryDetails);
            jsonObjectPost.put("query_detail",queryDetails);
            jsonObjectPost.put("query_type","0");
            jsonObjectPost.put("query_id","");
            jsonObjectPost.put("user_id", SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));

            loadJsonData(Constant.POST_RURAL, jsonObjectPost.toString(), Constant.POST_RURAL);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/
}
