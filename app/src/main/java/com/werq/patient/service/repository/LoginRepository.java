package com.werq.patient.service.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiCallback;
import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.model.ResponeError.ErrorData;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class LoginRepository {


    private String TAG="LoginRepository";

    public void signIn(UserCredential signInJson, MutableLiveData<String> toast, ApiCallback apiCallback, String url){
        Call<LoginResponce>  call= RetrofitClient.getRetrofit().signIn(Helper.ContentType,signInJson);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);


    }

    public void refreshAuthToken(String refreshTokenId, MutableLiveData<String> toast, ApiResponce apiResponce,String url){
        Helper.setLog("beforecall refreshTokenId", refreshTokenId);

        Call<Object>  call=RetrofitClient.getRetrofit().refreshAuthToken(refreshTokenId);
        RetrofitClient.callApi(call,url,apiResponce,toast);
    }

}
