package com.werq.patient.service.repository;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.RetrofitClient;

import retrofit2.Call;

public class DoctorRepository {

    private String TAG="DoctorRepository";

    public void  getDocterTeamAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                        ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getDoctorTeams(authToken,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }
}
