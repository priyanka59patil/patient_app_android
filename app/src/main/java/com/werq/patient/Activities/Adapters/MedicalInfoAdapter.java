package com.werq.patient.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.werq.patient.R;

import java.util.ArrayList;

/**
 * Created by nisostech on 7/2/18.
 */

public class MedicalInfoAdapter extends RecyclerView.Adapter<MedicalInfoAdapter.ChipHolder> {
    Context context;
    ArrayList<String> chipviewList;

    public MedicalInfoAdapter(Context context, ArrayList<String> chipviewList) {
        this.context = context;
        this.chipviewList = chipviewList;

    }


    @Override
    public ChipHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_medical_info, parent, false);
        return new ChipHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChipHolder holder, int position) {
        final String result = chipviewList.get(position);
        holder.tvTitle.setText(result);
    }

    @Override
    public int getItemCount() {
        return chipviewList.size();
    }

    public class ChipHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public ChipHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
