package com.werq.patient.viewmodel;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;

import com.scottyab.aescrypt.AESCrypt;
import com.werq.patient.Interfaces.Callback.ApiCallback;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.RequestJsonPojo.ChangePassword;
import com.werq.patient.service.model.ResponcejsonPojo.ApiResponse;
import com.werq.patient.service.model.ResponcejsonPojo.SignUpData;

import java.security.GeneralSecurityException;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

public class SetNewPasswordViewModel extends BaseViewModel {

    private static final String TAG = "ChangePasswordViewModel";
    private PatientRepository patientRepository;
    private CompositeDisposable disposable;
    ApiCallback apiCallback=this;

    MutableLiveData<Boolean> rememberMe;
    MutableLiveData<String> userName;
    public MutableLiveData<String> currentPassword;
    public MutableLiveData<String> newPassword;
    public MutableLiveData<String> reenteredPassword;
    MutableLiveData<String> currentPasswordError;
    MutableLiveData<String> newPasswordError;
    MutableLiveData<String> reenteredPasswordError;
    MutableLiveData<Boolean> changePasswordStatus;
    MutableLiveData<String> nextActivity;

    boolean isValidPassword=false;
    SessionManager sessionManager;
    Context mContext;

    public SetNewPasswordViewModel(String authToken) {
        super(authToken);

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

        rememberMe=new MutableLiveData<>();
        userName=new MutableLiveData<>();
        nextActivity=new MutableLiveData<>();
    }


    @Override
    public void onSuccess(String url, Response response) {
        getLoading().setValue(false);

        if(!TextUtils.isEmpty(url) && url.equals("SetNewPassword")){

            ApiResponse<SignUpData> apiResponse= (ApiResponse<SignUpData>) response.body();
            SignUpData signUpData=apiResponse.getData();
            getToast().setValue("Your password has been set successfully");
            changePasswordStatus.setValue(true);


                if(signUpData!=null){
                    String authToken=sessionManager.getAuthToken();
                    long timeStamp=sessionManager.getTimeStamp();

                    sessionManager.clear();

                    sessionManager.creteUserSession(authToken,
                            signUpData.getRefreshToken(),
                            signUpData.getUser().getUserName(),
                            signUpData.getUser().getID()+"",
                            timeStamp);

                    String encryptedUName = "";
                    String encryptedPass = "";
                    try {

                        encryptedUName = AESCrypt.encrypt("Asdrwsd", userName.getValue().trim().toLowerCase());
                        encryptedPass = AESCrypt.encrypt("Asdrwsd", newPassword.getValue().trim());

                    } catch (GeneralSecurityException e) {

                        Helper.setExceptionLog("GeneralSecurityException",e);
                    }

                    Helper.setLog("set encryptedUName",encryptedUName+"::"+ userName.getValue().trim());
                    sessionManager.setRememberUsername(rememberMe.getValue(), encryptedUName);
                    sessionManager.setRememberPassword(false, encryptedPass);
                    nextActivity.setValue("DashBoard");
                }

            //Helper.setLog("changePasswordResponse",changePasswordResponse.toString());

        }
    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
        changePasswordStatus.setValue(false);
    }

    @Override
    public void onTokenRefersh(Response response) {
        getLoading().setValue(false);
        changePasswordStatus.setValue(false);
    }

    private void setNewPasswordApiCall(){
        ChangePassword changePassword=new ChangePassword();
        changePassword.setCurrentPassword(currentPassword.getValue());
        changePassword.setNewPassword(newPassword.getValue());

        Helper.setLog("changePassword req",changePassword.toString());
        getLoading().setValue(true);
        patientRepository.setNewPassword(getAuthToken(),changePassword,getToast(),apiCallback,"SetNewPassword");
    }

    public void updateOnClick(){

        if( !TextUtils.isEmpty(currentPassword.getValue()) && !TextUtils.isEmpty(newPassword.getValue()) && !TextUtils.isEmpty(reenteredPassword.getValue()) ){

            if(newPassword.getValue().equals(reenteredPassword.getValue())){

                if(Helper.isValidPassword(newPassword.getValue())){
                    Helper.setLog("updateOnClick","setNewPasswordApiCall");
                    setNewPasswordApiCall();
                }
                else {

                    Helper.showToast(mContext,mContext.getResources().getString(R.string.password_pattern_msg));
                }

            }
            else {
                getToast().setValue("New Password and Confirm New Password should be same");
            }



        }
        else {

            if(TextUtils.isEmpty(currentPassword.getValue())){
                Helper.setLog("SetNewPwd", "please check current password");
            }

            if(TextUtils.isEmpty(newPassword.getValue())){
                newPasswordError.setValue("Please enter new password");
            }

            if(TextUtils.isEmpty(reenteredPassword.getValue())){
                reenteredPasswordError.setValue("Please enter confirm new password");
            }
        }

    }

    public TextWatcher newPwdTxtWatch=new TextWatcher() {
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

    public boolean isValidPassword() {
        return isValidPassword;
    }

    public void setValidPassword(boolean validPassword) {
        isValidPassword = validPassword;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public MutableLiveData<Boolean> getRememberMe() {
        return rememberMe;
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getNextActivity() {
        return nextActivity;
    }
}
