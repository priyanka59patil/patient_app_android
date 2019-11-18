package com.werq.patient.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Utils.Helper;
import com.werq.patient.service.model.Files;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentDetailResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.service.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleDetailsViewModel extends BaseViewModel {

    AppointmentResult appointmentResult;
    AppointmentInterface controller;

    //MutableLiveData<String> toolbar;
    MutableLiveData<String> day,month,time,status,fullUserName,speciality,address,
                            addressOnMap;
    MutableLiveData<ArrayList<Files>> filesList;

    public MutableLiveData<Boolean> attachmentVisibility;
    public MutableLiveData<Boolean> confirmButtonVisibility;
    public MutableLiveData<String> appointmentStatus;
    public MutableLiveData<List<AttachmentResult>> attachmentList;
    public MutableLiveData<String> doctorProfilePhoto;
    //public MutableLiveData<String> toolbarTitle;

    String authToken;
    ApiResponce apiResponce=this;
    AppointmentRepository appointmentRepository;
    AppointmentDetailResponse apptDetailResponse;
    private String TAG="ScheduleDetailsViewModel";

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        prepareData();
        getAppointmentData();
    }


    public ScheduleDetailsViewModel() {

    }

    public ScheduleDetailsViewModel(AppointmentResult appointmentResult, AppointmentInterface controller) {

        //toolbar=new MutableLiveData<>();
        day=new MutableLiveData<>();
        month=new MutableLiveData<>();
        time=new MutableLiveData<>();
        status=new MutableLiveData<>();
        fullUserName=new MutableLiveData<>();
        speciality=new MutableLiveData<>();
        address=new MutableLiveData<>();
        addressOnMap=new MutableLiveData<>();
        filesList=new MutableLiveData<>();
        day=new MutableLiveData<>();
        attachmentVisibility=new MutableLiveData<Boolean>();
        confirmButtonVisibility=new MutableLiveData<Boolean>();
        appointmentStatus =new MutableLiveData<>();
        confirmButtonVisibility.setValue(false);
        attachmentVisibility.setValue(false);
        attachmentList=new MutableLiveData<>();
        doctorProfilePhoto =new MutableLiveData<>();

        //toolbarTitle =new MutableLiveData<>();
        this.appointmentResult = appointmentResult;
        this.controller=controller;
        appointmentRepository=new AppointmentRepository();

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

    public MutableLiveData<String> getStatus() {
        return status;
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

    public MutableLiveData<ArrayList<Files>> getFilesList() {
        return filesList;
    }

    public MutableLiveData<List<AttachmentResult>> getAttachmentList() {
        return attachmentList;
    }

    public MutableLiveData<String> getAppointmentStatus() {
        return appointmentStatus;
    }

    public MutableLiveData<String> getDoctorProfilePhoto() {
        return doctorProfilePhoto;
    }

/* public MutableLiveData<String> getToolbarTitle() {
        return toolbarTitle;
    }*/

    public MutableLiveData<Boolean> getVisibility() {
        return attachmentVisibility;
    }

    private void prepareData()
    {
        //toolbar.setValue(data.getProvider().getFirst_name() + " " + data.getProvider().getLast_name());

        //this.appointmentResult=apptDetailResponse.getData().getAppointment();
        fullUserName.setValue(appointmentResult.getDoctor().getFirstName() + " " + appointmentResult.getDoctor().getLastName());
        speciality.setValue(appointmentResult.getDoctor().getSpeciality().getName());

        try {
            Date date = DateHelper.dateFromUtc(appointmentResult.getAppintmentDate());
            day.setValue(DateHelper.dayFromDate(date, "day"));
            month.setValue(DateHelper.dayFromDate(date, "month"));
            time.setValue(DateHelper.dayFromDate(date, "time"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.appointmentResult.getLocation() != null) {

            Location location=appointmentResult.getLocation();
            String strAddress =location.getOrganizationName()+" "+location.getAddress1()+" "+location.getCity()
                    +" "+location.getState()+" "+ location.getPostalcode()+""+location.getCountry();
            address.setValue(strAddress);

            addressOnMap.setValue(strAddress);
        }
        Log.e(TAG, "prepareData: "+ appointmentResult.getConfirmByPatient());

        confirmButtonVisibility.setValue(appointmentResult.getConfirmByPatient());

        appointmentStatus.setValue(appointmentResult.getAppointmentStatus());
        //getAttachments();
        /*ArrayList<Files> filesArrayList=new ArrayList<>();
        filesArrayList.addAll(Arrays.asList(appointmentResult.get()));
        filesList.setValue(filesArrayList);

        */

        if(attachmentList.getValue()!=null){
            if(attachmentList.getValue().size()>0){
                attachmentVisibility.setValue(true);

            }
            else {
                attachmentVisibility.setValue(false);
                appointmentStatus.setValue(apptDetailResponse.getData().getAppointment().getAppointmentStatus());
            }
        }


        //controller.checkFilesSize(files, basicActivities);
       // status.setValue();
    }

    private void getAppointmentData() {
        getLoading().setValue(true);
        appointmentRepository.getAppointmentDetails(authToken,appointmentResult.getiD(),getToast(),apiResponce,"GetAppointmentDetails");
    }

    @Override
    public void onSuccess(String url, String responseJson) {
        Helper.setLog(TAG,url+"::" +responseJson);

        getLoading().setValue(false);
        if(url!=null && !url.isEmpty())
        {
            if(url.equalsIgnoreCase("GetAppointmentDetails")){

                AppointmentDetailResponse apptDetailResponse= Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
                /*if(apptDetailResponse!=null && apptDetailResponse.getData().getVisitNoteAttachment()!=null)
                {
                    List<VisitNoteAttachment> visitNoteList=apptDetailResponse.getData().getVisitNoteAttachment();

                    List<VisitNoteAttachment> get=apptDetailResponse.getData().getVisitNoteAttachment();
                    if(visitNoteList!=null && visitNoteList.size()>0)
                    {
                        Helper.setLog("visitNoteList.size()",visitNoteList.size()+"");
                    }

                }*/

                doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());
                attachmentList.setValue(prepareAttachmentsList(apptDetailResponse));

                if(attachmentList.getValue()!=null){
                    if(attachmentList.getValue().size()>0){
                        attachmentVisibility.setValue(true);

                    }
                    else {
                        attachmentVisibility.setValue(false);
                        appointmentStatus.setValue(apptDetailResponse.getData().getAppointment().getAppointmentStatus());
                    }
                }
            }
            else if(url.equalsIgnoreCase("ConfirmAppointment")){
                AppointmentDetailResponse apptDetailResponse= Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
                Log.e(TAG, "onSuccess: "+apptDetailResponse.getData().getAppointment().getConfirmByPatient() );

                doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());
                if(apptDetailResponse.getData().getAppointment().getConfirmByPatient()==true)
                {
                    appointmentStatus.setValue("Confirmed");

                }else
                {
                   appointmentStatus.setValue(apptDetailResponse.getData().getAppointment().getAppointmentStatus());

                }
              confirmButtonVisibility.setValue(apptDetailResponse.getData().getAppointment().getConfirmByPatient());

            }
        }

    }

    @Override
    public void onError(String url, String errorCode) {
        getLoading().setValue(false);
    }

    @Override
    public void onTokenRefersh(String responseJson) {

    }

    public  void confirmButtonOnClick(){

        if(appointmentResult.getiD()!=null && appointmentResult.getiD()!=0)
        {
            getLoading().setValue(true);
            ConfirmAppointment confirmAppointment=new ConfirmAppointment();
            confirmAppointment.setID(appointmentResult.getiD());
            confirmAppointment.setConfirmByPatient("true");
            appointmentRepository.setConfirmAppointment(authToken,confirmAppointment,getToast(),apiResponce,"ConfirmAppointment");
        }
    }

    public List<AttachmentResult> prepareAttachmentsList(AppointmentDetailResponse apptDetailResponse)
    {
        List<AttachmentResult> attachmentResultList =new ArrayList<>();

        Helper.setLog("ReferralAttachment.size()",apptDetailResponse.getData().getAppointment().getReferralAttachment().size()+"");

        try{
            if(apptDetailResponse.getData().getAppointment().getReferralAttachment()!=null){
                attachmentResultList.addAll(apptDetailResponse.getData().getAppointment().getReferralAttachment());
            }
        }
        catch (Exception e){
            Helper.setLog(TAG+" -Exception",e.getMessage());
        }

        Helper.setLog("visitnote size",apptDetailResponse.getData().getVisitNoteAttachment().size()+"");

        try{


            for (int i = 0; i < apptDetailResponse.getData().getVisitNoteAttachment().size(); i++) {

                Helper.setLog("apptDetailResponse",apptDetailResponse.getData().getVisitNoteAttachment().get(i).getAttachement().size()+"");

                List<AttachmentResult> visitNote=apptDetailResponse.getData().getVisitNoteAttachment().get(i).getAttachement();
                if(visitNote!=null){
                    attachmentResultList.addAll(visitNote);
                }


            }
        }catch (Exception e){
            Helper.setLog(TAG+" -Exception",e.getMessage());
        }


        Helper.setLog("ReferralAttachment.size()",apptDetailResponse.getData().getAppointment().getReferralAttachment().size()+"");

        return attachmentResultList;
    }
}
