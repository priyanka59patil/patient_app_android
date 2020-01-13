package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.base.BaseViewModel;

import okhttp3.internal.http2.ErrorCode;
import retrofit2.Response;

public class NewChatViewModel extends BaseViewModel {

    MutableLiveData<String> searchText;


    @Override
    public void onSuccess(String url, Response response) {

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {

    }

    @Override
    public void onTokenRefersh(Response response) {

    }

}
