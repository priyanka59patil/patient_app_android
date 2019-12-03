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
import com.werq.patient.service.model.ResponcejsonPojo.Instruction;
import com.werq.patient.service.model.ResponcejsonPojo.PlanOfCare;
import com.werq.patient.viewmodel.SummeryCareViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanOfCareAdapter extends RecyclerView.Adapter<PlanOfCareAdapter.AppointmentViewHolder> {
    Context mContext;
    ArrayList<PlanOfCare> planOfCareArrayList ;


    public PlanOfCareAdapter(Context mContext,
                             ArrayList<PlanOfCare> planOfCareArrayList,
                             SummeryCareViewModel viewModel,
                             LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.planOfCareArrayList=planOfCareArrayList;

        viewModel.planOfCareList.observe(lifecycleOwner,planOfCares  -> {
            if(planOfCares!=null){
                planOfCareArrayList.clear();
                planOfCareArrayList.addAll(planOfCares);
                notifyDataSetChanged();

            }
        });


    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.instruction_cell, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        PlanOfCare planOfCare = planOfCareArrayList.get(position);


        if(planOfCare.getDetails()!=null && !planOfCare.getDetails().isEmpty()){
            holder.tvDetails.setText(planOfCare.getDetails());
        }else {
            holder.tvDetails.setText("-");
        }


        if(planOfCare.getInstructions()!=null && !planOfCare.getInstructions().isEmpty()){
            holder.tvInstruction.setText(planOfCare.getInstructions());
        }else {
            holder.tvInstruction.setText("-");
        }

    }


    @Override
    public int getItemCount() {
        return planOfCareArrayList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.conLayDetails)
        ConstraintLayout conLayDetails;
        @BindView(R.id.tvDetails) TextView tvDetails;
        @BindView(R.id.tvInstruction) TextView tvInstruction;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            conLayDetails.setVisibility(View.VISIBLE);
        }
    }
}
