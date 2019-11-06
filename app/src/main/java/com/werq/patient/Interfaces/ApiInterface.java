package com.werq.patient.Interfaces;

import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("signup")
    Call<Void> Signup(@Header("Content-Type") String contentType,
                             @Body SignUpJson params);


}





