package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Utils.Helper;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.Files;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentDetailResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.repository.AppointmentRepository;
import com.werq.patient.views.ui.Fragments.AppointmentFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import okhttp3.internal.http2.ErrorCode;

public class ScheduleDetailsViewModel extends BaseViewModel {

    AppointmentResult appointmentResult;
    AppointmentInterface controller;

    //MutableLiveData<String> toolbar;
    MutableLiveData<String> day,month,time,status,fullUserName,speciality,address,
                            addressOnMap;
    MutableLiveData<ArrayList<Files>> filesList;

    MutableLiveData<Boolean> visibility;
    public MutableLiveData<String> toolbarTitle;

    String authToken;
    ApiResponce apiResponce=this;
    AppointmentRepository appointmentRepository;
    int appointmentId;
    AppointmentDetailResponse apptDetailResponse;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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
        visibility=new MutableLiveData<Boolean>();
        toolbarTitle =new MutableLiveData<>();
        //this.appointmentResult = appointmentResult;
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

    public MutableLiveData<String> getToolbarTitle() {
        return toolbarTitle;
    }

    public MutableLiveData<Boolean> getVisibility() {
        return visibility;
    }

    private void prepareData()
    {
        //toolbar.setValue(data.getProvider().getFirst_name() + " " + data.getProvider().getLast_name());

        this.appointmentResult=apptDetailResponse.getData().getAppointment();
        String doctorfullName=appointmentResult.getDoctor().getFirstName() + " " + appointmentResult.getDoctor().getLastName();
        toolbarTitle.setValue(doctorfullName);
        fullUserName.setValue(doctorfullName);
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
            address.setValue(appointmentResult.getLocation().getOrganizationName());
            addressOnMap.setValue(appointmentResult.getLocation().getOrganizationName());
        }

        //getAttachments();
        /*ArrayList<Files> filesArrayList=new ArrayList<>();
        filesArrayList.addAll(Arrays.asList(appointmentResult.get()));
        filesList.setValue(filesArrayList);

        if(filesArrayList.size()>0){
            visibility.setValue(true);
        }
        else {
            visibility.setValue(false);
        }*/

        //controller.checkFilesSize(files, basicActivities);
       // status.setValue();
    }

    private void getAppointmentData() {
        getLoading().setValue(true);
        appointmentRepository.getAppointmentDetails(authToken,appointmentId+"",getToast(),apiResponce,"GetAppointmentDetails");
    }

    @Override
    public void onSuccess(String url, String responseJson) {
        getLoading().setValue(false);
        if(url!=null && !url.isEmpty())
        {
            if(url.equalsIgnoreCase("GetAppointmentDetails")){

                apptDetailResponse= Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
                if(apptDetailResponse!=null)
                {
                    prepareData();
                }
            }
        }

    }

    @Override
    public void onError(String url, String errorCode) {
        getLoading().setValue(false);
    }
}
