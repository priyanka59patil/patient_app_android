package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;
import com.werq.patient.service.model.ResponcejsonPojo.PastillnessHistory;
import com.werq.patient.service.model.ResponcejsonPojo.SocialHistory;
import com.werq.patient.viewmodel.SummeryCareViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialHistoryAdapter extends RecyclerView.Adapter<SocialHistoryAdapter.CustomViewHolder> {
    Context mContext;
    ArrayList<SocialHistory> socialHistoryList ;


    public SocialHistoryAdapter(Context mContext,
                                ArrayList<SocialHistory> socialHistoryList,
                                SummeryCareViewModel viewModel,
                                LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.socialHistoryList=socialHistoryList;

        viewModel.socialHistoryList.observe(lifecycleOwner,socialHistories  -> {
            if(socialHistories!=null){
                socialHistoryList.clear();
                socialHistoryList.addAll(socialHistories);
                notifyDataSetChanged();

            }
        });


    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_info_details_cell, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        SocialHistory pastillnessHistory = socialHistoryList.get(position);
        holder.label1.setText("Activity");
        holder.label2.setText("Code");


        if(pastillnessHistory.getActivity()!=null && !pastillnessHistory.getActivity().isEmpty()){
            holder.tvActivity.setText(pastillnessHistory.getActivity());
        }else {
            holder.tvActivity.setText("-");
        }

        if(pastillnessHistory.getCode()!=null && !pastillnessHistory.getCode().isEmpty()){
            holder.tvCode.setText(pastillnessHistory.getCode());
        }else {
            holder.tvCode.setText("-");
        }


    }


    @Override
    public int getItemCount() {
        return socialHistoryList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.conLayout1) ConstraintLayout conLayout1;
        @BindView(R.id.conLayout2) ConstraintLayout conLayout2;
        @BindView(R.id.conLayout3) ConstraintLayout conLayout3;
        @BindView(R.id.conLayout4) ConstraintLayout conLayout4;


        @BindView(R.id.label1) TextView label1;
        @BindView(R.id.label2) TextView label2;
       /* @BindView(R.id.label3) TextView lblReactions;
        @BindView(R.id.label4) TextView lblSeverity;*/

        @BindView(R.id.tvValue1) TextView tvActivity;
        @BindView(R.id.tvValue2) TextView tvCode;
        /*@BindView(R.id.tvValue3) TextView tvReactions;
        @BindView(R.id.tvValue4) TextView tvSeverity;*/
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            conLayout1.setVisibility(View.VISIBLE);
            conLayout2.setVisibility(View.VISIBLE);
            conLayout3.setVisibility(View.GONE);
            conLayout4.setVisibility(View.GONE);
        }
    }
}
