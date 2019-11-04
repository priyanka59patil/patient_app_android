package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.views.adapter.DoctorUserList;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorsListFragment extends Fragment {

    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;
    private DoctorUserList doctorUserList;

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctors_list, container, false);
        ButterKnife.bind(this,view);
        initializeVariables();

        setRecyclerView();

        return view;
    }

    private void initializeVariables() {
        mContext=getActivity();
        doctorUserList=new DoctorUserList(getActivity(),15);
    }

    private void setRecyclerView() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctorTeam,doctorUserList);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvDoctorTeam);
    }


}
