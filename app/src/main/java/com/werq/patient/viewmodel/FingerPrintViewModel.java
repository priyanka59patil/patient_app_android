package com.werq.patient.viewmodel;

import com.werq.patient.base.BaseViewModel;

import okhttp3.internal.http2.ErrorCode;
import retrofit2.Response;

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
    public void onSuccess(String url, Response response) {

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {

    }

    @Override
    public void onTokenRefersh(Response response) {

    }
}
