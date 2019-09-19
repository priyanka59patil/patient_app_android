package com.werq.patient.Models.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Models.pojo.AppointmentData;
import com.werq.patient.Models.pojo.Files;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ScheduleDetailsViewModel extends BaseViewModel {

    AppointmentData data;
    AppointmentInterface controller;

    MutableLiveData<String> toolbar;
    MutableLiveData<String> day,month,time,status,fullUserName,speciality,address,
                            addressOnMap;
    MutableLiveData<ArrayList<Files>> filesList;

    public ScheduleDetailsViewModel() {

    }

    public ScheduleDetailsViewModel(AppointmentData data,AppointmentInterface controller) {

        toolbar=new MutableLiveData<>();
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

        this.data = data;
        this.controller=controller;
        prepareData();

    }

    public MutableLiveData<String> getToolbar() {
        return toolbar;
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

    private void prepareData()
    {
        toolbar.setValue(data.getProvider().getFirst_name() + " " + data.getProvider().getLast_name());
        fullUserName.setValue(data.getProvider().getFirst_name() + " " + data.getProvider().getLast_name());
        speciality.setValue(data.getProvider().getSpeciality());

        try {
            Date date = DateHelper.dateFromUtc(data.getAppointment_date());
            day.setValue(DateHelper.dayFromDate(date, "day"));
            month.setValue(DateHelper.dayFromDate(date, "month"));
            time.setValue(DateHelper.dayFromDate(date, "time"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.data.getProvider().getOffice() != null) {
            address.setValue(data.getProvider().getOffice().toString());
            addressOnMap.setValue(data.getProvider().getOffice().toString());
        }
        ArrayList<Files> filesArrayList=new ArrayList<>();
        filesArrayList.addAll(Arrays.asList(data.getFiles()));
        filesList.setValue(filesArrayList);

        //controller.checkFilesSize(files, basicActivities);
       // status.setValue();
    }
}