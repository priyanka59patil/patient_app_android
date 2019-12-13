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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentTabHistoryBinding;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.views.ui.ScheduleDetailsActivity;
import com.werq.patient.views.adapter.AppointmentAdapter;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.AppointmentResponce;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.Factory.ViewModelProviderFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabHistoryFragment extends BaseFragment implements RecyclerViewClickListerner, BasicActivities {


    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    private AppointmentAdapter adapter;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
    int page = 0;
    int listcount = 0;


    //listener
    RecyclerViewClickListerner listener;
    Context mContext;
    private TabHistoryFragment basicActivities;
    private AppointmentController controller;
    private ArrayList<AppointmentResult> listAppointments;
    private AppointmentResponce data;
    TabAppoinmentViewModel viewModel;
    private String TAG="TabHistoryFragment";
    FragmentTabHistoryBinding fragmentTabHistoryBinding;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();
        listener = this::onclick;
        basicActivities = this;
        controller = new AppointmentController(basicActivities);
        listAppointments = new ArrayList<>();
        viewModel= ViewModelProviders.of(this,new ViewModelProviderFactory(false)).get(TabAppoinmentViewModel.class);
        if(Helper.hasNetworkConnection(mContext)){
            viewModel.fetchHistoryAppointmentList(0);
        }else {
            Helper.showToast(mContext,"No Network Connection");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_history, container, false);

        if(fragmentTabHistoryBinding== null){
            fragmentTabHistoryBinding=FragmentTabHistoryBinding.bind(view);
        }
        fragmentTabHistoryBinding.setLifecycleOwner(this);

        setBaseViewModel(viewModel);
        fragmentTabHistoryBinding.setAppontmentViewModel(viewModel);
        ButterKnife.bind(this, view);
        loadingView.setIndeterminateDrawable(fadingcircle);
        initializeVariables();
        getData();
        //   setRecyclerViewAdapters();
        return view;
    }


    @Override
    public void initializeVariables() {
        adapter = new AppointmentAdapter(getActivity(), false, listener,listAppointments,controller,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);
        rvAppointmentList.setAdapter(adapter);
    }


    @Override
    public void onclick(int position) {
        Helper.setLog(TAG,listAppointments.get(position).toString());
        Intent intent = new Intent(mContext, ScheduleDetailsActivity.class);
        intent.putExtra("IsFromUpcommming", false);
        intent.putExtra("AppointmentData",listAppointments.get(position));
        startActivity(intent);

    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getRvHistoryVisibility().observe(this,aBoolean -> {
            if(aBoolean)
            {
                rvAppointmentList.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }
            else {
                rvAppointmentList.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
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

        viewModel.getListHistoryAppointments().observe(this,appointmentResults -> {
            listcount=appointmentResults.size();
        });

        rvAppointmentList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                    viewModel.fetchHistoryAppointmentList(page);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void setRecyclerView() {
        /*adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);*/
    }

    @Override
    public void setView(Object data) {
       /* this.data = (AppointmentResponce) data;
        listAppointments.addAll(Arrays.asList(this.data.getResponse()));*/
        //setRecyclerView();
    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {

           /* controller.getHistoryData();*/

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideProgressBar(loadingView);
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
