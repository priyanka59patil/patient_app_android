package com.werq.patient.views.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.werq.patient.Factory.TabApptVmProviderFactory;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentAppointmentBinding;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.views.adapter.PagerAdapter;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AppointmentFragment extends BaseFragment {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private PagerAdapter adapter;
    Unbinder unbinder;
    FragmentAppointmentBinding fragmentAppointmentBinding;
    TabAppoinmentViewModel tabViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);
            fragmentAppointmentBinding= FragmentAppointmentBinding.bind(view);
            fragmentAppointmentBinding.setLifecycleOwner(this);
            tabViewModel= ViewModelProviders.of(this,new TabApptVmProviderFactory(getAuthToken(),false)).get(TabAppoinmentViewModel.class);
            setBaseViewModel(tabViewModel);
            fragmentAppointmentBinding.setTabAppoinmentViewModel(tabViewModel);
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
