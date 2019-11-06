package com.werq.patient.viewmodel;

import com.werq.patient.base.BaseViewModel;

import okhttp3.internal.http2.ErrorCode;

public class FingerPrintViewModel extends BaseViewModel {

    public FingerPrintViewModel() {
    }

    public void yesOnClick()
    {
        getActivity().setValue("BottomActivity");
    }

    public  void noOnClick()
    {
        getActivity().setValue("BottomActivity");
    }

    @Override
    public void onSuccess(String url, String jsonObject) {

    }

    @Override
    public void onError(String url, ErrorCode errorCode) {

    }
}
