package com.werq.patient.views.ui.Fragments;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.views.ui.ScheduleDetailsActivity;
import com.werq.patient.views.adapter.AppointmentAdapter;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.AppointmentResponce;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.Factory.ViewModelProviderFactory;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentTabAppointmentBinding;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabAppointmentFragment extends BaseFragment implements RecyclerViewClickListerner, BasicActivities {


    //adapter
    AppointmentAdapter adapter;
    //context
    Context mContext;
    @BindView(R.id.rvAppointmentList)
    RecyclerView rvAppointmentList;
    /*@BindView(R.id.loadingView)
    ProgressBar loadingView;
    Sprite fadingCircle;*/
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
    int page = 0;
    int listcount = 0;


    //listner
    RecyclerViewClickListerner listener;
    AppointmentInterface controller;
    BasicActivities basicActivities;
    //data
    AppointmentResponce data;
    ArrayList<AppointmentResult> listAppointments;
    TabAppoinmentViewModel viewModel;
    FragmentTabAppointmentBinding appointmentBinding;
    private String TAG="TabAppointmentFragment";

    ProgressDialog progressDialog;


    @Override
    public void initializeVariables() {

        setRecyclerView();

    }

    @Override
    public void setRecyclerView() {
        /*adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);*/

    }

    @Override
    public void setView(Object data) {
        /*this.data=(AppointmentResponce)data;
        listAppointments.addAll(Arrays.asList(this.data.getResponse()));
        setRecyclerView();*/
    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {
        /*controller.getUpcomingData();*/

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate: " );
        mContext = getContext();
        listAppointments=new ArrayList<>();
        listener = this::onclick;
        basicActivities=this;
        controller=new AppointmentController(basicActivities);
        viewModel= ViewModelProviders.of(this,new ViewModelProviderFactory(true)).get(TabAppoinmentViewModel.class);

        if(Helper.hasNetworkConnection(mContext)){

            viewModel.fetchUpcomingAppointmentList(0);

        } else {
            Helper.showToast(mContext,"No Network Connection");
        }

        LocalBroadcastManager.getInstance(mContext).registerReceiver(
                confirmedAppointmentBR,
                new IntentFilter(getString(R.string.CONFIRMED_APPOINTMENT_BROADCAST)));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e(TAG, "onActivityResult: "+requestCode+" "+resultCode );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );
        View view = inflater.inflate(R.layout.fragment_tab_appointment, container, false);

            if(appointmentBinding==null){
                appointmentBinding=FragmentTabAppointmentBinding.bind(view);
            }
            appointmentBinding.setLifecycleOwner(this);
            setBaseViewModel(viewModel);
            appointmentBinding.setAppontmentViewModel(viewModel);

            ButterKnife.bind(this, view);
            initializeVariables();
            adapter = new AppointmentAdapter(getActivity(), true, listener,listAppointments,controller,viewModel,this);
            RecyclerViewHelper.setAdapterToRecylerView(mContext, rvAppointmentList, adapter);
            RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvAppointmentList);
            rvAppointmentList.setAdapter(adapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        viewModel.getRvVisibility().observe(this,aBoolean -> {
            if(aBoolean)
            {
                rvAppointmentList.setVisibility(View.VISIBLE);
            }
            else {
                rvAppointmentList.setVisibility(View.GONE);
            }
        });

        viewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean ){
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
            }
        });

        viewModel.listUpcommingAppointments.observe(this,appointmentResults -> {
            if(appointmentResults!=null){
                listcount=appointmentResults.size();
            }
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
                        if (listcount < 4) {
                            loading = false;
                        }
                        int count = page + 1;
                        int data = totalItemCount;

                        if (data == (count * 4)) {
                            if (loading) {
                                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                    loading=true;
                                    ++page;
                                    viewModel.fetchUpcomingAppointmentList(page);
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
    public void onclick(int position) {

      //  String gsonData= Helper.getGsonInstance().toJson(listAppointments.get(position));
        Helper.setLog(TAG,listAppointments.get(position).toString());
        Intent intent = new Intent(mContext, ScheduleDetailsActivity.class);
        intent.putExtra("IsFromUpcommming", true);
        intent.putExtra("AppointmentData",listAppointments.get(position));
        startActivity(intent);

    }


    private BroadcastReceiver confirmedAppointmentBR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Helper.setLog("listening","confirmedAppointmentBR");
            if(Helper.hasNetworkConnection(mContext)){

                viewModel.fetchUpcomingAppointmentList(0);

            } else {
                Helper.showToast(mContext,"No Network Connection");
            }
           /* Log.e(TAG, "unreadCountReceiver: "+ intent.getStringExtra("type"));

                if (intent.getStringExtra("type").equals("UnreadCount"))

                    try {
                        if (adapter != null && rvAvailableChatUser != null) {
                            sortRecentChatList();
                            adapter.notifyDataSetChanged();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/


        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.hide();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.hide();
        }
    }
}
