package com.werq.patient.views.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityProfileDoctorBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.PagerAdapter;
import com.werq.patient.views.ui.Fragments.DoctorsListFragment;
import com.werq.patient.views.ui.Fragments.PracticeFragment;

public class DoctorDetails extends BaseActivity {

    ActivityProfileDoctorBinding activityProfileDoctorBinding;
    ProfileDoctorViewModel viewModel;
    TextView tvUsername;
    FrameLayout fragment;
    ViewPager viewpager;
    TabLayout tabs;

    Doctor doctorData;
    boolean isMessageDisabled;
    PagerAdapter adapter;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityProfileDoctorBinding= DataBindingUtil.setContentView(this, R.layout.activity_profile_doctor);
        tvUsername=findViewById(R.id.tvUsername);
        fragment=findViewById(R.id.fragment);
        viewpager=findViewById(R.id.viewpager);
        tabs=findViewById(R.id.tabs);
        getIntentData();
        viewModel= ViewModelProviders.of(this).get(ProfileDoctorViewModel.class);
        activityProfileDoctorBinding.setLifecycleOwner(this);
        activityProfileDoctorBinding.setViewModel(viewModel);
        setBaseViewModel(viewModel);
        viewModel.setDoctorId(doctorData.getiD());
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);

        viewModel.getDoctorName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                Helper.setLog("test",s);

                tvUsername.setText(s);
                Helper.setLog("test aftre",tvUsername.getText().toString());



            }
        });





    }
    public void getIntentData() {
        if(getIntent()!=null){
            doctorData= (Doctor) getIntent().getSerializableExtra("doctorData");
            Helper.setLog("doctorData",doctorData.toString());
          /*  setupViewPager(viewpager);
            tabs.setupWithViewPager(viewpager);*/
            /*isMessageDisabled = getIntent().getBooleanExtra("isMessageDisabled", false);
            if (isMessageDisabled) {
                layoutBtChat.setVisibility(View.GONE);
                relTablayouts.setVisibility(View.GONE);
                fragment.setVisibility(View.VISIBLE);
                addFragment(new PracticeFragment());
            } else {
                fragment.setVisibility(View.GONE);
                layoutBtChat.setVisibility(View.VISIBLE);
                relTablayouts.setVisibility(View.VISIBLE);
                setupViewPager(viewpager);
                tabs.setupWithViewPager(viewpager);
            }*/
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapter(getSupportFragmentManager());

        /*DoctorsListFragment fra=new DoctorsListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("doctorDetailsResponse", profileDoctorViewModel.doctorDetailsResponse.getValue());
        fra.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fra);
        transaction.commitNow();*/

        adapter.addFragment(new PracticeFragment(), getString(R.string.label_practice));
        adapter.addFragment(new DoctorsListFragment(), getString(R.string.label_doctorTEAM));
        viewpager.setAdapter(adapter);


    }

    public void addFragment(Fragment fra) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fra);
        transaction.commitNow();

    }

}
