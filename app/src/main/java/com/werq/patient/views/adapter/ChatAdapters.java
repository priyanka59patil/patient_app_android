package com.werq.patient.views.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.viewmodel.ChatFragmentViewModel;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.ArrayList;

public class ChatAdapters extends RecyclerView.Adapter<ChatAdapters.ViewHolders> {
    Context mContext;
    private StackImagesAdapter stackImageView;
    boolean isFromRecentChat;
    RecyclerViewClickListerner recyclerViewClickListerner;

    public ChatAdapters(Context mContext, boolean isFromRecentChat,
                        RecyclerViewClickListerner recyclerViewClickListerner,
                        ChatFragmentViewModel viewModel,
                        LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.isFromRecentChat=isFromRecentChat;
        this.recyclerViewClickListerner=recyclerViewClickListerner;
       /* viewModel.getLoading().observe(lifecycleOwner,aBoolean -> {

        });*/
    }



    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat,parent, false);
       return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        if(position==0){
            holder.tvMessage.setTypeface(null, Typeface.BOLD);
            holder.tvMessage.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.tvTime.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.tvTime.setTypeface(null, Typeface.BOLD);
            holder.tvDoctorTeamName.setTypeface(null, Typeface.BOLD);

        }

       // stackImageView = new StackImagesAdapter(mContext, setImageResources());
        RecyclerViewHelper.setAdapterToStackRecylerView(mContext,  holder.rvUsers,stackImageView);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,holder.rvUsers);
        holder.cbFilter.setVisibility(View.GONE);
        if(isFromRecentChat){
            holder.tvTime.setVisibility(View.VISIBLE);

        }
        else {
            holder.tvTime.setVisibility(View.GONE);
        }

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

    public class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView rvUsers;
        TextView tvMessage,tvTime,tvDoctorTeamName;
        CardView cvMainLayout;
        CustomCheckBox cbFilter;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            rvUsers=(RecyclerView)itemView.findViewById(R.id.rvUsers);
            tvMessage=(TextView)itemView.findViewById(R.id.tvMessage);
            tvTime=(TextView)itemView.findViewById(R.id.tvTime);
            cvMainLayout =(CardView)itemView.findViewById(R.id.cvMainLayout);
            cbFilter=(CustomCheckBox)itemView.findViewById(R.id.cbFilter);
            tvDoctorTeamName=(TextView)itemView.findViewById(R.id.tvDoctorTeamName);
            cvMainLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListerner.onclick(getAdapterPosition());
        }
    }
}
