package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.base.BaseViewModel;

import okhttp3.internal.http2.ErrorCode;

public class NewChatViewModel extends BaseViewModel {

    MutableLiveData<String> searchText;

    @Override
    public void onSuccess(String url, String jsonObject) {

    }

    @Override
    public void onError(String url, ErrorCode errorCode) {

    }
}