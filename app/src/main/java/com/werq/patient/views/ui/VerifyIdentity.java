package com.werq.patient.views.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.viewmodel.SignUpViewModel;
import com.werq.patient.viewmodel.VerifyIdentityViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.Utils.EditTextUtils;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityVerifyIdentityBinding;

import java.text.ParseException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyIdentity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Context mContext;
    Calendar myCalendar;
    @BindView(R.id.spDob)
    TextInputLayout spDob;

    SignUpViewModel viViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_verify_identity);

        initlizeVariables();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Verify Your Identity");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            try {
                spDob.getEditText().setText(DateHelper.dateFormatmmddyyyy(myCalendar.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initlizeVariables() {
        ActivityVerifyIdentityBinding activityVIBinding= DataBindingUtil.setContentView(this,R.layout.activity_verify_identity);
        activityVIBinding.setLifecycleOwner(this);
        viViewModel= ViewModelProviders.of(this).get(SignUpViewModel.class);
        setBaseViewModel(viViewModel);
        activityVIBinding.setViViewModel(viViewModel);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mContext = this;
        myCalendar = Calendar.getInstance();
        String invitationcode=getIntent().getExtras().getString("invitationCode");
        viViewModel.setInvitaionCode(invitationcode);
        viViewModel.getOpenActivitywithBundle().observe(this, new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {
                if(bundle!=null){
                    Intent intent=new Intent(mContext,CreateAccountActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @OnClick({R.id.etspDob, })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.etspDob:
                new DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            /*case R.id.button:
                if (!validation())
                    startActivity(new Intent(mContext, CreateAccountActivity.class));
                break;*/
        }
    }

    private boolean validation() {
        boolean isInvalid = false;

        isInvalid = EditTextUtils.isEmpty(spDob, getResources().getString(R.string.empty_dob));

        return isInvalid;
    }
}
