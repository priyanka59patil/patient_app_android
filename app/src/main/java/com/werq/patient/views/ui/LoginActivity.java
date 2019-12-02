package com.werq.patient.views.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.werq.patient.Factory.LoginVmProviderFactory;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.viewmodel.LoginViewModel;
import com.werq.patient.R;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityLoginBinding;

import java.security.spec.ECField;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.luongvo.widget.iosswitchview.SwitchView;


public class LoginActivity extends BaseActivity {



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
    private ActivityLoginBinding activityLoginBinding;

    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  setContentView(R.layout.activity_login);
        initBinding();

    }

    private void initBinding() {
        activityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner(this);
        mContext=this;
        loginViewModel = ViewModelProviders.of(this,new LoginVmProviderFactory(mContext)).get(LoginViewModel.class);
        setBaseViewModel(loginViewModel);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        ButterKnife.bind(this);
        loadingView.setIndeterminateDrawable(fadingCircle);
        toolbar.setTitle("Log In");
        loginViewModel.setSessionManager( SessionManager.getSessionManager(mContext));

    }

    @Override
    protected void onResume() {
        super.onResume();

        swRememberMe.setOnCheckedChangeListener(new SwitchView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchView switchView, boolean b) {
                loginViewModel.getRememberMe().setValue(b);
            }
        });

        loginViewModel.getRememberMe().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                swRememberMe.setChecked(aBoolean);
            }
        });

        loginViewModel.getLoading().observe(this,aBoolean -> {
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

            if(aBoolean ){
                showProgressBar(loadingView);
                loadingView.bringToFront();
            }
            else {
                hideProgressBar(loadingView);
            }

        });


        /*loginViewModel.getActivity().observe(this,s ->  {
            if(s!=null){

                switch (s){

                    case "DashBoard": startActivity(new Intent(mContext,BottomTabActivity.class));
                        break;

                    case "ForgotPwd":startActivity(new Intent(mContext,ForgotPasswordActivity.class));
                        break;

                    case "SignUp":startActivity(new Intent(mContext,SignUpActivity.class));
                        break;

                }
            }

        });*/

    }

    @Override
    protected void onStop() {
        super.onStop();
       /* if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.hide();
        }*/
        loadingView.setVisibility(View.GONE);

    }
}
