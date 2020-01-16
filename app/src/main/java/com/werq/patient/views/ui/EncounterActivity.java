package com.werq.patient.views.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.werq.patient.Factory.SummaryCareVmFactory;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityEncounterBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Encounter;
import com.werq.patient.viewmodel.SummaryCareViewModel;
import com.werq.patient.views.adapter.EncounterAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EncounterActivity extends BaseActivity {

    @BindView(R.id.rvEncounterList)
    RecyclerView rvEncounterList;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    ActivityEncounterBinding encounterBinding;
    ArrayList<Encounter> encounterArrayList;
    Context mContext;
    SummaryCareViewModel viewModel;
    EncounterAdapter encounterAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_encounter);
        mContext=this;

        encounterBinding = DataBindingUtil.setContentView(this,R.layout.activity_encounter);
        ButterKnife.bind(this);
        viewModel= ViewModelProviders.of(this,new SummaryCareVmFactory(getAuthToken())).get(SummaryCareViewModel.class);
        encounterBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        encounterBinding.setSummaryCareViewModel(viewModel);
        initializeVariables();
        encounterAdapter=new EncounterAdapter(mContext,encounterArrayList,viewModel,this);
        setRecyclerView();

        if(Helper.hasNetworkConnection(mContext)){
                viewModel.fetchEncounterList(0);
        }else {
            Helper.showToast(mContext,getResources().getString(R.string.no_network_conection));
        }

        viewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean ){
                showProgressBar(loadingView);
                loadingView.bringToFront();
            }
            else {
                hideProgressBar(loadingView);
            }
        });

        viewModel.getRvEnountersVisibility().observe(this,aBoolean -> {
            if(aBoolean){
                rvEncounterList.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }else {
                rvEncounterList.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    public void setRecyclerView() {

        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvEncounterList, encounterAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvEncounterList);
    }

    public void initializeVariables() {

        encounterArrayList = new ArrayList<>();
        loadingView.setIndeterminateDrawable(fadingCircle);
        getSupportActionBar().setTitle(getResources().getString(R.string.encounter));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressBar(loadingView);
    }
}
