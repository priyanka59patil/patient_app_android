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

import com.werq.patient.Factory.LoginVmProviderFactory;
import com.werq.patient.viewmodel.LoginViewModel;
import com.werq.patient.R;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityLoginBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.luongvo.widget.iosswitchview.SwitchView;

;

public class LoginActivity extends BaseActivity {



    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.swRememberMe)
    SwitchView swRememberMe;

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
        mContext=this;
        loginViewModel = ViewModelProviders.of(this,new LoginVmProviderFactory(mContext)).get(LoginViewModel.class);
        setViewModel(loginViewModel);
        activityLoginBinding.setLoginViewModel(loginViewModel);

        ButterKnife.bind(this);
        toolbar.setTitle("Log In");

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
