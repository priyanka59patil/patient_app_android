package com.werq.patient.views.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Factory.SummaryCareVmFactory;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityPastillnessHistoryBinding;
import com.werq.patient.service.model.ResponcejsonPojo.PastillnessHistory;
import com.werq.patient.viewmodel.SummaryCareViewModel;
import com.werq.patient.views.adapter.PastillnessHistoryAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastillnessHistoryActivity extends BaseActivity {

    @BindView(R.id.rvPastillnessList)
    RecyclerView rvPastillnessList;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    @BindView(R.id.tvNoData)
    TextView tvNoData;

    ActivityPastillnessHistoryBinding activityBinding;
    ArrayList<PastillnessHistory> pastillnessList;
    Context mContext;
    SummaryCareViewModel viewModel;
    PastillnessHistoryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pastillness_history);

        mContext = this;


        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_pastillness_history);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this,new SummaryCareVmFactory(getAuthToken())).get(SummaryCareViewModel.class);
        activityBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        activityBinding.setSummaryCareViewModel(viewModel);
        initializeVariables();
        adapter = new PastillnessHistoryAdapter(mContext, pastillnessList, viewModel, this);
        setRecyclerView();

        if (Helper.hasNetworkConnection(mContext)) {
            viewModel.fetchPastillnessHistoryList(0);
        } else {
            Helper.showToast(mContext, getResources().getString(R.string.no_network_conection));
        }

        viewModel.getLoading().observe(this, aBoolean -> {
            if (aBoolean) {
                showProgressBar(loadingView);
                loadingView.bringToFront();
            } else {
                hideProgressBar(loadingView);
            }
        });

        viewModel.getRvPastillnessHistoryVisibility().observe(this, aBoolean -> {
            if (aBoolean) {
                rvPastillnessList.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            } else {
                rvPastillnessList.setVisibility(View.GONE);
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

        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvPastillnessList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvPastillnessList);
    }

    public void initializeVariables() {

        pastillnessList = new ArrayList<>();
        loadingView.setIndeterminateDrawable(fadingCircle);
        getSupportActionBar().setTitle(getResources().getString(R.string.history_of_pastillness));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressBar(loadingView);
    }
}
