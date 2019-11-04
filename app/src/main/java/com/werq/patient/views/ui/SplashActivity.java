package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.bugsee.library.Bugsee;
import com.werq.patient.BuildConfig;
import com.werq.patient.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageView imageview;
Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mContext=this;
        initBugsee();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }



        }, 2000);



    }

    private void initBugsee() {
        HashMap<String, Object> options1 = new HashMap<>();
        options1.put(Bugsee.Option.ReportPrioritySelector, true);
        Bugsee.launch(this, "d801e4ee-338e-4595-955a-0b8608429dad", options1);
//            }
        Bugsee.setAttribute("Env", "Android-" + BuildConfig.BUILD_TYPE);
    }

}
