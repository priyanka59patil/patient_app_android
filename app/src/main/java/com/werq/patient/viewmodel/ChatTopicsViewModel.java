package com.werq.patient.viewmodel;

import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.repository.ChatTopicRepository;

import io.reactivex.disposables.CompositeDisposable;

public class ChatTopicsViewModel extends BaseViewModel {

    ChatTopicRepository chatTopicRepository;
    CompositeDisposable compositeDisposable;

}
