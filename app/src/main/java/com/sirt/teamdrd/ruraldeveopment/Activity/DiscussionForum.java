package com.sirt.teamdrd.ruraldeveopment.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.sirt.teamdrd.ruraldeveopment.Activity.Adapter.DiscussionForumAdapter;
import com.sirt.teamdrd.ruraldeveopment.R;

import org.json.JSONObject;

public class DiscussionForum extends BaseActivity {
    RecyclerView queryForum;
    DiscussionForumAdapter discussionForumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_forum);
        queryForum = (RecyclerView) findViewById(R.id.recyclerview);
        discussionForumAdapter = new DiscussionForumAdapter(DiscussionForum.this);

        RecyclerView.LayoutManager rlm= new LinearLayoutManager(this);
        queryForum.setLayoutManager(rlm);
        queryForum.setItemAnimator(new DefaultItemAnimator());
        queryForum.setAdapter(discussionForumAdapter);

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
