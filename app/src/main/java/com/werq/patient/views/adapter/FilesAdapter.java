package com.werq.patient.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.werq.patient.BuildConfig;
import com.werq.patient.Utils.Helper;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.viewmodel.AttachmentViewModel;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.viewmodel.ChatInfoViewModel;
import com.werq.patient.views.ui.ViewFileActivity;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.service.model.Files;
import com.werq.patient.viewmodel.ScheduleDetailsViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.DateHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.FileadapterViewHolder> {
    Context mContext;
    ArrayList<Files> allFiles;
    ArrayList<AttachmentResult> attachmentResultArrayList;
    RecyclerViewClickListerner recyclerViewClickListerner;
    boolean fileTab = false;
    AppointmentInterface controller;

   /* public FilesAdapter(Context mContext, ArrayList<Files> allFiles, RecyclerViewClickListerner recyclerViewClickListerner) {
        this.mContext = mContext;
        this.allFiles = allFiles;
        this.recyclerViewClickListerner = recyclerViewClickListerner;
    }*/



    public FilesAdapter(Context mContext,
                        ArrayList<AttachmentResult> attachmentResultArrayList,
                        RecyclerViewClickListerner recyclerViewClickListerner,
                        boolean fileTab,
                        BottomTabViewModel viewModel,
                        LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.attachmentResultArrayList = attachmentResultArrayList;
        this.recyclerViewClickListerner = recyclerViewClickListerner;
        this.fileTab = fileTab;

        viewModel.getListAttachments().observe(lifecycleOwner,attachmentResultArrayList1 -> {
            if(attachmentResultArrayList1!=null)
            {
                Helper.setLog("attachmentList1",attachmentResultArrayList1.size()+"");
                attachmentResultArrayList.clear();
                attachmentResultArrayList.addAll(attachmentResultArrayList1);
                notifyDataSetChanged();
            }
        });

    }

    public FilesAdapter(Context mContext,
                        ArrayList<AttachmentResult> attachmentResultArrayList,
                        RecyclerViewClickListerner recyclerViewClickListerner,
                        AppointmentInterface controller,
                        ScheduleDetailsViewModel viewModel,
                        LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.attachmentResultArrayList = attachmentResultArrayList;
        this.recyclerViewClickListerner = recyclerViewClickListerner;
        this.controller=controller;

        viewModel.getAttachmentList().observe(lifecycleOwner,attachmentResults -> {
            if(attachmentResults!=null){
                Helper.setLog("attachmentList1",attachmentResults.size()+"");
                attachmentResultArrayList.clear();
                attachmentResultArrayList.addAll(attachmentResults);
                notifyDataSetChanged();
            }
        });

    }


    public FilesAdapter(Context mContext,
                        /*ArrayList<Files> allFiles,*/
                        RecyclerViewClickListerner recyclerViewClickListerner,
                        ChatInfoViewModel viewModel,
                        LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        //this.allFiles = allFiles;
        allFiles=new ArrayList<>();
        this.recyclerViewClickListerner = recyclerViewClickListerner;
        this.controller=controller;

        viewModel.getFilesList().observe(lifecycleOwner,files -> {
            if(files!=null)
            {
                allFiles.clear();
                allFiles.addAll(files);
                notifyDataSetChanged();
            }
        });

    }

    @NonNull
    @Override
    public FileadapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (fileTab) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_list_files, parent, false);
            return new FileadapterViewHolder(itemView);
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_files, parent, false);
        return new FileadapterViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull FileadapterViewHolder holder, int position) {
       // if(fileTab){


            AttachmentResult result = attachmentResultArrayList.get(position);
            holder.tvFileName.setText(result.getFileName());

            holder.tvprefix.setText("From :");

           /* Helper.setLog("FileType",result.getFileType());
            Log.e( "onBindViewHolder: ", result.getFileUrl());*/
            switch (result.getFileType()) {
                case "image/png":
                    Glide.with(mContext).load(result.getFileUrl()).apply(new RequestOptions()
                            .placeholder(R.drawable.ic_image_gray_24dp)
                            .error(R.drawable.ic_image_gray_24dp).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(holder.file_view);

                    //holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.imageone));
                    break;
                case "image/jpeg":
                    //holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.imageone));
                    Glide.with(mContext).load(result.getFileUrl()).apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image_gray_24dp)
                        .error(R.drawable.ic_image_gray_24dp).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(holder.file_view);
                    break;
                case "application/pdf":
                    holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pdf));
                    break;
                case "visitNote":
                    holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.visitnote));
                    break;
            }

            holder.tvUsername.setText(result.getCreatedByUser().getFirstName()
                    + " " + result.getCreatedByUser().getLastName());

            holder.tvTime.setText("Time not available");

            if(result.getCreatedByUser().getProfilePhoto()!=null && !result.getCreatedByUser().getProfilePhoto().equals("")){
                String url = null;
                url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile+result.getCreatedByUser().getProfilePhoto();
                Glide.with(mContext).load(url).apply(new RequestOptions()
                        .placeholder(R.drawable.user_image_placeholder)
                        .error(R.drawable.user_image_placeholder).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(holder.ivUserView);

            }
            else {
                holder.ivUserView.setImageResource(R.drawable.user_image_placeholder);
            }

            if(result.getVisitNoteId()!=0){
                holder.tvHasVisitNote.setVisibility(View.VISIBLE);
            }else
                holder.tvHasVisitNote.setVisibility(View.GONE);
            /*try {
                Date date = DateHelper.dateFromUtc(result.getCreatedByUser().);
                holder.tvTime.setText("today " + DateHelper.dayFromDate(date, "time"));
            } catch (ParseException e) {
                e.printStackTrace();
            }*/


       /* }else {
            Files file = allFiles.get(position);
            holder.tvFileName.setText(file.getFile_name());

            holder.tvprefix.setText("From :");

            switch (file.getFile_type()) {
                case "png":
                    holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.imageone));
                    break;
                case "jpg":
                    holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.imageone));
                    break;
                case "pdf":
                    holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pdf));
                    break;
                case "visitNote":
                    holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.visitnote));
                    break;
            }
            if (fileTab)
                holder.tvUsername.setText(file.getProvider().getFirst_name() + " " + file.getProvider().getLast_name());
            else
                holder.tvUsername.setText("Parag Mane");
            try {
                Date date = DateHelper.dateFromUtc(file.getCreated_at());
                holder.tvTime.setText("today " + DateHelper.dayFromDate(date, "time"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/


    }

    @Override
    public int getItemCount() {

        if(fileTab){
            return attachmentResultArrayList.size();
        }else {
            return allFiles.size();
        }

    }

    public class FileadapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.file_view) ImageView file_view;
        @BindView(R.id.tvFileName) TextView tvFileName;
        @BindView(R.id.tvprefix) TextView tvprefix;
        @BindView(R.id.tvUsername) TextView tvUsername;
        @BindView(R.id.tvTime) TextView tvTime;
        @BindView(R.id.ivUserView) CircleImageView ivUserView;
        @BindView(R.id.cvMainlayout) CardView cvMainlayout;
        @BindView(R.id.tvHasVisitNote) TextView tvHasVisitNote;


        public FileadapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cvMainlayout.setOnClickListener(this);
            file_view.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {


                case R.id.file_view:
                    if(fileTab)
                    {
                        switch (attachmentResultArrayList.get(getAdapterPosition()).getFileType()) {
                            case "image/png":
                                openViewPhoto(attachmentResultArrayList.get(getAdapterPosition()));
                                break;

                            case "image/jpeg":
                                openViewPhoto(attachmentResultArrayList.get(getAdapterPosition()));
                                break;
                        }

                    }else {
                        switch (allFiles.get(getAdapterPosition()).getFile_type()) {
                            case "png":
                                openViewPhoto(null);
                                break;

                            case "jpg":
                                openViewPhoto(null);
                                break;
                        }
                    }


                        case R.id.cvMainlayout:
                            recyclerViewClickListerner.onclick(getAdapterPosition());
                            break;
                    }

            }
        }

        private void openViewPhoto(AttachmentResult attachmentResult) {

        Intent intent=new Intent(mContext, ViewFileActivity.class);
        intent.putExtra("attachmentResult",attachmentResult);
            mContext.startActivity(intent);
        }
    }

