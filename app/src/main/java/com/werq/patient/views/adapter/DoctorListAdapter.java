package com.werq.patient.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.werq.patient.BuildConfig;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.service.model.ResponcejsonPojo.Coworker;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.service.model.ResponeError.ProfileIntentService;

import net.igenius.customcheckbox.CustomCheckBox;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Doctor> coworkerArrayList;
    RecyclerViewClickListerner rvClickListerner;

    public DoctorListAdapter(Context mContext,
                             ArrayList<Doctor> coworkerArrayList
                             /*RecyclerViewClickListerner rvClickListerner*/) {
        this.mContext = mContext;
        this.coworkerArrayList = coworkerArrayList;
        this.rvClickListerner=rvClickListerner;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        if(coworkerArrayList.get(position).getnPINumber()!=null && coworkerArrayList.get(position).getnPINumber()!=0){

            if (coworkerArrayList.get(position).getSpeciality() != null) {
                holder.tvUsername.setText("Dr."+coworkerArrayList.get(position).getFirstName() + " " +
                        coworkerArrayList.get(position).getMiddleName() + " " +
                        coworkerArrayList.get(position).getLastName());
            }else {
                holder.tvUsername.setText(coworkerArrayList.get(position).getFirstName() + " " +
                        coworkerArrayList.get(position).getMiddleName() + " " +
                        coworkerArrayList.get(position).getLastName());
            }


        }else {
            holder.tvUsername.setText(coworkerArrayList.get(position).getFirstName() + " " +
                    coworkerArrayList.get(position).getMiddleName() + " " +
                    coworkerArrayList.get(position).getLastName());
        }



        if (coworkerArrayList.get(position).getSpeciality() != null) {

            if (!coworkerArrayList.get(position).getSpeciality().getName().trim().isEmpty()
                    && coworkerArrayList.get(position).getSpeciality().getSubSpeciality() != null) {

                String speciality = " ";
                for (int i = 0; i < coworkerArrayList.get(position).getSpeciality().getSubSpeciality().size(); i++) {

                    if (!coworkerArrayList.get(position).getSpeciality().getSubSpeciality().get(i).getName().isEmpty())
                        speciality = coworkerArrayList.get(position).getSpeciality().getSubSpeciality().get(i).getName() + ", ";
                }
                holder.tvSpeciality.setText(speciality);
            } else {
                holder.tvSpeciality.setText(coworkerArrayList.get(position).getSpeciality().getName());
            }
        } else {
            if (coworkerArrayList.get(position).getJobTitle() != null)
                holder.tvSpeciality.setText(coworkerArrayList.get(position).getJobTitle().getTitle());
            else
                holder.tvSpeciality.setText("-");
        }

        if (coworkerArrayList.get(position).getProfilePhoto() != null) {
            String url = null;
            url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile + coworkerArrayList.get(position).getProfilePhoto();
            Glide.with(mContext).load(url).apply(new RequestOptions()
                    .placeholder(R.drawable.user_image_placeholder)
                    .error(R.drawable.user_image_placeholder)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(holder.ivDoctorProfile);

        } else {
            holder.ivDoctorProfile.setImageResource(R.drawable.user_image_placeholder);
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfileIntentService profileIntentService
                        =new ProfileIntentService(coworkerArrayList.get(position), false);

                EventBus.getDefault().post(profileIntentService);
                /*Intent intent = new Intent(mContext.getResources().getString(R.string.NEW_DOCTOR_PROFILE));
                intent.putExtra("doctorData",);
                intent.putExtra("isMessageDisabled",false);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);*/
            }
        });



    }

    @Override
    public int getItemCount() {
        return coworkerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        @BindView(R.id.tvUsername)
        TextView tvUsername;
        @BindView(R.id.tvSpeciality)
        TextView tvSpeciality;
        @BindView(R.id.ivDoctorProfile)
        CircleImageView ivDoctorProfile;

        @BindView(R.id.cbFilter)
        CustomCheckBox cbFilter;

        @BindView(R.id.main_layout)
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            cbFilter.setVisibility(View.GONE);
            //mainLayout.setOnClickListener(this::onClick);
        }

       /* @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.main_layout:
                    rvClickListerner.onclick(getAdapterPosition());
                    break;
            }

        }*/
    }
}