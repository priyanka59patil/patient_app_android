package com.werq.patient.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.base.BaseViewModel;

import okhttp3.internal.http2.ErrorCode;

public class ForgotPassswordViewModel extends BaseViewModel {

    MutableLiveData<String> contact=new MutableLiveData<>();
    MutableLiveData<String> contactError=new MutableLiveData<>();

    public ForgotPassswordViewModel() {
    }

    public MutableLiveData<String> getContact() {
        return contact;
    }


    public MutableLiveData<String> getContactError() {
        return contactError;
    }

    public void sendOnClick()
    {
        Log.e( "sendOnClick: ", contact.getValue()+" ");
        if(contact.getValue()!=null && !contact.getValue().isEmpty()) {
            getActivity().setValue("Login");
        }
        else {
            contactError.setValue("Email or phone number cannot be empty");
        }
    }

    public TextWatcher contactTextWatcher=new TextWatcher() {

        private final String TAG = "in-un";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            contactError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public void onSuccess(String url, String jsonObject) {

    }

    @Override
    public void onError(String url, ErrorCode errorCode) {

    }
}