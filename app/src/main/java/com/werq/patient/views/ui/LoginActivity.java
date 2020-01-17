package com.werq.patient.views.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bugsee.library.Bugsee;
import com.scottyab.aescrypt.AESCrypt;
import com.werq.patient.BR;
import com.werq.patient.BuildConfig;
import com.werq.patient.Factory.LoginVmProviderFactory;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.base.CustomBaseActivity;
import com.werq.patient.databinding.ActivityLoginBinding;
import com.werq.patient.viewmodel.LoginViewModel;

import java.security.GeneralSecurityException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.luongvo.widget.iosswitchview.SwitchView;


public class LoginActivity extends CustomBaseActivity<ActivityLoginBinding,LoginViewModel> {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btLogin)
    Button loginButton;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    @BindView(R.id.swRememberMe)
    SwitchView swRememberMe;

    Context mContext;

    LoginViewModel loginViewModel;
    @BindView(R.id.tvAppVersion)
    TextView tvAppVersion;

    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etUsername)
    EditText etUsername;

    private ActivityLoginBinding activityLoginBinding;

    ProgressDialog progressDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        loginViewModel = ViewModelProviders.of(this, new LoginVmProviderFactory(this,getAuthToken())).get(LoginViewModel.class);
        return loginViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  setContentView(R.layout.activity_login);
        initBinding();

    }

    private void initBinding() {
        //activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding=getViewDataBinding();
        activityLoginBinding.setLifecycleOwner(this);
        mContext = this;
        //setBaseViewModel(loginViewModel);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        ButterKnife.bind(this);
        loadingView.setIndeterminateDrawable(fadingCircle);
        toolbar.setTitle("Log In");
        loginViewModel.setSessionManager(SessionManager.getSessionManager(mContext));
        swRememberMe.setChecked(SessionManager.getSessionManager(mContext).isRememberUsername());
        loginViewModel.getRememberMe().setValue(SessionManager.getSessionManager(mContext).isRememberUsername());

        tvAppVersion.setText("v - " + BuildConfig.VERSION_NAME);
        initBugsee();


        swRememberMe.setOnCheckedChangeListener(new SwitchView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchView switchView, boolean b) {
                loginViewModel.getRememberMe().setValue(b);
            }
        });

        loginViewModel.getLoading().observe(this, aBoolean -> {
           /* try{
                if(aBoolean ){
                    if( progressDialog!=null && !progressDialog.isShowing()){

                        progressDialog.show();

                    }else {
                        progressDialog=Helper.createProgressDialog(mContext);
                    }
                }
                else {
                    if(progressDialog!=null && progressDialog.isShowing()){
                        progressDialog.hide();
                    }
                }
            }catch (Exception e){
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.hide();
                }
            }*/

            if (aBoolean) {
                showProgressBar(loadingView);
                loadingView.bringToFront();
            } else {
                hideProgressBar(loadingView);
            }

        });


        loginViewModel.getNextActivity().observe(this, s -> {
            if (s != null) {

                switch (s) {

                    case "DashBoard":
                        startActivity(new Intent(mContext, BottomTabActivity.class));
                        finish();
                        break;

                    case "SetNewPassword":

                        Intent i = new Intent(mContext, SetNewPawsswordActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("userName", loginViewModel.getUserName().getValue());
                        bundle.putString("currentPassword", etPassword.getText().toString());
                        bundle.putBoolean("rememberMe",loginViewModel.getRememberMe().getValue());
                        i.putExtras(bundle);
                        startActivity(i);
                        finish();

                        break;

                }
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
       /* if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.hide();
        }*/
        loadingView.setVisibility(View.GONE);
        loginViewModel.getNextActivity().setValue(null);

    }

    private void initBugsee() {

        if (BuildConfig.BUILD_TYPE.equals("dev") || BuildConfig.BUILD_TYPE.equals("demo")) {
            HashMap<String, Object> options1 = new HashMap<>();
            options1.put(Bugsee.Option.VideoScale, 0.25);
            options1.put(Bugsee.Option.NotificationBarTrigger, true);
            options1.put(Bugsee.Option.ShakeToTrigger, true);
            options1.put(Bugsee.Option.ReportPrioritySelector, true);
            Bugsee.launch(this, "d801e4ee-338e-4595-955a-0b8608429dad", options1);
            Bugsee.setAttribute("Env", "Android-" + BuildConfig.BUILD_TYPE);
            try {

                String uNameAfterDecrypt = AESCrypt.decrypt("Asdrwsd", SessionManager.getSessionManager(mContext).getRem_username());
                Bugsee.setAttribute("UserName", SessionManager.getSessionManager(mContext).getRem_username());

            } catch (GeneralSecurityException e) {

                Helper.setExceptionLog("GeneralSecurityException", e);
            }

        }
    }

    @OnClick(R.id.tvForgotPassword)
    public void onViewClicked() {

        startActivity(new Intent(mContext, ForgotPasswordActivity.class));

    }


}
