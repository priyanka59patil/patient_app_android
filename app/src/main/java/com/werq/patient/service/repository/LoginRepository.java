package com.werq.patient.service.repository;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiCallback;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.ApiResponse;
import com.werq.patient.service.model.ResponcejsonPojo.SignUpData;

import retrofit2.Call;


public class LoginRepository {


    private String TAG="LoginRepository";

    public void signIn(UserCredential signInJson, MutableLiveData<String> toast, ApiCallback apiCallback, String url){
        Call<ApiResponse<SignUpData>>  call= RetrofitClient.getRetrofit().signIn(Helper.ContentType,signInJson);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);


    }

    public void refreshAuthToken(String refreshTokenId, MutableLiveData<String> toast, ApiCallback apiCallback,String url){
        Helper.setLog("beforecall refreshTokenId", refreshTokenId);

        Call<Object>  call=RetrofitClient.getRetrofit().refreshAuthToken(refreshTokenId);
        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);
    }

}
