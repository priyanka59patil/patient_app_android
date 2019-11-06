package com.werq.patient.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.base.BaseViewModel;

import okhttp3.internal.http2.ErrorCode;

public class LoginViewModel extends BaseViewModel {

    MutableLiveData<String> userName;
    MutableLiveData<String> password;
    MutableLiveData<String> userNameError;
    MutableLiveData<String> passwordError;


    public LoginViewModel() {
        super();

        userName=new MutableLiveData<>();
        password=new MutableLiveData<>();
        userNameError=new MutableLiveData<>();
        passwordError=new MutableLiveData<>();

    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<String> getUserNameError() {
        return userNameError;
    }

    public MutableLiveData<String> getPasswordError() {
        return passwordError;
    }

    public void loginOnClick()
    {
        Log.e( "loginOnClick: ", userName.getValue()+" "+password.getValue() );
        if(userName.getValue()!=null && !userName.getValue().isEmpty() &&
        password.getValue()!=null && !password.getValue().isEmpty() ) {
            getActivity().setValue("DashBoard");
        }
        else {
            if(userName.getValue()==null || userName.getValue().trim().equals(""))
            {
                userNameError.setValue("Email Cannot Be Empty");

            }
             if(password.getValue()==null || password.getValue().trim().equals(""))
            {
                passwordError.setValue("Password Cannot Be Empty");
            }

        }
    }

    public void signUpOnClick()
    {
        getActivity().setValue("SignUp");
    }

    public void forgotPasswordOnClick()
    {
        getActivity().setValue("ForgotPwd");
    }

    public TextWatcher unTextWatcher=new TextWatcher() {

        private final String TAG = "in-un";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.e(TAG, "beforeTextChanged: ");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.e(TAG, "onTextChanged: ");
            userNameError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.e(TAG, "afterTextChanged: ");

        }
    };

    public TextWatcher pwdTextWatcher=new TextWatcher() {
        private final String TAG = "in-pwd";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            passwordError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onSuccess(String url, String jsonObject) {

    }

    @Override
    public void onError(String url, String errorCode) {

    }
}
