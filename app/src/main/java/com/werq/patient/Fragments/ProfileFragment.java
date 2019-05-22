package com.werq.patient.Activities.Fragments;

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

import com.google.android.material.tabs.TabLayout;
import com.werq.patient.Activities.Adapters.PagerAdapter;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ProfileFragment extends Fragment {

    public PagerAdapter adapter;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;


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

        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
        viewpager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable androidx.viewpager.widget.PagerAdapter oldAdapter, @Nullable androidx.viewpager.widget.PagerAdapter newAdapter) {

            }
        });

        return view;
    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MedicalInfoFragment(), getString(R.string.medical_info) );
        adapter.addFragment(new MedicalInfoFragment(), getString(R.string.insurance) );
        adapter.addFragment(new MedicalInfoFragment(), getString(R.string.medications) );
        viewpager.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
