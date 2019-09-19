package com.werq.patient.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Activities.ViewFileActivity;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.pojo.Files;
import com.werq.patient.Models.viewModel.ScheduleDetailsViewModel;
import com.werq.patient.Models.viewModel.TabAppoinmentViewModel;
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
    RecyclerViewClickListerner recyclerViewClickListerner;
    boolean fileTab = false;
    AppointmentInterface controller;

   /* public FilesAdapter(Context mContext, ArrayList<Files> allFiles, RecyclerViewClickListerner recyclerViewClickListerner) {
        this.mContext = mContext;
        this.allFiles = allFiles;
        this.recyclerViewClickListerner = recyclerViewClickListerner;
    }*/



    public FilesAdapter(Context mContext, ArrayList<Files> allFiles, RecyclerViewClickListerner recyclerViewClickListerner, boolean fileTab) {
        this.mContext = mContext;
        this.allFiles = allFiles;
        this.recyclerViewClickListerner = recyclerViewClickListerner;
        this.fileTab = fileTab;
    }

    public FilesAdapter(Context mContext,
                        ArrayList<Files> allFiles,
                        RecyclerViewClickListerner recyclerViewClickListerner,
                        AppointmentInterface controller,
                        ScheduleDetailsViewModel viewModel,
                        LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.allFiles = allFiles;
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

    }

    @Override
    public int getItemCount() {

        return allFiles.size();
    }

    public class FileadapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.file_view) ImageView file_view;
        @BindView(R.id.tvFileName) TextView tvFileName;
        @BindView(R.id.tvprefix) TextView tvprefix;
        @BindView(R.id.tvUsername) TextView tvUsername;
        @BindView(R.id.tvTime) TextView tvTime;
        @BindView(R.id.ivUserView) CircleImageView ivUserView;
        @BindView(R.id.cvMainlayout) CardView cvMainlayout;

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
                    switch (allFiles.get(getAdapterPosition()).getFile_type()) {
                        case "png":
                            openViewPhoto();
                            break;

                        case "jpg":
                            openViewPhoto();
                            break;
                    }
                        break;
                        case R.id.cvMainlayout:
                            recyclerViewClickListerner.onclick(getAdapterPosition());
                            break;
                    }

            }
        }

        private void openViewPhoto() {
            mContext.startActivity(new Intent(mContext, ViewFileActivity.class));
        }
    }

