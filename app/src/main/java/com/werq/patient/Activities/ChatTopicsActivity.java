package com.werq.patient.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.ChatTopicsAdapter;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatTopicsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvTopics)
    RecyclerView rvTopics;
    ChatTopicsAdapter chatTopicsAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_topics);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializeVariables();
        Helper.setToolbarwithCross(getSupportActionBar(),getResources().getString(R.string.hint_choose_topic));
        chatTopicsAdapter=new ChatTopicsAdapter(mContext,getArrayList());
        rvTopics.setLayoutManager(new LinearLayoutManager(mContext));
        rvTopics.setHasFixedSize(false);
        rvTopics.setAdapter(chatTopicsAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<String> getArrayList() {
       ArrayList<String> topics=new ArrayList<>();
        topics.add("General Health Questions");
        topics.add("Allergic Reaction");
        topics.add("Bad Health");
        topics.add("Schedule");
        topics.add("Medicine");
        topics.add("Some Health Questions");
        topics.add("Some Reaction");
        topics.add("Future Appointment");
        topics.add("Past Appointment");


        return topics;
    }

    private void initializeVariables() {
        mContext=this;
    }

}
