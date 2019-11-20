package com.werq.patient.Interfaces;

import okhttp3.internal.http2.ErrorCode;

public interface ApiResponce {

    void onSuccess(String url, String responseJson);
    void onError(String url, String errorCode,String errorMessage);
    void onTokenRefersh(String responseJson);


}
