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

import com.werq.patient.Activities.ScheduleDetailsActivity;
import com.werq.patient.Adapters.AppointmentAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabHistoryFragment extends Fragment implements RecyclerViewClickListerner {


    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    private AppointmentAdapter adapter;

    //listener
    RecyclerViewClickListerner listener;
     Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_history, container, false);
        ButterKnife.bind(this,view);
       IntializeVariables();
        adapter = new AppointmentAdapter(getActivity(), false,listener);
        rvAppointmentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAppointmentList.setAdapter(adapter);
        return view;
    }

    private void IntializeVariables() {
        listener=this::onclick;
        mContext=getActivity();
    }


    @Override
    public void onclick(int position) {
        Intent intent=new Intent(mContext, ScheduleDetailsActivity.class);
        intent.putExtra(getResources().getString(R.string.intent_is_from_upcoming),false);
        startActivity(intent);
    }
}
