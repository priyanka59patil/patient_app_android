package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.werq.patient.Factory.PatientProfileVmFactory;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentProfileBinding;
import com.werq.patient.viewmodel.PatientProfileViewModel;
import com.werq.patient.views.adapter.PagerAdapter;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.BasicFragments;
import com.werq.patient.Interfaces.Callback.DiologListner;
import com.werq.patient.service.model.Responce;
import com.werq.patient.R;
import com.werq.patient.Utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ProfileFragment extends BaseFragment implements BasicActivities, DiologListner {

    public PagerAdapter adapter;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;

    //ProfileInterface profileInterface;

    BasicFragments basicFragments;

    BasicActivities basicActivities;

    Responce data;

    Context mContext;

    Bundle medicalBundle;
    Bundle insuranceBundle;
    Bundle medicationBundle;

    /*MedicalInfoFragment medicalInfoFragment;
    InsuranceFragment insuranceFragment;
    MedicationsFragment medicationsFragment;
*/
    DiologListner diologListner;
    private BottomSheetDialog mBottomSheetDialog;
    FragmentProfileBinding fragmentProfileBinding;
    PatientProfileViewModel viewModel;
    //ProgressDialog progressDialog;
    private String TAG="ProfileMainFrag";

    Fragment medicalInfoFragment=new MedicalInfoFragment();
    Fragment insuranceFragment =new InsuranceFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeVariables();

        viewModel= ViewModelProviders.of(this,new PatientProfileVmFactory(getAuthToken())).get(PatientProfileViewModel.class);
        if(Helper.hasNetworkConnection(mContext)){
            Helper.setLog(TAG,"Call to api");
            viewModel.fetchPatientProfileData();

        }else {
            Helper.showToast(mContext,getString(R.string.no_network_conection));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        if(fragmentProfileBinding==null){
            fragmentProfileBinding=FragmentProfileBinding.bind(view);
        }
        unbinder=ButterKnife.bind(this,view);
        fragmentProfileBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        fragmentProfileBinding.setPatientProfileViewModel(viewModel);

        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
        //getData();

       /* viewpager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable androidx.viewpager.widget.PagerAdapter oldAdapter, @Nullable androidx.viewpager.widget.PagerAdapter newAdapter) {

            }
        });*/
      /*  viewModel.getLoading().observe(this,aBoolean -> {
            try{
                if(aBoolean ){
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
                }
            }catch (Exception e){
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.hide();
                }
            }

        });*/
        return view;
    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MedicalInfoFragment(), getString(R.string.medical_info) );
        adapter.addFragment(new InsuranceFragment(), getString(R.string.insurance) );
        adapter.addFragment(new MedicationsFragment(),getString(R.string.medications));
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
        //profileInterface=new ProfileController(basicActivities);
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


       /* medicalBundle=profileInterface.bundle(this.data,"medical_info");
        insuranceBundle=profileInterface.bundle(this.data,"insurance_info");
        medicationBundle=profileInterface.bundle(this.data,"medications");*/


       /* medicalInfoFragment= (MedicalInfoFragment) basicFragments.newInstance(mContext,medicalBundle,new MedicalInfoFragment());
        insuranceFragment=(InsuranceFragment)basicFragments.newInstance(mContext,insuranceBundle,new InsuranceFragment());
        medicationsFragment=(MedicationsFragment)basicFragments.newInstance(mContext,insuranceBundle,new MedicationsFragment());
*/
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);


    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {
     //   profileInterface.getData();

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
