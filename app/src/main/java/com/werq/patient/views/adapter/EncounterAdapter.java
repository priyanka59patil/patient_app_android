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
import com.werq.patient.service.model.ResponcejsonPojo.Encounter;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationDatum;
import com.werq.patient.viewmodel.PatientProfileViewModel;
import com.werq.patient.viewmodel.SummeryCareViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EncounterAdapter extends RecyclerView.Adapter<EncounterAdapter.AppointmentViewHolder> {
    Context mContext;
    ArrayList<Encounter> encounterArrayList ;


    public EncounterAdapter(Context mContext,
                            ArrayList<Encounter> encounterArrayList,
                            SummeryCareViewModel viewModel,
                            LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.encounterArrayList=encounterArrayList;

        /*viewModel.medicationList.observe(lifecycleOwner,medicationList -> {
            medicationDatumArrayList.clear();
            medicationDatumArrayList.addAll(medicationList);
            notifyDataSetChanged();
        });*/


    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_cell, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        Encounter encounter = encounterArrayList.get(position);

        if(encounter.getParagraph()!=null && !encounter.getParagraph().isEmpty()){
            holder.tvParagraph.setText(encounter.getParagraph());
        }else {
            holder.tvParagraph.setText("-");
        }

        if(encounter.getDiagnosis()!=null && !encounter.getDiagnosis().isEmpty()){
            holder.tvDignosis.setText(encounter.getDiagnosis());
        }else {
            holder.tvDignosis.setText("-");
        }


        if(encounter.getLocation()!=null && !encounter.getLocation().isEmpty()){
            holder.tvLocation.setText(encounter.getLocation());
        }else {
            holder.tvLocation.setText("-");
        }

        if(encounter.getEncounterDate()!=null && !encounter.getEncounterDate().isEmpty()){
            holder.tvEncounterDate.setText(encounter.getEncounterDate());
        }else {
            holder.tvEncounterDate.setText("-");
        }




    }


    @Override
    public int getItemCount() {
        return encounterArrayList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvMedication) TextView tvParagraph;
        @BindView(R.id.tvStrenght) TextView tvDignosis;
        @BindView(R.id.tvStrengthUnit) TextView tvLocation;
        @BindView(R.id.tvDoseForm) TextView tvEncounterDate;


        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
