package com.werq.patient.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {
    private MutableLiveData<String> mShowToast;
    private MutableLiveData<String> mNewActivity;

    public BaseViewModel(){
        mShowToast=new MutableLiveData<>();
        mNewActivity=new MutableLiveData<>();
    }


    public MutableLiveData<String> getToast(){
        return mShowToast;
    }

    public MutableLiveData<String> getActivity(){
        return mNewActivity;
    }




}
