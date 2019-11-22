package com.werq.patient.views.ui.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentDoctorTeamBinding;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.viewmodel.DoctorTeamViewModel;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.AppointmentAdapter;
import com.werq.patient.views.ui.DoctorDetails;
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

    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    Sprite fadingCircle;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean loading = true;
    int page = 0;
    int listcount = 0;

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
    //ProgressDialog progressDialog;



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
        setBaseViewModel(viewModel);
        fragmentDoctorTeamBinding.setBottomTabViewModel(viewModel);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setRefreshTokenId(SessionManager.getSessionManager(mContext).getRefreshTokenId());

        //progressDialog=Helper.createProgressDialog(mContext);
        ButterKnife.bind(this, view);
        intializeVariables();
        setRecyclerView();


        rvDoctorTeam.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                try {
                    if (dy > 0) //check for scroll down
                    {
                        visibleItemCount = recyclerView.getChildCount();
                        //                    totalItemCount = recyclerView.getLayoutManager().getItemCount();
                        totalItemCount = recyclerView.getAdapter().getItemCount();
                        pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                        //Log.("check",String.valueOf(listcount == totalItemCount));
                        if (listcount < 10) {
                            //Log.("check","xzx");
                            loading = false;
                        }
                        int count = page + 1;
                        int data = totalItemCount;

                        if (data == (count * 10)) {
                            if (loading) {
                                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                    //                                loading = false;
                                    loading=true;
                                    //Logv("...", "Last Item Wow !");
                                    ++page;
                                    viewModel.fetchTeamList(page);
                                    //Do pagination.. i.e. fetch new data
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Helper.hasNetworkConnection(mContext)){
            viewModel.fetchTeamList(0);
        }
        else {
            Helper.showToast(mContext,"No Network Connection");
        }


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
                loadingView.setVisibility(View.VISIBLE);
            }
            else {
                loadingView.setVisibility(View.GONE);
            }

        });

        viewModel.teamList.observe(this,doctorTeamResults -> {
            if(doctorTeamResults!=null){
                listcount=doctorTeamResults.size();
            }
        });
    }

    private void intializeVariables() {

        fadingCircle=new Circle();
        loadingView.setIndeterminateDrawable(fadingCircle);
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
        //Helper.setLog(TAG,teamList.get(position).getDoctors().get(teamList.get(position).getDoctors().size()-1).toString());
        Helper.setLog(TAG,teamList.get(position).getDoctors().get(0).toString());
        Intent intent=new Intent(mContext, ProfileDoctorActivity.class);

        //intent.putExtra("doctorData",teamList.get(position).getDoctors().get(teamList.get(position).getDoctors().size()-1));
        intent.putExtra("doctorData",teamList.get(position).getDoctors().get(0));
        intent.putExtra("isMessageDisabled",isMessageDisabled);
        startActivity(intent);
    }
}
