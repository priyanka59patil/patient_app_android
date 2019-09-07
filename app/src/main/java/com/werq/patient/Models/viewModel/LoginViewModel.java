package com.werq.patient.Models.viewModel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    MutableLiveData<String> userName=new MutableLiveData<>();
    MutableLiveData<String> password=new MutableLiveData<>();
    MutableLiveData<String> userNameError=new MutableLiveData<>();
    MutableLiveData<String> passwordError=new MutableLiveData<>();
    MutableLiveData<String> openActivity=new MutableLiveData<>();


    public LoginViewModel() {

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
            openActivity.setValue("DashBoard");
        }
        else {
            if(userName.getValue()==null || userName.getValue().trim().equals(""))
            {
                userNameError.setValue("Email Cannot Be Empty");

               // passwordError.setValue(null);
            }
             if(password.getValue()==null || password.getValue().trim().equals(""))
            {
               // userNameError.setValue(null);
                passwordError.setValue("Password Cannot Be Empty");
            }

        }
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
            Log.e(TAG, "beforeTextChanged: ");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.e(TAG, "onTextChanged: ");
            passwordError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.e(TAG, "afterTextChanged: ");

        }
    };

}
