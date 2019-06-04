package com.werq.patient.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.werq.patient.Activities.Adapters.PagerAdapter;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AppointmentFragment extends Fragment {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private PagerAdapter adapter;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);
        unbinder= ButterKnife.bind(this,view);
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);


        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(new TabAppointmentFragment(), getString(R.string.tab_upcoming));
        adapter.addFragment(new TabHistoryFragment(), getString(R.string.tab_history));
        viewpager.setAdapter(adapter);


    }
}
