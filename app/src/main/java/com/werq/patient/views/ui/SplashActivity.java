package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.bugsee.library.Bugsee;
import com.werq.patient.BuildConfig;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageView imageview;
    Context mContext;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mContext=this;
        sessionManager=SessionManager.getSessionManager(mContext);

        Helper.setLog("PackageName",getApplicationContext().getPackageName());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               launchNextScreen();
            }

        }, 2000);


    }

    private void launchNextScreen() {
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(mContext, BottomTabActivity.class));
            finish();

        } else {
            startActivity(new Intent(mContext, LoginActivity.class));
            finish();

        }
    }


}
