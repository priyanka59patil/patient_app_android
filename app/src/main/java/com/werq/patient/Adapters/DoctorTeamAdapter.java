package com.werq.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;
import com.werq.patient.Utils.OverlapDecoration;

import java.util.ArrayList;

public class DoctorTeamAdapter extends RecyclerView.Adapter<DoctorTeamAdapter.ViewHolder> {
    private StackImagesAdapter stackImageView;
    Context mContext;
    boolean fromSearchName;

    public DoctorTeamAdapter(Context mContext,boolean fromSearchName) {
        this.mContext = mContext;
        this.fromSearchName=fromSearchName;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        holder.rvUserProfiles.addItemDecoration(new OverlapDecoration());
        holder.rvUserProfiles.setLayoutManager(layoutManager);
        holder.rvUserProfiles.setHasFixedSize(true);
        holder.rvUserProfiles.setAdapter(stackImageView);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
         RecyclerView rvUserProfiles;
        Button btAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvUserProfiles=(RecyclerView)itemView.findViewById(R.id.rvUserProfiles);
            btAdd=(Button)itemView.findViewById(R.id.btAdd);
        }
    }
}
