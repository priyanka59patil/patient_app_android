package com.werq.patient.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.StackImagesAdapter;
import com.werq.patient.R;
import com.werq.patient.Utils.OverlapDecoration;

import java.util.ArrayList;

import app.com.stackimageview.customviews.StackImageView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorTeamFragment extends Fragment {


    @BindView(R.id.rvUserProfiles)
    RecyclerView rvUserProfiles;
    StackImagesAdapter stackImageView;
    //Context
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_team, container, false);
        ButterKnife.bind(this, view);
         intializeVariables();
        stackImageView=new StackImagesAdapter(mContext,setImageResources());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rvUserProfiles.addItemDecoration(new OverlapDecoration());
        rvUserProfiles.setLayoutManager(layoutManager);
        rvUserProfiles.setHasFixedSize(true);
        rvUserProfiles.setAdapter(stackImageView);
        return view;
    }

    private void intializeVariables() {
        mContext=getActivity();
    }

    private ArrayList<Integer> setImageResources() {
        ArrayList<Integer> imageLists = new ArrayList<>();
        imageLists.add(R.drawable.imageone);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imageone);
        return imageLists;
    }

}
