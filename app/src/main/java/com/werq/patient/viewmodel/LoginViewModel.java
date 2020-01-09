package com.werq.patient.viewmodel;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.scottyab.aescrypt.AESCrypt;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.model.ResponcejsonPojo.SignUpData;
import com.werq.patient.service.repository.LoginRepository;
import com.werq.patient.service.repository.SignUpRepository;

import java.security.GeneralSecurityException;
import java.text.ParseException;

import okhttp3.internal.http2.ErrorCode;

public class LoginViewModel extends BaseViewModel {

    MutableLiveData<String> userName;
    MutableLiveData<String> password;
    MutableLiveData<String> userNameError;
    public TextWatcher unTextWatcher = new TextWatcher() {

        private final String TAG = "in-un";

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userNameError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    MutableLiveData<String> passwordError;
    public TextWatcher pwdTextWatcher = new TextWatcher() {
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
    MutableLiveData<String> nextActivity;
    MutableLiveData<Boolean> rememberMe;
    LoginRepository loginRepository;
    ApiResponce apiResponce = this;
    SessionManager sessionManager;
    Context mContext;

    public LoginViewModel() {
    }

    public LoginViewModel(Context context) {
        super();

        userName = new MutableLiveData<>();
        password = new MutableLiveData<>();
        userNameError = new MutableLiveData<>();
        passwordError = new MutableLiveData<>();
        loginRepository = new LoginRepository();
        rememberMe = new MutableLiveData<>();
        nextActivity = new MutableLiveData<>();
        this.mContext = context;
        /*sessionManager = new SessionManager(context);
        userRememberPref = new SessionManager(context, "");*/


    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getPassword() {
        password.setValue("Test@123");
        return password;
    }

    public MutableLiveData<String> getUserNameError() {
        return userNameError;
    }

    public MutableLiveData<String> getPasswordError() {
        return passwordError;
    }

    public MutableLiveData<Boolean> getRememberMe() {
        return rememberMe;
    }

    public MutableLiveData<String> getNextActivity() {
        return nextActivity;
    }

    public void loginOnClick() {
        Helper.setLog("loginOnClick: ", userName.getValue() + " " + password.getValue());


        if (validateData()) {
            UserCredential userCredential = new UserCredential();
            userCredential.setUsername(userName.getValue());
            userCredential.setPassword(password.getValue());


            if (Helper.hasNetworkConnection(mContext)) {

                getLoading().setValue(true);
                loginRepository.signIn(userCredential, getToast(), apiResponce, "SIGNIN");

            } else {
                getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
            }
        }
    }

    public boolean validateData() {
        boolean check = true;

        if (userName.getValue() != null) {

            if (userName.getValue().trim().equals("")) {
                check = false;
                userNameError.setValue(mContext.getResources().getString(R.string.empty_email));
            } else {
                if (!Helper.isValidEmail(userName.getValue().trim())) {
                    check = false;
                    userNameError.setValue(mContext.getResources().getString(R.string.invalid_email_id));
                }
            }

        } else {
            check = false;
            userNameError.setValue(mContext.getResources().getString(R.string.empty_email));
        }

        if (password.getValue() != null) {

            if (password.getValue().trim().equals("")) {
                check = false;
                passwordError.setValue(mContext.getResources().getString(R.string.empty_password));
            }

        } else {
            check = false;
            passwordError.setValue(mContext.getResources().getString(R.string.empty_password));
        }

        return check;

    }

    public void signUpOnClick() {

        getActivity().setValue("SignUp");
    }

    @Override
    public void onSuccess(String url, String responseJson) {

        LoginResponce loginResponce = Helper.getGsonInstance().fromJson(responseJson, LoginResponce.class);


        getLoading().setValue(false);
        if (url.equals("SIGNIN")) {

            if (loginResponce != null) {

                if (loginResponce.getData().getTempPassChangedFlag()!=0) {

                    sessionManager.clear();
                    long timestamp = 0;
                    try {
                        timestamp = Helper.parseUtcStringToDate(loginResponce.getData().getAuthExpiryTime()).getTime();
                    } catch (ParseException e) {
                        Helper.setExceptionLog("ParseException",e);
                        e.printStackTrace();
                    }

                    sessionManager.creteUserSession(loginResponce.getData().getAuthToken(),
                            loginResponce.getData().getRefreshToken(),
                            loginResponce.getData().getUser().getUserName(),
                            loginResponce.getData().getUser().getID()+"",
                            timestamp);

                    String encryptedUName = "";
                    String encryptedPass = "";
                    try {

                        encryptedUName = AESCrypt.encrypt("Asdrwsd", userName.getValue().trim().toLowerCase());
                        encryptedPass = AESCrypt.encrypt("Asdrwsd", password.getValue().trim());

                    } catch (GeneralSecurityException e) {

                        Helper.setExceptionLog("GeneralSecurityException", e);
                    }

                    //Helper.setLog("set rememberMe",rememberMe.getValue()+"");
                    sessionManager.setRememberUsername(rememberMe.getValue(), encryptedUName);
                    sessionManager.setRememberPassword(false, encryptedPass);
                    nextActivity.setValue("DashBoard");
                } else {
                    //intent to change password activity
                    sessionManager.clear();
                    long timestamp = 0;
                    try {
                        timestamp = Helper.parseUtcStringToDate(loginResponce.getData().getAuthExpiryTime()).getTime();
                    } catch (ParseException e) {
                        Helper.setExceptionLog("ParseException",e);
                        e.printStackTrace();
                    }
                    sessionManager.setAuthToken(loginResponce.getData().getAuthToken(),timestamp);
                    nextActivity.setValue("SetNewPassword");


                }

            }


        }

    }

    @Override
    public void onError(String url, String errorCode, String errorMessage) {

        getLoading().setValue(false);

    }

    @Override
    public void onTokenRefersh(String responseJson) {
        getLoading().setValue(false);
    }

    private void setPrefilledUsername() {
        try {

            String uNameAfterDecrypt = AESCrypt.decrypt("Asdrwsd", sessionManager.getRem_username());
            //String pwdAfterDecrypt = AESCrypt.decrypt("Asdrwsd", sessionManager.getRem_password());
            userName.setValue(uNameAfterDecrypt);
            //password.setValue(pwdAfterDecrypt);

        } catch (GeneralSecurityException e) {
            //handle error - could be due to incorrect password or tampered encryptedMsg
            Helper.setExceptionLog("GeneralSecurityException", e);
            e.printStackTrace();
        }
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        if (sessionManager.isRememberUsername()) {
            setPrefilledUsername();
        }
    }
}
