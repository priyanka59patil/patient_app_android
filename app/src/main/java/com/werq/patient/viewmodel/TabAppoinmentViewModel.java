package com.werq.patient.viewmodel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.MockData.JsonData;
import com.werq.patient.R;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.AppointmentResponce;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentDetailResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AvailableTimeSlot;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.model.ResponcejsonPojo.TimeSlotResponse;
import com.werq.patient.service.repository.AppointmentRepository;
import com.werq.patient.base.BaseViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.internal.http2.ErrorCode;

public class TabAppoinmentViewModel extends BaseViewModel {

    private static final String TAG = "TabAppoinmentViewModel";
    private final AppointmentRepository appointmentRepository;
    public MutableLiveData<ArrayList<AppointmentResult>> listUpcommingAppointments;
    public MutableLiveData<ArrayList<AppointmentResult>> listHistoryAppointments;
    public MutableLiveData<List<AttachmentResult>> attachmentList;
    public MutableLiveData<List<AvailableTimeSlot>> availableTimeSlot;
    public MutableLiveData<AppointmentResult> appointmentResultData;
    public MutableLiveData<Boolean> attachmentVisibility;
    public MutableLiveData<String> doctorProfilePhoto;
    public MutableLiveData<String> appointmentNote;
    boolean isFromUpcoming = true;
    MutableLiveData<String> currentAppointmentDate;
    MutableLiveData<String> day, month, time, fullUserName, speciality, address, addressOnMap;
    ApiResponce apiResponce = this;
    int upcommingPageNo = 0;
    int historyPageNo = 0;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> rvVisibility;
    private MutableLiveData<Boolean> rvHistoryVisibility;
    private MutableLiveData<Boolean> scheduleDetailsVisibility;
    MutableLiveData<Boolean> onSuccessConfirmAppt;
    MutableLiveData<Integer> selectTimeSlotItem;
    String selectedTimeSlot="";
    private Calendar myCalendar = Calendar.getInstance();


    public TabAppoinmentViewModel() {
        appointmentRepository = new AppointmentRepository();
    }

    public TabAppoinmentViewModel(boolean isFromUpcoming) {

        appointmentRepository = new AppointmentRepository();
        disposable = new CompositeDisposable();
        this.isFromUpcoming = isFromUpcoming;
        rvVisibility = new MutableLiveData<>();
        listUpcommingAppointments = new MutableLiveData<>();
        listHistoryAppointments = new MutableLiveData<>();
        rvHistoryVisibility = new MutableLiveData<>();

        day = new MutableLiveData<>();
        month = new MutableLiveData<>();
        time = new MutableLiveData<>();
        fullUserName = new MutableLiveData<>();
        speciality = new MutableLiveData<>();
        address = new MutableLiveData<>();
        addressOnMap = new MutableLiveData<>();
        day = new MutableLiveData<>();
        attachmentVisibility = new MutableLiveData<Boolean>();
        //confirmByPatient=new MutableLiveData<Boolean>();
        //appointmentStatus =new MutableLiveData<>();
        attachmentVisibility.setValue(false);
        attachmentList = new MutableLiveData<>();
        doctorProfilePhoto = new MutableLiveData<>();
        scheduleDetailsVisibility = new MutableLiveData<>();
        onSuccessConfirmAppt=new MutableLiveData<>();

        availableTimeSlot =new MutableLiveData<>();
        appointmentResultData = new MutableLiveData<>();
        appointmentNote = new MutableLiveData<>();
        selectTimeSlotItem = new MutableLiveData<>();
        currentAppointmentDate = new MutableLiveData<>();


    }

    public MutableLiveData<Boolean> getRvVisibility() {
        return rvVisibility;
    }

    public void setRvVisibility(MutableLiveData<Boolean> rvVisibility) {
        this.rvVisibility = rvVisibility;
    }

    public MutableLiveData<ArrayList<AppointmentResult>> getListUpcommingAppointments() {
        return listUpcommingAppointments;
    }

    public MutableLiveData<ArrayList<AppointmentResult>> getListHistoryAppointments() {
        return listHistoryAppointments;
    }

    public MutableLiveData<Boolean> getRvHistoryVisibility() {
        return rvHistoryVisibility;
    }

    public MutableLiveData<String> getDay() {
        return day;
    }

    public MutableLiveData<String> getMonth() {
        return month;
    }

    public MutableLiveData<String> getTime() {
        return time;
    }

    public MutableLiveData<String> getFullUserName() {
        return fullUserName;
    }

    public MutableLiveData<String> getSpeciality() {
        return speciality;
    }

    public MutableLiveData<String> getAddress() {
        return address;
    }

    public MutableLiveData<String> getAddressOnMap() {
        return addressOnMap;
    }

    public MutableLiveData<List<AttachmentResult>> getAttachmentList() {
        return attachmentList;
    }

    public MutableLiveData<AppointmentResult> getAppointmentResultData() {
        return appointmentResultData;
    }

    public MutableLiveData<Boolean> getAttachmentVisibility() {
        return attachmentVisibility;
    }

    public MutableLiveData<String> getDoctorProfilePhoto() {
        return doctorProfilePhoto;
    }

    public MutableLiveData<Boolean> getOnSuccessConfirmAppt() {
        return onSuccessConfirmAppt;
    }

    public MutableLiveData<Boolean> getScheduleDetailsVisibility() {
        return scheduleDetailsVisibility;
    }

    public MutableLiveData<String> getAppointmentNote() {
        return appointmentNote;
    }

    public MutableLiveData<List<AvailableTimeSlot>> getAvailableTimeSlot() {
        return availableTimeSlot;
    }

    public MutableLiveData<Integer> getSelectTimeSlotItem() {
        return selectTimeSlotItem;
    }

    public MutableLiveData<String> getCurrentAppointmentDate() {
        return currentAppointmentDate;
    }

    public String getSelectedTimeSlot() {
        return selectedTimeSlot;
    }

    public void setSelectedTimeSlot(String selectedTimeSlot) {
        this.selectedTimeSlot = selectedTimeSlot;
    }

    /*private void fetchRepos() {
                                                        getLoading().setValue(true);
                                                        disposable.add(repoRepository.getRepositories().subscribeOn(Schedulers.io())
                                                                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                                                                    @Override
                                                                    public void onSuccess(List<Repo> value) {
                                                                        repoLoadError.setValue(false);
                                                                        repos.setValue(value);
                                                                        loading.setValue(false);
                                                                    }

                                                                    @Override
                                                                    public void onError(Throwable e) {
                                                                        repoLoadError.setValue(true);
                                                                        loading.setValue(false);
                                                                    }
                                                                }));
                                                    }
                                                */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    @Override
    public void onSuccess(String url, String responseJson) {

        AppointmentResponse appointmentResponce;
        ArrayList<AppointmentResult> dataArrayList ;

        if(!TextUtils.isEmpty(url)){

            switch (url){

                case "UpcomingAppointment":
                    getLoading().setValue(false);
                    appointmentResponce = Helper.getGsonInstance().fromJson(responseJson, AppointmentResponse.class);
                    dataArrayList = new ArrayList<>();
                    if (listUpcommingAppointments.getValue() != null && upcommingPageNo != 0) {
                        dataArrayList.addAll(listUpcommingAppointments.getValue());
                    }

                    dataArrayList.addAll(Arrays.asList(appointmentResponce.getData().getResult()));
                    listUpcommingAppointments.setValue(dataArrayList);

                    if (listUpcommingAppointments.getValue().size() > 0) {
                        rvVisibility.setValue(true);
                        //noVisitNote.setVisibility(View.GONE);

                    } else {
                        rvVisibility.setValue(false);
                    }

                    break;

                case "HistoryAppointment":
                    getLoading().setValue(false);
                    appointmentResponce = Helper.getGsonInstance().fromJson(responseJson, AppointmentResponse.class);
                    dataArrayList = new ArrayList<>();
                    if (listHistoryAppointments.getValue() != null && historyPageNo != 0) {
                        dataArrayList.addAll(listHistoryAppointments.getValue());
                    }
                    dataArrayList.addAll(Arrays.asList(appointmentResponce.getData().getResult()));
                    listHistoryAppointments.setValue(dataArrayList);


                    if (listHistoryAppointments.getValue().size() > 0) {
                        rvHistoryVisibility.setValue(true);
                        //noVisitNote.setVisibility(View.GONE);

                    } else {
                        rvHistoryVisibility.setValue(false);
                    }
                    break;

                case "GetAppointmentDetails":
                    getLoading().setValue(false);
                    AppointmentDetailResponse apptDetailResponse = Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
                /*if(apptDetailResponse!=null && apptDetailResponse.getData().getVisitNoteAttachment()!=null)
                {
                    List<VisitNoteAttachment> visitNoteList=apptDetailResponse.getData().getVisitNoteAttachment();

                    List<VisitNoteAttachment> get=apptDetailResponse.getData().getVisitNoteAttachment();
                    if(visitNoteList!=null && visitNoteList.size()>0)
                    {
                        Helper.setLog("visitNoteList.size()",visitNoteList.size()+"");
                    }

                }*/

                    if (apptDetailResponse != null) {
                        // scheduleDetailsVisibility.setValue(true);
                        appointmentResultData.setValue(apptDetailResponse.getData().getAppointment());
                        prepareAppointmentDetailsData();
                        doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());
                        attachmentList.setValue(prepareAttachmentsList(apptDetailResponse));



                        if (attachmentList.getValue() != null) {
                            if (attachmentList.getValue().size() > 0) {
                                attachmentVisibility.setValue(true);

                            } else {
                                attachmentVisibility.setValue(false);
                            }
                        }

                    } else {
                        scheduleDetailsVisibility.setValue(false);
                    }
                    break;

                case "ConfirmAppointment":

                    getLoading().setValue(false);
                    onSuccessConfirmAppt.setValue(true);
                    apptDetailResponse = Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
                    Log.e(TAG, "onSuccess: " + apptDetailResponse.getData().getAppointment().getConfirmByPatient());
                    appointmentResultData.setValue(apptDetailResponse.getData().getAppointment());
                    prepareAppointmentDetailsData();
                    doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());
                    break;

                case "TimeSlots":

                   // getLoading().setValue(false);
                    TimeSlotResponse timeSlotResponse=Helper.getGsonInstance().fromJson(responseJson,TimeSlotResponse.class);
                    if(timeSlotResponse!=null && timeSlotResponse.getData()!=null
                            && timeSlotResponse.getData().getAvailableTimeSlot()!=null){

                        ArrayList<AvailableTimeSlot> availableTimeSlots=new ArrayList<>();
                        availableTimeSlots.addAll(timeSlotResponse.getData().getAvailableTimeSlot());
                        availableTimeSlot.setValue(availableTimeSlots);

                        if (!TextUtils.isEmpty(selectedTimeSlot)) {
                            for (int i = 0; i < availableTimeSlots.size(); i++) {
                                AvailableTimeSlot a = availableTimeSlots.get(i);
                               // Log.e("onResponse: ", a.getStartTime());
                                if ( a.getStartTime().equals(selectedTimeSlot)) {
                                    selectTimeSlotItem.setValue(i);
                                }
                            }
                        }

                    }else {
                        availableTimeSlot.setValue(null);
                    }

                    break;

                default:
                    break;
            }
        }
/*
        Helper.setLog("responseJson", responseJson);
        AppointmentResponse appointmentResponce = Helper.getGsonInstance().fromJson(responseJson, AppointmentResponse.class);

        getLoading().setValue(false);
        if (url != null && url.equals("UpcomingAppointment")) {
           // upcommingloading.setValue(false);


            ArrayList<AppointmentResult> dataArrayList = new ArrayList<>();
            if (listUpcommingAppointments.getValue() != null && upcommingPageNo != 0) {
                dataArrayList.addAll(listUpcommingAppointments.getValue());
            }

            dataArrayList.addAll(Arrays.asList(appointmentResponce.getData().getResult()));
            listUpcommingAppointments.setValue(dataArrayList);

            if (listUpcommingAppointments.getValue().size() > 0) {
                rvVisibility.setValue(true);
                //noVisitNote.setVisibility(View.GONE);

            } else {
                rvVisibility.setValue(false);
            }
        }

        if (url != null && url.equals("HistoryAppointment")) {
           // historyloading.setValue(false);
            ArrayList<AppointmentResult> dataArrayList = new ArrayList<>();
            if (listHistoryAppointments.getValue() != null && historyPageNo != 0) {
                dataArrayList.addAll(listHistoryAppointments.getValue());
            }
            dataArrayList.addAll(Arrays.asList(appointmentResponce.getData().getResult()));
            listHistoryAppointments.setValue(dataArrayList);


            if (listHistoryAppointments.getValue().size() > 0) {
                rvHistoryVisibility.setValue(true);
                //noVisitNote.setVisibility(View.GONE);

            } else {
                rvHistoryVisibility.setValue(false);
            }

        }

        if (url != null && url.equalsIgnoreCase("GetAppointmentDetails")) {

           // apptDetailsloading.setValue(false);

            AppointmentDetailResponse apptDetailResponse = Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
                *//*if(apptDetailResponse!=null && apptDetailResponse.getData().getVisitNoteAttachment()!=null)
                {
                    List<VisitNoteAttachment> visitNoteList=apptDetailResponse.getData().getVisitNoteAttachment();

                    List<VisitNoteAttachment> get=apptDetailResponse.getData().getVisitNoteAttachment();
                    if(visitNoteList!=null && visitNoteList.size()>0)
                    {
                        Helper.setLog("visitNoteList.size()",visitNoteList.size()+"");
                    }

                }*//*

            if (apptDetailResponse != null) {
               // scheduleDetailsVisibility.setValue(true);
                appointmentResultData.setValue(apptDetailResponse.getData().getAppointment());
                prepareAppointmentDetailsData();
                doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());
                attachmentList.setValue(prepareAttachmentsList(apptDetailResponse));



                if (attachmentList.getValue() != null) {
                    if (attachmentList.getValue().size() > 0) {
                        attachmentVisibility.setValue(true);

                    } else {
                        attachmentVisibility.setValue(false);
                    }
                }

            } else {
                scheduleDetailsVisibility.setValue(false);
            }


        }

        if (url != null && url.equalsIgnoreCase("ConfirmAppointment")) {
            //apptDetailsloading.setValue(false);
            confirmedAppointment.setValue(false);
            AppointmentDetailResponse apptDetailResponse = Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
            Log.e(TAG, "onSuccess: " + apptDetailResponse.getData().getAppointment().getConfirmByPatient());
            appointmentResultData.setValue(apptDetailResponse.getData().getAppointment());
            prepareAppointmentDetailsData();
            doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());

               *//* if(apptDetailResponse.getData().getAppointment().getConfirmByPatient()==true)
                {
                    appointmentStatus.setValue("Confirmed");

                }else
                {
                   appointmentStatus.setValue(apptDetailResponse.getData().getAppointment().getAppointmentStatus());

                }*//*

            //confirmByPatient.setValue(true);

        }

        if (url != null && url.equalsIgnoreCase("TimeSlots")) {
            //apptDetailsloading.setValue(false);
            getLoading().setValue(false);
            TimeSlotResponse timeSlotResponse=Helper.getGsonInstance().fromJson(responseJson,TimeSlotResponse.class);
            if(timeSlotResponse!=null && timeSlotResponse.getData()!=null
                    && timeSlotResponse.getData().getAvailableTimeSlot()!=null){

                ArrayList<AvailableTimeSlot> availableTimeSlots=new ArrayList<>();
                availableTimeSlots.addAll(timeSlotResponse.getData().getAvailableTimeSlot());
                availableTimeSlot.setValue(availableTimeSlots);

                if (!TextUtils.isEmpty(selectedTimeSlot)) {
                    for (int i = 0; i < availableTimeSlots.size(); i++) {
                        AvailableTimeSlot a = availableTimeSlots.get(i);
                        Log.e("onResponse: ", a.getStartTime());
                        if ( a.getStartTime().equals(selectedTimeSlot)) {
                            selectTimeSlotItem.setValue(i);
                        }
                    }
                }

            }else {
                availableTimeSlot.setValue(null);
            }

        }*/

    }

    @Override
    public void onError(String url, String errorCode, String errorMessage) {
        /*if (isFromUpcoming) {
            upcommingloading.setValue(false);
        } else {
            historyloading.setValue(false);
        }*/
        getLoading().setValue(false);
    }

    @Override
    public void onTokenRefersh(String responseJson) {
        /*if (isFromUpcoming) {
            upcommingloading.setValue(false);
        } else {
            historyloading.setValue(false);
        }*/
        getLoading().setValue(false);
    }

    private void prepareAppointmentDetailsData() {
        //toolbar.setValue(data.getProvider().getFirst_name() + " " + data.getProvider().getLast_name());

        //this.appointmentResult=apptDetailResponse.getData().getAppointment();
        AppointmentResult appointmentResult = appointmentResultData.getValue();
        fullUserName.setValue(appointmentResult.getDoctor().getFirstName() + " " + appointmentResult.getDoctor().getLastName());
        speciality.setValue(appointmentResult.getDoctor().getSpeciality().getName());

        try {
            Date date = DateHelper.dateFromUtc(appointmentResult.getAppintmentDate());

            currentAppointmentDate.setValue(DateHelper.dateFormatMMMddyyyy(date));
            selectedTimeSlot = new SimpleDateFormat("hh:mmaa").format(date);
            day.setValue(DateHelper.dayFromDate(date, "day"));
            month.setValue(DateHelper.dayFromDate(date, "month"));
            time.setValue(DateHelper.dayFromDate(date, "time"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appointmentResultData.getValue().getLocation() != null) {

            Location location = appointmentResult.getLocation();
            String strAddress = location.getOrganizationName() + " " + location.getAddress1() + " " + location.getCity()
                    + " " + location.getState() + " " + location.getPostalcode() + "" + location.getCountry();
            address.setValue(strAddress);

            addressOnMap.setValue(strAddress);
        }
        Log.e(TAG, "prepareData: " + appointmentResult.getConfirmByPatient());

        if (appointmentResultData.getValue().getReferralReason() != null) {

            appointmentNote.setValue(appointmentResultData.getValue().getReferralReason());

        }else {
            appointmentNote.setValue("Not Available");
        }


        if (attachmentList.getValue() != null) {
            if (attachmentList.getValue().size() > 0) {
                attachmentVisibility.setValue(true);

            } else {
                attachmentVisibility.setValue(false);
            }
        }

    }


    public void fetchUpcomingAppointmentList(int page) {
        getLoading().setValue(true);
        upcommingPageNo = page;

            appointmentRepository.getUpcommingAppoitment(Helper.autoken, "10", "" + page * 10,
                    getToast(), apiResponce, "UpcomingAppointment");

    }

    public void fetchHistoryAppointmentList(int page) {

        getLoading().setValue(true);
        historyPageNo = page;
        appointmentRepository.getHistoryAppoitment(Helper.autoken, "10", "" + page * 10,
                getToast(), apiResponce, "HistoryAppointment");
    }

    public void getAppointmentData(int appointmentId) {
        getLoading().setValue(true);
        appointmentRepository.getAppointmentDetails(Helper.autoken, appointmentId, getToast(), apiResponce, "GetAppointmentDetails");
    }

    public List<AttachmentResult> prepareAttachmentsList(AppointmentDetailResponse apptDetailResponse) {
        List<AttachmentResult> attachmentResultList = new ArrayList<>();

        Helper.setLog("ReferralAttachment.size()", apptDetailResponse.getData().getAppointment().getReferralAttachment().size() + "");

        try {
            if (apptDetailResponse.getData().getAppointment().getReferralAttachment() != null) {
                attachmentResultList.addAll(apptDetailResponse.getData().getAppointment().getReferralAttachment());
            }
        } catch (Exception e) {
            Helper.setLog(TAG + " -Exception", e.getMessage());
        }

        Helper.setLog("visitnote size", apptDetailResponse.getData().getVisitNoteAttachment().size() + "");

        try {


            for (int i = 0; i < apptDetailResponse.getData().getVisitNoteAttachment().size(); i++) {

                Helper.setLog("apptDetailResponse", apptDetailResponse.getData().getVisitNoteAttachment().get(i).getAttachement().size() + "");

                List<AttachmentResult> visitNote = apptDetailResponse.getData().getVisitNoteAttachment().get(i).getAttachement();
                if (visitNote != null) {
                    attachmentResultList.addAll(visitNote);
                }


            }
        } catch (Exception e) {
            Helper.setLog(TAG + " -Exception", e.getMessage());
        }


        Helper.setLog("ReferralAttachment.size()", apptDetailResponse.getData().getAppointment().getReferralAttachment().size() + "");

        return attachmentResultList;
    }

    public void setConfirmStatus() {

        if (appointmentResultData.getValue().getiD() != null && appointmentResultData.getValue().getiD() != 0) {

            ConfirmAppointment confirmAppointment = new ConfirmAppointment();
            confirmAppointment.setID(appointmentResultData.getValue().getiD());
            confirmAppointment.setConfirmByPatient("true");
            getLoading().setValue(true);
            appointmentRepository.setConfirmAppointment(Helper.autoken, confirmAppointment, getToast(), apiResponce, "ConfirmAppointment");
        }
    }

    public void fetchTimeSlots(String date) {

        if (appointmentResultData.getValue().getLocation().getiD() != null &&
                appointmentResultData.getValue().getLocation().getiD() != 0) {

            //getLoading().setValue(true);


            appointmentRepository.getTimeSlots(Helper.autoken, appointmentResultData.getValue().getLocation().getiD()
                    ,date, getToast(), apiResponce, "TimeSlots");
        }
    }

    public void sendRescheduleRequest(String date,String reason) {

        if (appointmentResultData.getValue().getLocation().getiD() != null &&
                appointmentResultData.getValue().getLocation().getiD() != 0) {
            String appdate=date;
            String time = "";

            if(availableTimeSlot.getValue()!=null && availableTimeSlot.getValue().size()>0
                    && selectTimeSlotItem.getValue()!=null){
                time = availableTimeSlot.getValue().get(selectTimeSlotItem.getValue()).getStartTime();
                time = time.replace("AM", " AM");
                time = time.replace("PM", " PM");
                appdate = appdate + " " + time;
                Helper.setLog("final date",appdate);
                /*String output = "null";
                try {
                    Date dt = new SimpleDateFormat(MMM).parse(appdate);
                    output = df1.format(dt);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
*/

            }
           /* getLoading().setValue(true);


            appointmentRepository.getTimeSlots(Helper.autoken, appointmentResultData.getValue().getLocation().getiD()
                    ,date, getToast(), apiResponce, "TimeSlots");*/
        }
    }


}
