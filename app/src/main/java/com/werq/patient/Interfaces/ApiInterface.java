package com.werq.patient.Interfaces;

import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @GET("Appointments/Details/{apppointmentId}")
    Call<Object> getAppointmentDetails(@Header("Authorization") String authToken,
                                       @Path("apppointmentId") int appointmentId);

    @POST("Auth/RefreshToken")
    Call<Object> refreshAuthToken(@Header("RefreshToken") String refreshTokenId);

    @PUT("Appointments/ConfirmAppt")
    Call<Object> setConfirmAppointment(@Header("Authorization") String authToken,
                                       @Header("Content-Type") String contentType,
                                       @Body ConfirmAppointment confirmAppointment);


    @GET("Doctors/Team")
    Call<Object> getDoctorTeams(@Header("Authorization") String authToken,
                                        @Query("take") String take,
                                        @Query("skip") String skip);
    @GET("Doctors/Details/{doctorId}")
    Call<Object> getDoctorDetails(@Header("Authorization") String authToken,
                                @Path("doctorId") int doctorId);
}





