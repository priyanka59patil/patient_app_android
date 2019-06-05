package com.werq.patient.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.PracticeAdapter;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PracticeFragment extends Fragment {


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
       /// tvtitlepractice.requestFocus();

        locationpracticeAdapter=new PracticeAdapter(mContext,true);
        rvPracticeLocations.setHasFixedSize(false);
        rvPracticeLocations.setLayoutManager(new LinearLayoutManager(mContext));
        rvPracticeLocations.setAdapter(locationpracticeAdapter);

        hospitalpracticeAdapter=new PracticeAdapter(mContext,false);
        rvHospitleAffiliates.setHasFixedSize(false);
        rvHospitleAffiliates.setLayoutManager(new LinearLayoutManager(mContext));
        rvHospitleAffiliates.setAdapter(hospitalpracticeAdapter);

        return view;
    }

    private void initializeVariables() {
        mContext=getActivity();
    }


}
