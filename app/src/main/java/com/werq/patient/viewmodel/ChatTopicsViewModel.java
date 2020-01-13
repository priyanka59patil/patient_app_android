package com.werq.patient.viewmodel;

import com.werq.patient.base.BaseViewModel;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

public class ChatTopicsViewModel extends BaseViewModel {

    CompositeDisposable compositeDisposable;

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
