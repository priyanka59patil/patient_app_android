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
import com.werq.patient.service.model.ResponcejsonPojo.Problem;
import com.werq.patient.viewmodel.SummaryCareViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProblemListAdapter extends RecyclerView.Adapter<ProblemListAdapter.CustomViewHolder> {
    Context mContext;
    ArrayList<Problem> problemList ;


    public ProblemListAdapter(Context mContext,
                              ArrayList<Problem> problemList,
                              SummaryCareViewModel viewModel,
                              LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.problemList=problemList;

        viewModel.problemList.observe(lifecycleOwner,problems   -> {
            if(problems!=null){
                problemList.clear();
                problemList.addAll(problems);
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

        Problem problem = problemList.get(position);

        holder.label1.setText("Problem Name");
        holder.label2.setText("Problem Type");
        holder.label3.setText("Problem Diagnosed Date");
        holder.label4.setText("Problem Status");

        if(problem.getProblemStatus()!=null && !problem.getProblemStatus().isEmpty()){
            holder.tvProblemStatus.setText(problem.getProblemStatus());
        }else {
            holder.tvProblemStatus.setText("-");
        }

        if(problem.getProblemDiagnosedDate()!=null && !problem.getProblemDiagnosedDate().isEmpty()){
            holder.tvDiagnosedDate.setText(problem.getProblemDiagnosedDate());
        }else {
            holder.tvDiagnosedDate.setText("-");
        }

        if(problem.getProblemName()!=null && !problem.getProblemName().isEmpty()){
            holder.tvProblemName.setText(problem.getProblemName());
        }else {
            holder.tvProblemName.setText("-");
        }

        if(problem.getProblemType()!=null && !problem.getProblemType().isEmpty()){
            holder.tvProblemType.setText(problem.getProblemType());
        }else {
            holder.tvProblemType.setText("-");
        }


    }


    @Override
    public int getItemCount() {
        return problemList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.conLayout1) ConstraintLayout conLayout1;
        @BindView(R.id.conLayout2) ConstraintLayout conLayout2;
        @BindView(R.id.conLayout3) ConstraintLayout conLayout3;
        @BindView(R.id.conLayout4) ConstraintLayout conLayout4;


        @BindView(R.id.label1) TextView label1;
        @BindView(R.id.label2) TextView label2;
        @BindView(R.id.label3) TextView label3;
        @BindView(R.id.label4) TextView label4;

        @BindView(R.id.tvValue1) TextView tvProblemName;
        @BindView(R.id.tvValue2) TextView tvProblemType;
        @BindView(R.id.tvValue3) TextView tvDiagnosedDate;
        @BindView(R.id.tvValue4) TextView tvProblemStatus;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            conLayout1.setVisibility(View.VISIBLE);
            conLayout2.setVisibility(View.VISIBLE);
            conLayout3.setVisibility(View.VISIBLE);
            conLayout4.setVisibility(View.VISIBLE);
        }
    }
}
