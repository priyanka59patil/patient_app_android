package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.base.BaseViewModel;

public class CreateAccountViewModel extends BaseViewModel {

    MutableLiveData<String> userName;
    MutableLiveData<String> newpassword;
    MutableLiveData<String> userNameError;
    MutableLiveData<String> newpasswordError;

    public CreateAccountViewModel() {
        userName=new MutableLiveData<>();
        newpassword=new MutableLiveData<>();
        userNameError=new MutableLiveData<>();
        newpasswordError=new MutableLiveData<>();
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

    public void signUpOnClick()
    {
        if(userName.getValue()!=null && !userName.getValue().isEmpty() &&
                newpassword.getValue()!=null && !newpassword.getValue().isEmpty() ) {
            getActivity().setValue("FingerPrintActivity");
        }
        else {
            if(userName.getValue()==null || userName.getValue().trim().equals(""))
            {
                userNameError.setValue("Email Cannot Be Empty");

            }
            if(newpassword.getValue()==null || newpassword.getValue().trim().equals(""))
            {
                newpasswordError.setValue("Password can not be empty");
            }

        }
    }

    public void unOnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        userNameError.setValue(null);
    }

    public void pwdOnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        newpasswordError.setValue(null);
    }

}
