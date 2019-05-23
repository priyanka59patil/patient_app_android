package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.werq.patient.R;

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
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializevariable();
        getSupportActionBar().setTitle("Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_close_white_24dp));


    }

    private void initializevariable() {
        mContext=this;

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

    @OnClick({R.id.tvPassword, R.id.tvChangePassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvPassword:
                break;
            case R.id.tvChangePassword:
                Intent changePasswordIntent=new Intent(mContext,ForgotPasswordActivity.class);
                changePasswordIntent.putExtra(getResources().getString(R.string.intent_change_password),true);
                startActivity(changePasswordIntent);
                break;
        }
    }
}
