package com.werq.patient.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    Context mContext;
    boolean isFromUpcoming;
    RecyclerViewClickListerner listerner;

    public AppointmentAdapter(Context mContext, boolean isFromUpcoming, RecyclerViewClickListerner listerner) {
        this.mContext = mContext;
        this.isFromUpcoming=isFromUpcoming;
        this.listerner=listerner;
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

    public class AppointmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvstatus;
        RelativeLayout appointment;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvstatus = (TextView) itemView.findViewById(R.id.tvstatus);
            appointment=(RelativeLayout)itemView.findViewById(R.id.appointment);
            appointment.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.appointment:
                    listerner.onclick(getAdapterPosition());
                    break;
            }

        }
    }
}
