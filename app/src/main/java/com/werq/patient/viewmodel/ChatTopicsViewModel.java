package com.werq.patient.viewmodel;

import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.repository.ChatTopicRepository;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.internal.http2.ErrorCode;

public class ChatTopicsViewModel extends BaseViewModel {

    ChatTopicRepository chatTopicRepository;
    CompositeDisposable compositeDisposable;

    @Override
    public void onSuccess(String url, String jsonObject) {

    }

    @Override
    public void onError(String url, ErrorCode errorCode) {

    }
}
