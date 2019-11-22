package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.viewmodel.CreateAccountViewModel;
import com.werq.patient.R;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityCreateAccountBinding;
import com.werq.patient.viewmodel.SignUpViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvCreateAccount)
    TextView tvCreateAccount;
    SignUpViewModel caViewModel;
    Context mContext;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    Sprite fadingCircle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_account);
        initBinding();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create an Account");


    }

    private void initBinding() {
        ActivityCreateAccountBinding createAccountBinding= DataBindingUtil.setContentView(this,R.layout.activity_create_account);
        createAccountBinding.setLifecycleOwner(this);
        caViewModel= ViewModelProviders.of(this).get(SignUpViewModel.class);
        createAccountBinding.setCaViewModel(caViewModel);
        ButterKnife.bind(this);
        setBaseViewModel(caViewModel);
        mContext=this;
        setSupportActionBar(toolbar);
        fadingCircle=new Circle();
        loadingView.setIndeterminateDrawable(fadingCircle);

        String  invitationCode=getIntent().getStringExtra("invitationCode");
        String dob=getIntent().getStringExtra("dob");
        caViewModel.setInvitaionCode(invitationCode);
        caViewModel.setDobData(dob);



    }

    @Override
    protected void onResume() {
        super.onResume();

        caViewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean)
            {
                loadingView.setVisibility(View.VISIBLE);
            }else {
                loadingView.setVisibility(View.GONE);
            }
        });

       /*
        caViewModel.getToast().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

            }
        });*/

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

  /*  @OnClick({R.id.btSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btSignUp:
                if (!Validation())
                    startActivity(new Intent(this, FingerPrintActivity.class));
                break;
        }
    }

    private boolean Validation() {
        boolean isInvalid = false;
        isInvalid = EditTextUtils.isEmpty(txtInputUserName, getResources().getString(R.string.error_email));
        isInvalid = EditTextUtils.isEmpty(txtInputPassword, getResources().getString(R.string.error_password));
        return isInvalid;
    }*/
}
