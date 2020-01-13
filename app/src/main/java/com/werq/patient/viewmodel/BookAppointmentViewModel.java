package com.werq.patient.viewmodel;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;

import com.scottyab.aescrypt.AESCrypt;
import com.werq.patient.Interfaces.ApiCallback;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.RequestJsonPojo.ChangePassword;
import com.werq.patient.service.model.RequestJsonPojo.NewAppointment;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

public class BookAppointmentViewModel extends BaseViewModel {

    private static final String TAG = "ChangePasswordViewModel";
    private PatientRepository patientRepository;
    private CompositeDisposable disposable;
    ApiCallback apiCallback=this;

    MutableLiveData<ArrayList<Location>> locationList;
    MutableLiveData<String> selectedLocation;
    MutableLiveData<String> appointmentDate;
    MutableLiveData<String> appointmentTime;
    MutableLiveData<Boolean> locationError;
    MutableLiveData<Boolean> dateError;
    MutableLiveData<Boolean> timeError;
    MutableLiveData<Boolean> requestStatus;


    public BookAppointmentViewModel() {
        super();

        patientRepository = new PatientRepository();
        disposable = new CompositeDisposable();
        this.patientRepository =new PatientRepository();
        locationList=new MutableLiveData<>();
        selectedLocation=new MutableLiveData<>();
        appointmentDate=new MutableLiveData<>();
        appointmentTime=new MutableLiveData<>();
        locationError=new MutableLiveData<>();
        dateError=new MutableLiveData<>();
        timeError=new MutableLiveData<>();
        requestStatus=new MutableLiveData<>();
        requestStatus.setValue(false);

        getLocationsData();
    }


    @Override
    public void onSuccess(String url, Response response) {
        getLoading().setValue(false);
        if(!TextUtils.isEmpty(url)){
            switch (url){
                case "SetNewApptRequest":
                    getToast().setValue("Success! Your appointment has been created!");
                    requestStatus.setValue(true);
                    break;
            }
        }
    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
        requestStatus.setValue(false);
    }

    @Override
    public void onTokenRefersh(Response response) {
        getLoading().setValue(false);
        requestStatus.setValue(false);
    }

    /*private void setNewPasswordApiCall(){
        ChangePassword changePassword=new ChangePassword();
        changePassword.setCurrentPassword(currentPassword.getValue());
        changePassword.setNewPassword(newPassword.getValue());

        Helper.setLog("changePassword req",changePassword.toString());
        getLoading().setValue(true);
        patientRepository.setNewPassword(Helper.autoken,changePassword,getToast(),apiCallback,"SetNewPassword");
    }*/

    public void saveOnClick(){

        if(validateData()){
            NewAppointment newAppointment=new NewAppointment();
          String date=appointmentDate.getValue()+" "+appointmentTime.getValue();
          Helper.setLog("date",date);

            try {
                Date parsedDate=new SimpleDateFormat(Helper.MMM_DD_YYYY+" "+Helper.HH_MM_a).parse(date);

                newAppointment.setRequestedApptDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(parsedDate));
                Helper.setLog("parsedDate",newAppointment.getRequestedApptDate());
            } catch (ParseException e) {
                e.printStackTrace();
                Helper.setExceptionLog("ParseException",e);
                newAppointment.setRequestedApptDate("");
            }

            String location=selectedLocation.getValue();
            newAppointment.setLocationName(selectedLocation.getValue());

            getLoading().setValue(true);
            patientRepository.setNewAppointmentRequest(Helper.autoken,newAppointment,getToast(),apiCallback,"SetNewApptRequest");

        }


    }

    private boolean validateData() {

        boolean result=true;
        if(TextUtils.isEmpty(selectedLocation.getValue())){
            locationError.setValue(true);
            result=false;
        }

        if(TextUtils.isEmpty(appointmentDate.getValue())){
            dateError.setValue(true);
        }

        if(TextUtils.isEmpty(appointmentTime.getValue())){
            timeError.setValue(true);
        }

        return result;
    }

    void getLocationsData(){
        ArrayList<Location> locations=new ArrayList<>();
        locations.add(new Location(1,"329 E. 149th St, Second Floor"));
        locations.add(new Location(2,"147-32 Jamaica Avenue"));
        locations.add(new Location(3,"74-09 37th Avenue"));
        locationList.setValue(locations);
    }

    public MutableLiveData<ArrayList<Location>> getLocationList() {
        return locationList;
    }

    public MutableLiveData<String> getAppointmentDate() {
        return appointmentDate;
    }

    public MutableLiveData<String> getAppointmentTime() {
        return appointmentTime;
    }

    public MutableLiveData<String> getSelectedLocation() {
        return selectedLocation;
    }

    public MutableLiveData<Boolean> getLocationError() {
        return locationError;
    }

    public MutableLiveData<Boolean> getDateError() {
        return dateError;
    }

    public MutableLiveData<Boolean> getTimeError() {
        return timeError;
    }

    public MutableLiveData<Boolean> getRequestStatus() {
        return requestStatus;
    }
}
