package com.werq.patient.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.ChatAdapters;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatFragments extends Fragment {


    @BindView(R.id.rv_chats)
    RecyclerView rvChats;
  ChatAdapters chatAdapters;
  Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_fragments, container, false);
        ButterKnife.bind(this,view);
       inilizeVariables();
        chatAdapters=new ChatAdapters(mContext,false);
        rvChats.setLayoutManager(new LinearLayoutManager(mContext));
        rvChats.setHasFixedSize(true);
        rvChats.setAdapter(chatAdapters);
        return view;
    }

    private void inilizeVariables() {
        mContext=getActivity();
    }


}
