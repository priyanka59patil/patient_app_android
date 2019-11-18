package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentPracticeBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.PracticeAdapter;
import com.werq.patient.Controller.ProfileController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.ProfileInterface;
import com.werq.patient.service.model.Responce;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PracticeFragment extends BaseFragment /*implements BasicActivities*/ {


    @BindView(R.id.tvtitlepractice)
    TextView tvtitlepractice;
    @BindView(R.id.tvpracticeabout)
    TextView tvpracticeabout;
    @BindView(R.id.tvtittlePracticeLocation)
    TextView tvtittlePracticeLocation;
    @BindView(R.id.rvPracticeLocations)
    RecyclerView rvPracticeLocations;
    /*@BindView(R.id.tvtitleHospitleAffiliates)
    TextView tvtitleHospitleAffiliates;
    @BindView(R.id.rvHospitleAffiliates)
    RecyclerView rvHospitleAffiliates;*/
    @BindView(R.id.tvtitlepracticeweb)
    TextView tvtitlepracticeweb;
    @BindView(R.id.tvWebsite)
    TextView tvWebsite;

    ArrayList<Location> locationsList;
    PracticeAdapter locationpracticeAdapter;
    //PracticeAdapter hospitalpracticeAdapter;

    ProfileInterface profileInterface;
    BasicActivities basicActivities;

    Responce data;
    Context mContext;
    FragmentPracticeBinding fragmentPracticeBinding;
    ProfileDoctorViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        mContext=getActivity();
        if(fragmentPracticeBinding==null){
            fragmentPracticeBinding =FragmentPracticeBinding.bind(view);
        }


        viewModel = ViewModelProviders.of(getActivity()).get(ProfileDoctorViewModel.class);
        fragmentPracticeBinding.setDoctorProfileViewModel(viewModel);
        fragmentPracticeBinding.setLifecycleOwner(this);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setRefreshTokenId(SessionManager.getSessionManager(mContext).getRefreshTokenId());
        ButterKnife.bind(this,view);
        initializeVariables();


        viewModel.about.observe(this,s -> {
            if(s!=null && !s.isEmpty()){
                tvpracticeabout.setText(s);
            }else {
                tvpracticeabout.setText("Not Available");
            }
        });

        viewModel.practiceWebUrl.observe(this,s -> {
            if(s!=null && !s.isEmpty()){
                tvWebsite.setText(s);
            }else {
                tvWebsite.setText("Not Available");
            }
        });

        viewModel.rvPracticesVisibility.observe(this,aBoolean -> {
            if(aBoolean){
                tvtittlePracticeLocation.setVisibility(View.VISIBLE);
                rvPracticeLocations.setVisibility(View.VISIBLE);
            }
            else {
                tvtittlePracticeLocation.setVisibility(View.GONE);
                rvPracticeLocations.setVisibility(View.GONE);
            }
        });

        viewModel.locationsList.observe(this,locations -> {
            if(locations!=null){
                tvtitlepractice.setText(locations.get(0).getOrganizationName());

            }
        });


       //     getData();



        return view;
    }




    public void initializeVariables() {
        profileInterface=new ProfileController(basicActivities);
        locationsList=new ArrayList<>();
        locationpracticeAdapter=new PracticeAdapter(mContext,locationsList,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvPracticeLocations,locationpracticeAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvPracticeLocations);
        //hospitalpracticeAdapter=new PracticeAdapter(mContext,false);

    }



}
