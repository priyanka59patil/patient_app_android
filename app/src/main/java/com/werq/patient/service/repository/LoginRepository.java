package com.werq.patient.service.repository;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRepository {



    public void signIn(UserCredential signInJson, MutableLiveData<String> toast, ApiResponce apiResponce, String url){
        Call call= RetrofitClient.getRetrofit().signIn(Helper.ContentType,signInJson);

        RetrofitClient.callApi(call,url,apiResponce,toast);


    }

}
