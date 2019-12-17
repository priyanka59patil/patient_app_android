package com.werq.patient.viewmodel;

import com.werq.patient.base.BaseViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class ChatTopicsViewModel extends BaseViewModel {

    CompositeDisposable compositeDisposable;

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
