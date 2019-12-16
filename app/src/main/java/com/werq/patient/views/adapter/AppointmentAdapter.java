package com.werq.patient.views.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Utils.Helper;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.Provider;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.DateHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    Context mContext;
    boolean isFromUpcoming;
    RecyclerViewClickListerner listerner;
    ArrayList<AppointmentResult> listAppointments;
    AppointmentInterface controller;
    TabAppoinmentViewModel viewModel;


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
                              ArrayList<AppointmentResult> listAppointments,
                              AppointmentInterface controller,
                              TabAppoinmentViewModel viewModel,
                              LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.isFromUpcoming = isFromUpcoming;
        this.listerner = listerner;
        this.controller = controller;
        this.listAppointments = listAppointments;
        this.viewModel =viewModel;

        if (isFromUpcoming) {
            viewModel.getListUpcommingAppointments().observe(lifecycleOwner, new Observer<ArrayList<AppointmentResult>>() {
                @Override
                public void onChanged(ArrayList<AppointmentResult> appointmentResults) {

                    if (appointmentResults != null) {
                        listAppointments.clear();
                        listAppointments.addAll(appointmentResults);
                        notifyDataSetChanged();
                    }
                }
            });
        } else {

            viewModel.getListHistoryAppointments().observe(lifecycleOwner, appointmentResults -> {
                if (appointmentResults != null) {
                    listAppointments.clear();
                    listAppointments.addAll(appointmentResults);
                    notifyDataSetChanged();
                }
            });

        }

       /* viewModel.getListAppointments().observe(lifecycleOwner, new Observer<ArrayList<AppointmentData>>() {
            @Override
            public void onChanged(ArrayList<AppointmentData> appointmentData) {

                if(appointmentData!=null) {
                    listAppointments.clear();
                    listAppointments.addAll(appointmentData);
                    notifyDataSetChanged();
                }
            }
        });*/
        // setHasStableIds(true);

    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_appointment, parent, false);
        return new AppointmentViewHolder(itemView, listerner);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        AppointmentResult result = listAppointments.get(position);
        //Provider provider = result.get();
        Doctor doctor = result.getDoctor();

        holder.tvUseFullName.setText(doctor.getFirstName() + " " + doctor.getLastName());

        try {

            Date date = DateHelper.dateFromUtc(result.getAppintmentDate());
            holder.tvday.setText(DateHelper.dayFromDate(date, "day"));
            holder.tvMonth.setText(DateHelper.dayFromDate(date, "month"));
            holder.tvTime.setText(DateHelper.dayFromDate(date, "time"));
        } catch (ParseException e) {
            Helper.setExceptionLog("ParseException",e);
            e.printStackTrace();
        }
        Location location = result.getLocation();
        //Helper.setLog("Location",location.toString());
        holder.tvAddress.setText(location.getOrganizationName() + "\n" + location.getAddress1() + ", " + location.getCity()
                + ", " + location.getState() + ", " + location.getCountry() + ", " + location.getPostalcode());
        holder.tvSpeciality.setText(doctor.getSpeciality().getName());

        if (!TextUtils.isEmpty(result.getRescheduleApptReqDate())) {

            holder.llRescheduledLayout.setVisibility(View.VISIBLE);

            String date=viewModel.prepareRescheduledDate(result.getRescheduleApptReqDate());
            if(!TextUtils.isEmpty(date)){
                holder.tvRescheduledDate.setText(date);

            }else {
                holder.llRescheduledLayout.setVisibility(View.VISIBLE);
            }


        } else {
            holder.llRescheduledLayout.setVisibility(View.GONE);

        }


        if (isFromUpcoming) {
            if (result.getConfirmByPatient() != null && result.getConfirmByPatient()) {
                controller.statusButtonBackground(mContext, "confirmed", holder.tvstatus);
                holder.rl_profile_view.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder.layout_schedule_view.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            } else {
                controller.statusButtonBackground(mContext, "toconfirm", holder.tvstatus);
                holder.rl_profile_view.setBackgroundColor(mContext.getResources().getColor(R.color.toconfirm_bg_color));
                holder.layout_schedule_view.setBackgroundColor(mContext.getResources().getColor(R.color.toconfirm_bg_color));
            }
        } else {

            /*holder.rl_profile_view.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.layout_schedule_view.setBackgroundColor(mContext.getResources().getColor(R.color.white));*/

            if (result.getAppointmentStatus() != null && !result.getAppointmentStatus().trim().isEmpty()) {

                controller.statusButtonBackground(mContext, result.getAppointmentStatus(), holder.tvstatus);
            } else {
                holder.tvstatus.setVisibility(View.GONE);
            }

        }


        /*if(holder.tvstatus.getText().toString().toLowerCase().equals("toconfirm"))
        {

            holder.rl_profile_view.setBackgroundColor(mContext.getResources().getColor(R.color.toconfirm_bg_color));
            holder.layout_schedule_view.setBackgroundColor(mContext.getResources().getColor(R.color.toconfirm_bg_color));


        }*/


        /*Picasso.get()
                .load(provider.getProfile_photo())
                .error(R.drawable.user_image_placeholder)
                .placeholder(R.drawable.user_image_placeholder)
                .into(holder.ivUseImage);*/


    }


    @Override
    public int getItemCount() {
        return listAppointments.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvstatus)
        TextView tvstatus;
        @BindView(R.id.tvday)
        TextView tvday;
        @BindView(R.id.tvMonth)
        TextView tvMonth;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvUseFullName)
        TextView tvUseFullName;
        @BindView(R.id.tvSpeciality)
        TextView tvSpeciality;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.appointment)
        LinearLayout appointment;
        @BindView(R.id.ivUseImage)
        CircleImageView ivUseImage;
        @BindView(R.id.rl_profile_view)
        RelativeLayout rl_profile_view;
        @BindView(R.id.layout_schedule_view)
        LinearLayout layout_schedule_view;
        @BindView(R.id.llRescheduledLayout)
        LinearLayout llRescheduledLayout;
        @BindView(R.id.tvRescheduledDate)
        TextView tvRescheduledDate;

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
