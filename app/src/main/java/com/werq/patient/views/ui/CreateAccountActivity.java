package com.werq.patient.views.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.Utils.Helper;
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
    @BindView(R.id.btSignUp)
    Button btSignUp;
    SignUpViewModel caViewModel;
    Context mContext;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    Sprite fadingCircle;
    AlertDialog.Builder alertDialog;
    AlertDialog.Builder postAlertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_account);
        initBinding();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create an Account");

        alertDialog = new AlertDialog.Builder(this);
        caViewModel.getIsSuccessfull().observe(this,aBoolean -> {
            if(aBoolean){
                postAlertDialog = new AlertDialog.Builder(this);

                postAlertDialog.setMessage("Please check your email for temporary password ");
                postAlertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                startActivity(new Intent(mContext,LoginActivity.class));
                                finish();
                            }
                        });
                postAlertDialog.show();
            }
        });


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

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Helper.hasNetworkConnection(mContext)){
                    if(caViewModel.signUpDataValidate()){
                            showPreAlert();
                    }

                }else {
                    caViewModel.getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
                }
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

    public void showPreAlert(){
        alertDialog.setMessage("Would you like to continue if you entered email id is correct?");
        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        caViewModel.signUpOnClick();
                    }
                });
        alertDialog.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                caViewModel.getUserName().setValue(null);
                caViewModel.getUserNameError().setValue(null);

            }
        });
        alertDialog.show();
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
