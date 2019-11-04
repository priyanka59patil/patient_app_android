package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewFileActivity extends AppCompatActivity implements BasicActivities {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Helper.setToolbarwithCross(getSupportActionBar(),"");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_forword, menu);
        return super.onCreateOptionsMenu(menu);
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

    @Override
    public void initializeVariables() {
        mContext=this;

    }

    @Override
    public void setRecyclerView() {

    }

    @Override
    public void setView(Object data) {

    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void setToolbar() {

    }
}
