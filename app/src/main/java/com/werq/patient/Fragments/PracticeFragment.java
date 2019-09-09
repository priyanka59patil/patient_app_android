package com.werq.patient.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.PracticeAdapter;
import com.werq.patient.Controller.ProfileController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.ProfileInterface;
import com.werq.patient.Models.pojo.Responce;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PracticeFragment extends Fragment implements BasicActivities {


    @BindView(R.id.tvtitlepractice)
    TextView tvtitlepractice;
    @BindView(R.id.tvpracticeabout)
    TextView tvpracticeabout;
    @BindView(R.id.tvtittlePracticeLocation)
    TextView tvtittlePracticeLocation;
    @BindView(R.id.rvPracticeLocations)
    RecyclerView rvPracticeLocations;
    @BindView(R.id.tvtitleHospitleAffiliates)
    TextView tvtitleHospitleAffiliates;
    @BindView(R.id.rvHospitleAffiliates)
    RecyclerView rvHospitleAffiliates;
    @BindView(R.id.tvtitlepracticeweb)
    TextView tvtitlepracticeweb;

    PracticeAdapter locationpracticeAdapter;
    PracticeAdapter hospitalpracticeAdapter;
    //
    ProfileInterface profileInterface;
    BasicActivities basicActivities;

    Responce data;
    Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        ButterKnife.bind(this,view);
        initializeVariables();
        setRecyclerView();

            getData();



        return view;
    }



    @Override
    public void initializeVariables() {
        //context
        mContext=getActivity();

        //listner
        basicActivities=this;
        profileInterface=new ProfileController(basicActivities);


        //adaters
        locationpracticeAdapter=new PracticeAdapter(mContext,true);
        hospitalpracticeAdapter=new PracticeAdapter(mContext,false);

    }

    @Override
    public void setRecyclerView() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvPracticeLocations,locationpracticeAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvPracticeLocations);


        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvHospitleAffiliates,hospitalpracticeAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvHospitleAffiliates);

    }

    @Override
    public void setView(Object data) {
        this.data=(Responce)data;
     //   Log.d("SDasd",((Responce) data).getResponse().getFirst_name());

    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData()  {
        profileInterface.getData();

    }

    @Override
    public void setToolbar() {

    }


}
