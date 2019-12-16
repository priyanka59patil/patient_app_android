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

    SessionManager sessionManager;
    Context mContext;

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
        this.mContext=context;
        Helper.setLog("context",context+"");
        /*sessionManager = new SessionManager(context);
        userRememberPref = new SessionManager(context, "");*/



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
      /*  if(userName.getValue()!=null && !userName.getValue().isEmpty() &&
        password.getValue()!=null && !password.getValue().isEmpty() ) {


            UserCredential userCredential=new UserCredential();
            userCredential.setUsername(userName.getValue());
            userCredential.setPassword(password.getValue());


            if(Helper.hasNetworkConnection(mContext)){

                getLoading().setValue(true);
                loginRepository.signIn(userCredential,getToast(),apiResponce,"SIGNIN");

            }else {
                getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
            }


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

        }*/


        if(validateData()){
            UserCredential userCredential=new UserCredential();
            userCredential.setUsername(userName.getValue());
            userCredential.setPassword(password.getValue());


            if(Helper.hasNetworkConnection(mContext)){

                getLoading().setValue(true);
                loginRepository.signIn(userCredential,getToast(),apiResponce,"SIGNIN");

            }else {
                getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
            }
        }
    }

    public boolean validateData() {
        boolean check = true;

        if(userName.getValue()!=null){

            if (userName.getValue().trim().equals("")) {
                check = false;
                userNameError.setValue("Email Cannot Be Empty");
            }else {
                if(!Helper.isValidEmail(userName.getValue())){
                    check = false;
                    userNameError.setValue("Please enter valid email");
                }
            }

        }
        else {
            check = false;
            userNameError.setValue("Email Cannot Be Empty");
        }

        if(password.getValue()!=null){

            if(password.getValue().trim().equals("")){
                check = false;
                password.setValue("Password can not be empty");
            }

        }else {
            check = false;
            password.setValue("Password can not be empty");
        }

        return check;

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
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userNameError.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

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
    public void onSuccess(String url, String responseJson) {

        Helper.setLog("responseJson",responseJson);

        LoginResponce loginResponce=Helper.getGsonInstance().fromJson(responseJson,LoginResponce.class);

        Helper.setLog("convertedLogin",loginResponce.toString());

        getLoading().setValue(false);
        if(url.equals("SIGNIN")){

            if(loginResponce!=null)
            {

                /*long expiryTimestamp = 0;

                try{
                    expiryTimestamp=Long.parseLong(loginResponce.getData().getAuthExpiryTime());

                }catch (Exception e){

                }*/
                Helper.setLog("dbTimeStamp",loginResponce.getData().getAuthExpiryTime());
                sessionManager.clear();
                long timestamp=Helper.convertTimestamp(loginResponce.getData().getAuthExpiryTime());

                sessionManager.creteUserSession(loginResponce.getData().getAuthToken(),
                        loginResponce.getData().getRefreshToken(),
                        loginResponce.getData().getUser().getUserName(),
                        loginResponce.getData().getUser().getID(),
                        timestamp);

                Helper.setLog("convertedTimeStamp",timestamp+"");

                if(rememberMe.getValue()){

                    String encryptedUName = "";
                    String encryptedPass = "";
                    try {
                        encryptedUName = AESCrypt.encrypt("Asdrwsd", userName.getValue().trim().toLowerCase());
                        encryptedPass = AESCrypt.encrypt("Asdrwsd", password.getValue());
                    } catch (GeneralSecurityException e) {
                        //handle error
                        e.printStackTrace();
                        Helper.setExceptionLog("GeneralSecurityException",e);
                    }
                    sessionManager.setRememberUsername(true, encryptedUName);
                    sessionManager.setRememberPassword(true, encryptedPass);
                }
            }

            getActivity().setValue("DashBoard");
        }

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {

        getLoading().setValue(false);

    }

    @Override
    public void onTokenRefersh(String responseJson) {
        getLoading().setValue(false);
    }

    private void setPrefilledUsername() {
        try {

            String uNameAfterDecrypt = AESCrypt.decrypt("Asdrwsd", sessionManager.getRem_username());
            String pwdAfterDecrypt = AESCrypt.decrypt("Asdrwsd", sessionManager.getRem_password());
               Helper.setLog("decrypUname", uNameAfterDecrypt);
            userName.setValue(uNameAfterDecrypt);
            password.setValue(pwdAfterDecrypt);

        } catch (GeneralSecurityException e) {
            //handle error - could be due to incorrect password or tampered encryptedMsg
            Helper.setExceptionLog("GeneralSecurityException",e);
            e.printStackTrace();
        }
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        if(sessionManager.isRememberUsername()) {
            rememberMe.setValue(true);
            setPrefilledUsername();
        }
    }
}
