package com.werq.patient.service;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
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


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getDoctorDetails(Helper.autoken,doctorId,coworkerTake,coworkerSkip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getAttachments(String authToken,String doctor, String take, String skip , MutableLiveData<String> toast,
                                ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getAttachments(authToken,doctor,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getVisitNoteDetails(String authToken, int appointmentId, int visitNoteId, String take, String skip
                        , MutableLiveData<String> toast, ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit()
                                        .getVisitNoteDetails(authToken,appointmentId+"",visitNoteId,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getPatientProfile(String authToken, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getPatientProfileData(authToken);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getMedicationList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicationList(authToken,take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }
    public void  getEncounterList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"encounters",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getAssessmets(String authToken,MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getAssessments(authToken);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getInstructionList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"instructions",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

}
