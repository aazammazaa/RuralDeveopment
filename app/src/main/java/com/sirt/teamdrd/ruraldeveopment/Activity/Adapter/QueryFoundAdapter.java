package com.sirt.teamdrd.ruraldeveopment.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sirt.teamdrd.ruraldeveopment.Activity.DiscussionForum;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.Constant;
import com.sirt.teamdrd.ruraldeveopment.Activity.Util.SharedPrefrencesManager;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by Aazam on 26/03/2017.
 */

public class QueryFoundAdapter extends RecyclerView.Adapter<QueryFoundAdapter.QueryFoundViewHolder> {

    Context context;
    JSONArray jsonArray;
    JSONObject jobj;




    public QueryFoundAdapter (Context context, JSONArray jsonArray){
        this.context = context;
        this.jsonArray = jsonArray;
    }

    public  class QueryFoundViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ln;
        TextView tv;

        public QueryFoundViewHolder(View itemView) {
            super(itemView);
            ln = (LinearLayout) itemView.findViewById(R.id.query_adapter);
            tv = (TextView) itemView.findViewById(R.id.question);

        }
    }
    @Override
    public QueryFoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewAdapter = LayoutInflater.from(context).inflate(R.layout.query_adapter, parent,false);

        return new QueryFoundViewHolder(viewAdapter);

    }

    @Override
    public void onBindViewHolder(QueryFoundViewHolder holder, int position) {
        try {
            jobj = jsonArray.getJSONObject(position);
            holder.tv.setText(jobj.getString("query_detail"));
            holder.ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        SharedPrefrencesManager.setPreference(Constant.CURRENT_QUESTION_ID, jobj.getString("query_id").toString());
                        SharedPrefrencesManager.setPreference(Constant.CURRENT_QUESTION_DETAILS,jobj.getString("query_detail"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent in = new Intent(context, DiscussionForum.class);
                    context.startActivity(in);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }




}
