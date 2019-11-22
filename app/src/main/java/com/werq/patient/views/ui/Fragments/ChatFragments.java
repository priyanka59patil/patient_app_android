package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.base.BaseFragment;
import com.werq.patient.viewmodel.ChatFragmentViewModel;
import com.werq.patient.views.ui.ChatRoomActivity;
import com.werq.patient.views.adapter.ChatAdapters;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatFragments extends BaseFragment implements RecyclerViewClickListerner {


    @BindView(R.id.rv_chats)
    RecyclerView rvChats;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    ChatAdapters chatAdapters;
    Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;
    ChatFragmentViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_fragments, container, false);
        viewModel= ViewModelProviders.of(this).get(ChatFragmentViewModel.class);
        ButterKnife.bind(this,view);
        inilizeVariables();

        //setRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        viewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean !=null && aBoolean)
                loadingView.setVisibility(View.VISIBLE);
            else
                loadingView.setVisibility(View.INVISIBLE);
        });


    }

    private void setRecyclerView() {
      /*  RecyclerViewHelper.setAdapterToRecylerView(mContext,rvChats,chatAdapters);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvChats);*/
    }

    private void inilizeVariables() {
        mContext=getActivity();
        recyclerViewClickListerner=this;
        chatAdapters=new ChatAdapters(mContext,true,recyclerViewClickListerner,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvChats,chatAdapters);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvChats);

    }


    @Override
    public void onclick(int position) {
        startActivity(new Intent(mContext, ChatRoomActivity.class));

    }
}
