package com.werq.patient.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Activities.ScheduleDetailsActivity;
import com.werq.patient.Adapters.AppointmentAdapter;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.pojo.AppointmentData;
import com.werq.patient.Models.pojo.AppointmentResponce;
import com.werq.patient.Models.viewModel.TabAppoinmentViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabHistoryFragment extends Fragment implements RecyclerViewClickListerner, BasicActivities {


    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    private AppointmentAdapter adapter;

    //listener
    RecyclerViewClickListerner listener;
    Context mContext;
    private TabHistoryFragment basicActivities;
    private AppointmentController controller;
    private ArrayList<AppointmentData> listAppointments;
    private AppointmentResponce data;
    TabAppoinmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_history, container, false);
        viewModel= ViewModelProviders.of(this,new ViewModelProviderFactory(false)).get(TabAppoinmentViewModel.class);
        ButterKnife.bind(this, view);
        initializeVariables();
        getData();
        //   setRecyclerViewAdapters();
        return view;
    }


    @Override
    public void initializeVariables() {
        //context
        mContext = getActivity();
        //listner
        listener = this::onclick;
        basicActivities = this;
        controller = new AppointmentController(basicActivities);
        listAppointments = new ArrayList<>();
        adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);
        rvAppointmentList.setAdapter(adapter);
    }


    @Override
    public void onclick(int position) {
        Intent intent = new Intent(mContext, ScheduleDetailsActivity.class);
        intent.putExtra(getResources().getString(R.string.intent_is_from_upcoming), false);
        intent.putExtra(getResources().getString(R.string.label_data),listAppointments.get(position));
        startActivity(intent);
    }


    @Override
    public void setRecyclerView() {
        /*adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);*/
    }

    @Override
    public void setView(Object data) {
       /* this.data = (AppointmentResponce) data;
        listAppointments.addAll(Arrays.asList(this.data.getResponse()));*/
        //setRecyclerView();
    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {

           /* controller.getHistoryData();*/

    }

    @Override
    public void setToolbar() {

    }
}
