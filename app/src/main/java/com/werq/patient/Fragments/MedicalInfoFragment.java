package com.werq.patient.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werq.patient.Adapters.MedicalInfoAdapter;
import com.werq.patient.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MedicalInfoFragment extends Fragment {

    MedicalInfoAdapter adapter;
    @BindView(R.id.rvMedicalInfo)
    RecyclerView rvMedicalInfo;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_medical_info, container, false);
        ButterKnife.bind(this,view);

        ArrayList<String> titleList=new ArrayList<>();
        titleList.add("Summery Of Care");
        titleList.add("Immunization And Results");
        titleList.add("Functional And Cognitive Status");
        titleList.add("Vital sign");
        titleList.add("Problem list");

        adapter=new MedicalInfoAdapter(getActivity(),titleList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvMedicalInfo.setLayoutManager(linearLayoutManager);
        rvMedicalInfo.setHasFixedSize(true);
        rvMedicalInfo.setAdapter(adapter);


        return view;
    }

}
