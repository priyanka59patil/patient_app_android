package com.werq.patient.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.Callback.ApiCallback;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.SingleLiveEvent;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.RequestJsonPojo.RescheduleAppointment;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentDetailResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AvailableTimeSlot;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.service.model.ResponcejsonPojo.RescheduleResponse;
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
import retrofit2.Response;

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
    public MutableLiveData<String> rescheduledDate;
    boolean isFromUpcoming = true;
    MutableLiveData<String> currentAppointmentDate;
    MutableLiveData<String> day, month, time, fullUserName, speciality, address, addressOnMap;
    ApiCallback apiCallback = this;
    int upcommingPageNo = 0;
    int historyPageNo = 0;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> rvVisibility;
    private MutableLiveData<Boolean> rvHistoryVisibility;
    private MutableLiveData<Boolean> scheduleDetailsVisibility;

    MutableLiveData<Boolean> refreshListFlag;
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
        refreshListFlag=new MutableLiveData<>();

        availableTimeSlot =new MutableLiveData<>();
        appointmentResultData = new MutableLiveData<>();
        appointmentNote = new MutableLiveData<>();
        selectTimeSlotItem = new MutableLiveData<>();
        currentAppointmentDate = new MutableLiveData<>();
        rescheduledDate = new MutableLiveData<>();

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

    public MutableLiveData<String> getRescheduledDate() {
        return rescheduledDate;
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

    public MutableLiveData<Boolean> getRefreshListFlag() {
        return refreshListFlag;
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
    public void onSuccess(String url, Response response) {
        AppointmentResponse appointmentResponce;
        ArrayList<AppointmentResult> dataArrayList ;

        if(!TextUtils.isEmpty(url)){

            switch (url){

                case "UpcomingAppointment":
                    getLoading().setValue(false);
                    appointmentResponce = (AppointmentResponse) response.body();
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
                    appointmentResponce = (AppointmentResponse) response.body();
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
                    AppointmentDetailResponse apptDetailResponse = (AppointmentDetailResponse) response.body();
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
                    refreshListFlag.setValue(true);
                    apptDetailResponse = (AppointmentDetailResponse) response.body();
                    appointmentResultData.setValue(apptDetailResponse.getData().getAppointment());
                    prepareAppointmentDetailsData();
                    doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());
                    break;

                case "TimeSlots":

                    // getLoading().setValue(false);
                    TimeSlotResponse timeSlotResponse= (TimeSlotResponse) response.body();
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

                case "RescheduleRequest":
                    getLoading().setValue(false);
                    refreshListFlag.setValue(true);
                    Helper.setLog("response",response.body().toString());
                    RescheduleResponse rescheduleResponse= (RescheduleResponse) response.body();
                    if( rescheduleResponse.getData().getItem2().getAppointment().getRescheduleApptReqDate()!=null &&
                            !TextUtils.isEmpty(rescheduleResponse.getData().getItem2().getAppointment().getRescheduleApptReqDate())){

                        rescheduledDate.setValue(prepareRescheduledDate
                                (rescheduleResponse.getData().getItem2().getAppointment().getRescheduleApptReqDate())
                        );

                    }else {
                        rescheduledDate.setValue("");
                    }
                    getToast().setValue("Success! Your request has been sent!");

                    break;

                default:
                    break;
            }
        }
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
    public void onTokenRefersh(Response response) {
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
            Helper.setExceptionLog("Exception",e);
            e.printStackTrace();
        }

        if (appointmentResultData.getValue().getLocation() != null) {

            Location location = appointmentResult.getLocation();
            String strAddress = location.getOrganizationName() + " " + location.getAddress1() + " " + location.getCity()
                    + " " + location.getState() + " " + location.getPostalcode() + "" + location.getCountry();
            address.setValue(strAddress);

            addressOnMap.setValue(strAddress);
        }

        if(!TextUtils.isEmpty(appointmentResult.getRescheduleApptReqDate()) && appointmentResult.getRescheduleApptReqDate()!=null){

            rescheduledDate.setValue(prepareRescheduledDate(appointmentResult.getRescheduleApptReqDate()));

        }else {
            rescheduledDate.setValue("");
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
                    getToast(), apiCallback, "UpcomingAppointment");

    }

    public void fetchHistoryAppointmentList(int page) {

        getLoading().setValue(true);
        historyPageNo = page;
        appointmentRepository.getHistoryAppoitment(Helper.autoken, "10", "" + page * 10,
                getToast(), apiCallback, "HistoryAppointment");
    }

    public void getAppointmentData(int appointmentId) {
        getLoading().setValue(true);
        appointmentRepository.getAppointmentDetails(Helper.autoken, appointmentId, getToast(), apiCallback, "GetAppointmentDetails");
    }

    public List<AttachmentResult> prepareAttachmentsList(AppointmentDetailResponse apptDetailResponse) {
        List<AttachmentResult> attachmentResultList = new ArrayList<>();

        Helper.setLog("ReferralAttachment.size()", apptDetailResponse.getData().getAppointment().getReferralAttachment().size() + "");

        try {
            if (apptDetailResponse.getData().getAppointment().getReferralAttachment() != null) {
                attachmentResultList.addAll(apptDetailResponse.getData().getAppointment().getReferralAttachment());
            }
        } catch (Exception e) {
            Helper.setExceptionLog("Exception",e);
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
            Helper.setExceptionLog("Exception",e);
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
            appointmentRepository.setConfirmAppointment(Helper.autoken, confirmAppointment, getToast(), apiCallback, "ConfirmAppointment");
        }
    }

    public void fetchTimeSlots(String date) {

        if (appointmentResultData.getValue().getLocation().getiD() != null &&
                appointmentResultData.getValue().getLocation().getiD() != 0) {

            //getLoading().setValue(true);


            appointmentRepository.getTimeSlots(Helper.autoken, appointmentResultData.getValue().getLocation().getiD()
                    ,date, getToast(), apiCallback, "TimeSlots");
        }
    }

    public void sendRescheduleRequest(String date,String reason) {

        if (appointmentResultData.getValue().getiD() != null && appointmentResultData.getValue().getiD() != 0) {
            String appdate=date;
            String time = "";

            if(availableTimeSlot.getValue()!=null && availableTimeSlot.getValue().size()>0
                    && selectTimeSlotItem.getValue()!=null){

                time = availableTimeSlot.getValue().get(selectTimeSlotItem.getValue()).getStartTime();
                time = time.replace("AM", " AM");
                time = time.replace("PM", " PM");
                appdate = appdate + " " + time;
                Helper.setLog("final date",appdate);
                String output = "null";
                try {

                    Date dt = new SimpleDateFormat(Helper.MMM_DD_YYYY+" hh:mm aa").parse(appdate);
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24 hour format
                    output = df.format(dt);
                    output=output.replace(" ","T");
                    Helper.setLog("db date",output);

                    RescheduleAppointment rescheduleAppointment=new RescheduleAppointment();

                    rescheduleAppointment.setAppointmentId(appointmentResultData.getValue().getiD());
                    rescheduleAppointment.setRescheduleApptReqDate(output);
                    if(!TextUtils.isEmpty(reason)){
                        rescheduleAppointment.setRescheduleApptRequestReason(reason);
                    }
                    else {
                        rescheduleAppointment.setRescheduleApptRequestReason("");
                    }

                    Helper.setLog("rescheduleAppointment",rescheduleAppointment.toString());
                    getLoading().setValue(true);

                    appointmentRepository.sendRescheduleRequest(Helper.autoken,rescheduleAppointment
                            ,getToast(),apiCallback,"RescheduleRequest");

                } catch (ParseException e) {
                    e.printStackTrace();
                    Helper.setExceptionLog("ParseException",e);
                }
            }
        }
    }

    public String prepareRescheduledDate(String rawDate){

        String rescheduledDate="";
        try {
            Helper.setLog("database date",rawDate);
            Date d=new SimpleDateFormat(Helper.YYYY_MM_DD_T_HH_MM_SS).parse(rawDate);
            Helper.setLog("parsed date",d.toString());
           /* String date=new SimpleDateFormat(Helper.MMM_DD_YYYY).format(d);
            String time =new SimpleDateFormat(Helper.HH_MM_a).format(d);*/

            String date=new SimpleDateFormat(Helper.MMM_DD_YYYY+ "' At '"+Helper.HH_MM_a).format(d);
            Helper.setLog("formated date",date);
            rescheduledDate=date;

        } catch (ParseException e) {
            e.printStackTrace();
            Helper.setExceptionLog("ParseException",e);
            rescheduledDate="";

        }
        return  rescheduledDate;
    }


}
