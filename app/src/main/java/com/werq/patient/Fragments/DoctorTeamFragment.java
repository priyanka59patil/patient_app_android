package com.werq.patient.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Activities.ProfileDoctorActivity;
import com.werq.patient.Activities.ScrollingActivity;
import com.werq.patient.Adapters.DoctorTeamAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorTeamFragment extends Fragment  implements RecyclerViewClickListerner {

    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;

    //Context
    Context mContext;
    //adapter
    DoctorTeamAdapter doctorTeamAdapter;

    //recyclerviewonclick
    RecyclerViewClickListerner recyclerViewClickListerner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_team, container, false);
        ButterKnife.bind(this, view);
        intializeVariables();
        setRecyclerView();

        return view;
    }

    private void intializeVariables() {
        mContext = getActivity();
        recyclerViewClickListerner=this;
        doctorTeamAdapter=new DoctorTeamAdapter(mContext,false,recyclerViewClickListerner);
    }
    private void setRecyclerView() {
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
