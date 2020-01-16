package com.werq.patient.views.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.Factory.ChangePasswordVmFactory;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityChangePasswordBinding;
import com.werq.patient.viewmodel.ChangePasswordViewModel;
import com.werq.patient.views.ui.Fragments.EnterNewPassword;
import com.werq.patient.views.ui.Fragments.RepeateFragmentFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etCurrentPwd)
    TextInputEditText etCurrentPwd;
    @BindView(R.id.tilCurrentPwd)
    TextInputLayout tilCurrentPwd;
    @BindView(R.id.etNewPwd)
    TextInputEditText etNewPwd;
    @BindView(R.id.tilNewPwd)
    TextInputLayout tilNewPwd;
    @BindView(R.id.etRenteredPwd)
    TextInputEditText etRenteredPwd;
    @BindView(R.id.tilReenterPwd)
    TextInputLayout tilReenterPwd;

    ActivityChangePasswordBinding activityBinding;
    ChangePasswordViewModel viewModel;
    Context mContext;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_change_password);
        mContext = this;
        if (activityBinding == null) {
            activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        }
        viewModel = ViewModelProviders.of(this,new ChangePasswordVmFactory(getAuthToken())).get(ChangePasswordViewModel.class);
        activityBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        activityBinding.setCpViewModel(viewModel);
        viewModel.setmContext(mContext);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.page_change_password));
        /*ChangePasswordFragment fra = new ChangePasswordFragment();
        addFragment(fra);*/

        viewModel.getChangePasswordStatus().observe(this,aBoolean -> {
            if(aBoolean){
                finish();
            }
        });

        /*etNewPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {


                if(!b){

                    Helper.setLog("onFocusChangee","lostFocus");

                    if(!TextUtils.isEmpty(etNewPwd.getText().toString())
                            && !Helper.isValidPassword(etNewPwd.getText().toString())){


                        viewModel.getToast().setValue(getResources().getString(R.string.password_pattern_msg));

                        viewModel.setValidPassword(false);


                    }
                    else {
                        viewModel.setValidPassword(true);
                    }
                }
            }
        });*/

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String action) {
        switch (action) {
            case "New Password":
                EnterNewPassword enterNewPassword = new EnterNewPassword();
                addFragment(enterNewPassword);
                break;
            case "Change Password":
                RepeateFragmentFragment repeateFragmentFragment = new RepeateFragmentFragment();
                addFragment(repeateFragmentFragment);
                break;
        }

    }


    public void addFragment(Fragment fra) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.maincontainer, fra);
        transaction.commitNow();
    }

    @OnClick(R.id.btUpdate)
    public void onViewClicked() {

        if(Helper.hasNetworkConnection(mContext)){
            viewModel.updateOnClick();
        }else {
            viewModel.getToast().setValue(getResources().getString(R.string.no_network_conection));
        }
    }
}
