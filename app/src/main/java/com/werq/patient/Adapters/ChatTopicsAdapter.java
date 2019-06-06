package com.werq.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;

import java.util.ArrayList;

public class ChatTopicsAdapter extends RecyclerView.Adapter<ChatTopicsAdapter.ViewHolder> {
    Context mContext;

    ArrayList<String> topics;

    public ChatTopicsAdapter(Context mContext, ArrayList<String> topics) {
        this.mContext = mContext;
        this.topics=topics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_topics,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTopics.setText(topics.get(position));
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopics;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTopics=(TextView)itemView.findViewById(R.id.tvTopics);
        }
    }
}
