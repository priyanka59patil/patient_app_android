package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.views.ui.ScheduleDetailsActivity;
import com.werq.patient.views.adapter.AppointmentAdapter;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.AppointmentResponce;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.Factory.ViewModelProviderFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabHistoryFragment extends BaseFragment implements RecyclerViewClickListerner, BasicActivities {


    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    private AppointmentAdapter adapter;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    //listener
    RecyclerViewClickListerner listener;
    Context mContext;
    private TabHistoryFragment basicActivities;
    private AppointmentController controller;
    private ArrayList<AppointmentResult> listAppointments;
    private AppointmentResponce data;
    TabAppoinmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_history, container, false);
        mContext = getActivity();
        viewModel= ViewModelProviders.of(this,new ViewModelProviderFactory(false,mContext)).get(TabAppoinmentViewModel.class);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        ButterKnife.bind(this, view);
        initializeVariables();
        getData();
        //   setRecyclerViewAdapters();
        return view;
    }


    @Override
    public void initializeVariables() {
        //context

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
     /*   Intent intent = new Intent(mContext, ScheduleDetailsActivity.class);
        intent.putExtra(getResources().getString(R.string.intent_is_from_upcoming), false);
        intent.putExtra(getResources().getString(R.string.label_data),listAppointments.get(position));
        startActivity(intent);*/
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


       /* viewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean !=null && aBoolean)
                loadingView.setVisibility(View.VISIBLE);
            else
                loadingView.setVisibility(View.INVISIBLE);
        });*/


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
