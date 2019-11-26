package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.viewmodel.BottomTabViewModel;

import java.util.ArrayList;

public class DoctorTeamAdapter extends RecyclerView.Adapter<DoctorTeamAdapter.ViewHolder> {
    private StackImagesAdapter stackImageView;
    Context mContext;
    boolean fromSearchName;
    RecyclerViewClickListerner recyclerViewClickListerner;
    ArrayList<DoctorTeamResult> teamList;

    public DoctorTeamAdapter(Context mContext, boolean fromSearchName,
                             RecyclerViewClickListerner recyclerViewClickListerner,
                             ArrayList<DoctorTeamResult> teamList/*,
                             BottomTabViewModel viewModel,
                             LifecycleOwner lifecycleOwner*/) {
        this.mContext = mContext;
        this.fromSearchName=fromSearchName;
        this.recyclerViewClickListerner=recyclerViewClickListerner;
        this.teamList=teamList;

       /* viewModel.getTeamList().observe(lifecycleOwner,doctorTeamResults -> {
            if(doctorTeamResults!=null ){
                this.teamList.clear();
                this.teamList.addAll(doctorTeamResults);
                notifyDataSetChanged();
                Helper.setLog("doctorTeamResults",doctorTeamResults.size()+"");
            }
        });*/
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



            if(fromSearchName)
                holder.btAdd.setVisibility(View.VISIBLE);
            else
                holder.btAdd.setVisibility(View.GONE);
       // }





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
        Button btAdd;
        RelativeLayout layout_mainlayout;
        TextView tvPracticeName,tvAddress,tvPhoneNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvUserProfiles=(RecyclerView)itemView.findViewById(R.id.rvUserProfiles);
            btAdd=(Button)itemView.findViewById(R.id.btAdd);
            tvPracticeName=(TextView) itemView.findViewById(R.id.tvPracticeName);
            tvAddress=(TextView) itemView.findViewById(R.id.tvAddress);
            tvPhoneNumber=(TextView) itemView.findViewById(R.id.tvPhoneNumber);

            layout_mainlayout=(RelativeLayout)itemView.findViewById(R.id.layout_mainlayout);
            layout_mainlayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListerner.onclick(getAdapterPosition());
        }
    }
}
