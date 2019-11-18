package com.werq.patient.service;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.RetrofitClient;

import retrofit2.Call;

public class PatientRepository {
    private String TAG="PR";

    public void  getDocterTeamAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                         ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getDoctorTeams(authToken,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getDocterDetails(String authToken, int doctorId, MutableLiveData<String> toast
                                , String coworkerTake, String coworkerSkip,
                                  ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getDoctorDetails(authToken,doctorId,coworkerTake,coworkerSkip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getAttachments(String authToken,String doctor, String take, String skip , MutableLiveData<String> toast,
                                ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getAttachments(authToken,doctor,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getVisitNoteDetails(String authToken, int appointmentId, int visitNoteId, String take, String skip
                        , MutableLiveData<String> toast, ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit()
                                        .getVisitNoteDetails(authToken,appointmentId+"",visitNoteId,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

}
