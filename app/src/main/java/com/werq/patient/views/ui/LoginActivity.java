package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.werq.patient.viewmodel.LoginViewModel;
import com.werq.patient.R;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityLoginBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

;

public class LoginActivity extends BaseActivity {



    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    Context mContext;

    LoginViewModel loginViewModel;
    private ActivityLoginBinding activityLoginBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  setContentView(R.layout.activity_login);
        initBinding();

    }

    private void initBinding() {
         activityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        setViewModel(loginViewModel);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        mContext=this;
        ButterKnife.bind(this);
        toolbar.setTitle("Log In");

    }

    @Override
    protected void onResume() {
        super.onResume();

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

        });
*/

    }




}
