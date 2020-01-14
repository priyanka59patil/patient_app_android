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
import com.werq.patient.service.model.ResponcejsonPojo.Insurance;
import com.werq.patient.viewmodel.PatientProfileViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.AppointmentViewHolder> {
    Context mContext;
    ArrayList<Insurance> insuranceList ;


    public InsuranceAdapter(Context mContext,
                            ArrayList<Insurance> insuranceList,
                            PatientProfileViewModel viewModel,
                            LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.insuranceList=insuranceList;

        viewModel.insuranceList.observe(lifecycleOwner,insurances -> {
            if(insurances!=null){
                insuranceList.clear();
                insuranceList.addAll(insurances);
                notifyDataSetChanged();

            }
        });

       /* viewModel.getListAppointments().observe(lifecycleOwner, new Observer<ArrayList<AppointmentData>>() {
            @Override
            public void onChanged(ArrayList<AppointmentData> appointmentData) {

                if(appointmentData!=null) {
                    listAppointments.clear();
                    listAppointments.addAll(appointmentData);
                    notifyDataSetChanged();
                }
            }
        });*/
       // setHasStableIds(true);

    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.insurance_cell, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        Insurance insurance = insuranceList.get(position);

        if(insurance.getPlanCompanyName()!=null && !insurance.getPlanCompanyName().isEmpty()){
            holder.tvCompanyName.setText(insurance.getPlanCompanyName());
        }else {
            holder.tvCompanyName.setText("-");
        }

        if(insurance.getPlanType()!=null && !insurance.getPlanType().isEmpty()){
            holder.tvInsuranceType.setText(insurance.getPlanType());
        }else {
            holder.tvInsuranceType.setText("-");
        }

        if(insurance.getPlanName()!=null && !insurance.getPlanName().isEmpty()){
            holder.tvInsuranceName.setText(insurance.getPlanName());
        }else {
            holder.tvInsuranceName.setText("-");
        }

        if(insurance.getMemberNumber()!=null && !insurance.getMemberNumber().isEmpty()){
            holder.tvMemberNumber.setText(insurance.getMemberNumber());
        }else {
            holder.tvMemberNumber.setText("-");
        }


    }


    @Override
    public int getItemCount() {
        return insuranceList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCompanyName) TextView tvCompanyName;
        @BindView(R.id.tvInsuranceName) TextView tvInsuranceName;
        @BindView(R.id.tvInsuranceType) TextView tvInsuranceType;
        @BindView(R.id.tvMemberNumber) TextView tvMemberNumber;


        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
