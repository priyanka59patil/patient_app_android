package com.werq.patient.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    Context mContext;
    boolean isFromUpcoming;

    public AppointmentAdapter(Context mContext,boolean isFromUpcoming) {
        this.mContext = mContext;
        this.isFromUpcoming=isFromUpcoming;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_appointment, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {


        if (position == 3 ) {

            if(isFromUpcoming){
                holder.tvstatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.blue_button));
                holder.tvstatus.setText(mContext.getResources().getString(R.string.label_status_confirmed));
            }
            else {
                holder.tvstatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.red_button));
                holder.tvstatus.setText(mContext.getResources().getString(R.string.label_status_missed));
            }

        }
        else {
            if(!isFromUpcoming){
                holder.tvstatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.green_button));
                holder.tvstatus.setText(mContext.getResources().getString(R.string.label_status_visited));
            }
        }
    }


    @Override
    public int getItemCount() {
        return 6;
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvstatus;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvstatus = (TextView) itemView.findViewById(R.id.tvstatus);
        }
    }
}
