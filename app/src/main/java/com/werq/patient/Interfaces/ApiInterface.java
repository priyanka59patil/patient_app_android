package com.werq.patient.Interfaces;

import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("Auth/signup")
    Call<LoginResponce> signUp(@Header("Content-Type") String contentType,
                             @Body SignUpJson params);

    @POST("Auth")
    Call<Object> signIn(@Header("Content-Type") String contentType,
                               @Body UserCredential userCredential);

    @GET("Appointments/upcomming")
    Call<Object> getUpcomingAppointment(@Header("Authorization") String authToken,
                                                     @Query("take") String take,
                                                     @Query("skip") String skip);

    @GET("Appointments/history")
    Call<Object> getHistoryAppointment(@Header("Authorization") String authToken,
                                        @Query("take") String take,
                                        @Query("skip") String skip);


}





