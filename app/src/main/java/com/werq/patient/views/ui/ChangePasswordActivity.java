package com.werq.patient.views.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.R;
import com.werq.patient.views.ui.Fragments.ChangePasswordFragment;
import com.werq.patient.views.ui.Fragments.EnterNewPassword;
import com.werq.patient.views.ui.Fragments.RepeateFragmentFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

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
    @BindView(R.id.btSubmit)
    Button btSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change Password");
        ChangePasswordFragment fra = new ChangePasswordFragment();
        addFragment(fra);

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

    ;

    public void addFragment(Fragment fra) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.maincontainer, fra);
        transaction.commitNow();
    }

    @OnClick(R.id.btSubmit)
    public void onViewClicked() {
    }
}
