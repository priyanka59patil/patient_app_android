package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Utils.SessionManager;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.viewmodel.DoctorTeamViewModel;
import com.werq.patient.views.adapter.AppointmentAdapter;
import com.werq.patient.views.ui.ProfileDoctorActivity;
import com.werq.patient.views.adapter.DoctorTeamAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorTeamFragment extends Fragment  implements RecyclerViewClickListerner {

    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    //Context
    Context mContext;
    //adapter
    DoctorTeamAdapter doctorTeamAdapter;

    //recyclerviewonclick
    RecyclerViewClickListerner recyclerViewClickListerner;
    DoctorTeamViewModel viewModel;
    ArrayList<DoctorTeamResult> teamList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_team, container, false);
        mContext = getActivity();
        viewModel= ViewModelProviders.of(this).get(DoctorTeamViewModel.class);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setRefreshTokenId(SessionManager.getSessionManager(mContext).getRefreshTokenId());
        ButterKnife.bind(this, view);
        intializeVariables();
        setRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getRepoLoadError().observe(this,aBoolean -> {
            if (aBoolean != null && aBoolean) {
                viewModel.getToast().setValue(getResources().getString(R.string.something_went_wrong));
            }else {
                viewModel.getToast().setValue(null);
            }
        });

        viewModel.getRvVisibility().observe(this,aBoolean -> {
            if(aBoolean)
            {
                rvDoctorTeam.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }
            else {
                rvDoctorTeam.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void intializeVariables() {
        recyclerViewClickListerner=this;
        teamList =new ArrayList<>();
        doctorTeamAdapter=new DoctorTeamAdapter(mContext,false,recyclerViewClickListerner,
                teamList,viewModel,this);
    }
    private void setRecyclerView() {

      /*  adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller,viewModel,this);
            RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
            RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);
            rvAppointmentList.setAdapter(adapter);*/
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctorTeam,doctorTeamAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvDoctorTeam);
    }


    @Override
    public void onclick(int position) {
        if(position==3){
            openProfileDoctorActivity(true);
        }
        else {
            openProfileDoctorActivity(false);
        }


    }

    private void openProfileDoctorActivity(boolean isMessageDisabled) {
        Intent intent=new Intent(mContext, ProfileDoctorActivity.class);
        intent.putExtra("isMessageDisabled",isMessageDisabled);
        startActivity(intent);
    }
}
