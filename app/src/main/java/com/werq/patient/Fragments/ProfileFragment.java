package com.werq.patient.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.werq.patient.Activities.Adapters.PagerAdapter;
import com.werq.patient.Controller.ProfileController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.BasicFragments;
import com.werq.patient.Interfaces.DiologListner;
import com.werq.patient.Interfaces.ProfileInterface;
import com.werq.patient.Models.Responce;
import com.werq.patient.R;
import com.werq.patient.Utils.DiologHelper;
import com.werq.patient.Utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ProfileFragment extends Fragment implements BasicActivities, DiologListner {

    public PagerAdapter adapter;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;

    ProfileInterface profileInterface;

    BasicFragments basicFragments;

    BasicActivities basicActivities;

    Responce data;

    Context mContext;

    Bundle medicalBundle;
    Bundle insuranceBundle;
    Bundle medicationBundle;

    MedicalInfoFragment medicalInfoFragment;
    InsuranceFragment insuranceFragment;
    MedicationsFragment medicationsFragment;

    DiologListner diologListner;
    private BottomSheetDialog mBottomSheetDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        unbinder=ButterKnife.bind(this,view);
        initializeVariables();
        getData();

        viewpager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable androidx.viewpager.widget.PagerAdapter oldAdapter, @Nullable androidx.viewpager.widget.PagerAdapter newAdapter) {

            }
        });

        return view;
    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(medicalInfoFragment, getString(R.string.medical_info) );
        adapter.addFragment(insuranceFragment, getString(R.string.insurance) );
        adapter.addFragment(medicationsFragment, getString(R.string.medications) );
        viewpager.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initializeVariables() {
        //context
        mContext=getActivity();

        //interface
        basicActivities=this;
        profileInterface=new ProfileController(basicActivities);
        diologListner=this;

        //other

        basicFragments=new FragmentUtils();




    }

    @Override
    public void setRecyclerView() {

    }

    @Override
    public void setView(Object data) {
        this.data=(Responce)data;


        medicalBundle=profileInterface.bundle(this.data,"medical_info");
        insuranceBundle=profileInterface.bundle(this.data,"insurance_info");
        medicationBundle=profileInterface.bundle(this.data,"medications");


        medicalInfoFragment= (MedicalInfoFragment) basicFragments.newInstance(mContext,medicalBundle,new MedicalInfoFragment());
        insuranceFragment=(InsuranceFragment)basicFragments.newInstance(mContext,insuranceBundle,new InsuranceFragment());
        medicationsFragment=(MedicationsFragment)basicFragments.newInstance(mContext,insuranceBundle,new MedicationsFragment());

        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);


    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {
        profileInterface.getData();

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void setdiologview(View view) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
