package com.werq.patient.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

public class DoctorTeamAdapter extends RecyclerView.Adapter<DoctorTeamAdapter.ViewHolder> {
    private StackImagesAdapter stackImageView;
    Context mContext;
    boolean fromSearchName;
    RecyclerViewClickListerner recyclerViewClickListerner;

    public DoctorTeamAdapter(Context mContext, boolean fromSearchName, RecyclerViewClickListerner recyclerViewClickListerner) {
        this.mContext = mContext;
        this.fromSearchName=fromSearchName;
        this.recyclerViewClickListerner=recyclerViewClickListerner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_doctor_name, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        stackImageView = new StackImagesAdapter(mContext, setImageResources());
        RecyclerViewHelper.setAdapterToStackRecylerView(mContext,  holder.rvUserProfiles,stackImageView);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,holder.rvUserProfiles);

        if(fromSearchName)
            holder.btAdd.setVisibility(View.VISIBLE);
        else
            holder.btAdd.setVisibility(View.GONE);

    }
    private ArrayList<Integer> setImageResources() {
        ArrayList<Integer> imageLists = new ArrayList<>();
        imageLists.add(R.drawable.imageone);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imagetwo);
        imageLists.add(R.drawable.imageone);
        return imageLists;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         RecyclerView rvUserProfiles;
        Button btAdd;
        RelativeLayout layout_mainlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvUserProfiles=(RecyclerView)itemView.findViewById(R.id.rvUserProfiles);
            btAdd=(Button)itemView.findViewById(R.id.btAdd);
            layout_mainlayout=(RelativeLayout)itemView.findViewById(R.id.layout_mainlayout);
            layout_mainlayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListerner.onclick(getAdapterPosition());
        }
    }
}
