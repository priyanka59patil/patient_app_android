package com.werq.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Models.Files;
import com.werq.patient.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.FileadapterViewHolder> {
   Context mContext;
    ArrayList<Files> allFiles;

    public FilesAdapter(Context mContext, ArrayList<Files> allFiles) {
        this.mContext = mContext;
        this.allFiles = allFiles;
    }

    @NonNull
    @Override
    public FileadapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_files, parent, false);
        return new FileadapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FileadapterViewHolder holder, int position) {
     Files file=allFiles.get(position);
        holder.tvFileName.setText(file.getFileName());
        if(file.getUserType().equals("sender")){
            holder.tvprefix.setText("To :");
        }
        else {
            holder.tvprefix.setText("From :");
        }
        switch (file.getFileType()){
            case "image":
                holder.file_view.setImageDrawable(mContext.getResources().getDrawable(file.getFile()));
                break;
            case "pdf":
                holder.file_view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pdf));
                break;
        }

        holder.tvUsername.setText(file.getUserName());
        holder.tvTime.setText(file.getTime());
    }

    @Override
    public int getItemCount() {
        return allFiles.size();
    }

    public class FileadapterViewHolder extends RecyclerView.ViewHolder {
        ImageView file_view;
        TextView tvFileName,tvprefix,tvUsername,tvTime;
        CircleImageView ivUserView;
        public FileadapterViewHolder(@NonNull View itemView) {
            super(itemView);
            file_view=(ImageView) itemView.findViewById(R.id.file_view);
            tvFileName=(TextView)itemView.findViewById(R.id.tvFileName);
            tvprefix=(TextView)itemView.findViewById(R.id.tvprefix);
            ivUserView=(CircleImageView)itemView.findViewById(R.id.ivUserView);
            tvUsername=(TextView)itemView.findViewById(R.id.tvUsername);
            tvTime=(TextView)itemView.findViewById(R.id.tvTime);

        }
    }
}
