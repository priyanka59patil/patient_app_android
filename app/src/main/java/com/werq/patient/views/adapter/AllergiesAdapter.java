package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;
import com.werq.patient.service.model.ResponcejsonPojo.Allergy;
import com.werq.patient.viewmodel.SummaryCareViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllergiesAdapter extends RecyclerView.Adapter<AllergiesAdapter.ProcedureViewHolder> {
    Context mContext;
    ArrayList<Allergy> allergyList ;


    public AllergiesAdapter(Context mContext,
                            ArrayList<Allergy> allergyList,
                            SummaryCareViewModel viewModel,
                            LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.allergyList=allergyList;

        viewModel.allergyList.observe(lifecycleOwner,allergyArrayList  -> {
            if(allergyArrayList!=null){
                allergyList.clear();
                allergyList.addAll(allergyArrayList);
                notifyDataSetChanged();

            }
        });


    }

    @NonNull
    @Override
    public ProcedureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_of_prodedure_cell, parent, false);
        return new ProcedureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcedureViewHolder holder, int position) {

        Allergy allergy = allergyList.get(position);
        holder.lblStatus.setText("Status");
        holder.lblSubstance.setText("Substance");
        holder.lblReactions.setText("Reactions");
        holder.lblSeverity.setText("Severity");


        if(allergy.getStatus()!=null && !allergy.getStatus().isEmpty()){
            holder.tvStatus.setText(allergy.getStatus());
        }else {
            holder.tvStatus.setText("-");
        }

        if(allergy.getSubstance()!=null && !allergy.getSubstance().isEmpty()){
            holder.tvSubstance.setText(allergy.getSubstance());
        }else {
            holder.tvSubstance.setText("-");
        }


        if(allergy.getReactions()!=null && !allergy.getReactions().isEmpty()){
            holder.tvReactions.setText(allergy.getReactions());
        }else {
            holder.tvReactions.setText("-");
        }


        if(allergy.getSeverity()!=null && !allergy.getSeverity().isEmpty()){
            holder.tvSeverity.setText(allergy.getSeverity());
        }else {
            holder.tvSeverity.setText("-");
        }

    }


    @Override
    public int getItemCount() {
        return allergyList.size();
    }

    public class ProcedureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lblCode) TextView lblStatus;
        @BindView(R.id.lblSite) TextView lblSubstance;
        @BindView(R.id.lblDate) TextView lblReactions;
        @BindView(R.id.lblStatus) TextView lblSeverity;

        @BindView(R.id.tvCode) TextView tvStatus;
        @BindView(R.id.tvSite) TextView tvSubstance;
        @BindView(R.id.tvDate) TextView tvReactions;
        @BindView(R.id.tvStatus) TextView tvSeverity;
        public ProcedureViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
