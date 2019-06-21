package com.werq.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.OverlapDecoration;
import com.werq.patient.Utils.RecyclerViewHelper;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.ArrayList;

public class FilterDoctorAdapter extends RecyclerView.Adapter<FilterDoctorAdapter.ViewHolder> {
    Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;
    private StackImagesAdapter stackImageView;

    public FilterDoctorAdapter(Context mContext, RecyclerViewClickListerner recyclerViewClickListerner) {
        this.mContext = mContext;
        this.recyclerViewClickListerner=recyclerViewClickListerner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        stackImageView = new StackImagesAdapter(mContext, setImageResources());
        RecyclerViewHelper.setAdapterToStackRecylerView(mContext,  holder.rvUsers,stackImageView);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,holder.rvUsers);

        holder.cbFilter.setVisibility(View.VISIBLE);
        holder.tvTime.setVisibility(View.GONE);

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
        return 3;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        RecyclerView rvUsers;
        TextView tvMessage,tvTime;
        CardView cvMainLayout;
        CustomCheckBox cbFilter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvUsers=(RecyclerView)itemView.findViewById(R.id.rvUsers);
            tvMessage=(TextView)itemView.findViewById(R.id.tvMessage);
            tvTime=(TextView)itemView.findViewById(R.id.tvTime);
            cvMainLayout =(CardView)itemView.findViewById(R.id.cvMainLayout);
            cbFilter=(CustomCheckBox)itemView.findViewById(R.id.cbFilter);
        }
    }
}
