package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.views.ui.ScheduleDetailsActivity;
import com.werq.patient.views.adapter.AppointmentAdapter;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.AppointmentResponce;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.Factory.ViewModelProviderFactory;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentTabAppointmentBinding;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabAppointmentFragment extends BaseFragment implements RecyclerViewClickListerner, BasicActivities {


    //adapter
    AppointmentAdapter adapter;
    //context
    Context mContext;
    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    //listner
    RecyclerViewClickListerner listener;
    AppointmentInterface controller;
    BasicActivities basicActivities;
    //data
    AppointmentResponce data;
    ArrayList<AppointmentData> listAppointments;
    TabAppoinmentViewModel viewModel;
    FragmentTabAppointmentBinding appointmentBinding;

    @Override
    public void initializeVariables() {
        //context
        mContext = getActivity();
        listAppointments=new ArrayList<>();
        listener = this::onclick;
        basicActivities=this;
        controller=new AppointmentController(basicActivities);
        setRecyclerView();

    }

    @Override
    public void setRecyclerView() {
        /*adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);*/

    }

    @Override
    public void setView(Object data) {
        /*this.data=(AppointmentResponce)data;
        listAppointments.addAll(Arrays.asList(this.data.getResponse()));
        setRecyclerView();*/
    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {
        /*controller.getUpcomingData();*/

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_tab_appointment, container, false);
            viewModel= ViewModelProviders.of(this,new ViewModelProviderFactory(true)).get(TabAppoinmentViewModel.class);
            ButterKnife.bind(this, view);
            initializeVariables();
            adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller,viewModel,this);
            RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
            RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);
            rvAppointmentList.setAdapter(adapter);

        //getData();

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


        viewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean !=null && aBoolean)
                loadingView.setVisibility(View.VISIBLE);
            else
                loadingView.setVisibility(View.INVISIBLE);
        });


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
