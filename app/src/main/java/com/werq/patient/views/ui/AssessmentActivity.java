package com.werq.patient.views.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.werq.patient.Factory.SummaryCareVmFactory;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityAssessmentBinding;
import com.werq.patient.viewmodel.SummaryCareViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssessmentActivity extends BaseActivity {

    @BindView(R.id.tvAssessments)
    TextView tvAssessments;

    @BindView(R.id.conLayAssessment)
    ConstraintLayout conLayAssessment;

    @BindView(R.id.tvNoData)
    TextView tvNoData;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    ActivityAssessmentBinding assessmentBinding;
    Context mContext;
    SummaryCareViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_assessment);

        mContext=this;

        assessmentBinding = DataBindingUtil.setContentView(this,R.layout.activity_assessment);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(getResources().getString(R.string.assesssment));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel= ViewModelProviders.of(this,new SummaryCareVmFactory(getAuthToken())).get(SummaryCareViewModel.class);
        assessmentBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        assessmentBinding.setSummaryCareViewModel(viewModel);
        loadingView.setIndeterminateDrawable(fadingCircle);

        viewModel.assessments.observe(this,s -> {
            if(s!=null && !s.isEmpty()){
                conLayAssessment.setVisibility(View.VISIBLE);
                tvAssessments.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }
            else {
                conLayAssessment.setVisibility(View.GONE);
                tvAssessments.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }
        });

        if(Helper.hasNetworkConnection(mContext)){
            viewModel.fetchAssessments();
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




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressBar(loadingView);
    }

}
