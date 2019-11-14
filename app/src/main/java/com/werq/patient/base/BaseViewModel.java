package com.werq.patient.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.werq.patient.Interfaces.ApiResponce;

public abstract class BaseViewModel extends ViewModel implements ApiResponce {
    private MutableLiveData<String> mShowToast;
    private MutableLiveData<String> mNewActivity;
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<Boolean> repoLoadError;

    public BaseViewModel(){
        mShowToast=new MutableLiveData<>();
        mNewActivity=new MutableLiveData<>();
        loading=new MutableLiveData<>();
        //loading.setValue(false);
        repoLoadError=new MutableLiveData<>();

    }


    public MutableLiveData<String> getToast(){
        return mShowToast;
    }

    public MutableLiveData<String> getActivity(){
        return mNewActivity;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public MutableLiveData<Boolean> getRepoLoadError() {
        return repoLoadError;
    }
}
