package com.werq.patient.applicationServices;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.werq.patient.Utils.Helper;

public class TimerService extends Service {
    private final static String TAG = "TimerService";

    CountDownTimer cdt = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Helper.setLog(TAG, "Starting timer...");

        cdt = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Helper.setLog(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Helper.setLog(TAG, "Timer finished");
            }
        };

        cdt.start();
    }

    @Override
    public void onDestroy() {

        cdt.cancel();
        Helper.setLog(TAG, "Timer cancelled");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
