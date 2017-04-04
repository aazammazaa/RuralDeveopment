package com.sirt.teamdrd.ruraldeveopment.Activity.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.Activity.DiscussionForum;
import com.sirt.teamdrd.ruraldeveopment.Activity.MachedQuries;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Constant;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Network.RequestObjectJson;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Network.Volley.VolleyErrorListener;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.SharedPrefrencesManager;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.app.RuralDevelopment;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Aazam on 26/03/2017.
 */

public class DiscussionForumAdapter extends RecyclerView.Adapter<DiscussionForumAdapter.ForumViewHolder> {
    Context context;
    int size =5;
    JSONArray jaray;
    JSONObject jobj;
    String queryDetail ="";

    public  DiscussionForumAdapter(Context context, JSONArray jaray){
        this.context = context;
        this.jaray = jaray;
    }
    public  class ForumViewHolder extends RecyclerView.ViewHolder{
        public TextView query, name;
        public EditText answer;
        public Button btn;
        public ForumViewHolder(View itemView) {
            super(itemView);
            query = (TextView) itemView.findViewById(R.id.query);
            answer = (EditText) itemView.findViewById(R.id.askquery);
            btn = (Button) itemView.findViewById(R.id.likeorsubmit);
            name = (TextView) itemView.findViewById(R.id.farmer);
        }
    }
    @Override
    public ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewAdapter = LayoutInflater.from(context).inflate(R.layout.forum_adapter, parent,false);
        return new ForumViewHolder(viewAdapter);
    }




    @Override
    public void onBindViewHolder(final ForumViewHolder holder, int position) {

        if(position == 0){
            holder.query.setText(SharedPrefrencesManager.getStringPreference(Constant.CURRENT_QUESTION_DETAILS, null));
            holder.name.setText(SharedPrefrencesManager.getStringPreference(Constant.USER_NAME, null));
            holder.btn.setVisibility(View.GONE);
        }else if(position == size+1){
            holder.query.setVisibility(View.GONE);
            holder.answer.setVisibility(View.VISIBLE);
            holder.btn.setText("Submit");
            holder.name.setText(SharedPrefrencesManager.getStringPreference(Constant.USER_NAME, null));
            jobj= new JSONObject();

            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    queryDetail = holder.answer.getText().toString();
                    if(!queryDetail.trim().equals("")){
                        postQuery(jobj,queryDetail );
                    }
                }
            });

        }else{
            holder.btn.setVisibility(View.GONE);
        }


    }



    @Override
    public int getItemCount() {
        return size +2 ;
    }


    protected void loadJsonData(String url, String formattedJson, final String reqTag) {
        loadJsonData(Request.Method.POST, url, formattedJson, reqTag, true);
    }



    protected void loadJsonData(int type, String url, String formattedJson,
                                final String reqTag, boolean shouldCache) {
        url = Constant.getBaseAPIUrl() + url;
        //url = "http://www.admin.faizkolkata.com/assets/global/php/login.php";
        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        //showProgress(true, reqTag);
        Cache cache = RuralDevelopment.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(url);
        if (entry != null) {
            try {
//                String data = new String(entry.data, Constants.CHARSET);
                String data = new String(entry.data, Constant.CONTENT_TYPE_JSON);
                JSONObject response = new JSONObject(data);
                Log.i("Cache :", "data cached fron the catche");
                onSuccess(response, reqTag);
            } catch (JSONException je) {
                je.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //showProgress(false, reqTag);

        } else {
            JSONObject reqParams = null;
            try {
                reqParams = TextUtils.isEmpty(formattedJson) ? null
                        : new JSONObject(formattedJson);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            Log.i("request", "Request for Json network");
            RequestObjectJson jsonObjReq = new RequestObjectJson(type, url, reqParams,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("request", "Request recieved 1");
                            onSuccess(response, reqTag);
                            //showProgress(false, reqTag);
                        }
                    }, new VolleyErrorListener(context.getApplicationContext()) {

                @Override
                public void handleVolleyError(VolleyError error,
                                              String message) {
                    //onError(error, message, reqTag);
                    //showProgress(false, reqTag);
                }
            });
            Log.i("request", "code is ready to hit");
            //jsonObjReq.setShouldCache(shouldCache);
            // Adding request to request queue
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(500,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            RuralDevelopment.getInstance().addToRequestQueue(jsonObjReq);
            //requestQueue.add(jsonObjReq);

        }
    }

    int status =0;
    private void onSuccess(JSONObject response, String reqTag) {
        if (reqTag.equals(Constant.POST_RURAL + "1")){
            Log.e("ERROR", response.toString());
            try {
                status = response.getInt("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(status == 200){
                try {
                    //queryText.setText("");
                    SharedPrefrencesManager.setPreference(Constant.CURRENT_ANSWER_ID, response.getString("query_id").toString());
                    SharedPrefrencesManager.setPreference(Constant.CURRENT_ANSWER_DETAILS, response.getString("query_id").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent in = new Intent(context.getApplicationContext(), DiscussionForum.class);
                context.startActivity(in);
            }else {
                try {
                    Log.e("ERROR", response.getString("msg"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void postQuery(JSONObject jsonObjectPost, String queryDetails){
        try {

            jsonObjectPost.put("query_detail", queryDetails);
            jsonObjectPost.put("query_type","1");
            jsonObjectPost.put("query_id",SharedPrefrencesManager.getStringPreference(Constant.CURRENT_QUESTION_ID,null));
            jsonObjectPost.put("user_id", SharedPrefrencesManager.getStringPreference(Constant.USER_ID, null));

            loadJsonData(Constant.POST_RURAL, jsonObjectPost.toString(), Constant.POST_RURAL + "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
