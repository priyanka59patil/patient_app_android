package com.werq.patient.Fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Adapters.ImagePagerAdapter;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InsuranceFragment extends Fragment {

    @BindView(R.id.imageViewPager)
    ViewPager imageViewPager;

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
    @BindView(R.id.conLayoutProvider)
    ConstraintLayout conLayoutProvider;
    @BindView(R.id.lblInsuranceType)
    TextView lblInsuranceType;
    @BindView(R.id.tvValInsuranceType)
    TextView tvValInsuranceType;
    @BindView(R.id.conInsuType)
    ConstraintLayout conInsuType;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.lblInsuranceAddr)
    TextView lblInsuranceAddr;
    @BindView(R.id.tvValInsuranceAddr)
    TextView tvValInsuranceAddr;
    @BindView(R.id.conInsuAddr)
    ConstraintLayout conInsuAddr;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.lblInsurancePhone)
    TextView lblInsurancePhone;
    @BindView(R.id.tvValInsurancePhone)
    TextView tvValInsurancePhone;
    @BindView(R.id.conInsuPhone)
    ConstraintLayout conInsuPhone;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.lblInsuranceName)
    TextView lblInsuranceName;
    @BindView(R.id.tvValInsuranceName)
    TextView tvValInsuranceName;
    @BindView(R.id.conInsuName)
    ConstraintLayout conInsuName;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.lblCoverageDates)
    TextView lblCoverageDates;
    @BindView(R.id.tvValCoverageDates)
    TextView tvValCoverageDates;
    @BindView(R.id.conCoverageDates)
    ConstraintLayout conCoverageDates;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.lblMemId)
    TextView lblMemId;
    @BindView(R.id.tvValMemId)
    TextView tvValMemId;
    @BindView(R.id.conMemId)
    ConstraintLayout conMemId;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.lblRelation)
    TextView lblRelation;
    @BindView(R.id.tvValRelation)
    TextView tvValRelation;
    @BindView(R.id.conRelation)
    ConstraintLayout conRelation;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.lblPatAddr)
    TextView lblPatAddr;
    @BindView(R.id.tvValPatAddr)
    TextView tvValPatAddr;
    @BindView(R.id.conPatAddr)
    ConstraintLayout conPatAddr;
    @BindView(R.id.view8)
    View view8;
    @BindView(R.id.lblPatPhone)
    TextView lblPatPhone;
    @BindView(R.id.tvValPatPhone)
    TextView tvValPatPhone;
    @BindView(R.id.conPatPhone)
    ConstraintLayout conPatPhone;
    @BindView(R.id.view9)
    View view9;
    @BindView(R.id.lblPatName)
    TextView lblPatName;
    @BindView(R.id.tvValPatName)
    TextView tvValPatName;
    @BindView(R.id.conPatName)
    ConstraintLayout conPatName;
    @BindView(R.id.view10)
    View view10;
    @BindView(R.id.lblPatDob)
    TextView lblPatDob;
    @BindView(R.id.tvValPatDob)
    TextView tvValPatDob;
    @BindView(R.id.conPatDob)
    ConstraintLayout conPatDob;
    @BindView(R.id.view11)
    View view11;
    @BindView(R.id.lblPatSubsId)
    TextView lblPatSubsId;
    @BindView(R.id.tvValPatSubsId)
    TextView tvValPatSubsId;
    @BindView(R.id.conSubsId)
    ConstraintLayout conSubsId;
    @BindView(R.id.view12)
    View view12;
    @BindView(R.id.lblPatSubsName)
    TextView lblPatSubsName;
    @BindView(R.id.tvValPatSubsName)
    TextView tvValPatSubsName;
    @BindView(R.id.conSubsName)
    ConstraintLayout conSubsName;
    @BindView(R.id.view13)
    View view13;
    @BindView(R.id.lblPatSubsDob)
    TextView lblPatSubsDob;
    @BindView(R.id.tvValPatSubsDob)
    TextView tvValPatSubsDob;
    @BindView(R.id.conSubsDob)
    ConstraintLayout conSubsDob;
    @BindView(R.id.view14)
    View view14;
    @BindView(R.id.lblGroupNo)
    TextView lblGroupNo;
    @BindView(R.id.tvValGroupNo)
    TextView tvValGroupNo;
    @BindView(R.id.conGroupNo)
    ConstraintLayout conGroupNo;
    @BindView(R.id.conMain)
    ConstraintLayout conMain;
    private int dotscount;
    private ImageView[] dots;
   //context
    Context mContext;
    BottomSheetDialog mBottomSheetDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insurance, container, false);
        ButterKnife.bind(this, view);
        InitializationVariables();
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
        mContext=getActivity();
        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        View sheetView = getActivity().getLayoutInflater().inflate(R.layout.camera_diolog_layout, null);
        mBottomSheetDialog.setContentView(sheetView);
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
}
