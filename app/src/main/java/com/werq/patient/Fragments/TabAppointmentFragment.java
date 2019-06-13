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
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TabAppointmentFragment extends Fragment implements RecyclerViewClickListerner {


    //adapter
    AppointmentAdapter adapter;
    //context
    Context mContext;
    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    //listner
    RecyclerViewClickListerner listerner;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void initializeVariables() {
        mContext = getActivity();
        listerner=this::onclick;
        adapter = new AppointmentAdapter(getActivity(),true,listerner);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_appointment, container, false);
        ButterKnife.bind(this,view);
        initializeVariables();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvAppointmentList,adapter);

    }


    @Override
    public void onclick(int position) {
        Intent intent=new Intent(mContext, ScheduleDetailsActivity.class);
        intent.putExtra(getResources().getString(R.string.intent_is_from_upcoming),true);
        startActivity(intent);

    }
}
