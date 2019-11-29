package com.werq.patient.views.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityEncounterBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Encounter;
import com.werq.patient.viewmodel.SummeryCareViewModel;
import com.werq.patient.views.adapter.EncounterAdapter;
import com.werq.patient.views.adapter.MedicalInfoAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EncounterActivity extends BaseActivity {

    @BindView(R.id.rvEncounterList)
    RecyclerView rvEncounterList;
    ActivityEncounterBinding encounterBinding;
    ArrayList<Encounter> encounterArrayList;
    Context mContext;
    SummeryCareViewModel viewModel;
    EncounterAdapter encounterAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_encounter);
        mContext=this;
        ButterKnife.bind(this);
        encounterBinding = DataBindingUtil.setContentView(this,R.layout.activity_encounter);
        viewModel= ViewModelProviders.of(this).get(SummeryCareViewModel.class);
        encounterBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        encounterBinding.setSummeryCareViewModel(viewModel);
        initializeVariables();
        encounterAdapter=new EncounterAdapter(mContext,encounterArrayList,viewModel,this);
        setRecyclerView();

        if(Helper.hasNetworkConnection(mContext)){

        }else {

        }

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


    }
}
