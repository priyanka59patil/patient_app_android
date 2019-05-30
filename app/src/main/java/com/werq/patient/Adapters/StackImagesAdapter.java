package com.werq.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.werq.patient.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StackImagesAdapter extends RecyclerView.Adapter<StackImagesAdapter.ViewHolder> {
    ArrayList<Integer> images;
    Context mContext;

    public StackImagesAdapter( Context mContext,ArrayList<Integer> images) {
        this.images = images;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stack_image, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.ivUserProfile.setImageDrawable(mContext.getResources().getDrawable(images.get(position)));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView ivUserProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUserProfile=(RoundedImageView)itemView.findViewById(R.id.ivUserProfile);
        }
    }
}
