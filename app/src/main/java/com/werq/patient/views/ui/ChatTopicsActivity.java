package com.werq.patient.views.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.views.adapter.ChatTopicsAdapter;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

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

        setToobar();

        setAdapterTopics();


    }

    private void setToobar() {

        Helper.setToolbarwithCross(getSupportActionBar(),getResources().getString(R.string.hint_choose_topic));

    }

    private void setAdapterTopics() {

        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvTopics,chatTopicsAdapter);
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
        //context
        mContext=this;

        //adapters
        chatTopicsAdapter=new ChatTopicsAdapter(mContext,getArrayList());
    }

}
