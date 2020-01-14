package com.werq.patient.Interfaces;

import com.werq.patient.service.model.RequestJsonPojo.ChangePassword;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.RequestJsonPojo.NewAppointment;
import com.werq.patient.service.model.RequestJsonPojo.RescheduleAppointment;
import com.werq.patient.service.model.RequestJsonPojo.SendMessage;
import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.ApiResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentData;
import com.werq.patient.service.model.ResponcejsonPojo.ApptDetailsData;
import com.werq.patient.service.model.ResponcejsonPojo.Assessment;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentData;
import com.werq.patient.service.model.ResponcejsonPojo.ChatMessageData;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorData;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorDetailsData;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorListData;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationData;
import com.werq.patient.service.model.RequestJsonPojo.NewChat;
import com.werq.patient.service.model.ResponcejsonPojo.PatientProfileData;
import com.werq.patient.service.model.ResponcejsonPojo.RescheduleData;
import com.werq.patient.service.model.ResponcejsonPojo.SignUpData;
import com.werq.patient.service.model.ResponcejsonPojo.TimeSlotData;
import com.werq.patient.service.model.ResponcejsonPojo.VisitNoteDetailsData;

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
    Call<ApiResponse<SignUpData>> signUp(@Header("Content-Type") String contentType,
                             @Body SignUpJson params);

    @POST("Auth")
    Call<ApiResponse<SignUpData>> signIn(@Header("Content-Type") String contentType,
                                         @Body UserCredential userCredential);

    @GET("Appointments/upcoming")
    Call<ApiResponse<AppointmentData>> getUpcomingAppointment(@Header("Authorization") String authToken,
                                                              @Query("take") String take,
                                                              @Query("skip") String skip);

    @GET("Appointments/history")
    Call<ApiResponse<AppointmentData>> getHistoryAppointment(@Header("Authorization") String authToken,
                                        @Query("take") String take,
                                        @Query("skip") String skip);

    @GET("Appointments/Details/{apppointmentId}")
    Call<ApiResponse<ApptDetailsData>> getAppointmentDetails(@Header("Authorization") String authToken,
                                                             @Path("apppointmentId") int appointmentId);

    @POST("Auth/RefreshToken")
    Call<Object> refreshAuthToken(@Header("RefreshToken") String refreshTokenId);

    @PUT("Appointments/ConfirmAppt")
    Call<ApiResponse<ApptDetailsData>> setConfirmAppointment(@Header("Authorization") String authToken,
                                       @Header("Content-Type") String contentType,
                                       @Body ConfirmAppointment confirmAppointment);


    @GET("Doctors/Team")
    Call<ApiResponse<DoctorData>> getDoctorTeams(@Header("Authorization") String authToken,
                                                 @Query("take") String take,
                                                 @Query("skip") String skip);
    @GET("Doctors/Details/{doctorId}")
    Call<ApiResponse<DoctorDetailsData>> getDoctorDetails(@Header("Authorization") String authToken,
                                                          @Path("doctorId") int doctorId,
                                                          @Query("coworker_take" ) String coworkerTake,
                                                          @Query("coworker_skip" ) String coworkerSkip);

    @GET("Attachments")
    Call<ApiResponse<AttachmentData>> getAttachments(@Header("Authorization") String authToken,
                                                     @Query("doctors") String doctors,
                                                     @Query("filter") String filter,
                                                     @Query("take") String take,
                                                     @Query("skip") String skip);

    @GET("Attachments/VisitNote/{appointmentId}")
    Call<ApiResponse<VisitNoteDetailsData>> getVisitNoteDetails(@Header("Authorization") String authToken,
                                                                @Path("appointmentId") String appointmentId,
                                                                @Query("visit_note_id") int visitNoteId,
                                                                @Query("take") String take,
                                                                @Query("skip") String skip);

    @GET("Patient")
    Call<ApiResponse<PatientProfileData>> getPatientProfileData(@Header("Authorization") String authToken);

    @GET("PatientData?filter=medication")
    Call<ApiResponse<MedicationData>> getMedicationList(@Header("Authorization") String authToken,
                                                        @Query("take") String take,
                                                        @Query("skip") String skip );

    //medication,encounters,instruction
    @GET("PatientData?")
    Call<Object> getMedicalInfoList(@Header("Authorization") String authToken,
                                  @Query("filter") String filterType,
                                   @Query("take") String take,
                                   @Query("skip") String skip );

    @GET("PatientData/assessment")
    Call<ApiResponse<Assessment>> getAssessments(@Header("Authorization") String authToken);

    @GET("Doctors?")
    Call<ApiResponse<DoctorListData>> getDoctorList(@Header("Authorization") String authToken,
                                                    @Query("take") String take,
                                                    @Query("skip") String skip );

    @POST("Auth/changepassword")
    Call<ApiResponse<SignUpData>> changePassword(@Header("Content-Type") String contentType,
                                @Header("Authorization") String authToken,
                                @Body ChangePassword changePassword);

    @GET("Office/availability/{orgnizationId}/{date}")
    Call<ApiResponse<TimeSlotData>> getTimeSlots(@Header("Authorization") String authToken,
                                                 @Path("orgnizationId") int orgnizationId,
                                                 @Path("date") String date);

    @PUT("Appointments/RescheduleAppt")
    Call<ApiResponse<RescheduleData>> sendRescheduleRequest(@Header("Authorization") String authToken,
                                                            @Header("Content-Type") String contentType,
                                                            @Body RescheduleAppointment rescheduleAppointment);

    @POST("Appointments/AppointmentReq")
    Call<Object> sendNewAppointmentRequest(@Header("Authorization") String authToken,
                                    @Header("Content-Type") String contentType,
                                    @Body NewAppointment newAppointment);
    @POST("Chat")
    Call<ApiResponse<String>> sendNewChatRequest(@Header("Authorization") String authToken,
                                             @Header("Content-Type") String contentType,
                                             @Body NewChat newChat);

    @GET("Chat/patient/{channelId}")
    Call<ApiResponse<ChatMessageData>> fetchChatList(@Header("Authorization") String authToken,
                                                     @Path("channelId") String channelId,
                                                     @Query("flag") int flag,
                                                     @Query("timeStamp") String timeStamp,
                                                     @Query("take") String take,
                                                     @Query("skip") String skip);

    @GET("Chat/history/patient")
    Call<ApiResponse<ChatMessageData>> getChatHistory(@Header("Authorization") String authToken,
                                     @Query("flag") int flag,
                                     @Query("timeStamp") String timeStamp,
                                     @Query("take") String take,
                                     @Query("skip") String skip);


    @POST("Chat/sendbypatient")
    Call<ApiResponse<Boolean>> sendMessageToServer(@Header("Authorization") String authToken,
                                                  @Header("Content-Type") String contentType,
                                                  @Body SendMessage sendMessage);
}





