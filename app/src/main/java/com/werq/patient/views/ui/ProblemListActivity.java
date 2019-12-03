package com.werq.patient.views.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityProblemListBinding;
import com.werq.patient.databinding.ActivitySocialHistoryBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Problem;
import com.werq.patient.service.model.ResponcejsonPojo.SocialHistory;
import com.werq.patient.viewmodel.SummeryCareViewModel;
import com.werq.patient.views.adapter.ProblemListAdapter;
import com.werq.patient.views.adapter.SocialHistoryAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProblemListActivity extends BaseActivity {

    @BindView(R.id.rvProblemList)
    RecyclerView rvProblemList;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    @BindView(R.id.tvNoData)
    TextView tvNoData;

    ActivityProblemListBinding activityBinding;
    ArrayList<Problem> problemList;
    Context mContext;
    SummeryCareViewModel viewModel;
    ProblemListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_problem_list);
        mContext = this;

        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_problem_list);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(SummeryCareViewModel.class);
        activityBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        activityBinding.setSummeryCareViewModel(viewModel);
        initializeVariables();
        adapter = new ProblemListAdapter(mContext, problemList, viewModel, this);
        setRecyclerView();

        if (Helper.hasNetworkConnection(mContext)) {
            viewModel.fetchProblemList(0);
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

        viewModel.getRvProblemListVisibility().observe(this, aBoolean -> {
            if (aBoolean) {
                rvProblemList.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            } else {
                rvProblemList.setVisibility(View.GONE);
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

        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvProblemList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvProblemList);
    }

    public void initializeVariables() {

        problemList = new ArrayList<>();
        loadingView.setIndeterminateDrawable(fadingCircle);
        getSupportActionBar().setTitle(getResources().getString(R.string.problem));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressBar(loadingView);
    }
}
