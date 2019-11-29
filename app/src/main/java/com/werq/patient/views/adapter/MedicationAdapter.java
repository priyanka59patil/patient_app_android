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
import com.werq.patient.service.model.ResponcejsonPojo.MedicationDatum;
import com.werq.patient.viewmodel.PatientProfileViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.AppointmentViewHolder> {
    Context mContext;
    ArrayList<MedicationDatum> medicationDatumArrayList ;


    public MedicationAdapter(Context mContext,
                             ArrayList<MedicationDatum> medicationDatumArrayList,
                             PatientProfileViewModel viewModel,
                             LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.medicationDatumArrayList=medicationDatumArrayList;

        viewModel.medicationList.observe(lifecycleOwner,medicationList -> {
            medicationDatumArrayList.clear();
            medicationDatumArrayList.addAll(medicationList);
            notifyDataSetChanged();
        });


    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.insurance_cell, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        MedicationDatum medication = medicationDatumArrayList.get(position);

        if(medication.getMedication()!=null && !medication.getMedication().isEmpty()){
            holder.tvMedication.setText(medication.getMedication());
        }else {
            holder.tvMedication.setText("-");
        }

        if(medication.getGenericCode()!=null && !medication.getGenericCode().isEmpty()){
            holder.tvGenericCode.setText(medication.getGenericCode());
        }else {
            holder.tvGenericCode.setText("-");
        }

        if(medication.getRxNorm()!=null && !medication.getRxNorm().isEmpty()){
            holder.tvRxnorm.setText(medication.getRxNorm());
        }else {
            holder.tvRxnorm.setText("-");
        }

        if(medication.getStrength()!=null && !medication.getStrength().isEmpty()){
            holder.tvStrenght.setText(medication.getStrength());
        }else {
            holder.tvStrenght.setText("-");
        }

        if(medication.getStrengthUnit()!=null && !medication.getStrengthUnit().isEmpty()){
            holder.tvStrenghtUnit.setText(medication.getStrengthUnit());
        }else {
            holder.tvStrenghtUnit.setText("-");
        }

        if(medication.getRoute()!=null && !medication.getRoute().isEmpty()){
            holder.tvRoute.setText(medication.getRoute());
        }else {
            holder.tvRoute.setText("-");
        }

        if(medication.getDose()!=null && !medication.getDose().isEmpty()){
            holder.tvDose.setText(medication.getDose());
        }else {
            holder.tvDose.setText("-");
        }

        if(medication.getDoseForm()!=null && !medication.getDoseForm().isEmpty()){
            holder.tvDoseForm.setText(medication.getDoseForm());
        }else {
            holder.tvDoseForm.setText("-");
        }

        if(medication.getFrequency()!=null && !medication.getFrequency().isEmpty()){
            holder.tvFrequency.setText(medication.getFrequency());
        }else {
            holder.tvFrequency.setText("-");
        }
        if(medication.getDateStarted()!=null && !medication.getDateStarted().isEmpty()){
            holder.tvDateStarted.setText(medication.getDateStarted());
        }else {
            holder.tvDateStarted.setText("-");
        }

        if(medication.getDateEnded()!=null && !medication.getDateEnded().isEmpty()){
            holder.tvDateEnded.setText(medication.getDateEnded());
        }else {
            holder.tvDateEnded.setText("-");
        }

        if(medication.getStatus()!=null && !medication.getStatus().isEmpty()){
            holder.tvStatus.setText(medication.getStatus());
        }else {
            holder.tvStatus.setText("-");
        }

        if(medication.getSig()!=null && !medication.getSig().isEmpty()){
            holder.tvSig.setText(medication.getSig());
        }else {
            holder.tvSig.setText("-");
        }


    }


    @Override
    public int getItemCount() {
        return medicationDatumArrayList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCompanyName) TextView tvMedication;
        @BindView(R.id.tvInsuranceName) TextView tvGenericCode;
        @BindView(R.id.tvInsuranceType) TextView tvRxnorm;
        @BindView(R.id.tvMemberNumber) TextView tvStrenght;
        @BindView(R.id.tvMemberNumber) TextView tvStrenghtUnit;

        @BindView(R.id.tvCompanyName) TextView tvRoute;
        @BindView(R.id.tvInsuranceName) TextView tvDose;
        @BindView(R.id.tvInsuranceName) TextView tvDoseForm;
        @BindView(R.id.tvInsuranceType) TextView tvFrequency;
        @BindView(R.id.tvMemberNumber) TextView tvDateStarted;
        @BindView(R.id.tvMemberNumber) TextView tvDateEnded;
        @BindView(R.id.tvMemberNumber) TextView tvStatus;
        @BindView(R.id.tvMemberNumber) TextView tvSig;


        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
