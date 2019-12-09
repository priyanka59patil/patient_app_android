package com.werq.patient.viewmodel;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.RequestJsonPojo.ChangePassword;
import com.werq.patient.service.model.ResponcejsonPojo.ChangePasswordResponse;

import io.reactivex.disposables.CompositeDisposable;

public class ChangePasswordViewModel extends BaseViewModel {

    private static final String TAG = "ChangePasswordViewModel";
    private PatientRepository patientRepository;
    private CompositeDisposable disposable;
    ApiResponce apiResponce=this;

    public MutableLiveData<String> currentPassword;
    public MutableLiveData<String> newPassword;
    public MutableLiveData<String> reenteredPassword;
    MutableLiveData<String> currentPasswordError;
    MutableLiveData<String> newPasswordError;
    MutableLiveData<String> reenteredPasswordError;
    MutableLiveData<Boolean> changePasswordStatus;


    public ChangePasswordViewModel() {
        super();

        patientRepository = new PatientRepository();
        disposable = new CompositeDisposable();
        this.patientRepository =new PatientRepository();

        currentPassword=new MutableLiveData<>();
        newPassword =new MutableLiveData<>();
        reenteredPassword= new MutableLiveData<>();

        currentPasswordError=new MutableLiveData<>();
        newPasswordError =new MutableLiveData<>();
        reenteredPasswordError =new MutableLiveData<>();
        changePasswordStatus =new MutableLiveData<>();


    }


    @Override
    public void onSuccess(String url, String responseJson) {
        getLoading().setValue(false);

        if(!TextUtils.isEmpty(url) && url.equals("ChangePassword")){

            ChangePasswordResponse changePasswordResponse
                    =Helper.getGsonInstance().fromJson(responseJson,ChangePasswordResponse.class);
            getToast().setValue("Your password has been set successfully");
            changePasswordStatus.setValue(true);
            Helper.setLog("changePasswordResponse",changePasswordResponse.toString());



        }

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
        changePasswordStatus.setValue(false);
    }

    @Override
    public void onTokenRefersh(String responseJson) {
        getLoading().setValue(false);
        changePasswordStatus.setValue(false);
    }

    private void setNewPasswordApiCall(){
        ChangePassword changePassword=new ChangePassword();
        changePassword.setCurrentPassword(currentPassword.getValue());
        changePassword.setNewPassword(newPassword.getValue());

        getLoading().setValue(true);
        patientRepository.setNewPassword(Helper.autoken,changePassword,getToast(),apiResponce,"ChangePassword");
    }

    public void updateOnClick(){

        if(!TextUtils.isEmpty(currentPassword.getValue()) && !TextUtils.isEmpty(newPassword.getValue())
                && !TextUtils.isEmpty(reenteredPassword.getValue()) ){

            if(newPassword.getValue().equals(reenteredPassword.getValue())){

                setNewPasswordApiCall();
            }
            else {
                getToast().setValue("New password and re-entered passwords are not same");
            }

        }
        else {
            if(TextUtils.isEmpty(currentPassword.getValue())){
                currentPasswordError.setValue("Current password cannot be empty");
            }
            if(TextUtils.isEmpty(newPassword.getValue())){
                newPasswordError.setValue("New password cannot be empty");
            }

            if(TextUtils.isEmpty(reenteredPassword.getValue())){
                reenteredPasswordError.setValue("Re-entered password cannot be empty");
            }
        }

    }

    public TextWatcher currentPwdTxtWatch=new TextWatcher() {

        private final String TAG = "in-un";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            currentPasswordError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public TextWatcher newPwdTxtWatch=new TextWatcher() {
        private final String TAG = "in-pwd";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            newPasswordError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public TextWatcher reenterPwdTxtWatch=new TextWatcher() {
        private final String TAG = "in-pwd";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            reenteredPasswordError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public MutableLiveData<String> getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(MutableLiveData<String> currentPassword) {
        this.currentPassword = currentPassword;
    }

    public MutableLiveData<String> getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(MutableLiveData<String> newPassword) {
        this.newPassword = newPassword;
    }

    public MutableLiveData<String> getReenteredPassword() {
        return reenteredPassword;
    }

    public void setReenteredPassword(MutableLiveData<String> reenteredPassword) {
        this.reenteredPassword = reenteredPassword;
    }

    public MutableLiveData<String> getCurrentPasswordError() {
        return currentPasswordError;
    }

    public void setCurrentPasswordError(MutableLiveData<String> currentPasswordError) {
        this.currentPasswordError = currentPasswordError;
    }

    public MutableLiveData<String> getNewPasswordError() {
        return newPasswordError;
    }

    public void setNewPasswordError(MutableLiveData<String> newPasswordError) {
        this.newPasswordError = newPasswordError;
    }

    public MutableLiveData<String> getReenteredPasswordError() {
        return reenteredPasswordError;
    }

    public void setReenteredPasswordError(MutableLiveData<String> reenteredPasswordError) {
        this.reenteredPasswordError = reenteredPasswordError;
    }

    public MutableLiveData<Boolean> getChangePasswordStatus() {
        return changePasswordStatus;
    }
}
