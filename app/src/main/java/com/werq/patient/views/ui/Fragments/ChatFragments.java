package com.werq.patient.views.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.FirebaseApp;
import com.stfalcon.chatkit.messages.MessageInput;
import com.werq.patient.Factory.ChatFragmentVmFactory;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.viewmodel.ChatFragmentViewModel;
import com.werq.patient.views.ui.ChatRoomActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatFragments extends BaseFragment implements RecyclerViewClickListerner {


    /*@BindView(R.id.rv_chats)
    RecyclerView rvChats;*/

    //ChatAdapters chatAdapters;
    Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;
    ChatFragmentViewModel viewModel;
    @BindView(R.id.input)
    MessageInput input;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_fragments, container, false);
        mContext = getActivity();
        viewModel = ViewModelProviders.of(this,new ChatFragmentVmFactory(mContext)).get(ChatFragmentViewModel.class);
        ButterKnife.bind(this, view);
        inilizeVariables();


        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence inputString) {
                if (Helper.hasNetworkConnection(mContext)) {

                    if (!inputString.toString().trim().isEmpty()) {

                        viewModel.getTypedMsg().setValue(inputString.toString().trim());
                        viewModel.setNewChat(inputString.toString().trim());
                        input.getInputEditText().setText("");

                    }

                } else {
                    Helper.showToast(mContext, getString(R.string.no_network_conection));
                    return false;
                }
                return true;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void inilizeVariables() {

        recyclerViewClickListerner = this;
      /*  chatAdapters=new ChatAdapters(mContext,true,recyclerViewClickListerner,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvChats,chatAdapters);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvChats);*/

    }


    @Override
    public void onclick(int position) {
        startActivity(new Intent(mContext, ChatRoomActivity.class));

    }
}
