package com.werq.patient.service;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiCallback;
import com.werq.patient.Interfaces.ChatListApiCall;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.service.model.RequestJsonPojo.ChangePassword;
import com.werq.patient.service.model.RequestJsonPojo.NewAppointment;
import com.werq.patient.service.model.RequestJsonPojo.SendMessage;
import com.werq.patient.service.model.ResponcejsonPojo.AssessmentsResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.ChatMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorDetailsResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorListResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResponse;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationResponse;
import com.werq.patient.service.model.ResponcejsonPojo.NewChat;
import com.werq.patient.service.model.ResponcejsonPojo.NewChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.PatientProfileResponse;
import com.werq.patient.service.model.ResponcejsonPojo.SentMessageResponse;
import com.werq.patient.service.model.ResponcejsonPojo.VisitNoteResponse;

import retrofit2.Call;

public class PatientRepository {
    private String TAG="PR";

    public void  getDocterTeamAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                         ApiCallback apiCallback, String url){

        Call<DoctorTeamResponse> appointmentDataCall= RetrofitClient.getRetrofit().getDoctorTeams(authToken,take,skip);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  getDocterDetails(String authToken, int doctorId, MutableLiveData<String> toast
                                , String coworkerTake, String coworkerSkip,
                                  ApiCallback apiCallback, String url){


        Call<DoctorDetailsResponse> appointmentDataCall= RetrofitClient.getRetrofit().getDoctorDetails(Helper.autoken,doctorId,coworkerTake,coworkerSkip);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  getAttachments(String authToken,String doctor,String filter, String take, String skip , MutableLiveData<String> toast,
                                ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<AttachmentResponse> appointmentDataCall= RetrofitClient.getRetrofit().getAttachments(authToken,doctor,filter,take,skip);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  getVisitNoteDetails(String authToken, int appointmentId, int visitNoteId, String take, String skip
                        , MutableLiveData<String> toast, ApiCallback apiCallback, String url){


        Call<VisitNoteResponse> appointmentDataCall= RetrofitClient.getRetrofit()
                                        .getVisitNoteDetails(authToken,appointmentId+"",visitNoteId,take,skip);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  getPatientProfile(String authToken, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

       Helper.setLog("authToken :- ",authToken+"");
        Call<PatientProfileResponse> call= RetrofitClient.getRetrofit().getPatientProfileData(authToken);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getMedicationList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken+"");
        Call<MedicationResponse> call= RetrofitClient.getRetrofit().getMedicationList(authToken,take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }
    public void  getEncounterList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken+"");
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"encounters",take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getAssessmets(String authToken,MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken+"");
        Call<AssessmentsResponse> call= RetrofitClient.getRetrofit().getAssessments(authToken);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getInstructionList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"instructions",take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getPlanOfCareList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"planofcare",take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getHistoryOfProcedureList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"historyofproc",take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getAllergyList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"allergies",take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getPastillnessHistoryList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"historyofpastillness",take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getSocialHistoryList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"socialhistory",take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  getProblemList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<Object> call= RetrofitClient.getRetrofit().getMedicalInfoList(authToken,"problems",take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }


    public void  getFilterDoctorList(String authToken, String take, String skip, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("authToken :- ",authToken);
        Call<DoctorListResponse> call= RetrofitClient.getRetrofit().getDoctorList(authToken,take,skip);

        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  setNewPassword(String authToken, ChangePassword changePassword, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Call<LoginResponce> call= RetrofitClient.getRetrofit().changePassword(Helper.ContentType,authToken,changePassword);
        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  setNewChatRequest(String authToken, NewChat newChat, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("setNewChatRequest-authToken :- ",authToken);
        Call<NewChatResponse> call= RetrofitClient.getRetrofit().sendNewChatRequest(authToken,Helper.ContentType,newChat);
        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  setNewAppointmentRequest(String authToken, NewAppointment newAppointment, MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Call<Object> call= RetrofitClient.getRetrofit().sendNewAppointmentRequest(authToken,Helper.ContentType,newAppointment);
        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }

    public void  fetchChatList(String authToken, String channelId, int flag, String timestamp, String take, String skip
            , MutableLiveData<String> toast, ChatListApiCall chatListApiCall, String url){

        Helper.setLog("fetchChatList-authToken :- ",authToken);
        Call<ChatResponse> call= RetrofitClient.getRetrofit().fetchChatList(authToken,channelId,flag,timestamp,take,skip);
        RetrofitClient.chatListCall(call,url,chatListApiCall,toast);

    }

    public void sendMessageToServer(String authToken, SendMessage sendMessage,
                                    MutableLiveData<String> toast, ApiCallback apiCallback, String url){

        Helper.setLog("fetchChatList-authToken :- ",authToken);
        Call<SentMessageResponse> call= RetrofitClient.getRetrofit().sendMessageToServer(authToken,Helper.ContentType,sendMessage);
        RetrofitClient.dynamicApiCall(call,url,apiCallback,toast);

    }


}
