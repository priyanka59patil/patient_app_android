package com.werq.patient.views.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.views.adapter.ChatAdapters;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

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

        setToolbar();

        initializeVariables();

        setNewChatsList();


    }

    private void setToolbar() {
        Helper.setToolbarwithBack(getSupportActionBar(),"New Chat");
    }

    private void setNewChatsList() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvChats,chatAdapters);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvChats);
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
        //context
        mContext=this;

        //listner
        recyclerViewClickListerner=this::onclick;

        //adapters
        chatAdapters=new ChatAdapters(mContext,false,recyclerViewClickListerner);

    }

    @Override
    public void onclick(int position) {

    }
}
