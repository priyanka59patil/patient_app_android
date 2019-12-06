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
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.Utils.Helper;
import com.werq.patient.service.model.Files;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.viewmodel.ChatInfoViewModel;
import com.werq.patient.viewmodel.ScheduleDetailsViewModel;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.viewmodel.ViewVisitNoteViewModel;
import com.werq.patient.views.ui.ViewFileActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AttachmentsAdapter extends RecyclerView.Adapter<AttachmentsAdapter.FileadapterViewHolder> {
    Context mContext;
    ArrayList<AttachmentResult> attachmentResultArrayList;
    RecyclerViewClickListerner recyclerViewClickListerner;
    boolean fileTab = false, fromVisitNoteDetails = false;
    AppointmentInterface controller;

   /* public FilesAdapter(Context mContext, ArrayList<Files> allFiles, RecyclerViewClickListerner recyclerViewClickListerner) {
        this.mContext = mContext;
        this.allFiles = allFiles;
        this.recyclerViewClickListerner = recyclerViewClickListerner;
    }*/

    public AttachmentsAdapter(Context mContext,
                              ArrayList<AttachmentResult> attachmentResultArrayList,
                              RecyclerViewClickListerner recyclerViewClickListerner,
                              boolean fileTab/*,
                              BottomTabViewModel viewModel,
                              LifecycleOwner lifecycleOwner*/) {
        this.mContext = mContext;
        this.attachmentResultArrayList = attachmentResultArrayList;
        this.recyclerViewClickListerner = recyclerViewClickListerner;
        this.fileTab = fileTab;

/*
        viewModel.getListAttachments().observe(lifecycleOwner, attachmentResultArrayList1 -> {
            if (attachmentResultArrayList1 != null) {
                Helper.setLog("attachmentList1", attachmentResultArrayList1.size() + "");
                attachmentResultArrayList.clear();
                attachmentResultArrayList.addAll(attachmentResultArrayList1);
                notifyDataSetChanged();
            }
        });*/

    }


    public AttachmentsAdapter(Context mContext,
                              ArrayList<AttachmentResult> attachmentResultArrayList,
                              RecyclerViewClickListerner recyclerViewClickListerner,
                              ViewVisitNoteViewModel viewModel,
                              LifecycleOwner lifecycleOwner) {
        this.fromVisitNoteDetails = true;
        this.mContext = mContext;
        this.attachmentResultArrayList = attachmentResultArrayList;
        this.recyclerViewClickListerner = recyclerViewClickListerner;

        viewModel.getVisitNoteAttachments().observe(lifecycleOwner, vnAttachmentList -> {
            if (vnAttachmentList != null) {
                Helper.setLog("vnAttachmentList", vnAttachmentList.size() + "");
                attachmentResultArrayList.clear();
                attachmentResultArrayList.addAll(vnAttachmentList);
                notifyDataSetChanged();
            }
        });

    }

    public AttachmentsAdapter(Context mContext,
                              ArrayList<AttachmentResult> attachmentResultArrayList,
                              RecyclerViewClickListerner recyclerViewClickListerner,
                              AppointmentInterface controller,
                              TabAppoinmentViewModel viewModel,
                              LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.attachmentResultArrayList = attachmentResultArrayList;
        this.recyclerViewClickListerner = recyclerViewClickListerner;
        this.controller = controller;

        viewModel.getAttachmentList().observe(lifecycleOwner, attachmentResults -> {

            if (attachmentResults != null) {
                Helper.setLog("attachmentList1", attachmentResults.size() + "");
                attachmentResultArrayList.clear();
                attachmentResultArrayList.addAll(attachmentResults);
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

        AttachmentResult result = attachmentResultArrayList.get(position);
        holder.tvFileName.setText(result.getFileName());

        holder.tvprefix.setText("From :");

       // Helper.setLog("FileType", result.getFileType());
        switch (result.getFileType()) {
            case "image/png":
                Glide.with(mContext).load(result.getFileUrl()).apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image_gray_24dp)
                        .error(R.drawable.ic_image_gray_24dp).skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(holder.file_view);

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

        if (result.getCreatedByUser().getProfilePhoto() != null && !result.getCreatedByUser().getProfilePhoto().equals("")) {
            String url = null;
            url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile + result.getCreatedByUser().getProfilePhoto();
            Glide.with(mContext).load(url).apply(new RequestOptions()
                    .placeholder(R.drawable.user_image_placeholder)
                    .error(R.drawable.user_image_placeholder).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(holder.ivUserView);

        } else {
            holder.ivUserView.setImageResource(R.drawable.user_image_placeholder);
        }

        if (!fromVisitNoteDetails) {
            if (result.getVisitNoteId() != null) {
                if (result.getVisitNoteId() != 0)
                    holder.tvHasVisitNote.setVisibility(View.VISIBLE);
                else
                    holder.tvHasVisitNote.setVisibility(View.GONE);
            } else
                holder.tvHasVisitNote.setVisibility(View.GONE);
        } else
            holder.tvHasVisitNote.setVisibility(View.GONE);



            /*try {
                Date date = DateHelper.dateFromUtc(result.getCreatedByUser().);
                holder.tvTime.setText("today " + DateHelper.dayFromDate(date, "time"));
            } catch (ParseException e) {
                e.printStackTrace();
            }*/


    }

    @Override
    public int getItemCount() {

        return attachmentResultArrayList.size();

    }

    private void openViewPhoto(AttachmentResult attachmentResult) {

        Intent intent = new Intent(mContext, ViewFileActivity.class);
        intent.putExtra("attachmentResult", attachmentResult);
        mContext.startActivity(intent);
    }

    public class FileadapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.file_view)
        ImageView file_view;
        @BindView(R.id.tvFileName)
        TextView tvFileName;
        @BindView(R.id.tvprefix)
        TextView tvprefix;
        @BindView(R.id.tvUsername)
        TextView tvUsername;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.ivUserView)
        CircleImageView ivUserView;
        @BindView(R.id.cvMainlayout)
        CardView cvMainlayout;
        @BindView(R.id.tvHasVisitNote)
        TextView tvHasVisitNote;


        public FileadapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cvMainlayout.setOnClickListener(this::onClick);
            file_view.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {


                case R.id.file_view:
                    Log.e( "onClick: ", attachmentResultArrayList.get(getAdapterPosition()).getFileType());

                    switch (attachmentResultArrayList.get(getAdapterPosition()).getFileType()) {
                        case "image/png":
                            openViewPhoto(attachmentResultArrayList.get(getAdapterPosition()));
                            break;

                        case "image/jpeg":
                            openViewPhoto(attachmentResultArrayList.get(getAdapterPosition()));
                            break;

                        case "application/pdf":
                            openViewPhoto(attachmentResultArrayList.get(getAdapterPosition()));
                            break;
                    }


                case R.id.cvMainlayout:

                    if(fromVisitNoteDetails)
                    {
                        openViewPhoto(attachmentResultArrayList.get(getAdapterPosition()));
                    }else {
                        recyclerViewClickListerner.onclick(getAdapterPosition());
                    }

                    break;
            }

        }
    }
}

