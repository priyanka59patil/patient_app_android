package com.werq.patient.Interfaces;

import com.werq.patient.service.model.RequestJsonPojo.ChangePassword;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.RequestJsonPojo.NewAppointment;
import com.werq.patient.service.model.RequestJsonPojo.RescheduleAppointment;
import com.werq.patient.service.model.RequestJsonPojo.SendMessage;
import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentDetailResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AssessmentsResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.ChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorDetailsResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorListResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResponse;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationResponse;
import com.werq.patient.service.model.ResponcejsonPojo.NewChat;
import com.werq.patient.service.model.ResponcejsonPojo.NewChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.PatientProfileResponse;
import com.werq.patient.service.model.ResponcejsonPojo.RescheduleResponse;
import com.werq.patient.service.model.ResponcejsonPojo.SentMessageResponse;
import com.werq.patient.service.model.ResponcejsonPojo.TimeSlotResponse;
import com.werq.patient.service.model.ResponcejsonPojo.VisitNoteResponse;

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
    Call<LoginResponce> signIn(@Header("Content-Type") String contentType,
                               @Body UserCredential userCredential);

    @GET("Appointments/upcoming")
    Call<AppointmentResponse> getUpcomingAppointment(@Header("Authorization") String authToken,
                                                     @Query("take") String take,
                                                     @Query("skip") String skip);

    @GET("Appointments/history")
    Call<AppointmentResponse> getHistoryAppointment(@Header("Authorization") String authToken,
                                        @Query("take") String take,
                                        @Query("skip") String skip);

    @GET("Appointments/Details/{apppointmentId}")
    Call<AppointmentDetailResponse> getAppointmentDetails(@Header("Authorization") String authToken,
                                                          @Path("apppointmentId") int appointmentId);

    @POST("Auth/RefreshToken")
    Call<Object> refreshAuthToken(@Header("RefreshToken") String refreshTokenId);

    @PUT("Appointments/ConfirmAppt")
    Call<AppointmentDetailResponse> setConfirmAppointment(@Header("Authorization") String authToken,
                                       @Header("Content-Type") String contentType,
                                       @Body ConfirmAppointment confirmAppointment);


    @GET("Doctors/Team")
    Call<DoctorTeamResponse> getDoctorTeams(@Header("Authorization") String authToken,
                                            @Query("take") String take,
                                            @Query("skip") String skip);
    @GET("Doctors/Details/{doctorId}")
    Call<DoctorDetailsResponse> getDoctorDetails(@Header("Authorization") String authToken,
                                                 @Path("doctorId") int doctorId,
                                                 @Query("coworker_take" ) String coworkerTake,
                                                 @Query("coworker_skip" ) String coworkerSkip);

    @GET("Attachments")
    Call<AttachmentResponse> getAttachments(@Header("Authorization") String authToken,
                                            @Query("doctors") String doctors,
                                            @Query("filter") String filter,
                                            @Query("take") String take,
                                            @Query("skip") String skip);

    @GET("Attachments/VisitNote/{appointmentId}")
    Call<VisitNoteResponse> getVisitNoteDetails(@Header("Authorization") String authToken,
                                                @Path("appointmentId") String appointmentId,
                                                @Query("visit_note_id") int visitNoteId,
                                                @Query("take") String take,
                                                @Query("skip") String skip);

    @GET("Patient")
    Call<PatientProfileResponse> getPatientProfileData(@Header("Authorization") String authToken);

    @GET("PatientData?filter=medication")
    Call<MedicationResponse> getMedicationList(@Header("Authorization") String authToken,
                                               @Query("take") String take,
                                               @Query("skip") String skip );

    //medication,encounters,instruction
    @GET("PatientData?")
    Call<Object> getMedicalInfoList(@Header("Authorization") String authToken,
                                  @Query("filter") String filterType,
                                   @Query("take") String take,
                                   @Query("skip") String skip );

    @GET("PatientData/assessment")
    Call<AssessmentsResponse> getAssessments(@Header("Authorization") String authToken);

    @GET("Doctors?")
    Call<DoctorListResponse> getDoctorList(@Header("Authorization") String authToken,
                                           @Query("take") String take,
                                           @Query("skip") String skip );

    @POST("Auth/changepassword")
    Call<LoginResponce> changePassword(@Header("Content-Type") String contentType,
                                @Header("Authorization") String authToken,
                                @Body ChangePassword changePassword);

    @GET("Office/availability/{orgnizationId}/{date}")
    Call<TimeSlotResponse> getTimeSlots(@Header("Authorization") String authToken,
                                        @Path("orgnizationId") int orgnizationId,
                                        @Path("date") String date);

    @PUT("Appointments/RescheduleAppt")
    Call<RescheduleResponse> sendRescheduleRequest(@Header("Authorization") String authToken,
                                                   @Header("Content-Type") String contentType,
                                                   @Body RescheduleAppointment rescheduleAppointment);

    @POST("Appointments/AppointmentReq")
    Call<Object> sendNewAppointmentRequest(@Header("Authorization") String authToken,
                                    @Header("Content-Type") String contentType,
                                    @Body NewAppointment newAppointment);
    @POST("Chat")
    Call<NewChatResponse> sendNewChatRequest(@Header("Authorization") String authToken,
                                             @Header("Content-Type") String contentType,
                                             @Body NewChat newChat);

    @GET("Chat/patient/{channelId}")
    Call<ChatResponse> fetchChatList(@Header("Authorization") String authToken,
                                     @Path("channelId") String channelId,
                                     @Query("flag") int flag,
                                     @Query("timeStamp") String timeStamp,
                                     @Query("take") String take,
                                     @Query("skip") String skip);

    @POST("Chat/sendbypatient")
    Call<SentMessageResponse> sendMessageToServer(@Header("Authorization") String authToken,
                                                  @Header("Content-Type") String contentType,
                                                  @Body SendMessage sendMessage);
}





