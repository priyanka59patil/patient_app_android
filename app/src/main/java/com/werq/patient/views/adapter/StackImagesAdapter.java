package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.werq.patient.R;

import java.util.ArrayList;

public class StackImagesAdapter extends RecyclerView.Adapter<StackImagesAdapter.ViewHolder> {
    ArrayList<Integer> images;
    Context mContext;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private static final int TYPE_HEADER = 0;

    public StackImagesAdapter(Context mContext, ArrayList<Integer> images) {
        this.images = images;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stackimage_header, parent, false);
            return new HeaderViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stack_image, parent, false);
            return new ViewHolder(itemView);
        }


        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

//            headerHolder.txtHeader.setText(mHeaderTitle);

        } else if (holder instanceof ViewHolder) {
            holder.ivUserProfile.setImageDrawable(mContext.getResources().getDrawable(images.get(position)));

        }

    }
    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView ivUserProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUserProfile = (RoundedImageView) itemView.findViewById(R.id.ivUserProfile);
        }
    }

    private class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View v) {
            super(v);
        }
    }
}
