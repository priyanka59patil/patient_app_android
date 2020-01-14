package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.Callback.DoctorTeamClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.viewmodel.BottomTabViewModel;

import java.util.ArrayList;

public class DoctorTeamAdapter extends RecyclerView.Adapter<DoctorTeamAdapter.ViewHolder> {
    private StackImagesAdapter stackImageView;
    Context mContext;
    boolean fromSearchName;
    DoctorTeamClickListerner recyclerViewClickListerner;
    ArrayList<DoctorTeamResult> teamList;

    public DoctorTeamAdapter(Context mContext, boolean fromSearchName,
                             DoctorTeamClickListerner recyclerViewClickListerner,
                             ArrayList<DoctorTeamResult> teamList,
                             BottomTabViewModel viewModel,
                             LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.fromSearchName=fromSearchName;
        this.recyclerViewClickListerner=recyclerViewClickListerner;
        this.teamList=teamList;

        viewModel.getTeamList().observe(lifecycleOwner,doctorTeamResults -> {
            if(doctorTeamResults!=null ){
                this.teamList.clear();
                this.teamList.addAll(doctorTeamResults);
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_doctor_name, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            ArrayList<String> imageLists = new ArrayList<>();
            for(int i=0;i<teamList.get(position).getDoctors().size();i++){
                imageLists.add(teamList.get(position).getDoctors().get(i).getProfilePhoto());
            }
            stackImageView = new StackImagesAdapter(mContext, imageLists);
            RecyclerViewHelper.setStackImageAdapter(mContext,  holder.rvUserProfiles,stackImageView);
            RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,holder.rvUserProfiles);

        //if(teamList.size()>0 && position<teamList.size()){

            Location location=teamList.get(position).getLocation();
            String strAddress =location.getOrganizationName()+" "+location.getAddress1()+" "+location.getCity()
                    +" "+location.getState()+" "+ location.getPostalcode();

            holder.tvPracticeName.setText(teamList.get(position).getLocation().getOrganizationName());
            holder.tvAddress.setText(strAddress);
            holder.tvPhoneNumber.setText(teamList.get(position).getLocation().getPhoneNumber());
       // }

        holder.tvPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClickListerner.onCallclick(teamList.get(position).getLocation().getPhoneNumber());
            }
        });

        holder.llDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClickListerner.onclick(position);
            }
        });






    }
    /*private ArrayList<Integer> setImageResources() {
        ArrayList<Integer> imageLists = new ArrayList<>();
        imageLists.add(R.drawable.imageone);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imageone);
        return imageLists;
    }*/


    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView rvUserProfiles;
        RelativeLayout layout_mainlayout;
        TextView tvPracticeName,tvAddress,tvPhoneNumber;
        LinearLayout llDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvUserProfiles=(RecyclerView)itemView.findViewById(R.id.rvUserProfiles);
            tvPracticeName=(TextView) itemView.findViewById(R.id.tvPracticeName);
            tvAddress=(TextView) itemView.findViewById(R.id.tvAddress);
            tvPhoneNumber=(TextView) itemView.findViewById(R.id.tvPhoneNumber);
            llDetails=(LinearLayout) itemView.findViewById(R.id.llDetails);
            layout_mainlayout=(RelativeLayout)itemView.findViewById(R.id.layout_mainlayout);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
