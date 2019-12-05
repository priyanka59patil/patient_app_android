package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.werq.patient.BuildConfig;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.viewmodel.BottomTabViewModel;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FilterDoctorAdapter extends RecyclerView.Adapter<FilterDoctorAdapter.ViewHolder> {
    Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;
    ArrayList<Doctor> doctorArrayList;
    boolean isAll;
    BottomTabViewModel bottomTabViewModel;
    public static ArrayList<Doctor> selectedDoctorList;

    public FilterDoctorAdapter(Context mContext,
                               RecyclerViewClickListerner recyclerViewClickListerner,
                               ArrayList<Doctor> doctorArrayList,
                               BottomTabViewModel bottomTabViewModel,
                               LifecycleOwner lifecycleOwner,
                               boolean isAll)
    {
        this.mContext = mContext;
        this.recyclerViewClickListerner=recyclerViewClickListerner;
        this.doctorArrayList=doctorArrayList;
        this.isAll=isAll;
        this.bottomTabViewModel=bottomTabViewModel;
        selectedDoctorList=new ArrayList<>();
        bottomTabViewModel.filterDoctorsList.observe(lifecycleOwner,doctors -> {

            if(doctors!=null){
                Helper.setLog("DoctorList",doctors.size()+"");
                this.doctorArrayList.clear();
                this.doctorArrayList.addAll(doctors);
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        if(isAll){
            holder.cbFilter.setChecked(true);
        }else {
            holder.cbFilter.setChecked(false);

        }

        holder.cbFilter.setOnCheckedChangeListener(new CustomCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CustomCheckBox checkBox, boolean isChecked) {
                if(isChecked){
                    selectedDoctorList.add(doctorArrayList.get(position));
                }else {
                    if(selectedDoctorList.size()>0){
                        for (int i = 0; i <selectedDoctorList.size() ; i++) {
                            if(selectedDoctorList.get(i).getiD()==doctorArrayList.get(position).getiD()){
                                selectedDoctorList.remove(i);
                            }
                        }
                    }

                }

              /*  for (int i = 0; i <selectedDoctorList.size() ; i++) {
                    Helper.setLog("selected",selectedDoctorList.get(i).toString());
                }*/

            }
        });

        holder.tvUsername.setText(doctorArrayList.get(position).getFirstName() +" "+
                doctorArrayList.get(position).getMiddleName()+" "+
                doctorArrayList.get(position).getLastName());

        /*if(doctorArrayList.get(position).getSpeciality()!=null   ){

            if(!doctorArrayList.get(position).getSpeciality().getName().trim().isEmpty()
                    && doctorArrayList.get(position).getSpeciality().getSubSpeciality()!=null ){

                String speciality=" ";
                for (int i = 0; i < doctorArrayList.get(position).getSpeciality().getSubSpeciality().size(); i++) {

                    if(!doctorArrayList.get(position).getSpeciality().getSubSpeciality().get(i).getName().isEmpty())
                        speciality=doctorArrayList.get(position).getSpeciality().getSubSpeciality().get(i).getName()+", ";
                }
                holder.tvSpeciality.setText(speciality);
            }
            else {
                holder.tvSpeciality.setText(doctorArrayList.get(position).getSpeciality().getName());
            }
        }
        else {
            if(doctorArrayList.get(position).getJobTitle()!=null)
                holder.tvSpeciality.setText(doctorArrayList.get(position).getJobTitle().getTitle());
            else
                holder.tvSpeciality.setText("-");
        }*/

        if(doctorArrayList.get(position).getProfilePhoto()!=null){
            String url = null;
            url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile+doctorArrayList.get(position).getProfilePhoto();
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

  /*  private ArrayList<Integer> setImageResources() {
        ArrayList<Integer> imageLists = new ArrayList<>();
        imageLists.add(R.drawable.imageone);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imageone);
        return imageLists;
    }*/

    @Override
    public int getItemCount() {
        return doctorArrayList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
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
            tvSpeciality.setVisibility(View.GONE);
            cbFilter.setVisibility(View.VISIBLE);


        }
    }

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
        /*if(isAll){
            selectedDoctorList.addAll(doctorArrayList);
        }else {
            selectedDoctorList.clear();
        }*/
    }
}
