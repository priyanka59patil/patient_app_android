package com.werq.patient.views.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.views.adapter.FilterDoctorAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

import net.igenius.customcheckbox.CustomCheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterDoctorList extends AppCompatActivity implements RecyclerViewClickListerner {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;
    FilterDoctorAdapter adapter;
    Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;
    @BindView(R.id.tvlabelallDoctors)
    TextView tvlabelallDoctors;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.cbFilter)
    CustomCheckBox cbFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_doctor_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        inilializeVariables();
        setToolbar();
        setDoctorTeams();



    }

    private void setDoctorTeams() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctorTeam,adapter);
    }

    private void setToolbar() {
        Helper.setToolbarwithCross(getSupportActionBar(), "Filter by Doctors");
    }

    private void inilializeVariables() {
        //Context
        mContext = this;

        //listner
        recyclerViewClickListerner=this::onclick;

        //adapters
        adapter = new FilterDoctorAdapter(mContext, recyclerViewClickListerner);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tick_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_check:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onclick(int position) {

    }


}