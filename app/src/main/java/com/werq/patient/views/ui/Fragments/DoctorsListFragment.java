package com.werq.patient.views.ui.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentDoctorsListBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Coworker;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.DoctorListAdapter;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorsListFragment extends BaseFragment {


    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
    int page = 0;
    int listcount = 0;


    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    private ArrayList<Doctor> coworkerList;
    Context mContext;
    ProfileDoctorViewModel viewModel;
    FragmentDoctorsListBinding fragmentDoctorsListBinding;
    //ProgressDialog progressDialog;
    DoctorListAdapter doctorListAdapter;
    RecyclerViewClickListerner recyclerViewClickListerner;
    private String TAG="DoctorsListFragment";
    FrameLayout mainContainer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        coworkerList =new ArrayList<>();
        doctorListAdapter=new DoctorListAdapter(getActivity(),coworkerList);
        viewModel= ViewModelProviders.of(getActivity()).get(ProfileDoctorViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctors_list, container, false);


        if(fragmentDoctorsListBinding==null){
            fragmentDoctorsListBinding=FragmentDoctorsListBinding.bind(view);
        }

        fragmentDoctorsListBinding.setDoctorProfileViewModel(viewModel);
        setBaseViewModel(viewModel);
        fragmentDoctorsListBinding.setLifecycleOwner(this);
        ButterKnife.bind(this,view);
        initializeVariables();

        viewModel.coworkerLoading.observe(this,aBoolean -> {
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


        viewModel.getCoworkerList().observe(this,coworkerArrayList -> {
            if(coworkerArrayList!=null){

                coworkerList.addAll(coworkerArrayList);
                listcount=coworkerList.size();
                doctorListAdapter.notifyDataSetChanged();
            }

            if(coworkerList!=null && coworkerList.size()>0)
            {
                rvDoctorTeam.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }else {
                tvNoData.setVisibility(View.VISIBLE);
                rvDoctorTeam.setVisibility(View.GONE);
            }
        });


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

                        if (data == (count *10)) {
                            if (loading) {
                                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                    loading=true;
                                    ++page;
                                    viewModel.getDoctorDetails(page);
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

    private void initializeVariables() {

        loadingView.setIndeterminateDrawable(fadingcircle);
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctorTeam,doctorListAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvDoctorTeam);
    }

    public void getIntentData(){


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

    public RecyclerViewClickListerner getRecyclerViewClickListerner() {
        return recyclerViewClickListerner;
    }


    /*@Override
    public void onclick(int position) {
        Helper.setLog(TAG,position+"");
        Intent intent = new Intent(getResources().getString(R.string.NEW_DOCTOR_PROFILE));
        intent.putExtra("doctorData",coworkerList.get(position));
        intent.putExtra("isMessageDisabled",false);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }*/

    public FrameLayout getMainContainer() {
        return mainContainer;
    }

    public void setMainContainer(FrameLayout mainContainer) {
        this.mainContainer = mainContainer;
    }
}
