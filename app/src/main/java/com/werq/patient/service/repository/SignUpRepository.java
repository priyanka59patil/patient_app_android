package com.werq.patient.service.repository;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiCallback;
import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;

import retrofit2.Call;
import retrofit2.Retrofit;

public class SignUpRepository {


    public void signUp(SignUpJson signUpJson, MutableLiveData<String> toast, ApiCallback apiCallback, String url){
        Helper.setLog("beforecall", signUpJson.toString());
        Call<LoginResponce> call= RetrofitClient.getRetrofit().signUp(Helper.ContentType,signUpJson);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);


    }





}
