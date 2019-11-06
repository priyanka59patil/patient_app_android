package com.werq.patient.viewmodel;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.scottyab.aescrypt.AESCrypt;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.repository.LoginRepository;
import com.werq.patient.service.repository.SignUpRepository;

import java.security.GeneralSecurityException;

import okhttp3.internal.http2.ErrorCode;

public class LoginViewModel extends BaseViewModel {

    MutableLiveData<String> userName;
    MutableLiveData<String> password;
    MutableLiveData<String> userNameError;
    MutableLiveData<String> passwordError;
    MutableLiveData<Boolean> rememberMe;


    LoginRepository loginRepository;
    ApiResponce apiResponce=this;

    SessionManager sessionManager,userRememberPref;

    public LoginViewModel() {
    }

    public LoginViewModel(Context context) {
        super();

        userName=new MutableLiveData<>();
        password=new MutableLiveData<>();
        userNameError=new MutableLiveData<>();
        passwordError=new MutableLiveData<>();
        loginRepository=new LoginRepository();
        rememberMe=new MutableLiveData<>();

        Helper.setLog("context",context+"");
        sessionManager = new SessionManager(context);
        userRememberPref = new SessionManager(context, "");

        if(userRememberPref.isRememberUsername()) {
            rememberMe.setValue(true);
            setPrefilledUsername();
        }

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

    public MutableLiveData<Boolean> getRememberMe() {
        return rememberMe;
    }

    public void loginOnClick()
    {
        Log.e( "loginOnClick: ", userName.getValue()+" "+password.getValue() );
        if(userName.getValue()!=null && !userName.getValue().isEmpty() &&
        password.getValue()!=null && !password.getValue().isEmpty() ) {

            UserCredential userCredential=new UserCredential();
            userCredential.setUsername(userName.getValue());
            userCredential.setPassword(password.getValue());
            getLoading().setValue(true);
            loginRepository.signIn(userCredential,getToast(),apiResponce,"SIGNIN");

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
            //Log.e(TAG, "beforeTextChanged: ");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Log.e(TAG, "onTextChanged: ");
            userNameError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
           // Log.e(TAG, "afterTextChanged: ");

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
    public void onSuccess(String url, Object object) {


        getLoading().setValue(false);
        if(url.equals("SIGNIN")){

            if(object!=null)
            {
                LoginResponce loginResponce=(LoginResponce) object;

                long expiryTimestamp = 0;

                try{
                    expiryTimestamp=Long.parseLong(loginResponce.getData().getAuthExpiryTime());

                }catch (Exception e){

                }
                sessionManager.clear();
                sessionManager.creteUserSession(loginResponce.getData().getAuthToken(),
                        loginResponce.getData().getRefreshToken(),
                        loginResponce.getData().getUser().getUserName(),
                        loginResponce.getData().getUser().getID(),
                        expiryTimestamp);

                if(rememberMe.getValue()){

                    String encryptedUName = "";
                    String encryptedPass = "";
                    try {
                        encryptedUName = AESCrypt.encrypt("Asdrwsd", userName.getValue().trim().toLowerCase());
                        encryptedPass = AESCrypt.encrypt("Asdrwsd", password.getValue());
                    } catch (GeneralSecurityException e) {
                        //handle error
                        e.printStackTrace();
                    }
                    userRememberPref.setRememberUsername(true, encryptedUName);
                    userRememberPref.setRememberPassword(true, encryptedPass);
                }
            }

            getActivity().setValue("DashBoard");
        }

    }

    @Override
    public void onError(String url, String errorCode) {

        getLoading().setValue(false);

    }

    private void setPrefilledUsername() {
        try {
            String uNameAfterDecrypt = AESCrypt.decrypt("Asdrwsd", userRememberPref.getRem_username());
               Helper.setLog("decrypUname", uNameAfterDecrypt);
            userName.setValue(uNameAfterDecrypt);
        } catch (GeneralSecurityException e) {
            //handle error - could be due to incorrect password or tampered encryptedMsg
            e.printStackTrace();
        }
    }

}
