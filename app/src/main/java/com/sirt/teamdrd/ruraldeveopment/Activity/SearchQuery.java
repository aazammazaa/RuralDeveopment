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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.Activity.Adapter.QueryFoundAdapter;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Constant;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.SharedPrefrencesManager;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchQuery extends BaseActivity {
    RecyclerView queryRecycler;
    QueryFoundAdapter queryAdapter;
    Boolean myQuery;
    ImageButton searchButton;
    EditText searchKeyword;

    RelativeLayout rl;

    JSONObject searchJsonObject;
    JSONArray searchJsonArray;

    String type ;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_query);
        Bundle bundle = getIntent().getExtras();
        myQuery = bundle.getBoolean("MY_QUERIES");
        searchJsonObject = new JSONObject();
        searchJsonArray = new JSONArray();
        searchKeyword = (EditText) findViewById(R.id.searchkeyword);
        if(myQuery){
            rl = (RelativeLayout) findViewById(R.id.myquestion);
            rl.setVisibility(View.VISIBLE);
            type = "2";
            query ="";
            onSearchQueries(searchJsonObject);

        }else{
            type = "3";
            onSearchQueries(searchJsonObject);
        }

        searchButton = (ImageButton) findViewById(R.id.searchbutton);

        queryRecycler = (RecyclerView) findViewById(R.id.query_recycler);
        queryAdapter = new QueryFoundAdapter(SearchQuery.this,searchJsonArray);

        RecyclerView.LayoutManager rlm= new LinearLayoutManager(this);
        queryRecycler.setLayoutManager(rlm);
        queryRecycler.setItemAnimator(new DefaultItemAnimator());
        queryRecycler.setAdapter(queryAdapter);
        //queryRecycler.setOn




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = searchKeyword.getText().toString();
                onSearchQueries(searchJsonObject);
            }
        });


    }

    @Override
    public void showProgress(Boolean show, String tag) {

    }
    int status = 0;
    @Override
    public void onSuccess(JSONObject response, String tag) {
        if(tag.equals(Constant.SEARCH_RURAL+type)){
            Log.e("ERROR", response.toString());
            try {
                status = response.getInt("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(status == 200){
                try {
                    searchJsonArray = new JSONArray(response.getString("data"));

                    queryAdapter = new QueryFoundAdapter(SearchQuery.this, searchJsonArray);
                    queryRecycler.setAdapter(queryAdapter);
                    //JSONObject jobj = new JSO(response.getString("data"));
                    Log.e("ERROR", searchJsonArray.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else  {
                try {
                    Toast.makeText(SearchQuery.this,response.getString("msg"), Toast.LENGTH_SHORT).show();
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
            //searchJsonObject.put("user_id", SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));
            /*if(searchKeyword.getText().equals("")){
                searchJsonObject.put("query_detail", "%"+""+ "%");
            }else{
                searchJsonObject.put("query_detail", "%"+searchKeyword.getText()+ "%");
            }*/
            searchJsonObject.put("query_detail", "%"+query+ "%");
            if(myQuery){
                searchJsonObject.put("search_type", "0");
                searchJsonObject.put("user_id", SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));
            }else{
                searchJsonObject.put("search_type", "1");
                searchJsonObject.put("user_id", "");
            }

            //searchJsonObject.put("user_id", SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));

            loadJsonData(Constant.SEARCH_RURAL, searchJsonObject.toString(), Constant.SEARCH_RURAL+type);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
