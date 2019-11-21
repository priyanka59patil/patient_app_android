package com.werq.patient.views.ui.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.werq.patient.databinding.FragmentDoctorsListBinding;
import com.werq.patient.databinding.FragmentTabAppointmentBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Coworker;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorDetailsResponse;
import com.werq.patient.viewmodel.DoctorTeamViewModel;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.DoctorListAdapter;
import com.werq.patient.views.adapter.DoctorUserList;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorsListFragment extends BaseFragment {


    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    Sprite fadingCircle;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
    int page = 0;
    int listcount = 0;


    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    private ArrayList<Coworker> cowerkerList;
    Context mContext;
    ProfileDoctorViewModel viewModel;
    FragmentDoctorsListBinding fragmentDoctorsListBinding;
    //ProgressDialog progressDialog;
    DoctorListAdapter doctorListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    /*    if(Helper.hasNetworkConnection(mContext)){
            viewModel.getDoctorDetails(0);
        }
        else {
            Helper.showToast(mContext,"No Network Connection");
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctors_list, container, false);
        //viewModel = ViewModelProviders.of(this,)
        mContext = getActivity();

        if(fragmentDoctorsListBinding==null){
            fragmentDoctorsListBinding=FragmentDoctorsListBinding.bind(view);
        }
        viewModel= ViewModelProviders.of(getActivity()).get(ProfileDoctorViewModel.class);
        fragmentDoctorsListBinding.setDoctorProfileViewModel(viewModel);
        fragmentDoctorsListBinding.setLifecycleOwner(this);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setRefreshTokenId(SessionManager.getSessionManager(mContext).getRefreshTokenId());
        //progressDialog=Helper.createProgressDialog(mContext);
        ButterKnife.bind(this,view);
        initializeVariables();

       // setRecyclerView();

        viewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean ){
                //if(!loadingView.isAnimating())
                loadingView.setVisibility(View.VISIBLE);
            }
            else {
                //if(loadingView.isShowing())
                loadingView.setVisibility(View.GONE);
            }
        });

        viewModel.rvCoworkerVisibility.observe(this,aBoolean -> {
            if(aBoolean)
            {
                rvDoctorTeam.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }else {
                tvNoData.setVisibility(View.VISIBLE);
                rvDoctorTeam.setVisibility(View.GONE);
            }
        });

        viewModel.coworkerList.observe(this,coworkerArrayList -> {
            if(coworkerArrayList!=null){
                listcount=coworkerArrayList.size();
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

                        if (data == (count *10)) {
                            if (loading) {
                                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                    //                                loading = false;
                                    loading=true;
                                    //Logv("...", "Last Item Wow !");
                                    ++page;
                                   // viewModel.fetchDoctorDetails(page);
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

    private void initializeVariables() {

        fadingCircle=new Circle();
        loadingView.setIndeterminateDrawable(fadingCircle);
        cowerkerList =new ArrayList<>();
        doctorListAdapter=new DoctorListAdapter(getActivity(),cowerkerList,viewModel,this);
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctorTeam,doctorListAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvDoctorTeam);
    }

    public void getIntentData(){


    }


}
