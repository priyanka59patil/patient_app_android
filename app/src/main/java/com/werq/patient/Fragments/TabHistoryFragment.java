package com.werq.patient.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.AppointmentAdapter;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabHistoryFragment extends Fragment {


    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    private AppointmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_history, container, false);
        ButterKnife.bind(this,view);
        adapter = new AppointmentAdapter(getActivity(), false);
        rvAppointmentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAppointmentList.setAdapter(adapter);
        return view;
    }


}
