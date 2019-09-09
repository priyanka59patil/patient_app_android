package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.Models.viewModel.ForgotPassswordViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.EditTextUtils;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityForgotPasswordBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTextpassword)
    TextView tvTextpassword;
    @BindView(R.id.btForgotPassword)
    Button btForgotPassword;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvPasswordHint)
    TextView tvPasswordHint;

    boolean isFromChangePassword;
    Intent intent;
    @BindView(R.id.etemail)
    EditText etemail;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.tilemail)
    TextInputLayout tilemail;

    Context mContext;
    ForgotPassswordViewModel fpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_forgot_password);
        initBinding();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");
        initializeVariables();
        getIntentData();
        setView();

    }

    private void initBinding() {
        ActivityForgotPasswordBinding activityFPBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        activityFPBinding.setLifecycleOwner(this);
        fpViewModel= ViewModelProviders.of(this).get(ForgotPassswordViewModel.class);
        activityFPBinding.setFpViewModel(fpViewModel);
        mContext=this;
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        fpViewModel.getActivity().observe(this,s -> {
            if(s!=null && s.equals("Login")) {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        });
    }

    private void setView() {
        if (isFromChangePassword) {
            tvTextpassword.setVisibility(View.GONE);
            getActionBarTitle(getResources().getString(R.string.label_change_password));
            etPassword.setVisibility(View.VISIBLE);
            etemail.setVisibility(View.GONE);

        } else {
            tvTextpassword.setVisibility(View.VISIBLE);
            getActionBarTitle(getResources().getString(R.string.label_forgot_password));
            etPassword.setVisibility(View.GONE);
            etemail.setVisibility(View.VISIBLE);
        }
    }

    private void getActionBarTitle(String actionbarTitle) {
        getSupportActionBar().setTitle(actionbarTitle);
    }

    private void initializeVariables() {
        intent = getIntent();
    }

    private void getIntentData() {
        isFromChangePassword = intent.getBooleanExtra(getResources().getString(R.string.intent_change_password), false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
