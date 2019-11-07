package com.werq.patient.service.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentData;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentRepository  {


    private String TAG="AppointmentRepository";

    public void  getUpcommingAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                        ApiResponce apiResponce, String url){

        Helper.setLog(TAG,"authToken:- "+authToken);

        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getUpcomingAppointment(authToken,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getHistoryAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                        ApiResponce apiResponce, String url){

        Helper.setLog(TAG,"authToken:- "+authToken);

        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getHistoryAppointment(authToken,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

}
