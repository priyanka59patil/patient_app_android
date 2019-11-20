package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.repository.ChatRepository;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.internal.http2.ErrorCode;

public class ChatFragmentViewModel  extends BaseViewModel {

    ChatRepository chatRepository;
    CompositeDisposable disposable;

  //  MutableLiveData<ArrayList<User>> userList=new MutableLiveData<>();


    public ChatFragmentViewModel() {
    }

   /* public ChatFragmentViewModel(ChatRepository chatRepository, CompositeDisposable disposable) {
        this.chatRepository = chatRepository;
        this.disposable = disposable;
    }*/

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    @Override
    public void onSuccess(String url, String responseJson) {

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {

    }

    @Override
    public void onTokenRefersh(String responseJson) {

    }
}
