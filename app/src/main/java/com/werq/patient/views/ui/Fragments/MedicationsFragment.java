package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werq.patient.R;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentMedicalInfoBinding;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationDatum;
import com.werq.patient.viewmodel.PatientProfileViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicationsFragment extends BaseFragment {

    @BindView(R.id.rvMedicationList)
    RecyclerView rvMedicationList;

    FragmentMedicalInfoBinding fragmentMedicalInfoBinding;
    PatientProfileViewModel viewModel;
    ArrayList<MedicationDatum> medicationList;

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
        medicationList=new ArrayList<>();
        viewModel= ViewModelProviders.of(getParentFragment()).get(PatientProfileViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_medications, container, false);
        if(fragmentMedicalInfoBinding==null){
            fragmentMedicalInfoBinding=FragmentMedicalInfoBinding.bind(view);
        }
        fragmentMedicalInfoBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        fragmentMedicalInfoBinding.setPatientProfileViewModel(viewModel);

        ButterKnife.bind(this,view);

        return view;
    }

}
