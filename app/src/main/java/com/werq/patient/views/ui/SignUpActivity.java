package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.werq.patient.R;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivitySignUpBinding;
import com.werq.patient.viewmodel.SignUpViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    /*@BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;*/
    @BindView(R.id.Tv_already_have_account)
    TextView TvAlreadyHaveAccount;
    @BindView(R.id.etOTP)
    OtpTextView otpTextView;

    @BindView(R.id.btNext)
    Button btNext;

   /* @BindView(R.id.etPin1)
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
    EditText etPin6;*/

    Context mContext;
    SignUpViewModel signUpViewModel;
    @BindView(R.id.tvOtpError)
    TextView tvOtpError;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_up);
        initilizevariables();
    }

    private void initilizevariables() {
        ActivitySignUpBinding activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        activitySignUpBinding.setLifecycleOwner(this);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        setBaseViewModel(signUpViewModel);
        activitySignUpBinding.setSignupViewModel(signUpViewModel);

        mContext = this;
        ButterKnife.bind(this);

        setToolbarTitleWithBack(toolbar, "Sign Up");


        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (otpTextView.getOtp() != null && otpTextView.getOtp().length() == 6) {
                    Bundle bundle = new Bundle();
                    bundle.putString("invitationCode", otpTextView.getOtp());
                    Intent intent = new Intent(mContext, VerifyIdentity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    otpTextView.showError();
                    tvOtpError.setVisibility(View.VISIBLE);
                }
            }
        });

        signUpViewModel.getOpenActivitywithBundle().observe(this, new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {
                if (bundle != null) {


                }
            }
        });


        otpTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(otpTextView.getOtp() != null && otpTextView.getOtp().length() == 1){
                    tvOtpError.setVisibility(View.GONE);
                }
                return false;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
                tvOtpError.setVisibility(View.GONE);
            }
            @Override
            public void onOTPComplete(String otp) {
                // fired when user has entered the OTP fully.
            }
        });

       /* otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
                Helper.setLog("setOtpListener","onInteractionListener");
            }
            @Override
            public void onOTPComplete(String otp) {
                // fired when user has entered the OTP fully.
            }
        });
*/


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                signUpViewModel.getActivity().setValue("Login");
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
