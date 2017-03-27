package com.sirt.teamdrd.ruraldeveopment.Activity.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sirt.teamdrd.ruraldeveopment.R;

/**
 * Created by Aazam on 26/03/2017.
 */

public class DiscussionForumAdapter extends RecyclerView.Adapter<DiscussionForumAdapter.ForumViewHolder> {
    Context context;
    int size =5;

    public  DiscussionForumAdapter(Context context){
        this.context = context;
    }
    public  class ForumViewHolder extends RecyclerView.ViewHolder{
        public TextView query;
        public EditText answer;
        public Button btn;
        public ForumViewHolder(View itemView) {
            super(itemView);
            query = (TextView) itemView.findViewById(R.id.query);
            answer = (EditText) itemView.findViewById(R.id.askquery);
            btn = (Button) itemView.findViewById(R.id.likeorsubmit);
        }
    }
    @Override
    public ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewAdapter = LayoutInflater.from(context).inflate(R.layout.forum_adapter, parent,false);
        return new ForumViewHolder(viewAdapter);
    }




    @Override
    public void onBindViewHolder(final ForumViewHolder holder, int position) {

        if(position == size){
            holder.query.setVisibility(View.GONE);
            holder.answer.setVisibility(View.VISIBLE);
            holder.btn.setText("Submit");
        }else{
            holder.btn.setVisibility(View.GONE);
        }


    }



    @Override
    public int getItemCount() {
        return size +1 ;
    }
}
