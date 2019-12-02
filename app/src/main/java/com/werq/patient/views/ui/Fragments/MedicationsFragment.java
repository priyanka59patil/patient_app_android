package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentMedicalInfoBinding;
import com.werq.patient.databinding.FragmentMedicationsBinding;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationDatum;
import com.werq.patient.viewmodel.PatientProfileViewModel;
import com.werq.patient.views.adapter.MedicationAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicationsFragment extends BaseFragment {

    @BindView(R.id.rvMedicationList)
    RecyclerView rvMedicationList;
    @BindView(R.id.tvNoDataLayout)
    TextView tvNoDataLayout;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    FragmentMedicationsBinding fragmentMedicationsBinding;
    PatientProfileViewModel viewModel;
    ArrayList<MedicationDatum> medicationList;
    MedicationAdapter medicationAdapter;


    Context mContext;
    private String TAG="MedFrag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
        medicationList=new ArrayList<>();
        viewModel= ViewModelProviders.of(getParentFragment()).get(PatientProfileViewModel.class);

        if(Helper.hasNetworkConnection(mContext)){
            Helper.setLog(TAG,"Call to api");
            viewModel.fetchMedicationList(0);

        }else {
            Helper.showToast(mContext,getString(R.string.no_network_conection));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_medications, container, false);
        if(fragmentMedicationsBinding==null){
            fragmentMedicationsBinding=FragmentMedicationsBinding.bind(view);
        }
        fragmentMedicationsBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        fragmentMedicationsBinding.setPatientProfileViewModel(viewModel);

        ButterKnife.bind(this,view);
        loadingView.setIndeterminateDrawable(fadingcircle);
        setRecyclerView();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getRvMedicationVisibility().observe(this,aBoolean -> {

            if(aBoolean){
                rvMedicationList.setVisibility(View.VISIBLE);
                tvNoDataLayout.setVisibility(View.GONE);
            }else {

                rvMedicationList.setVisibility(View.GONE);
                tvNoDataLayout.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getLoading().observe(this,aBoolean -> {
           /* if(aBoolean ){
                if(progressDialog!=null && !progressDialog.isShowing()){
                    progressDialog.show();
                }else {
                    progressDialog=Helper.createProgressDialog(mContext);
                }
            }
            else {
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.hide();
                }
            }*/
            if(aBoolean ){
                showProgressBar(loadingView);
                loadingView.bringToFront();
            }
            else {
                hideProgressBar(loadingView);
            }
        });

    }

    public void setRecyclerView() {
        medicationAdapter=new MedicationAdapter(mContext,medicationList,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvMedicationList,medicationAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvMedicationList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideProgressBar(loadingView);
    }
}
