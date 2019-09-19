package com.werq.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.pojo.AppointmentData;
import com.werq.patient.Models.pojo.Provider;
import com.werq.patient.Models.viewModel.TabAppoinmentViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.DateHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    Context mContext;
    boolean isFromUpcoming;
    RecyclerViewClickListerner listerner;
    ArrayList<AppointmentData> listAppointments ;
    AppointmentInterface controller;


    /*public AppointmentAdapter(Context mContext, boolean isFromUpcoming,
                              RecyclerViewClickListerner listerner,
                              ArrayList<AppointmentData> listAppointments,
                              AppointmentInterface controller) {
        this.mContext = mContext;
        this.isFromUpcoming = isFromUpcoming;
        this.listerner = listerner;
        this.listAppointments = listAppointments;
        this.controller = controller;
    }*/

    public AppointmentAdapter(Context mContext,
                       boolean isFromUpcoming,
                       RecyclerViewClickListerner listerner,
                       ArrayList<AppointmentData> listAppointments,
                       AppointmentInterface controller,
                       TabAppoinmentViewModel viewModel,
                       LifecycleOwner lifecycleOwner)
    {
        this.mContext = mContext;
        this.isFromUpcoming = isFromUpcoming;
        this.listerner=listerner;
        this.controller = controller;
        this.listAppointments=listAppointments;
        viewModel.getListAppointments().observe(lifecycleOwner, new Observer<ArrayList<AppointmentData>>() {
            @Override
            public void onChanged(ArrayList<AppointmentData> appointmentData) {

                if(appointmentData!=null) {
                    listAppointments.clear();
                    listAppointments.addAll(appointmentData);
                    notifyDataSetChanged();
                }
            }
        });
       // setHasStableIds(true);

    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_appointment, parent, false);
        return new AppointmentViewHolder(itemView,listerner);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        AppointmentData result = listAppointments.get(position);
        Provider provider = result.getProvider();

        holder.tvUseFullName.setText(provider.getFirst_name() + " " + provider.getLast_name());

        controller.statusButtonBackground(mContext, result.getSchedule_status(), holder.tvstatus);
        if(result.getSchedule_status().toLowerCase().equals("toconfirm"))
        {

            holder.rl_profile_view.setBackgroundColor(mContext.getResources().getColor(R.color.toconfirm_bg_color));
            holder.layout_schedule_view.setBackgroundColor(mContext.getResources().getColor(R.color.toconfirm_bg_color));


        }

        try {
            Date date = DateHelper.dateFromUtc(result.getAppointment_date());
            holder.tvday.setText(DateHelper.dayFromDate(date, "day"));
            holder.tvMonth.setText(DateHelper.dayFromDate(date, "month"));
            holder.tvTime.setText(DateHelper.dayFromDate(date,"time"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvAddress.setText(provider.getOffice().toString());
        holder.tvSpeciality.setText(provider.getSpeciality());
        Picasso.get()
                .load(provider.getProfile_photo())
                .error(R.drawable.user_image_placeholder)
                .placeholder(R.drawable.user_image_placeholder)
                .into(holder.ivUseImage);


    }


    @Override
    public int getItemCount() {
        return listAppointments.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvstatus) TextView tvstatus;
        @BindView(R.id.tvday) TextView tvday;
        @BindView(R.id.tvMonth) TextView tvMonth;
        @BindView(R.id.tvTime) TextView tvTime;
        @BindView(R.id.tvUseFullName) TextView tvUseFullName;
        @BindView(R.id.tvSpeciality) TextView tvSpeciality;
        @BindView(R.id.tvAddress) TextView tvAddress;
        @BindView(R.id.appointment) LinearLayout appointment;
        @BindView(R.id.ivUseImage) CircleImageView ivUseImage;
        @BindView(R.id.rl_profile_view) RelativeLayout rl_profile_view;
        @BindView(R.id.layout_schedule_view) ConstraintLayout layout_schedule_view;


        public AppointmentViewHolder(@NonNull View itemView, RecyclerViewClickListerner listerner) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                switch (view.getId()) {
                    case R.id.appointment:
                        listerner.onclick(getAdapterPosition());
                        break;
                }
            });

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.appointment:
                    listerner.onclick(getAdapterPosition());
                    break;
            }

        }
    }
}
