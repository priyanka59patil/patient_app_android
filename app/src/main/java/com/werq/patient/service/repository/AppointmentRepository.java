package com.werq.patient.service.repository;


import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.Callback.ApiCallback;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.RequestJsonPojo.RescheduleAppointment;
import com.werq.patient.service.model.ResponcejsonPojo.ApiResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentData;
import com.werq.patient.service.model.ResponcejsonPojo.ApptDetailsData;
import com.werq.patient.service.model.ResponcejsonPojo.RescheduleData;
import com.werq.patient.service.model.ResponcejsonPojo.TimeSlotData;

import retrofit2.Call;

public class AppointmentRepository  {


    private String TAG="AppointmentRepository";

    public void  getUpcommingAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                        ApiCallback apiCallback, String url){


        Call<ApiResponse<AppointmentData>> appointmentDataCall= RetrofitClient.getRetrofit().getUpcomingAppointment(authToken,take,skip);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  getHistoryAppoitment(String authToken, String take, String skip , MutableLiveData<String> toast,
                                      ApiCallback apiCallback, String url){


        Call<ApiResponse<AppointmentData>> appointmentDataCall= RetrofitClient.getRetrofit().getHistoryAppointment(authToken,take,skip);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  getAppointmentDetails(String authToken, int appointmentId, MutableLiveData<String> toast,
                                      ApiCallback apiCallback, String url){

        //Helper.setLog(TAG,"authToken:- "+authToken+"");

        Call<ApiResponse<ApptDetailsData>> appointmentDataCall= RetrofitClient.getRetrofit().getAppointmentDetails(authToken,appointmentId);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  setConfirmAppointment(String authToken, ConfirmAppointment confirmAppointment, MutableLiveData<String> toast,
                                       ApiCallback apiCallback, String url){

        //Helper.setLog(TAG,"authToken:- "+authToken+"");

        Call<ApiResponse<ApptDetailsData>> appointmentDataCall= RetrofitClient.getRetrofit().
                                            setConfirmAppointment(authToken,Helper.ContentType,confirmAppointment);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  getTimeSlots(String authToken, int organizationId,String date, MutableLiveData<String> toast,
                                       ApiCallback apiCallback, String url){

        Call<ApiResponse<TimeSlotData>> appointmentDataCall= RetrofitClient.getRetrofit()
                .getTimeSlots(authToken,organizationId,date);

        RetrofitClient.dynamicApiCall(appointmentDataCall,url,apiCallback,toast);

    }

    public void  sendRescheduleRequest(String authToken, RescheduleAppointment rescheduleAppointment, MutableLiveData<String> toast,
                                       ApiCallback apiCallback, String url){

        //Helper.setLog(TAG,"authToken:- "+authToken+"");

        Call<ApiResponse<RescheduleData>> rescheduleRequestCall= RetrofitClient.getRetrofit().sendRescheduleRequest(authToken,Helper.ContentType,rescheduleAppointment);

        RetrofitClient.dynamicApiCall(rescheduleRequestCall,url,apiCallback,toast);

    }



}
