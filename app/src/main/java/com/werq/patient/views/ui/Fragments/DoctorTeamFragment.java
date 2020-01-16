package com.werq.patient.views.ui.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.werq.patient.Factory.BottomTabVmFactory;
import com.werq.patient.Interfaces.Callback.DoctorTeamClickListerner;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentDoctorTeamBinding;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.views.ui.ProfileDoctorActivity;
import com.werq.patient.views.adapter.DoctorTeamAdapter;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorTeamFragment extends BaseFragment implements DoctorTeamClickListerner {

    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean loading = true;
    int page = 0;
    int listcount = 0;

    //Context
    Context mContext;
    //adapter
    DoctorTeamAdapter doctorTeamAdapter;

    //recyclerviewonclick
    BottomTabViewModel viewModel;
    ArrayList<DoctorTeamResult> teamList;
    private String TAG="DoctorTeamFragment";
    FragmentDoctorTeamBinding fragmentDoctorTeamBinding;
    ProgressDialog progressDialog;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        teamList =new ArrayList<>();

        viewModel= ViewModelProviders.of(getActivity(),new BottomTabVmFactory(getAuthToken())).get(BottomTabViewModel.class);

        doctorTeamAdapter=new DoctorTeamAdapter(mContext,false,this,
                teamList,viewModel,this);
       /*     viewModel.getTeamList().observe(this,doctorTeamResults -> {
                Helper.setLog(TAG,"inside teamList observable page="+page);
                if(doctorTeamResults!=null && doctorTeamResults.size()>0){
                    if(page==0){
                        teamList.clear();
                    }

                    teamList.addAll(doctorTeamResults);
                    listcount=teamList.size();
                    doctorTeamAdapter.notifyDataSetChanged();
                }
            });*/


        if(Helper.hasNetworkConnection(mContext)){
            viewModel.fetchTeamList(0);
        }
        else {
            Helper.showToast(mContext,"No Network Connection");
        }
        Helper.setLog(TAG,"inside oncreateview");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_team, container, false);

        if(fragmentDoctorTeamBinding==null){
            fragmentDoctorTeamBinding= FragmentDoctorTeamBinding.bind(view);
        }
        fragmentDoctorTeamBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        fragmentDoctorTeamBinding.setBottomTabViewModel(viewModel);
        ButterKnife.bind(this, view);
        loadingView.setIndeterminateDrawable(fadingcircle);
        setRecyclerView();

        rvDoctorTeam.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                try {
                    if (dy > 0) //check for scroll down
                    {
                        visibleItemCount = recyclerView.getChildCount();
                        totalItemCount = recyclerView.getAdapter().getItemCount();
                        pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                        if (listcount < 10) {
                            loading = false;
                        }
                        int count = page + 1;
                        int data = totalItemCount;

                        if (data == (count * 10)) {
                            if (loading) {
                                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                    loading=true;
                                    ++page;
                                    viewModel.fetchTeamList(page);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    Helper.setExceptionLog("Exception",e);
                    e.printStackTrace();
                }
            }
        });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        viewModel.getRvDoctorListVisibility().observe(this,aBoolean -> {
            if(aBoolean)
            {
                rvDoctorTeam.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }
            else {
                rvDoctorTeam.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getLoading().observe(this,aBoolean -> {
            /*if(aBoolean ){
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


    private void setRecyclerView() {

      /*  adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller,viewModel,this);
            RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
            RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);
            rvAppointmentList.setAdapter(adapter);*/
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctorTeam,doctorTeamAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvDoctorTeam);
    }


    @Override
    public void onclick(int position) {

        openProfileDoctorActivity(false, position);


    }

    @Override
    public void onCallclick(String contactNo) {
        if (!TextUtils.isEmpty(contactNo)) {

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + contactNo.trim()));
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

    private void openProfileDoctorActivity(boolean isMessageDisabled,int position) {
        //Helper.setLog(TAG,teamList.get(position).getDoctors().get(teamList.get(position).getDoctors().size()-1).toString());
        Helper.setLog(TAG,teamList.get(position).getDoctors().get(0).toString());
        Intent intent=new Intent(mContext, ProfileDoctorActivity.class);

        //intent.putExtra("doctorData",teamList.get(position).getDoctors().get(teamList.get(position).getDoctors().size()-1));
        intent.putExtra("doctorData",teamList.get(position).getDoctors().get(0));
        intent.putExtra("isMessageDisabled",isMessageDisabled);
        startActivity(intent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideProgressBar(loadingView);
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressBar(loadingView);
    }
}
