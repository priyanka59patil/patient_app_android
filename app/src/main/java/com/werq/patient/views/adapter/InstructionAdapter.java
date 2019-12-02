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
import com.werq.patient.service.model.ResponcejsonPojo.Instruction;
import com.werq.patient.service.model.ResponcejsonPojo.Insurance;
import com.werq.patient.viewmodel.PatientProfileViewModel;
import com.werq.patient.viewmodel.SummeryCareViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapter.AppointmentViewHolder> {
    Context mContext;
    ArrayList<Instruction> instructionArrayList ;


    public InstructionAdapter(Context mContext,
                              ArrayList<Instruction> instructionArrayList,
                              SummeryCareViewModel viewModel,
                              LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.instructionArrayList=instructionArrayList;

        viewModel.instructionList.observe(lifecycleOwner,insurances -> {
            if(insurances!=null){
                instructionArrayList.clear();
                instructionArrayList.addAll(insurances);
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

        Instruction instruction = instructionArrayList.get(position);

        if(instruction.getInstructions()!=null && !instruction.getInstructions().isEmpty()){
            holder.tvInstruction.setText(instruction.getInstructions());
        }else {
            holder.tvInstruction.setText("-");
        }

    }


    @Override
    public int getItemCount() {
        return instructionArrayList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvInstruction) TextView tvInstruction;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
