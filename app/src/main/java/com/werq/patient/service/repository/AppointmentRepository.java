package com.werq.patient.service.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentData;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentRepository  {


    private String TAG="AppointmentRepository";

    public void  getUpcommingAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                        ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getUpcomingAppointment(authToken,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getHistoryAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                        ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getHistoryAppointment(authToken,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getAppointmentDetails(String authToken, int appointmentId, MutableLiveData<String> toast,
                                      ApiResponce apiResponce, String url){

        Helper.setLog(TAG,"authToken:- "+authToken);

        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getAppointmentDetails(authToken,appointmentId);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  setConfirmAppointment(String authToken, ConfirmAppointment confirmAppointment, MutableLiveData<String> toast,
                                       ApiResponce apiResponce, String url){

        Helper.setLog(TAG,"authToken:- "+authToken);

        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().
                                            setConfirmAppointment(authToken,Helper.ContentType,confirmAppointment);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

}
