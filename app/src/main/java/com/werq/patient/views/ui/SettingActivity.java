package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.werq.patient.BuildConfig;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTextTouchId)
    TextView tvTextTouchId;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tvPassword)
    TextView tvPassword;
    @BindView(R.id.tvChangePassword)
    TextView tvChangePassword;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.tv24HoursBefore)
    TextView tv24HoursBefore;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.tv1HourBefore)
    TextView tv1HourBefore;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.tvLogOut)
    TextView tvLogOut;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.tvAppVersion)
    TextView tvAppVersion;
    @BindView(R.id.view6)
    View view6;
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializevariable();
        setToolbar();
        //tvAppVersion.setText(BuildConfig.app_version);


    }

    private void setToolbar() {
        Helper.setToolbarwithCross(getSupportActionBar(), "Setting");
    }

    private void initializevariable() {
        mContext = this;

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

    @OnClick({R.id.tvPassword, R.id.tvChangePassword, R.id.tvLogOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvPassword:
                break;
            case R.id.tvChangePassword:
                startActivity(new Intent(mContext, ChangePasswordActivity.class));
                break;
            case R.id.tvLogOut:

                SessionManager.getSessionManager(mContext).logoutUser(mContext);
                break;
        }
    }


}
