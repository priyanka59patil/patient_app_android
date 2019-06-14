package com.werq.patient.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werq.patient.Adapters.MedicalInfoAdapter;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MedicalInfoFragment extends Fragment {

    MedicalInfoAdapter adapter;
    @BindView(R.id.rvMedicalInfo)
    RecyclerView rvMedicalInfo;
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_medical_info, container, false);
        ButterKnife.bind(this,view);
        initializeVariables();
        setRecyclerViewAdapters();

        return view;
    }

    private void setRecyclerViewAdapters() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvMedicalInfo,adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvMedicalInfo);
    }

    private void initializeVariables() {
        //context
        mContext=getActivity();

        //data
        ArrayList<String> titleList=new ArrayList<>();
        titleList.add("Summery Of Care");
        titleList.add("Immunization And Results");
        titleList.add("Functional And Cognitive Status");
        titleList.add("Vital sign");
        titleList.add("Problem list");

        //adapters
        adapter=new MedicalInfoAdapter(getActivity(),titleList);


    }

}
