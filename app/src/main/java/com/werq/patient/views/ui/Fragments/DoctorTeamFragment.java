package com.werq.patient.views.ui.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentDoctorTeamBinding;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.viewmodel.DoctorTeamViewModel;
import com.werq.patient.views.adapter.AppointmentAdapter;
import com.werq.patient.views.ui.ProfileDoctorActivity;
import com.werq.patient.views.adapter.DoctorTeamAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorTeamFragment extends BaseFragment implements RecyclerViewClickListerner {

    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    //Context
    Context mContext;
    //adapter
    DoctorTeamAdapter doctorTeamAdapter;

    //recyclerviewonclick
    RecyclerViewClickListerner recyclerViewClickListerner;
    BottomTabViewModel viewModel;
    ArrayList<DoctorTeamResult> teamList;
    private String TAG="DoctorTeamFragment";
    FragmentDoctorTeamBinding fragmentDoctorTeamBinding;
    ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_team, container, false);
        mContext = getActivity();
        if(fragmentDoctorTeamBinding==null){
            fragmentDoctorTeamBinding= FragmentDoctorTeamBinding.bind(view);
        }

        viewModel= ViewModelProviders.of(getActivity()).get(BottomTabViewModel.class);
        fragmentDoctorTeamBinding.setLifecycleOwner(this);
        fragmentDoctorTeamBinding.setBottomTabViewModel(viewModel);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setRefreshTokenId(SessionManager.getSessionManager(mContext).getRefreshTokenId());
        progressDialog=Helper.createProgressDialog(mContext);
        ButterKnife.bind(this, view);
        intializeVariables();
        setRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getRepoLoadError().observe(this,aBoolean -> {
            if (aBoolean != null && aBoolean) {
                viewModel.getToast().setValue(getResources().getString(R.string.something_went_wrong));
            }else {
                viewModel.getToast().setValue(null);
            }
        });

        viewModel.getRvVisibility().observe(this,aBoolean -> {
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

            if(aBoolean ){
                if(!progressDialog.isShowing())
                    progressDialog.show();
            }
            else {
                if(progressDialog.isShowing())
                    progressDialog.hide();
            }

        });
    }

    private void intializeVariables() {
        recyclerViewClickListerner=this;
        teamList =new ArrayList<>();
        doctorTeamAdapter=new DoctorTeamAdapter(mContext,false,recyclerViewClickListerner,
                teamList,viewModel,this);
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
        if(position==3){
            openProfileDoctorActivity(true, position);
        }
        else {
            openProfileDoctorActivity(false, position);
        }


    }

    private void openProfileDoctorActivity(boolean isMessageDisabled,int position) {
        Helper.setLog(TAG,teamList.get(position).getDoctors().get(teamList.get(position).getDoctors().size()-1).toString());
        Intent intent=new Intent(mContext, ProfileDoctorActivity.class);
        intent.putExtra("doctorData",teamList.get(position).getDoctors().get(teamList.get(position).getDoctors().size()-1));
        intent.putExtra("isMessageDisabled",isMessageDisabled);
        startActivity(intent);
    }
}
