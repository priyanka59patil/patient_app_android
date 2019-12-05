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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.werq.patient.BuildConfig;
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

import net.igenius.customcheckbox.CustomCheckBox;

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
                             ArrayList<Coworker> coworkerArrayList) {
        this.mContext = mContext;
        this.coworkerArrayList=coworkerArrayList;
        /*viewModel.coworkerList.observe(lifecycleOwner,coworkerArrayList1 -> {

            if(coworkerArrayList!=null)
            {
                coworkerArrayList.clear();
                coworkerArrayList.addAll(coworkerArrayList1);
                notifyDataSetChanged();
            }

        });*/
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

        if(coworkerArrayList.get(position).getSpeciality()!=null   ){

            if(!coworkerArrayList.get(position).getSpeciality().getName().trim().isEmpty()
                    && coworkerArrayList.get(position).getSpeciality().getSubSpeciality()!=null ){

                String speciality=" ";
                for (int i = 0; i < coworkerArrayList.get(position).getSpeciality().getSubSpeciality().size(); i++) {

                    if(!coworkerArrayList.get(position).getSpeciality().getSubSpeciality().get(i).getName().isEmpty())
                    speciality=coworkerArrayList.get(position).getSpeciality().getSubSpeciality().get(i).getName()+", ";
                }
                holder.tvSpeciality.setText(speciality);
            }
            else {
                holder.tvSpeciality.setText(coworkerArrayList.get(position).getSpeciality().getName());
            }
        }
        else {
            if(coworkerArrayList.get(position).getJobTitle()!=null)
            holder.tvSpeciality.setText(coworkerArrayList.get(position).getJobTitle().getTitle());
            else
                holder.tvSpeciality.setText("-");
        }

            if(coworkerArrayList.get(position).getProfilePhoto()!=null){
                String url = null;
                url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile+coworkerArrayList.get(position).getProfilePhoto();
                Glide.with(mContext).load(url).apply(new RequestOptions()
                        .placeholder(R.drawable.user_image_placeholder)
                        .error(R.drawable.user_image_placeholder)
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(holder.ivDoctorProfile);

            }
            else {
                holder.ivDoctorProfile.setImageResource(R.drawable.user_image_placeholder);
            }
    }

    @Override
    public int getItemCount() {
        return coworkerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvUsername)
        TextView tvUsername;
        @BindView(R.id.tvSpeciality)
        TextView tvSpeciality;
        @BindView(R.id.ivDoctorProfile)
        CircleImageView ivDoctorProfile;

        @BindView(R.id.cbFilter)
        CustomCheckBox cbFilter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
            cbFilter.setVisibility(View.GONE);
        }
    }
}