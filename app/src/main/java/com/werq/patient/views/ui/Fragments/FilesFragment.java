package com.werq.patient.views.ui.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.databinding.FragmentFilesBinding;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.views.adapter.AttachmentsAdapter;
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
    private AttachmentsAdapter attachmentsAdapter;
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
    BottomTabViewModel viewModel;
    FragmentFilesBinding fragmentFilesBinding;
    ProgressDialog progressDialog;

    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
    int page = 0;
    int listcount = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        diologListner=this::onClick;
        recyclerViewClickListerner=this::onclick;
        basicActivities=this;
        controller= new FilesController(basicActivities);
        attachmentList = new ArrayList<>();
        attachmentsAdapter = new AttachmentsAdapter(mContext, attachmentList,recyclerViewClickListerner,true);
        mBottomSheetDialog = DiologHelper.createDialogFromBottom(mContext,R.layout.filter_diolog_layout,diologListner);
        viewModel= ViewModelProviders.of(getActivity()).get(BottomTabViewModel.class);
        if(Helper.hasNetworkConnection(mContext)){
            loading =true;
            viewModel.fetchAttachments(0);

        }else {
            Helper.showToast(mContext,getString(R.string.no_network_conection));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_files, container, false);

        if(fragmentFilesBinding==null){
            fragmentFilesBinding= FragmentFilesBinding.bind(view);
        }

        fragmentFilesBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        fragmentFilesBinding.setBottomTabViewModel(viewModel);

        ButterKnife.bind(this, view);
        initializeVariables();
        //getData();

        setRecyclerView();



        viewModel.listAttachments.observe(this,attachmentResults -> {
            if(attachmentResults!=null){
                if(page==0){
                    attachmentList.clear();
                }

                attachmentList.addAll(attachmentResults);
                listcount=attachmentList.size();
                attachmentsAdapter.notifyDataSetChanged();
            }

            if(attachmentList!=null && attachmentList.size()>0){

                rvFiles.setVisibility(View.VISIBLE);
                tvNoData.setVisibility(View.GONE);

            }else {
                rvFiles.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

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

        rvFiles.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                    viewModel.fetchAttachments(page);
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





    }

    @Override
    public void initializeVariables() {

    }

    @Override
    public void setRecyclerView() {

        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvFiles,attachmentsAdapter);
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
            Helper.setLog("aaaa",attachmentList.get(position).toString());
            Helper.setLog("getAppointmentId",attachmentList.get(position).getAppointmentId()+"" );
            Helper.setLog("getVisitNoteId",attachmentList.get(position).getVisitNoteId()+"" );

            Intent intent =new Intent(mContext, ViewVisitNoteActivity.class);
            intent.putExtra("appointmentId",attachmentList.get(position).getAppointmentId());
            intent.putExtra("visitNoteId",attachmentList.get(position).getVisitNoteId());
            startActivity(intent);
        }
        else {
            Helper.showToast(mContext,"No Details Available");
        }



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
