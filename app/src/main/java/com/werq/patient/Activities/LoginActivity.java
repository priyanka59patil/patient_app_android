package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.Models.viewModel.LoginViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.EditTextUtils;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityLoginBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.luongvo.widget.iosswitchview.SwitchView;

;

public class LoginActivity extends BaseActivity {



    @BindView(R.id.txtInputUserName)
    TextInputLayout txtInputUserName;
    @BindView(R.id.txtInputPassword)
    TextInputLayout txtInputPassword;
    Context mContext;

    LoginViewModel loginViewModel;
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_login);
        initBinding();


    }

    private void initBinding() {
         activityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        mContext=this;





    }

    @Override
    protected void onResume() {
        super.onResume();

        loginViewModel.getOpenActivity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String open) {
                switch (open){

                    case "DashBoard": startActivity(new Intent(mContext,BottomTabActivity.class));
                        break;

                    case "ForgotPwd":startActivity(new Intent(mContext,ForgotPasswordActivity.class));
                        break;

                    case "SignUp":startActivity(new Intent(mContext,SignUpActivity.class));
                        break;

                }

            }
        });




    }




}
