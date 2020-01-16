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
import com.werq.patient.viewmodel.SummaryCareViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastillnessHistoryAdapter extends RecyclerView.Adapter<PastillnessHistoryAdapter.ProcedureViewHolder> {
    Context mContext;
    ArrayList<PastillnessHistory> pastillnessList ;


    public PastillnessHistoryAdapter(Context mContext,
                                     ArrayList<PastillnessHistory> pastillnessList,
                                     SummaryCareViewModel viewModel,
                                     LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.pastillnessList=pastillnessList;

        viewModel.pastillnessHistoryList.observe(lifecycleOwner,pastillnessHistories   -> {
            if(pastillnessHistories!=null){
                pastillnessList.clear();
                pastillnessList.addAll(pastillnessHistories);
                notifyDataSetChanged();

            }
        });


    }

    @NonNull
    @Override
    public ProcedureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_info_details_cell, parent, false);
        return new ProcedureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcedureViewHolder holder, int position) {

        PastillnessHistory pastillnessHistory = pastillnessList.get(position);
        holder.label1.setText("Problem");
        holder.label2.setText("Type");


        if(pastillnessHistory.getProblem()!=null && !pastillnessHistory.getProblem().isEmpty()){
            holder.tvProblem.setText(pastillnessHistory.getProblem());
        }else {
            holder.tvProblem.setText("-");
        }

        if(pastillnessHistory.getType()!=null && !pastillnessHistory.getType().isEmpty()){
            holder.tvType.setText(pastillnessHistory.getType());
        }else {
            holder.tvType.setText("-");
        }


    }


    @Override
    public int getItemCount() {
        return pastillnessList.size();
    }

    public class ProcedureViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.conLayout1) ConstraintLayout conLayout1;
        @BindView(R.id.conLayout2) ConstraintLayout conLayout2;
        @BindView(R.id.conLayout3) ConstraintLayout conLayout3;
        @BindView(R.id.conLayout4) ConstraintLayout conLayout4;


        @BindView(R.id.label1) TextView label1;
        @BindView(R.id.label2) TextView label2;
       /* @BindView(R.id.label3) TextView lblReactions;
        @BindView(R.id.label4) TextView lblSeverity;*/

        @BindView(R.id.tvValue1) TextView tvProblem;
        @BindView(R.id.tvValue2) TextView tvType;
        /*@BindView(R.id.tvValue3) TextView tvReactions;
        @BindView(R.id.tvValue4) TextView tvSeverity;*/
        public ProcedureViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            conLayout1.setVisibility(View.VISIBLE);
            conLayout2.setVisibility(View.VISIBLE);
            conLayout3.setVisibility(View.GONE);
            conLayout4.setVisibility(View.GONE);
        }
    }
}
