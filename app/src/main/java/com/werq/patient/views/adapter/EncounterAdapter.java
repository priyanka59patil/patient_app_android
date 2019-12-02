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
import com.werq.patient.service.model.ResponcejsonPojo.Encounter;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationDatum;
import com.werq.patient.viewmodel.PatientProfileViewModel;
import com.werq.patient.viewmodel.SummeryCareViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EncounterAdapter extends RecyclerView.Adapter<EncounterAdapter.AppointmentViewHolder> {
    Context mContext;
    ArrayList<Encounter> encounterArrayList ;
    String address="";


    public EncounterAdapter(Context mContext,
                            ArrayList<Encounter> encounterArrayList,
                            SummeryCareViewModel viewModel,
                            LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.encounterArrayList=encounterArrayList;

        viewModel.encounterList.observe(lifecycleOwner,encounters -> {
            if(encounterArrayList!=null){
                encounterArrayList.clear();
                encounterArrayList.addAll(encounters);
                notifyDataSetChanged();
            }
        });


        /*viewModel.medicationList.observe(lifecycleOwner,medicationList -> {
            medicationDatumArrayList.clear();
            medicationDatumArrayList.addAll(medicationList);
            notifyDataSetChanged();
        });*/


    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.encounter_cell, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        Encounter encounter = encounterArrayList.get(position);



        if(position==0){
            address=encounter.getParagraph();
            holder.conLayAddress.setVisibility(View.VISIBLE);
            if(encounter.getParagraph()!=null && !encounter.getParagraph().isEmpty()){
                holder.tvParagraph.setText(encounter.getParagraph());
            }else {
                holder.tvParagraph.setText("Not Available");
            }

        }else {

            if(address.equalsIgnoreCase(encounter.getParagraph())){
                holder.conLayAddress.setVisibility(View.GONE);

            }else {
                address=encounter.getParagraph();
                holder.conLayAddress.setVisibility(View.VISIBLE);
                if(encounter.getParagraph()!=null && !encounter.getParagraph().isEmpty()){
                    holder.tvParagraph.setText(encounter.getParagraph());
                }else {
                    holder.tvParagraph.setText("Not Available");
                }
            }

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
            Date encounterDate=null;
            try {
                encounterDate= new SimpleDateFormat("MM/dd/yyyy").parse(encounter.getEncounterDate());

                holder.tvEncounterDate.setText(new SimpleDateFormat("MMM dd, yyyy").format(encounterDate));

            } catch (ParseException e) {
                holder.tvEncounterDate.setText("-");
                e.printStackTrace();
            }
            //holder.tvEncounterDate.setText(encounter.getEncounterDate());
        }else {
            holder.tvEncounterDate.setText("-");
        }




    }


    @Override
    public int getItemCount() {
        return encounterArrayList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvAddress) TextView tvParagraph;
        @BindView(R.id.tvDignosis) TextView tvDignosis;
        @BindView(R.id.tvLocation) TextView tvLocation;
        @BindView(R.id.tvDate) TextView tvEncounterDate;
        @BindView(R.id.conLayAddress)
        ConstraintLayout conLayAddress;


        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
