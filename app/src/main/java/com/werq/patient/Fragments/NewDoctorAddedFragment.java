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

import com.werq.patient.Adapters.StackImagesAdapter;
import com.werq.patient.R;
import com.werq.patient.Utils.OverlapDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewDoctorAddedFragment extends Fragment {


    @BindView(R.id.tvTextMsgDoctoradded)
    TextView tvTextMsgDoctoradded;
    @BindView(R.id.rvUserProfiles)
    RecyclerView rvUserProfiles;

    StackImagesAdapter stackImageView;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_doctor_added, container, false);
        ButterKnife.bind(this, view);
        inilizevariables();
        setStackImageadapter();

        return view;
    }

    private void setStackImageadapter() {
        stackImageView = new StackImagesAdapter(mContext, setImageResources());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rvUserProfiles.addItemDecoration(new OverlapDecoration());
        rvUserProfiles.setLayoutManager(layoutManager);
        rvUserProfiles.setHasFixedSize(true);
        rvUserProfiles.setAdapter(stackImageView);
    }

    private ArrayList<Integer> setImageResources() {
        ArrayList<Integer> imageLists = new ArrayList<>();
        imageLists.add(R.drawable.imageone);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imageone);
        return imageLists;
    }

    private void inilizevariables() {
        mContext=getActivity();
    }


}
