package com.werq.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {
    Context mContext;
    boolean practiceLocation;

    public PracticeAdapter(Context mContext, boolean practiceLocation) {
        this.mContext = mContext;
        this.practiceLocation = practiceLocation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_practice,parent,false);
    return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(practiceLocation)
         holder.tvHospitalname.setVisibility(View.GONE);
            else
            holder.tvPhoneNumber.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHospitalname,tvAddress,tvPhoneNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospitalname=(TextView)itemView.findViewById(R.id.tvHospitalname);
            tvAddress=(TextView)itemView.findViewById(R.id.tvAddress);
            tvPhoneNumber=(TextView)itemView.findViewById(R.id.tvPhoneNumber);
        }
    }
}
