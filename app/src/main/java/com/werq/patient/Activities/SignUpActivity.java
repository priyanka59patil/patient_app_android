package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.werq.patient.Models.viewModel.SignUpViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.EditTextUtils;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivitySignUpBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    Context mContext;
    SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setSupportActionBar(toolbar);
        Helper.setToolbar(getSupportActionBar(), "Sign Up");
    }

    @Override
    protected void onResume() {
        super.onResume();

        signUpViewModel.getActivity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null)
                {
                    switch (s)
                    {
                        case "Login":startActivity(new Intent(mContext, LoginActivity.class));
                            break;

                        case "VerifyIdentity":startActivity(new Intent(mContext, VerifyIdentity.class));
                            finish();
                            break;
                    }
                }

            }
        });

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
                }
            }
        });


    }


  /* private class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.etPin1:
                    if (text.length() == 1)
                        etPin2.requestFocus();
                    break;
                case R.id.etPin2:
                    if (text.length() == 1)
                        etPin3.requestFocus();
                    else if (text.length() == 0)
                        etPin1.requestFocus();
                    break;
                case R.id.etPin3:
                    if (text.length() == 1)
                        etPin4.requestFocus();
                    else if (text.length() == 0)
                        etPin2.requestFocus();
                    break;
                case R.id.etPin4:
                    if (text.length() == 0)
                        etPin3.requestFocus();
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }*/
}
