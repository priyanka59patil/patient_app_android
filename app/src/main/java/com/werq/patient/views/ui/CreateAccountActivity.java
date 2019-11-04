package com.werq.patient.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.werq.patient.viewmodel.CreateAccountViewModel;
import com.werq.patient.R;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityCreateAccountBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvCreateAccount)
    TextView tvCreateAccount;
    CreateAccountViewModel caViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_account);
        initBinding();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create an Account");


    }

    private void initBinding() {
        ActivityCreateAccountBinding createAccountBinding= DataBindingUtil.setContentView(this,R.layout.activity_create_account);
        createAccountBinding.setLifecycleOwner(this);
        caViewModel= ViewModelProviders.of(this).get(CreateAccountViewModel.class);
        createAccountBinding.setCaViewModel(caViewModel);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        caViewModel.getActivity().observe(this,s -> {
            if(s!=null)
            {
                if(s.equals("FingerPrintActivity"))
                {
                    startActivity(new Intent(this, FingerPrintActivity.class));
                }
            }
        });
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
