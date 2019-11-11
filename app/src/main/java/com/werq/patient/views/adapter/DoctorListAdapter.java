package com.werq.patient.views.adapter;

import android.content.Context;
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
import com.werq.patient.R;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.Coworker;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Coworker> coworkerArrayList;


    public DoctorListAdapter(Context mContext,
                             ArrayList<Coworker> coworkerArrayList,
                             ProfileDoctorViewModel viewModel,
                             LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.coworkerArrayList=coworkerArrayList;
        viewModel.coworkerList.observe(lifecycleOwner,coworkerArrayList1 -> {

            if(coworkerArrayList!=null)
            {
                coworkerArrayList.clear();
                coworkerArrayList.addAll(coworkerArrayList1);
                notifyDataSetChanged();
            }

        });
    }

    @NonNull
    @Override
    public DoctorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user,parent,false);
        return new DoctorListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorListAdapter.ViewHolder holder, int position) {

        holder.tvUsername.setText(coworkerArrayList.get(position).getFirstName() +" "+
                coworkerArrayList.get(position).getMiddleName()+" "+
                coworkerArrayList.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return coworkerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvUsername)
        TextView tvUsername;

        TextView tvSpeciality;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}