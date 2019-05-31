package com.werq.patient.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.DoctorTeamAdapter;
import com.werq.patient.Adapters.StackImagesAdapter;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorTeamFragment extends Fragment {

    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;

    //Context
    Context mContext;
    //adapter
    DoctorTeamAdapter doctorTeamAdapter;


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
        doctorTeamAdapter=new DoctorTeamAdapter(mContext,false);
        rvDoctorTeam.setLayoutManager(new LinearLayoutManager(mContext));
        rvDoctorTeam.setHasFixedSize(true);
        rvDoctorTeam.setAdapter(doctorTeamAdapter);

        return view;
    }

    private void intializeVariables() {
        mContext = getActivity();
    }


}
