package com.werq.patient.Interfaces;

import okhttp3.internal.http2.ErrorCode;

public interface ApiResponce {

    void onSuccess(String url, Object jsonObject);
    void onError(String url, String errorCode);

}
