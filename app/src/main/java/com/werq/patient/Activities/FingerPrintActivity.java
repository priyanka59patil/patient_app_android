package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FingerPrintActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.btNo)
    Button btNo;
    @BindView(R.id.BtYes)
    Button BtYes;
    @BindView(R.id.tvFingerPrint)
    TextView tvFingerPrint;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializeVariables();

    }

    private void initializeVariables() {
        mContext=this;
    }

    @OnClick({R.id.btNo, R.id.BtYes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btNo:
                sendtoBottomActivity();
                break;
            case R.id.BtYes:
                sendtoBottomActivity();
                break;
        }
    }

    private void sendtoBottomActivity() {
        startActivity(new Intent(mContext,BottomTabActivity.class));
    }
}
