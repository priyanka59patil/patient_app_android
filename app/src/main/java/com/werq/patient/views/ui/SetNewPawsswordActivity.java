package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivitySetNewPawsswordBinding;
import com.werq.patient.service.model.ResponcejsonPojo.SignUpData;
import com.werq.patient.viewmodel.SetNewPasswordViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetNewPawsswordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etNewPwd)
    TextInputEditText etNewPwd;
    @BindView(R.id.tilNewPwd)
    TextInputLayout tilNewPwd;
    @BindView(R.id.etRenteredPwd)
    TextInputEditText etRenteredPwd;
    @BindView(R.id.tilReenterPwd)
    TextInputLayout tilReenterPwd;

    ActivitySetNewPawsswordBinding activityBinding;
    SetNewPasswordViewModel viewModel;
    Context mContext;

    Intent intent;
    SignUpData signUpData;
    String currentPassword;
    @BindView(R.id.btUpdate)
    Button btUpdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        if (activityBinding == null) {
            activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_set_new_pawssword);
        }
        viewModel = ViewModelProviders.of(this).get(SetNewPasswordViewModel.class);
        activityBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        activityBinding.setSetNewPasswordVm(viewModel);
        viewModel.setmContext(mContext);
        viewModel.setSessionManager(SessionManager.getSessionManager(mContext));
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.set_new_password));
        intent=getIntent();
        getIntentData();

        viewModel.getNextActivity().observe(this,s -> {
            if(!TextUtils.isEmpty(s) && s.equals("DashBoard")){
                startActivity(new Intent(mContext, BottomTabActivity.class));
                finish();
            }
        });


    }

    @Override
    public Intent getIntent() {
        return super.getIntent();

    }


    public void getIntentData() {

        if (intent != null) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Helper.setLog("SNP-userName",bundle.getString("userName"));
                Helper.setLog("SNP-currentPassword",bundle.getString("currentPassword"));
                Helper.setLog("SNP-rememberMe",bundle.getBoolean("rememberMe")+"");

                viewModel.getUserName().setValue(bundle.getString("userName"));
                viewModel.getCurrentPassword().setValue(bundle.getString("currentPassword"));
                viewModel.getRememberMe().setValue(bundle.getBoolean("rememberMe",false));
            }
        }

    }

    @OnClick(R.id.btUpdate)
    public void onViewClicked() {

        if (Helper.hasNetworkConnection(mContext)) {

            viewModel.updateOnClick();

        }else {
            viewModel.getToast().setValue(getResources().getString(R.string.no_network_conection));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.getNextActivity().setValue(null);
    }
}
