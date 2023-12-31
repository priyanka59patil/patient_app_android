package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.Callback.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;

import java.util.ArrayList;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Location> locationsList;
    RecyclerViewClickListerner rvClickListerner;

    public PracticeAdapter(Context mContext,
                           ArrayList<Location> locationsList,
                           ProfileDoctorViewModel profileDoctorViewModel,
                           LifecycleOwner lifecycleOwner,
                           RecyclerViewClickListerner rvClickListerner) {
        this.mContext = mContext;
        this.locationsList=locationsList;
        this.rvClickListerner=rvClickListerner;
        profileDoctorViewModel.locationsList.observe(lifecycleOwner,locations -> {
            if(locations!=null){
                locationsList.clear();
                locationsList.addAll(locations);
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public PracticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_practice,parent,false);
    return new PracticeAdapter.ViewHolder(view,rvClickListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        /*if(practiceLocation)
         holder.tvHospitalname.setVisibility(View.GONE);
            else
            holder.tvPhoneNumber.setVisibility(View.GONE);*/
        if(locationsList.get(position)!=null){

        Location location=locationsList.get(position);
        holder.tvAddress.setText(location.getOrganizationName()+" "+location.getAddress1()+" "+location.getCity()
                +" "+location.getPostalcode()+" "+ location.getPostalcode()+""+location.getCountry());


            if(location.getOrganizationName()!=null){
                holder.tvHospitalname.setText(location.getOrganizationName());
            }else {
                holder.tvHospitalname.setText("-");
            }

            if(location.getPhoneNumber()!=null){
                holder.tvPhoneNumber.setText(location.getPhoneNumber());
            }
        }


    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements RecyclerViewClickListerner{
        TextView tvHospitalname,tvAddress,tvPhoneNumber;

        public ViewHolder(@NonNull View itemView,RecyclerViewClickListerner recyclerViewClickListerner) {
            super(itemView);
            tvHospitalname=(TextView)itemView.findViewById(R.id.tvHospitalname);
            tvAddress=(TextView)itemView.findViewById(R.id.tvAddress);
            tvPhoneNumber=(TextView)itemView.findViewById(R.id.tvPhoneNumber);
            itemView.setOnClickListener(view -> {
                rvClickListerner.onclick(getAdapterPosition());
            });
        }

        @Override
        public void onclick(int position) {
            rvClickListerner.onclick(position);

        }
    }
}
