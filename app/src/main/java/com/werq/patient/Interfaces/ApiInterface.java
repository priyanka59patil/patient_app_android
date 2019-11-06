package com.werq.patient.Interfaces;

import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("Auth/signup")
    Call<Void> Signup(@Header("Content-Type") String contentType,
                             @Body SignUpJson params);

    @POST("Auth")
    Call<LoginResponce> signIn(@Header("Content-Type") String contentType,
                               @Body UserCredential userCredential);


}





