package com.werq.patient.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Activities.ChatRoomActivity;
import com.werq.patient.Activities.NewChatActivity;
import com.werq.patient.Adapters.ChatAdapters;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatFragments extends Fragment implements RecyclerViewClickListerner {


    @BindView(R.id.rv_chats)
    RecyclerView rvChats;
  ChatAdapters chatAdapters;
  Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_fragments, container, false);
        ButterKnife.bind(this,view);
       inilizeVariables();
       setRecyclerView();

        return view;
    }

    private void setRecyclerView() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvChats,chatAdapters);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvChats);
    }

    private void inilizeVariables() {
        mContext=getActivity();
        recyclerViewClickListerner=this;

        chatAdapters=new ChatAdapters(mContext,true,recyclerViewClickListerner);


    }


    @Override
    public void onclick(int position) {
        startActivity(new Intent(mContext, ChatRoomActivity.class));

    }
}
