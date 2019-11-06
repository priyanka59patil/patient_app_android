package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.werq.patient.databinding.ActivitySignUpBinding;
import com.werq.patient.viewmodel.SignUpViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.Tv_already_have_account)
    TextView TvAlreadyHaveAccount;

    @BindView(R.id.etPin1)
    EditText etPin1;
    @BindView(R.id.etPin2)
    EditText etPin2;
    @BindView(R.id.etPin3)
    EditText etPin3;
    @BindView(R.id.etPin4)
    EditText etPin4;
    @BindView(R.id.etPin5)
    EditText etPin5;
    @BindView(R.id.etPin6)
    EditText etPin6;

    Context mContext;
    SignUpViewModel signUpViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_up);
        initilizevariables();
    }

    private void initilizevariables() {
        ActivitySignUpBinding activitySignUpBinding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        activitySignUpBinding.setLifecycleOwner(this);
        signUpViewModel= ViewModelProviders.of(this).get(SignUpViewModel.class);
        activitySignUpBinding.setSignupViewModel(signUpViewModel);
        mContext = this;
        ButterKnife.bind(this);
        setToolbarTitle(toolbar,"signUp");

        signUpViewModel.getOpenActivitywithBundle().observe(this, new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {
                if(bundle!=null){
                    Intent  intent=new Intent(mContext,VerifyIdentity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();



        signUpViewModel.getEtFocus().observe(this,s -> {
            if(s!=null)
            {
                switch (s)
                {
                    case "pin1":
                            etPin1.requestFocus();
                        break;
                    case "pin2":
                            etPin2.requestFocus();
                        break;
                    case "pin3":
                            etPin3.requestFocus();
                        break;
                    case "pin4":
                            etPin4.requestFocus();
                        break;
                    case "pin5":
                        etPin5.requestFocus();
                        break;
                    case "pin6":
                        etPin6.requestFocus();
                        break;
                }
            }
        });


    }



}
