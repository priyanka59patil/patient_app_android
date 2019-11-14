package com.werq.patient.views.ui.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentFilesBinding;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.viewmodel.AttachmentViewModel;
import com.werq.patient.views.ui.FilterDoctorList;
import com.werq.patient.views.ui.ViewFileActivity;
import com.werq.patient.views.ui.ViewVisitNoteActivity;
import com.werq.patient.views.adapter.FilesAdapter;
import com.werq.patient.Controller.FilesController;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.DiologListner;
import com.werq.patient.Interfaces.FilesInteface;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.service.model.Files;
import com.werq.patient.service.model.FilesData;
import com.werq.patient.R;
import com.werq.patient.Utils.DiologHelper;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilesFragment extends BaseFragment implements View.OnClickListener, RecyclerViewClickListerner, DiologListner, BasicActivities {

    @BindView(R.id.tvFilterDoctors)
    TextView tvFilterDoctors;
    @BindView(R.id.tvFilterFiles)
    TextView tvFilterFiles;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    @BindView(R.id.rvFiles)
    RecyclerView rvFiles;

    Context mContext;
    ArrayList<Files> allFiles;
    ArrayList<AttachmentResult> attachmentList;
    private FilesAdapter filesAdapter;
    private BottomSheetDialog mBottomSheetDialog;
    private RelativeLayout layout_filter_allDoctors;
    private RelativeLayout layout_filter_received;
    private RelativeLayout layout_filter_sent;
    ImageView iv_sent_check,iv_received_check,iv_all_check;
    RecyclerViewClickListerner recyclerViewClickListerner;
    DiologListner diologListner;
    FilesInteface controller;
    BasicActivities basicActivities;
    FilesData filesData;
    AttachmentViewModel viewModel;
    FragmentFilesBinding fragmentFilesBinding;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_files, container, false);
       // Helper.setLog("in","FilesFragment");
        mContext = getActivity();
        if(fragmentFilesBinding==null){
            fragmentFilesBinding= FragmentFilesBinding.bind(view);
        }

        viewModel= ViewModelProviders.of(this).get(AttachmentViewModel.class);
        fragmentFilesBinding.setLifecycleOwner(this);
        fragmentFilesBinding.setAttachmentViewModel(viewModel);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setRefreshTokenId(SessionManager.getSessionManager(mContext).getRefreshTokenId());

        ButterKnife.bind(this, view);
        initializeVariables();
        //getData();

        setRecyclerView();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        viewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean){
                progressDialog.show();
            }
            else {
                progressDialog.hide();
            }
        });

        /*viewModel.rvVisibility.observe(this,aBoolean -> {
            if(aBoolean){
                rvFiles.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);
            }
            else {
                rvFiles.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }
        });*/


    }

    @Override
    public void initializeVariables() {
        //context


        //listner
        diologListner=this::onClick;
        recyclerViewClickListerner=this::onclick;
        basicActivities=this;
        controller= new FilesController(basicActivities);

        //data
        attachmentList = new ArrayList<>();
        progressDialog= Helper.createProgressDialog(mContext);
        //adapters


        //dialog
        mBottomSheetDialog = DiologHelper.createDialogFromBottom(mContext,R.layout.filter_diolog_layout,diologListner);

    }

    @Override
    public void setRecyclerView() {
        filesAdapter = new FilesAdapter(mContext, attachmentList,recyclerViewClickListerner,true,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvFiles,filesAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvFiles);
    }

    @Override
    public void setView(Object data) {
        filesData=(FilesData)data;
        allFiles.addAll(Arrays.asList(filesData.getResponse()));
        setRecyclerView();

}
    @Override
    public void getIntentData() {

    }

    @Override
    public void getData() {
        controller.getData();


    }

    @Override
    public void setToolbar() {

    }



    @OnClick({R.id.tvFilterDoctors, R.id.tvFilterFiles})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvFilterDoctors:
                startActivity(new Intent(mContext, FilterDoctorList.class));
                break;
            case R.id.tvFilterFiles:
                mBottomSheetDialog.show();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.layout_filter_allDoctors:
                break;
            case R.id.layout_filter_received:
                break;
            case  R.id.layout_filter_sent:
                break;
        }
    }

    @Override
    public void onclick(int position) {

        if(attachmentList.get(position).getVisitNoteId()!=0){

            startActivity(new Intent(mContext, ViewVisitNoteActivity.class));
        }
        /*else {
            mContext.startActivity(new Intent(mContext, ViewFileActivity.class));
        }*/

       /* if(allFiles.get(position).getFileType().equals("visitNote")){

        }*/


    }

    @Override
    public void setdiologview(View view) {
        layout_filter_allDoctors=(RelativeLayout)view.findViewById(R.id.layout_filter_allDoctors);
        layout_filter_received=(RelativeLayout)view.findViewById(R.id.layout_filter_received);
        layout_filter_sent=(RelativeLayout)view.findViewById(R.id.layout_filter_sent);
        iv_sent_check=(ImageView)view.findViewById(R.id.iv_sent_check);
        iv_received_check=(ImageView)view.findViewById(R.id.iv_received_check);
        iv_all_check=(ImageView)view.findViewById(R.id.iv_all_check);
        layout_filter_allDoctors.setOnClickListener(this::onClick);
        layout_filter_received.setOnClickListener(this::onClick);
        layout_filter_sent.setOnClickListener(this::onClick);
        iv_all_check.setVisibility(View.VISIBLE);
        iv_received_check.setVisibility(View.GONE);
        iv_sent_check.setVisibility(View.GONE);
    }
}
