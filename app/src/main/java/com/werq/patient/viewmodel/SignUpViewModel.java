package com.werq.patient.viewmodel;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.Callback.ApiCallback;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;
import com.werq.patient.service.repository.SignUpRepository;

import retrofit2.Response;

public class SignUpViewModel extends BaseViewModel {


    MutableLiveData<String> dob;
    MutableLiveData<String> dobError;
    MutableLiveData<String> userName;
    MutableLiveData<Boolean> isSuccessfull;
    MutableLiveData<String> userNameError;
    MutableLiveData<Bundle> openActivitywithBundle;

    String invitaionCode;
    String dobData;
    ApiCallback apiCallback=this;

    SignUpRepository signUpRepository;

    private final String SIGNUP="SignUp";

    public SignUpViewModel() {
        /*pin1 = new MutableLiveData<>();
        pin2 = new MutableLiveData<>();
        pin3 = new MutableLiveData<>();
        pin4 = new MutableLiveData<>();
        pin5 = new MutableLiveData<>();
        pin6 =new MutableLiveData<>();
        pin1Error = new MutableLiveData<>();
        pin2Error = new MutableLiveData<>();
        pin3Error = new MutableLiveData<>();
        pin4Error = new MutableLiveData<>();
        pin5Error=new MutableLiveData<>();
        pin6Error=new MutableLiveData<>();
        etFocus = new MutableLiveData<>();*/
        dob = new MutableLiveData<>();
        dobError = new MutableLiveData<>();
        userName = new MutableLiveData<>();
        isSuccessfull = new MutableLiveData<>();
        userNameError = new MutableLiveData<>();
        openActivitywithBundle=new MutableLiveData<>();
        signUpRepository=new SignUpRepository();

    }



    public void signUpOnClick() {

        SignUpJson signUpJson=new SignUpJson();
        signUpJson.setDOB(dobData);
        signUpJson.setInvitationCode(invitaionCode);
        // signUpJson.setPassword(newpassword.getValue());
        signUpJson.setUsername(userName.getValue());
        getLoading().setValue(true);
        signUpRepository.signUp(signUpJson,getToast(),apiCallback,SIGNUP);

         /*else {

            if(userName.getValue() != null && !userName.getValue().trim().equals("")){

               if(!Helper.isValidEmail(userName.getValue() ) || !Helper.isValidPhone(userName.getValue())){
                   userNameError.setValue("Please enter a valid email or phone");
               }

            }
            if (userName.getValue() == null || userName.getValue().trim().equals("")) {
                userNameError.setValue("Email Cannot Be Empty");

            }
            if (newpassword.getValue() == null || newpassword.getValue().trim().equals("")) {
                newpasswordError.setValue("Password can not be empty");
            }

        }*/
    }

    public boolean signUpDataValidate() {
        boolean check = true;

        if(userName.getValue()!=null){

            if (userName.getValue().trim().equals("")) {
                check = false;
                userNameError.setValue("Please enter an email id");
            }else {
                if(!Helper.isValidEmail(userName.getValue())){
                    check = false;
                    userNameError.setValue("Please enter a valid email id");
                }
            }

        }
        else {
            check = false;
            userNameError.setValue("Please enter an email id");
        }

        /*if(newpassword.getValue()!=null){

            if(newpassword.getValue().trim().equals("")){
                check = false;
                newpasswordError.setValue("Password can not be empty");
            }

        }else {
            check = false;
            newpasswordError.setValue("Password can not be empty");
        }*/

        return check;

    }

    /*public void nextOnClick() {
        if(invitaionCode!=null && invitaionCode.length()==6){
            Bundle bundle =new Bundle();
            bundle.putString("invitationCode",pin1.getValue()+pin2.getValue()+pin3.getValue()+pin4.getValue()+pin5.getValue()+pin6.getValue());

            getOpenActivitywithBundle().setValue(bundle);
        }
        else
        {
            pin1Error.setValue("Please Enter Pin Correct Pin");
        }
        if (pin1.getValue() != null && !pin1.getValue().trim().isEmpty()
                && pin2.getValue() != null && !pin2.getValue().trim().isEmpty()
                && pin3.getValue() != null && !pin3.getValue().trim().isEmpty()
                && pin4.getValue() != null && !pin4.getValue().trim().isEmpty()
                && pin5.getValue() != null && !pin5.getValue().trim().isEmpty()
                && pin6.getValue() != null && !pin6.getValue().trim().isEmpty()) {

            Bundle bundle =new Bundle();
            bundle.putString("invitationCode",pin1.getValue()+pin2.getValue()+pin3.getValue()+pin4.getValue()+pin5.getValue()+pin6.getValue());

            getOpenActivitywithBundle().setValue(bundle);
            //   getActivity().setValue("VerifyIdentity");

        } else {
            if (pin1.getValue() == null || pin1.getValue().trim().isEmpty()) {
                pin1Error.setValue("Please Enter Pin");
            }
            if (pin2.getValue() == null || pin2.getValue().trim().isEmpty()) {
                pin2Error.setValue("Please Enter Pin");
            }
            if (pin3.getValue() == null || pin3.getValue().trim().isEmpty()) {
                pin3Error.setValue("Please Enter Pin");
            }
            if (pin4.getValue() == null || pin4.getValue().trim().isEmpty()) {
                pin4Error.setValue("Please Enter Pin");
            }

            if (pin5.getValue() == null || pin5.getValue().trim().isEmpty()) {
                pin5Error.setValue("Please Enter Pin");
            }
            if (pin6.getValue() == null || pin6.getValue().trim().isEmpty()) {
                pin6Error.setValue("Please Enter Pin");
            }

        }

    }*/

    public void DobnextOnClick() {
        if (dob.getValue() != null && !dob.getValue().trim().isEmpty()) {
            Bundle bundle=new Bundle();
            bundle.putString("dob",dob.getValue());
            bundle.putString("invitationCode",invitaionCode);
          //  bundle.putString("invitationCode",invitaionCode);
            getOpenActivitywithBundle().setValue(bundle);

           // getActivity().setValue("CreateAccountActivity");
        } else {
            dobError.setValue("Please select your DOB for verification");
        }

    }

    public void loginOnClick() {
        getActivity().setValue("Login");
        Helper.setLog("loginOnClick",getActivity().getValue());
    }

    public MutableLiveData<Bundle> getOpenActivitywithBundle() {
        return openActivitywithBundle;
    }

    public void unOnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        userNameError.setValue(null);
    }


    public void onTextChanged(CharSequence s, int start, int before, int count) {
        dobError.setValue(null);
    }


    @Override
    public void onSuccess(String url, Response response) {
        getLoading().setValue(false);
        if(url!=null && !url.isEmpty()){
            if(url.equals(SIGNUP)){

                isSuccessfull.setValue(true);
            }
        }
    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
        if(url!=null && !url.isEmpty()){
            if(url.equals(SIGNUP)){
                isSuccessfull.setValue(false);

            }
        }

        Helper.setLog("SignUpVM onError","signUp failed");
    }

    @Override
    public void onTokenRefersh(Response response) {
        getLoading().setValue(false);
    }

    /************GETTER SETTERS****************************/

    public String getInvitaionCode() {
        return invitaionCode;
    }

    public String getDobData() {
        return dobData;
    }

    public void setDobData(String dobData) {
        this.dobData = dobData;
    }

    public void setInvitaionCode(String invitaionCode) {
        this.invitaionCode = invitaionCode;
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getUserNameError() {
        return userNameError;
    }

    public MutableLiveData<String> getDob() {
        return dob;
    }

    public MutableLiveData<String> getDobError() {
        return dobError;
    }

    public MutableLiveData<Boolean> getIsSuccessfull() {
        return isSuccessfull;
    }
}
