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
import com.werq.patient.databinding.ActivityEncounterBinding;
import com.werq.patient.databinding.ActivityInstructionBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Encounter;
import com.werq.patient.service.model.ResponcejsonPojo.Instruction;
import com.werq.patient.viewmodel.SummeryCareViewModel;
import com.werq.patient.views.adapter.EncounterAdapter;
import com.werq.patient.views.adapter.InstructionAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionActivity extends BaseActivity {

    @BindView(R.id.rvInstructionList)
    RecyclerView rvInstructionList;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    ActivityInstructionBinding instructionActivityBinding;
    ArrayList<Instruction> instructionArrayList;
    Context mContext;
    SummeryCareViewModel viewModel;
    InstructionAdapter instructionAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_instruction);

        mContext=this;

        instructionActivityBinding = DataBindingUtil.setContentView(this,R.layout.activity_instruction);
        ButterKnife.bind(this);
        viewModel= ViewModelProviders.of(this).get(SummeryCareViewModel.class);
        instructionActivityBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        instructionActivityBinding.setSummeryCareViewModel(viewModel);
        initializeVariables();
        instructionAdapter=new InstructionAdapter(mContext,instructionArrayList,viewModel,this);
        setRecyclerView();

        if(Helper.hasNetworkConnection(mContext)){
            viewModel.fetchInstruction(0);
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

        viewModel.getRvInstructionVisibility().observe(this,aBoolean -> {
            if(aBoolean){
                rvInstructionList.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }else {
                rvInstructionList.setVisibility(View.GONE);
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

        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvInstructionList, instructionAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvInstructionList);
    }

    public void initializeVariables() {

        instructionArrayList = new ArrayList<>();
        loadingView.setIndeterminateDrawable(fadingCircle);
        getSupportActionBar().setTitle(getResources().getString(R.string.instruction));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressBar(loadingView);
    }
}
