package com.werq.patient.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Activities.FilterDoctorList;
import com.werq.patient.Activities.ViewVisitNoteActivity;
import com.werq.patient.Adapters.FilesAdapter;
import com.werq.patient.Interfaces.DiologListner;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.Files;
import com.werq.patient.R;
import com.werq.patient.Utils.DiologHelper;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilesFragment extends Fragment implements View.OnClickListener, RecyclerViewClickListerner, DiologListner {

    @BindView(R.id.tvFilterDoctors)
    TextView tvFilterDoctors;
    @BindView(R.id.tvFilterFiles)
    TextView tvFilterFiles;
    @BindView(R.id.rvFiles)
    RecyclerView rvFiles;

    Context mContext;
    ArrayList<Files> allFiles;
    private FilesAdapter filesAdapter;
    private BottomSheetDialog mBottomSheetDialog;
    private RelativeLayout layout_filter_allDoctors;
    private RelativeLayout layout_filter_received;
    private RelativeLayout layout_filter_sent;
    ImageView iv_sent_check,iv_received_check,iv_all_check;
    RecyclerViewClickListerner recyclerViewClickListerner;
    DiologListner diologListner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_files, container, false);
        ButterKnife.bind(this, view);
        initializeVariables();
        setRecyclerViewAdapter();


        return view;
    }

    private void setRecyclerViewAdapter() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvFiles,filesAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvFiles);
    }

    private void initializeVariables() {
        //context
        mContext = getActivity();

         //listner
        diologListner=this::onClick;
        recyclerViewClickListerner=this::onclick;

        //data
        allFiles = getFilesData();

        //adapters
        filesAdapter = new FilesAdapter(mContext, allFiles,recyclerViewClickListerner,true);

        //dialog
        mBottomSheetDialog = DiologHelper.createDialogFromBottom(mContext,R.layout.filter_diolog_layout,diologListner);

       ;
    }

    private ArrayList<Files> getFilesData() {
        ArrayList<Files> files = new ArrayList<>();
        Files file = new Files(R.drawable.imageone, "visitNote", "Visit Note", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file1 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file2 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file3 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file4 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file5 = new Files(R.drawable.imagetwo, "visitNote", "Visit Note", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file6 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file7 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");

        files.add(file);
        files.add(file1);
        files.add(file2);
        files.add(file3);
        files.add(file4);
        files.add(file5);
        files.add(file6);
        files.add(file7);


        return files;
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
        if(allFiles.get(position).getFileType().equals("visitNote")){
            startActivity(new Intent(mContext, ViewVisitNoteActivity.class));
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
}
