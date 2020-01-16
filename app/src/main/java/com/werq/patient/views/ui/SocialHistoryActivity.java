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
import com.werq.patient.databinding.ActivitySocialHistoryBinding;
import com.werq.patient.service.model.ResponcejsonPojo.SocialHistory;
import com.werq.patient.viewmodel.SummaryCareViewModel;
import com.werq.patient.views.adapter.SocialHistoryAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialHistoryActivity extends BaseActivity {

    @BindView(R.id.rvSocialHistoryList)
    RecyclerView rvSocialHistoryList;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    @BindView(R.id.tvNoData)
    TextView tvNoData;

    ActivitySocialHistoryBinding activityBinding;
    ArrayList<SocialHistory> socialHistoryList;
    Context mContext;
    SummaryCareViewModel viewModel;
    SocialHistoryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_social_history);
        mContext = this;

        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_social_history);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this,new SummaryCareVmFactory(getAuthToken())).get(SummaryCareViewModel.class);
        activityBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        activityBinding.setSummaryCareViewModel(viewModel);
        initializeVariables();
        adapter = new SocialHistoryAdapter(mContext, socialHistoryList, viewModel, this);
        setRecyclerView();

        if (Helper.hasNetworkConnection(mContext)) {
            viewModel.fetchSocialHistoryList(0);
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

        viewModel.getRvSocialHistoryVisibility().observe(this, aBoolean -> {
            if (aBoolean) {
                rvSocialHistoryList.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            } else {
                rvSocialHistoryList.setVisibility(View.GONE);
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

        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvSocialHistoryList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvSocialHistoryList);
    }

    public void initializeVariables() {

        socialHistoryList = new ArrayList<>();
        loadingView.setIndeterminateDrawable(fadingCircle);
        getSupportActionBar().setTitle(getResources().getString(R.string.social_history));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressBar(loadingView);
    }
}
