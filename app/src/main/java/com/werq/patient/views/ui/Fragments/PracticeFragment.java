package com.werq.patient.views.ui.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.werq.patient.Controller.ProfileController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.ProfileInterface;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentPracticeBinding;
import com.werq.patient.service.model.Responce;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.PracticeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PracticeFragment extends BaseFragment /*implements BasicActivities*/ implements RecyclerViewClickListerner {

    /*@BindView(R.id.loadingView)
    ProgressBar loadingView;
    Sprite fadingCircle;*/
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
    int page = 0;
    int listcount = 0;

    @BindView(R.id.tvtitlepractice)
    TextView tvtitlepractice;
    @BindView(R.id.tvpracticeabout)
    TextView tvpracticeabout;
    @BindView(R.id.tvtittlePracticeLocation)
    TextView tvtittlePracticeLocation;
    @BindView(R.id.rvPracticeLocations)
    RecyclerView rvPracticeLocations;
    /*@BindView(R.id.tvtitleHospitleAffiliates)
    TextView tvtitleHospitleAffiliates;
    @BindView(R.id.rvHospitleAffiliates)
    RecyclerView rvHospitleAffiliates;*/
    @BindView(R.id.tvtitlepracticeweb)
    TextView tvtitlepracticeweb;
    @BindView(R.id.tvWebsite)
    TextView tvWebsite;

    ArrayList<Location> locationsList;
    PracticeAdapter locationpracticeAdapter;
    //PracticeAdapter hospitalpracticeAdapter;
    ProfileInterface profileInterface;
    BasicActivities basicActivities;

    Responce data;
    Context mContext;
    FragmentPracticeBinding fragmentPracticeBinding;
    ProfileDoctorViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        mContext = getActivity();
        if (fragmentPracticeBinding == null) {
            fragmentPracticeBinding = FragmentPracticeBinding.bind(view);
        }


        viewModel = ViewModelProviders.of(getActivity()).get(ProfileDoctorViewModel.class);
        fragmentPracticeBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        fragmentPracticeBinding.setDoctorProfileViewModel(viewModel);

        /*viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setRefreshTokenId(SessionManager.getSessionManager(mContext).getRefreshTokenId());*/
        ButterKnife.bind(this, view);
        initializeVariables();


       /* viewModel.about.observe(this,s -> {
            if(s!=null && !s.isEmpty()){
                tvpracticeabout.setText(s);
            }else {
                tvpracticeabout.setText("Not Available");
            }
        });*/

        viewModel.practiceWebUrl.observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                tvWebsite.setText(s);
            } else {
                tvWebsite.setText("Not Available");
            }
        });

        viewModel.rvPracticesVisibility.observe(this, aBoolean -> {
            if (aBoolean) {
                tvtittlePracticeLocation.setVisibility(View.VISIBLE);
                rvPracticeLocations.setVisibility(View.VISIBLE);
            } else {
                tvtittlePracticeLocation.setVisibility(View.GONE);
                rvPracticeLocations.setVisibility(View.GONE);
            }
        });

        /*viewModel.locationsList.observe(this,locations -> {
            if(locations!=null){
                tvtitlepractice.setText(locations.get(0).getOrganizationName());

            }
        });*/


      /*  viewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean ){
                //if(!loadingView.isAnimating())
                loadingView.setVisibility(View.VISIBLE);
            }
            else {
                //if(loadingView.isShowing())
                loadingView.setVisibility(View.GONE);
            }
        });*/

        //     getData();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void initializeVariables() {
       /* fadingCircle=new Circle();
        loadingView.setIndeterminateDrawable(fadingCircle);*/
        profileInterface = new ProfileController(basicActivities);

        locationsList = new ArrayList<>();
        locationpracticeAdapter = new PracticeAdapter(mContext, locationsList, viewModel, this, this::onclick);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvPracticeLocations, locationpracticeAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvPracticeLocations);
        //hospitalpracticeAdapter=new PracticeAdapter(mContext,false);

    }


    @Override
    public void onclick(int position) {
        String phoneNo = viewModel.getLocationsList().getValue().get(position).getPhoneNumber();
        if (!TextUtils.isEmpty(phoneNo)) {

            Helper.setLog("getPracticePhoneNumber", phoneNo + "-val");
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNo.trim()));
            //startActivity(callIntent);

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                Dexter.withActivity(getActivity()).withPermission(Manifest.permission.CALL_PHONE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        mContext.startActivity(callIntent);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {

                            Helper.setSnackbarWithMsg("Phone access is needed to make call", getView());
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

                //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

            } else {

                mContext.startActivity(callIntent);
            }
        }
    }

    @OnClick(R.id.tvWebsite)
    public void onViewClicked() {

        Uri uri;
        if(!TextUtils.isEmpty(tvWebsite.getText().toString()) && Helper.isValidUrl(tvWebsite.getText().toString())){
            if (tvWebsite.getText().toString().contains("http://") || tvWebsite.getText().toString().contains("https://")) {
                uri = Uri.parse(tvWebsite.getText().toString()); // missing 'http://' will cause crashed
            } else {
                uri = Uri.parse("http://" + tvWebsite.getText().toString()); // missing 'http://' will cause crashed

            }
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);
        }


    }
}
