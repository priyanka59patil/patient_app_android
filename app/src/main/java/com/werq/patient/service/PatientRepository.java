package com.werq.patient.service;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Interfaces.ChatListApiCall;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.service.model.RequestJsonPojo.ChangePassword;
import com.werq.patient.service.model.RequestJsonPojo.NewAppointment;
import com.werq.patient.service.model.RequestJsonPojo.SendMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ChatMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.NewChat;

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

    public void  getAttachments(String authToken,String doctor,String filter, String take, String skip , MutableLiveData<String> toast,
                                ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit().getAttachments(authToken,doctor,filter,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getVisitNoteDetails(String authToken, int appointmentId, int visitNoteId, String take, String skip
                        , MutableLiveData<String> toast, ApiResponce apiResponce, String url){


        Call<Object> appointmentDataCall= RetrofitClient.getRetrofit()
                                        .getVisitNoteDetails(authToken,appointmentId+"",visitNoteId,take,skip);

        RetrofitClient.callApi(appointmentDataCall,url,apiResponce,toast);

    }

    public void  getPatientProfile(String authToken, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

       Helper.setLog("authToken :- ",authToken+"");
        Call<Object> call= RetrofitClient.getRetrofit().getPatientProfileData(authToken);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getMedicationList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken+"");
        Call<Object> call= RetrofitClient.getRetrofit().getMedicationList(authToken,take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }
    public void  getEncounterList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken+"");
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"encounters",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getAssessmets(String authToken,MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken+"");
        Call<Object> call= RetrofitClient.getRetrofit().getAssessments(authToken);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getInstructionList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"instructions",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getPlanOfCareList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"planofcare",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getHistoryOfProcedureList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"historyofproc",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getAllergyList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"allergies",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getPastillnessHistoryList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"historyofpastillness",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getSocialHistoryList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"socialhistory",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  getProblemList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"problems",take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }


    public void  getFilterDoctorList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getDoctorList(authToken,take,skip);

        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  setNewPassword(String authToken, ChangePassword changePassword, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Call<Object> call= RetrofitClient.getRetrofit().changePassword(Helper.ContentType,authToken,changePassword);
        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  setNewChatRequest(String authToken, NewChat newChat, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("setNewChatRequest-authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().sendNewChatRequest(authToken,Helper.ContentType,newChat);
        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  setNewAppointmentRequest(String authToken, NewAppointment newAppointment, MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Call<Object> call= RetrofitClient.getRetrofit().sendNewAppointmentRequest(authToken,Helper.ContentType,newAppointment);
        RetrofitClient.callApi(call,url,apiResponce,toast);

    }

    public void  fetchChatList(String authToken, String channelId, int flag, String timestamp, String take, String skip
            , MutableLiveData<String> toast, ChatListApiCall chatListApiCall, String url){

        Helper.setLog("fetchChatList-authToken :- ",authToken);
        Call<ChatResponse> call= RetrofitClient.getRetrofit().fetchChatList(authToken,channelId,flag,timestamp,take,skip);
        RetrofitClient.chatListCall(call,url,chatListApiCall,toast);

    }

    public void sendMessageToServer(String authToken, SendMessage sendMessage,
                               MutableLiveData<String> toast, ApiResponce apiResponce, String url){

        Helper.setLog("fetchChatList-authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().sendMessageToServer(authToken,Helper.ContentType,sendMessage);
        RetrofitClient.callApi(call,url,apiResponce,toast);

    }


}
