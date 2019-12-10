package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;


import com.werq.patient.R;
import com.werq.patient.service.model.ResponcejsonPojo.AvailableTimeSlot;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;

import java.util.List;

public class NewTimeSlotAdapter extends RecyclerView.Adapter<NewTimeSlotAdapter.TimeSlotViewHolder> {
    List<AvailableTimeSlot> timeSlotList;
    LayoutInflater inflater;
    private Context context;
    TabAppoinmentViewModel viewModel;

    public NewTimeSlotAdapter(List<AvailableTimeSlot> timeSlotList,
                              TabAppoinmentViewModel viewModel,
                              LifecycleOwner lifecycleOwner) {
        this.timeSlotList = timeSlotList;
        this.viewModel=viewModel;
        viewModel.getAvailableTimeSlot().observe(lifecycleOwner,availableTimeSlots -> {
            if(availableTimeSlots!=null){
                timeSlotList.clear();
                timeSlotList.addAll(availableTimeSlots);
                notifyDataSetChanged();
            }
        });
         viewModel.getSelectTimeSlotItem().setValue(null) ;
    }

    @NonNull
    @Override
    public TimeSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.slotslayout, parent, false);
        context = parent.getContext();
        return new TimeSlotViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotViewHolder holder, final int position) {
        AvailableTimeSlot al = timeSlotList.get(position);
        holder.tvFrom.setText(al.getStartTime());
        holder.tvTo.setText(al.getEndTime());

        if (viewModel.getSelectTimeSlotItem().getValue() != null
                && viewModel.getSelectTimeSlotItem().getValue() == position) {

            holder.lltimeslot.setBackground(context.getResources().getDrawable(R.drawable.timeslotselect));
        }
        holder.lltimeslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getSelectTimeSlotItem().setValue(position) ;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }

    class TimeSlotViewHolder extends RecyclerView.ViewHolder {
        TextView tvFrom, tvTo;
        LinearLayout lltimeslot;

        public TimeSlotViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFrom = itemView.findViewById(R.id.tvFrom);
            tvTo = itemView.findViewById(R.id.tvTo);
            lltimeslot = itemView.findViewById(R.id.lltimeslot);

        }
    }
}
