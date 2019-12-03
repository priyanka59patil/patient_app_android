package com.werq.patient.views.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.databinding.ActivityPlanOfCareBinding;
import com.werq.patient.databinding.ActivityProcedureHistoryBinding;
import com.werq.patient.service.model.ResponcejsonPojo.HistoryOfProcedure;
import com.werq.patient.service.model.ResponcejsonPojo.PlanOfCare;
import com.werq.patient.viewmodel.SummeryCareViewModel;
import com.werq.patient.views.adapter.PlanOfCareAdapter;
import com.werq.patient.views.adapter.ProcedureHistoryAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcedureHistoryActivity extends BaseActivity {

    @BindView(R.id.rvProcedureList)
    RecyclerView rvProcedureList;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    ActivityProcedureHistoryBinding activityBinding;
    ArrayList<HistoryOfProcedure> procHistoryList;
    Context mContext;
    SummeryCareViewModel viewModel;
    ProcedureHistoryAdapter procHistoryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_plan_of_care);

        mContext=this;

        activityBinding = DataBindingUtil.setContentView(this,R.layout.activity_procedure_history);
        ButterKnife.bind(this);
        viewModel= ViewModelProviders.of(this).get(SummeryCareViewModel.class);
        activityBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        activityBinding.setSummeryCareViewModel(viewModel);
        initializeVariables();
        procHistoryAdapter=new ProcedureHistoryAdapter(mContext,procHistoryList,viewModel,this);
        setRecyclerView();

        if(Helper.hasNetworkConnection(mContext)){
            viewModel.fetchHistoryOfProcedureList(0);
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

        viewModel.getRvPlanOfCareVisibility().observe(this,aBoolean -> {
            if(aBoolean){
                rvProcedureList.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }else {
                rvProcedureList.setVisibility(View.GONE);
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

        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvProcedureList, procHistoryAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvProcedureList);
    }

    public void initializeVariables() {

        procHistoryList = new ArrayList<>();
        loadingView.setIndeterminateDrawable(fadingCircle);
        getSupportActionBar().setTitle("History Of Procedure");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressBar(loadingView);
    }
}
