package com.werq.patient.views.ui.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentInsuranceBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Insurance;
import com.werq.patient.viewmodel.PatientProfileViewModel;
import com.werq.patient.views.adapter.ImagePagerAdapter;
import com.werq.patient.Interfaces.DiologListner;
import com.werq.patient.R;
import com.werq.patient.Utils.DiologHelper;
import com.werq.patient.views.adapter.InsuranceAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InsuranceFragment extends BaseFragment implements DiologListner{

    @BindView(R.id.imageViewPager)
    ViewPager imageViewPager;
    @BindView(R.id.rvInsuranceList)
    RecyclerView rvInsuranceList;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.SliderDots)
    LinearLayout sliderDotspanel;
    @BindView(R.id.lblInsuranceCard)
    TextView lblInsuranceCard;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.conLayoutInsurance)
    ConstraintLayout conLayoutInsurance;
    @BindView(R.id.guideline1)
    Guideline guideline1;
    @BindView(R.id.lblInsuranceProvider)
    TextView lblInsuranceProvider;
    @BindView(R.id.conMain)
    ConstraintLayout conMain;
    private int dotscount;
    private ImageView[] dots;
   //context
    Context mContext;
    BottomSheetDialog mBottomSheetDialog;
    private DiologListner diologListner;

    FragmentInsuranceBinding fragmentInsuranceBinding;
    PatientProfileViewModel viewModel;
    ArrayList<Insurance> insuranceArrayList;
    InsuranceAdapter insuranceAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializationVariables();
        insuranceArrayList=new ArrayList<>();
        viewModel= ViewModelProviders.of(getParentFragment()).get(PatientProfileViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insurance, container, false);
        ButterKnife.bind(this, view);
        loadingView.setIndeterminateDrawable(fadingcircle);
        if(fragmentInsuranceBinding== null){
            fragmentInsuranceBinding=FragmentInsuranceBinding.bind(view);
        }
        fragmentInsuranceBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        fragmentInsuranceBinding.setPatientProfileViewModel(viewModel);

        insuranceAdapter=new InsuranceAdapter(mContext,insuranceArrayList,viewModel,this);
        setRecyclerView();

        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getContext());
        imageViewPager.setAdapter(imagePagerAdapter);


        dotscount = imagePagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_gray_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_blue_dot));


        return view;
    }

    private void InitializationVariables() {
       //context
        mContext=getActivity();

        //listner
        diologListner=this;

        mBottomSheetDialog = DiologHelper.createDialogFromBottom(mContext,R.layout.camera_diolog_layout,diologListner);


      /*  View sheetView = getActivity().getLayoutInflater().inflate(R.layout.camera_diolog_layout, null);
        mBottomSheetDialog.setContentView(sheetView);*/
    }
    public void setRecyclerView() {

        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvInsuranceList,insuranceAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvInsuranceList);
    }

    @Override
    public void onResume() {
        super.onResume();

        imageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_gray_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_blue_dot));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    @OnClick({R.id.lblInsuranceCard, R.id.tvEdit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lblInsuranceCard:
                break;
            case R.id.tvEdit:
                mBottomSheetDialog.show();
                break;
        }
    }

    private boolean chePermission() {
        if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED);
        return true;
    }

    @Override
    public void setdiologview(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideProgressBar(loadingView);
    }
}
