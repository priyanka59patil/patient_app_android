package com.werq.patient.viewmodel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.RequestJsonPojo.SignUpJson;
import com.werq.patient.service.repository.SignUpRepository;

import okhttp3.internal.http2.ErrorCode;

public class SignUpViewModel extends BaseViewModel {

    MutableLiveData<String> pin1;
    MutableLiveData<String> pin2;
    MutableLiveData<String> pin3;
    MutableLiveData<String> pin4;
    MutableLiveData<String> pin5;
    MutableLiveData<String> pin6;
    MutableLiveData<String> pin1Error;
    MutableLiveData<String> pin2Error;
    MutableLiveData<String> pin3Error;
    MutableLiveData<String> pin4Error;
    MutableLiveData<String> pin5Error;
    MutableLiveData<String> pin6Error;
    MutableLiveData<String> etFocus;
    MutableLiveData<String> dob;
    MutableLiveData<String> dobError;
    MutableLiveData<String> userName;
    MutableLiveData<String> newpassword;
    MutableLiveData<String> userNameError;
    MutableLiveData<String> newpasswordError;
    MutableLiveData<Bundle> openActivitywithBundle;

    String invitaionCode;
    String dobData;
    ApiResponce apiResponce=this;

    SignUpRepository signUpRepository;

    private final String SIGNUP="SignUp";

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




 /*   MutableLiveData<Boolean> pin2Focus;
    MutableLiveData<Boolean> pin3Focus;
    MutableLiveData<Boolean> pin4Focus;*/

    public void setPin1(MutableLiveData<String> pin1) {
        this.pin1 = pin1;
    }

    public void setPin2(MutableLiveData<String> pin2) {
        this.pin2 = pin2;
    }

    public void setPin3(MutableLiveData<String> pin3) {
        this.pin3 = pin3;
    }

    public void setPin4(MutableLiveData<String> pin4) {
        this.pin4 = pin4;
    }

    public void setPin1Error(MutableLiveData<String> pin1Error) {
        this.pin1Error = pin1Error;
    }

    public void setPin2Error(MutableLiveData<String> pin2Error) {
        this.pin2Error = pin2Error;
    }

    public void setPin3Error(MutableLiveData<String> pin3Error) {
        this.pin3Error = pin3Error;
    }

    public void setPin4Error(MutableLiveData<String> pin4Error) {
        this.pin4Error = pin4Error;
    }

    public void setEtFocus(MutableLiveData<String> etFocus) {
        this.etFocus = etFocus;
    }

    public void setDob(MutableLiveData<String> dob) {
        this.dob = dob;
    }

    public void setDobError(MutableLiveData<String> dobError) {
        this.dobError = dobError;
    }

    public void setUserName(MutableLiveData<String> userName) {
        this.userName = userName;
    }

    public void setNewpassword(MutableLiveData<String> newpassword) {
        this.newpassword = newpassword;
    }

    public void setUserNameError(MutableLiveData<String> userNameError) {
        this.userNameError = userNameError;
    }

    public void setNewpasswordError(MutableLiveData<String> newpasswordError) {
        this.newpasswordError = newpasswordError;
    }

    public void setPin1TextWatcher(TextWatcher pin1TextWatcher) {
        this.pin1TextWatcher = pin1TextWatcher;
    }

    public void setPin2TextWatcher(TextWatcher pin2TextWatcher) {
        this.pin2TextWatcher = pin2TextWatcher;
    }

    public void setPin3TextWatcher(TextWatcher pin3TextWatcher) {
        this.pin3TextWatcher = pin3TextWatcher;
    }

    public void setPin4TextWatcher(TextWatcher pin4TextWatcher) {
        this.pin4TextWatcher = pin4TextWatcher;
    }

    public MutableLiveData<String> getPin5() {
        return pin5;
    }

    public void setPin5(MutableLiveData<String> pin5) {
        this.pin5 = pin5;
    }

    public MutableLiveData<String> getPin6() {
        return pin6;
    }

    public void setPin6(MutableLiveData<String> pin6) {
        this.pin6 = pin6;
    }

    public MutableLiveData<String> getPin5Error() {
        return pin5Error;
    }

    public void setPin5Error(MutableLiveData<String> pin5Error) {
        this.pin5Error = pin5Error;
    }

    public MutableLiveData<String> getPin6Error() {
        return pin6Error;
    }

    public void setPin6Error(MutableLiveData<String> pin6Error) {
        this.pin6Error = pin6Error;
    }

    public SignUpViewModel() {
        pin1 = new MutableLiveData<>();
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
        etFocus = new MutableLiveData<>();
        dob = new MutableLiveData<>();
        dobError = new MutableLiveData<>();
        userName = new MutableLiveData<>();
        newpassword = new MutableLiveData<>();
        userNameError = new MutableLiveData<>();
        newpasswordError = new MutableLiveData<>();
        openActivitywithBundle=new MutableLiveData<>();
        signUpRepository=new SignUpRepository();

        etFocus.setValue("pin1");
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getNewpassword() {
        return newpassword;
    }

    public MutableLiveData<String> getUserNameError() {
        return userNameError;
    }

    public MutableLiveData<String> getNewpasswordError() {
        return newpasswordError;
    }

    public MutableLiveData<String> getPin1() {
        return pin1;
    }

    public MutableLiveData<String> getPin2() {
        return pin2;
    }

    public MutableLiveData<String> getPin3() {
        return pin3;
    }

    public MutableLiveData<String> getPin4() {
        return pin4;
    }

    public MutableLiveData<String> getPin1Error() {
        return pin1Error;
    }

    public MutableLiveData<String> getPin2Error() {
        return pin2Error;
    }

    public MutableLiveData<String> getPin3Error() {
        return pin3Error;
    }

    public MutableLiveData<String> getPin4Error() {
        return pin4Error;
    }

    public MutableLiveData<String> getEtFocus() {
        return etFocus;
    }

    public void signUpOnClick() {
        if (userName.getValue() != null && !userName.getValue().isEmpty() &&
                newpassword.getValue() != null && !newpassword.getValue().isEmpty()) {

            SignUpJson signUpJson=new SignUpJson();
            signUpJson.setDOB(dobData);
            signUpJson.setInvitationCode(invitaionCode);
            signUpJson.setPassword(newpassword.getValue());
            signUpJson.setUsername(userName.getValue());

            signUpRepository.signUp(signUpJson,getToast(),apiResponce,SIGNUP);






            getActivity().setValue("FingerPrintActivity");
        } else {
            if (userName.getValue() == null || userName.getValue().trim().equals("")) {
                userNameError.setValue("Email Cannot Be Empty");

            }
            if (newpassword.getValue() == null || newpassword.getValue().trim().equals("")) {
                newpasswordError.setValue("Password can not be empty");
            }

        }
    }

    public MutableLiveData<Bundle> getOpenActivitywithBundle() {
        return openActivitywithBundle;
    }



    public void unOnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        userNameError.setValue(null);
    }

    public void pwdOnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        newpasswordError.setValue(null);
    }

    public void nextOnClick() {
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

    }

    public MutableLiveData<String> getDob() {
        return dob;
    }

    public MutableLiveData<String> getDobError() {
        return dobError;
    }

    public void DobnextOnClick() {
        if (dob.getValue() != null && !dob.getValue().trim().isEmpty()) {
            Bundle bundle=new Bundle();
            bundle.putString("dob",dob.getValue());
            bundle.putString("invitationCode",invitaionCode);
            getOpenActivitywithBundle().setValue(bundle);

            getActivity().setValue("CreateAccountActivity");
        } else {
            dobError.setValue("Please Select Dob");
        }

    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        dobError.setValue(null);
    }


    public void loginOnClick() {
        getActivity().setValue("Login");
    }

    public TextWatcher pin1TextWatcher = new TextWatcher() {

        private final String TAG = "in-un";

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin1Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1) {
                etFocus.setValue("pin2");
            }
        }
    };

    public TextWatcher pin2TextWatcher = new TextWatcher() {

        private final String TAG = "in-un";

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin2Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1) {
                etFocus.setValue("pin3");
            } else if (editable.toString().length() == 0) {
                etFocus.setValue("pin1");
            }
        }
    };
    public TextWatcher pin3TextWatcher = new TextWatcher() {

        private final String TAG = "in-un";

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin3Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1) {
                etFocus.setValue("pin4");
            } else if (editable.toString().length() == 0)
                etFocus.setValue("pin2");
        }
    };
    public TextWatcher pin4TextWatcher = new TextWatcher() {

        private final String TAG = "in-un";

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin4Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1) {
                etFocus.setValue("pin5");
            }
            else if (editable.toString().length() == 0)
                etFocus.setValue("pin3");
        }
    };

    public TextWatcher pin5TextWatcher = new TextWatcher() {

        private final String TAG = "in-un";

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin5Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1) {
                etFocus.setValue("pin6");
            }
            else if (editable.toString().length() == 0)
                etFocus.setValue("pin4");
        }
    };


    public TextWatcher pin6TextWatcher = new TextWatcher() {

        private final String TAG = "in-un";

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin6Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 0)
                etFocus.setValue("pin5");
        }
    };


    @Override
    public void onSuccess(String url, String responseJson) {
        Helper.setLog("SignUpVM OnSuccess","successfull login intent to fingerprint");

    }

    @Override
    public void onError(String url, String errorCode) {
        Helper.setLog("SignUpVM onError","signUp failed");
    }
}
