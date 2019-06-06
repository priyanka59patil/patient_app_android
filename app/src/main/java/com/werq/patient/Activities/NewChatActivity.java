package com.werq.patient.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.ChatAdapters;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewChatActivity extends AppCompatActivity implements RecyclerViewClickListerner {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_Search)
    EditText etSearch;
    @BindView(R.id.search_layout)
    RelativeLayout searchLayout;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.rvChats)
    RecyclerView rvChats;
    private ChatAdapters chatAdapters;
    private Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Helper.setToolbarwithBack(getSupportActionBar(),"New Chat");
        initializeVariables();
        chatAdapters=new ChatAdapters(mContext,false,recyclerViewClickListerner);
        rvChats.setLayoutManager(new LinearLayoutManager(mContext));
        rvChats.setHasFixedSize(true);
        rvChats.setAdapter(chatAdapters);

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



    private void initializeVariables() {
        mContext=this;
        recyclerViewClickListerner=this::onclick;
    }

    @Override
    public void onclick(int position) {

    }
}
