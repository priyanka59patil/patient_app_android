package com.werq.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;
import com.werq.patient.Utils.OverlapDecoration;

import java.util.ArrayList;

public class ChatAdapters extends RecyclerView.Adapter<ChatAdapters.ViewHolders> {
    Context mContext;
    private StackImagesAdapter stackImageView;
    boolean isFromRecentChat;

    public ChatAdapters(Context mContext,boolean isFromRecentChat) {
        this.mContext = mContext;
        this.isFromRecentChat=isFromRecentChat;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat,parent, false);
    return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        stackImageView = new StackImagesAdapter(mContext, setImageResources());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        holder.rvUsers.addItemDecoration(new OverlapDecoration());
        holder.rvUsers.setLayoutManager(layoutManager);
        holder.rvUsers.setHasFixedSize(true);
        holder.rvUsers.setAdapter(stackImageView);
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

    public class ViewHolders extends RecyclerView.ViewHolder {
        RecyclerView rvUsers;
        TextView tvMessage,tvTime;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            rvUsers=(RecyclerView)itemView.findViewById(R.id.rvUsers);
            tvMessage=(TextView)itemView.findViewById(R.id.tvMessage);
            tvTime=(TextView)itemView.findViewById(R.id.tvTime);
        }
    }
}
