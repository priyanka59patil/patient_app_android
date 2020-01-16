package com.werq.patient.views.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.service.model.ResponcejsonPojo.HistoryOfProcedure;
import com.werq.patient.viewmodel.SummaryCareViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcedureHistoryAdapter extends RecyclerView.Adapter<ProcedureHistoryAdapter.ProcedureViewHolder> {
    Context mContext;
    ArrayList<HistoryOfProcedure> procedureArrayList ;


    public ProcedureHistoryAdapter(Context mContext,
                                   ArrayList<HistoryOfProcedure> procedureArrayList,
                                   SummaryCareViewModel viewModel,
                                   LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.procedureArrayList=procedureArrayList;

        viewModel.historyProcedureList.observe(lifecycleOwner,historyOfProcedures  -> {
            if(historyOfProcedures!=null){
                /*for (int i = 0; i <historyOfProcedures.size() ; i++) {
                    Helper.setLog("planOfCares",historyOfProcedures.toString());
                }*/
                procedureArrayList.clear();
                procedureArrayList.addAll(historyOfProcedures);
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

        HistoryOfProcedure HistoryProcedure = procedureArrayList.get(position);


        if(HistoryProcedure.getCode()!=null && !HistoryProcedure.getCode().isEmpty()){
            holder.tvCode.setText(HistoryProcedure.getCode());
        }else {
            holder.tvCode.setText("-");
        }


        if(HistoryProcedure.getSite()!=null && !HistoryProcedure.getSite().isEmpty()){
            holder.tvSite.setText(HistoryProcedure.getSite());
        }else {
            holder.tvSite.setText("-");
        }

        if(HistoryProcedure.getDateDiagnosed()!=null && !HistoryProcedure.getDateDiagnosed().isEmpty()){
            Date date=null;
            try {
                date= new SimpleDateFormat("MM/dd/yyyy").parse(HistoryProcedure.getDateDiagnosed());

                holder.tvDateDiagnosed.setText(new SimpleDateFormat("MMM dd, yyyy").format(date));

            } catch (ParseException e) {
                Helper.setExceptionLog("ParseException",e);
                if(!TextUtils.isEmpty(HistoryProcedure.getDateDiagnosed())){
                    holder.tvDateDiagnosed.setText(HistoryProcedure.getDateDiagnosed());
                }else {
                    holder.tvDateDiagnosed.setText("-");
                }

                e.printStackTrace();
            }
            //holder.tvEncounterDate.setText(encounter.getEncounterDate());
        }else {
            holder.tvDateDiagnosed.setText("-");
        }


        if(HistoryProcedure.getStatus()!=null && !HistoryProcedure.getStatus().isEmpty()){
            holder.tvStatus.setText(HistoryProcedure.getStatus());
        }else {
            holder.tvStatus.setText("-");
        }
    }


    @Override
    public int getItemCount() {
        return procedureArrayList.size();
    }

    public class ProcedureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCode) TextView tvCode;
        @BindView(R.id.tvSite) TextView tvSite;
        @BindView(R.id.tvDate) TextView tvDateDiagnosed;
        @BindView(R.id.tvStatus) TextView tvStatus;
        public ProcedureViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
