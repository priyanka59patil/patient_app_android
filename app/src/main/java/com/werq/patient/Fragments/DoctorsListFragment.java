package com.werq.patient.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.DoctorUserList;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorsListFragment extends Fragment {

    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;
    private DoctorUserList doctorUserList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctors_list, container, false);
        ButterKnife.bind(this,view);
         doctorUserList=new DoctorUserList(getActivity(),15);
        rvDoctorTeam.setHasFixedSize(false);
        rvDoctorTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDoctorTeam.setAdapter(doctorUserList);

        return view;
    }


}
