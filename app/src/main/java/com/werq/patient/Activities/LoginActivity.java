package com.werq.patient.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.swRememberMe)
    Switch swRememberMe;
    @BindView(R.id.swTouchId)
    Switch swTouchId;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.tvtextSignUp)
    TextView tvtextSignUp;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;

    //context
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        intilizevariables();
        Helper.setToolbar(getSupportActionBar(),"Log In");

    }

    private void intilizevariables() {
        mContext=this;
    }

    @OnClick({R.id.tvForgotPassword, R.id.tvSignUp,R.id.btLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                startActivity(new Intent(mContext,ForgotPasswordActivity.class));
                break;
            case R.id.tvSignUp:
                startActivity(new Intent(mContext,SignUpActivity.class));
                break;
            case R.id.btLogin:
                startActivity(new Intent(mContext,BottomTabActivity.class));
                finish();
                break;
        }
    }




}
