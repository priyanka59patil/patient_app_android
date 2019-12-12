package com.werq.patient.Interfaces;

import com.werq.patient.service.model.RequestJsonPojo.ChangePassword;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.RequestJsonPojo.RescheduleAppointment;
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

    @GET("Appointments/upcoming")
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
                                  @Path("doctorId") int doctorId,
                                  @Query("coworker_take" ) String coworkerTake,
                                  @Query("coworker_skip" ) String coworkerSkip);

    @GET("Attachments")
    Call<Object> getAttachments(@Header("Authorization") String authToken,
                                @Query("doctors") String doctors,
                                @Query("filter") String filter,
                                @Query("take") String take,
                                @Query("skip") String skip);

    @GET("Attachments/VisitNote/{appointmentId}")
    Call<Object> getVisitNoteDetails(@Header("Authorization") String authToken,
                                     @Path("appointmentId") String appointmentId,
                                     @Query("visit_note_id") int visitNoteId,
                                     @Query("take") String take,
                                     @Query("skip") String skip);

    @GET("Patient")
    Call<Object> getPatientProfileData(@Header("Authorization") String authToken);

    @GET("PatientData?filter=medication")
    Call<Object> getMedicationList(@Header("Authorization") String authToken,
                                   @Query("take") String take,
                                   @Query("skip") String skip );

    //medication,encounters,instruction
    @GET("PatientData?")
    Call<Object> getMedicalInfoList(@Header("Authorization") String authToken,
                                  @Query("filter") String filterType,
                                   @Query("take") String take,
                                   @Query("skip") String skip );

    @GET("PatientData/assessment")
    Call<Object> getAssessments(@Header("Authorization") String authToken);

    @GET("Doctors?")
    Call<Object> getDoctorList(@Header("Authorization") String authToken,
                                    @Query("take") String take,
                                    @Query("skip") String skip );

    @POST("Auth/changepassword")
    Call<Object> changePassword(@Header("Content-Type") String contentType,
                                @Header("Authorization") String authToken,
                                @Body ChangePassword changePassword);

    @GET("Office/availability/{orgnizationId}/{date}")
    Call<Object> getTimeSlots(@Header("Authorization") String authToken,
                              @Path("orgnizationId") int orgnizationId,
                              @Path("date") String date);

    @PUT("Appointments/RescheduleAppt")
    Call<Object> sendRescheduleRequest(@Header("Authorization") String authToken,
                                       @Header("Content-Type") String contentType,
                                       @Body RescheduleAppointment rescheduleAppointment);

}





