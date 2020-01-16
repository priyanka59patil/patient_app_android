package com.werq.patient.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.werq.patient.Interfaces.Callback.ApiCallback;

public abstract class BaseViewModel extends ViewModel implements ApiCallback {
    private MutableLiveData<String> mShowToast;
    private MutableLiveData<String> mNewActivity;
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<Boolean> repoLoadError;
    private String authToken;

    public BaseViewModel() {
    }

    public BaseViewModel(String authToken){
        this.authToken =authToken;
        mShowToast=new MutableLiveData<>();
        mNewActivity=new MutableLiveData<>();
        loading=new MutableLiveData<>();
        //loading.setValue(false);
        repoLoadError=new MutableLiveData<>();

    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
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
