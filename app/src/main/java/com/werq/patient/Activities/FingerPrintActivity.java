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
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.werq.patient.Models.viewModel.FingerPrintViewModel;
import com.werq.patient.R;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityFingerPrintBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FingerPrintActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.tvFingerPrint)
    TextView tvFingerPrint;
    Context mContext;
    FingerPrintViewModel fingerPrintViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);

        initializeVariables();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fingerprint Login");

    }

    private void initializeVariables() {
        ActivityFingerPrintBinding activityFPBinding= DataBindingUtil.setContentView(this,R.layout.activity_finger_print);
        activityFPBinding.setLifecycleOwner(this);
        fingerPrintViewModel= ViewModelProviders.of(this).get(FingerPrintViewModel.class);
        activityFPBinding.setFpViewModel(fingerPrintViewModel);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mContext=this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fingerPrintViewModel.getActivity().observe(this,s -> {
            if(s!=null)
            {
                switch (s)
                {
                    case "BottomActivity":
                        startActivity(new Intent(mContext,BottomTabActivity.class));
                        break;

                }
            }
        });
    }

    /*@OnClick({R.id.btNo, R.id.BtYes})
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
    }*/
}
