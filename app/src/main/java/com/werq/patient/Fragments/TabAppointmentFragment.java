package com.werq.patient.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Activities.ScheduleDetailsActivity;
import com.werq.patient.Adapters.AppointmentAdapter;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.AppointmentData;
import com.werq.patient.Models.AppointmentResponce;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TabAppointmentFragment extends Fragment implements RecyclerViewClickListerner, BasicActivities {


    //adapter
    AppointmentAdapter adapter;
    //context
    Context mContext;
    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    //listner
    RecyclerViewClickListerner listerner;
    AppointmentInterface controller;
    BasicActivities basicActivities;
    //data
    AppointmentResponce data;
    ArrayList<AppointmentData> listAppointments;


    @Override
    public void initializeVariables() {
        //context
        mContext = getActivity();
        //listner
        listerner = this::onclick;
        basicActivities=this;
        controller=new AppointmentController(basicActivities);

       //data
        listAppointments=new ArrayList<>();
    }

    @Override
    public void setRecyclerView() {
        adapter = new AppointmentAdapter(getActivity(), true, listerner,listAppointments,controller);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);

    }

    @Override
    public void setView(Object data) {
        this.data=(AppointmentResponce)data;
        listAppointments.addAll(Arrays.asList(this.data.getResponse()));
        setRecyclerView();
    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {
        controller.getUpcomingData();

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_appointment, container, false);
        ButterKnife.bind(this, view);
        initializeVariables();
        getData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }




    @Override
    public void onclick(int position) {
      //  String gsonData= Helper.getGsonInstance().toJson(listAppointments.get(position));
        Intent intent = new Intent(mContext, ScheduleDetailsActivity.class);
        intent.putExtra(getResources().getString(R.string.intent_is_from_upcoming), true);
        intent.putExtra(getResources().getString(R.string.label_data),listAppointments.get(position));
        startActivity(intent);

    }
}
