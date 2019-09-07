package com.werq.patient.Models.viewModel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    MutableLiveData<String> userName=new MutableLiveData<>();
    MutableLiveData<String> password=new MutableLiveData<>();
    MutableLiveData<Boolean> userNameError=new MutableLiveData<>();
    MutableLiveData<Boolean> passwordError=new MutableLiveData<>();
    MutableLiveData<String> openActivity=new MutableLiveData<>();

    public LoginViewModel() {
        userName.setValue("hi");
    }

    public MutableLiveData<String> getOpenActivity() {
        return openActivity;
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<Boolean> getUserNameError() {
        return userNameError;
    }

    public MutableLiveData<Boolean> getPasswordError() {
        return passwordError;
    }

    public void loginOnClick()
    {
        openActivity.setValue("DashBoard");
    }

    public void signUpOnClick()
    {
        openActivity.setValue("SignUp");
    }

    public void forgotPasswordOnClick()
    {
        openActivity.setValue("ForgotPwd");
    }

    public TextWatcher unTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.e("wAtcher",""+charSequence);
        password.setValue(charSequence+"");
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public TextWatcher pwdTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
