package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentMedicalInfoBinding;
import com.werq.patient.viewmodel.PatientProfileViewModel;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.MedicalInfoAdapter;
import com.werq.patient.Controller.ProfileController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.DiologListner;
import com.werq.patient.Interfaces.ProfileInterface;
import com.werq.patient.service.model.Medical_info;
import com.werq.patient.service.model.Personal_info;
import com.werq.patient.R;
import com.werq.patient.Utils.DiologHelper;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MedicalInfoFragment extends BaseFragment implements BasicActivities, DiologListner {


    @BindView(R.id.loadingView)
    ProgressBar loadingView;


    MedicalInfoAdapter adapter;
    @BindView(R.id.rvMedicalInfo)
    RecyclerView rvMedicalInfo;
    @BindView(R.id.ivProfilePhoto)
    CircularImageView ivProfilePhoto;
    @BindView(R.id.tvPatientName)
    TextView tvPatientName;
    @BindView(R.id.tvDob)
    TextView tvDob;
    @BindView(R.id.guideline)
    Guideline guideline;
    @BindView(R.id.lblContact)
    TextView lblContact;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.guideline3)
    Guideline guideline3;
    @BindView(R.id.tvContact)
    TextView tvContact;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.ivCall)
    ImageView ivCall;
    @BindView(R.id.ivLoc)
    ImageView ivLoc;
    @BindView(R.id.cvPerDetails)
    CardView cvPerDetails;
    @BindView(R.id.lblMedicalInfo)
    TextView lblMedicalInfo;
    @BindView(R.id.scrollView2)
    ScrollView scrollView2;
    private Context mContext;

    private ProfileInterface profileInterface;
    private BasicActivities basicActivities;
    DiologListner diologListner;

    private ArrayList<Medical_info> medical_infos;

    Personal_info personal_info;
    Medical_info[] medical_info;

    Bundle bundle;

    Gson gson;
    private BottomSheetDialog mBottomSheetDialog;
    FragmentMedicalInfoBinding fragmentMedicalInfoBinding;
    PatientProfileViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeVariables();
        viewModel= ViewModelProviders.of(getParentFragment()).get(PatientProfileViewModel.class);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_info, container, false);
        if(fragmentMedicalInfoBinding==null){
            fragmentMedicalInfoBinding=FragmentMedicalInfoBinding.bind(view);
        }
        fragmentMedicalInfoBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        fragmentMedicalInfoBinding.setPatientProfileViewModel(viewModel);
        ButterKnife.bind(this, view);
        loadingView.setIndeterminateDrawable(fadingcircle);
        getIntentData();
        setRecyclerView();

        viewModel.patientName.observe(this,s -> {
            tvPatientName.setText(s);
        });

        viewModel.patientDOB.observe(this,s -> {
            tvDob.setText(s);
        });

        viewModel.phoneNumber.observe(this,s -> {
            tvContact.setText(s);
        });

        viewModel.address.observe(this,s -> {

            tvLocation.setText(s);
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
        return view;
    }



    @Override
    public void initializeVariables() {
        //context
        mContext = getActivity();

        //data
        ArrayList<String> titleList = new ArrayList<>();
        /*titleList.add("Summery Of Care");
        titleList.add("Immunization And Results");
        titleList.add("Functional And Cognitive Status");
        titleList.add("Vital sign");
        titleList.add("Problem list");*/

        titleList.add(getResources().getString(R.string.summery_of_care));
        titleList.add(getResources().getString(R.string.allergies));
        titleList.add(getResources().getString(R.string.history_of_pastillness));
        titleList.add(getResources().getString(R.string.social_history));
        titleList.add(getResources().getString(R.string.problem));

        //listner
        basicActivities = this;
        profileInterface = new ProfileController(basicActivities);
        diologListner = this;

        //others
        mBottomSheetDialog = DiologHelper.createDialogFromBottom(mContext, R.layout.camera_diolog_layout, diologListner);

        gson = new Gson();
        medical_infos = new ArrayList<>();
        adapter = new MedicalInfoAdapter(getActivity(), titleList,false);


    }

    @Override
    public void setRecyclerView() {

        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvMedicalInfo, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvMedicalInfo);
    }

    @Override
    public void setView(Object data) {
        personal_info = gson.fromJson(bundle.getString("personal_info"), Personal_info.class);
        medical_info = gson.fromJson(bundle.getString("medical_info"), Medical_info[].class);
       // medical_infos.addAll(Arrays.asList(medical_info));

        /*tvPatientName.setText(personal_info.getFirst_name() + " " + personal_info.getLast_name());
        tvDob.setText(personal_info.getDob());
        tvContact.setText(personal_info.getPhone_number());*/
     //   tvLocation.setText(personal_info.getAddress().toString());


    }

    @Override
    public void getIntentData() {
        bundle = this.getArguments();
       // setView(bundle);

    }

    @Override
    public void getData() {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void setdiologview(View view) {

    }


    @OnClick(R.id.ivProfilePhoto)
    public void onViewClicked() {
        mBottomSheetDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideProgressBar(loadingView);
    }
}
