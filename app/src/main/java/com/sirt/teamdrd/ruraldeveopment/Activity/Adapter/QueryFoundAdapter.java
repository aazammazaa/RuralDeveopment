package com.sirt.teamdrd.ruraldeveopment.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sirt.teamdrd.ruraldeveopment.Activity.DiscussionForum;
import com.sirt.teamdrd.ruraldeveopment.R;

/**
 * Created by Aazam on 26/03/2017.
 */

public class QueryFoundAdapter extends RecyclerView.Adapter<QueryFoundAdapter.QueryFoundViewHolder> {

    Context context;



    public QueryFoundAdapter (Context context){
        this.context = context;
    }

    public  class QueryFoundViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ln;

        public QueryFoundViewHolder(View itemView) {
            super(itemView);
            ln = (LinearLayout) itemView.findViewById(R.id.query_adapter);

        }
    }
    @Override
    public QueryFoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewAdapter = LayoutInflater.from(context).inflate(R.layout.query_adapter, parent,false);
        return new QueryFoundViewHolder(viewAdapter);

    }

    @Override
    public void onBindViewHolder(QueryFoundViewHolder holder, int position) {
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, DiscussionForum.class);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }


}
