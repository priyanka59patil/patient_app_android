package com.werq.patient.views.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.werq.patient.BuildConfig;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import java.util.ArrayList;

public class StackImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Integer> images;
    ArrayList<String> profileUrl;
    Context mContext;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private static final int TYPE_HEADER = 0;

    public StackImagesAdapter(Context mContext/*, ArrayList<Integer> images*/,ArrayList<String> profileUrl) {
        this.images = images;
        this.mContext = mContext;
        this.profileUrl=profileUrl;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stackimage_header, parent, false);
            return new StackImagesAdapter.HeaderViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stack_image, parent, false);
            return new ViewHolder(itemView);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StackImagesAdapter.HeaderViewHolder) {
            StackImagesAdapter.HeaderViewHolder headerHolder = (StackImagesAdapter.HeaderViewHolder) holder;

            headerHolder.notify_badge.setText("+"+(profileUrl.size()-2));


        } else if (holder instanceof ViewHolder) {

            StackImagesAdapter.ViewHolder itemHolder = (StackImagesAdapter.ViewHolder) holder;

            if(profileUrl.get(position)!=null && !profileUrl.get(position).equals("")){
                String url = null;
                url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile+profileUrl.get(position);
                Glide.with(mContext).load(url).apply(new RequestOptions()
                        .placeholder(R.drawable.user_image_placeholder)
                        .error(R.drawable.user_image_placeholder).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(itemHolder.ivUserProfile);

            }
            else {
                itemHolder.ivUserProfile.setImageResource(R.drawable.user_image_placeholder);
            }
            //holder.ivUserProfile.setImageDrawable(mContext.getResources().getDrawable(images.get(position)));

        }
    }


    @Override
    public int getItemViewType(int position) {

        Helper.setLog("profileUrl.size()",profileUrl.size()+"");
        if(profileUrl.size()>2){

            if(position==getItemCount()-1){
                return TYPE_HEADER;
            }

            return TYPE_ITEM;
        }
        else {

            return TYPE_ITEM;
        }

       /* if(position==getItemCount()-1){
            return TYPE_HEADER;
        }

        return TYPE_ITEM;*/
    }

    @Override
    public int getItemCount() {
        if(profileUrl!=null){

            if(profileUrl.size()>2){
                return 3;
            }
            else {
                profileUrl.size();
            }

        }
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView ivUserProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUserProfile = (RoundedImageView) itemView.findViewById(R.id.ivUserProfile);
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView notify_badge;
        public HeaderViewHolder(View v) {
            super(v);
            notify_badge = (TextView) v.findViewById(R.id.notify_badge);
        }
    }
}
