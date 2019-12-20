package com.werq.patient.views.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.werq.patient.Controller.ProfileController;
import com.werq.patient.R;
import com.werq.patient.Utils.DiologHelper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.views.adapter.MedicalInfoAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryCareActivity extends AppCompatActivity {

    @BindView(R.id.rvSummeryCare)
    RecyclerView rvSummeryCare;
    Context mContext;
    ArrayList<String> titleList ;
    MedicalInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_care);
        ButterKnife.bind(this);
        mContext=this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.summery_of_care));
        initializeVariables();
        setRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    public void setRecyclerView() {

        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvSummeryCare, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvSummeryCare);
    }

    public void initializeVariables() {

        titleList = new ArrayList<>();
        titleList.add(getResources().getString(R.string.encounter));
        titleList.add(getResources().getString(R.string.assesssment));
        titleList.add(getResources().getString(R.string.instruction));
        titleList.add(getResources().getString(R.string.plan_of_care));
        titleList.add(getResources().getString(R.string.history_of_procedure));
        adapter = new MedicalInfoAdapter(this, titleList,true);


    }
}
