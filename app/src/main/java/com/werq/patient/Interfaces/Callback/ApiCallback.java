package com.werq.patient.Interfaces.Callback;

import retrofit2.Response;

public interface ApiCallback {

    void onSuccess(String url, Response response);
    void onError(String url, String errorCode, String errorMessage);
    void onTokenRefersh(Response response);


}
