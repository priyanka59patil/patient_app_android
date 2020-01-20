package com.werq.patient.applicationServices;

import android.app.Service;
import android.content.Intent;
import android.os.Message;

public abstract class AbstractService extends Service {

    protected final String TAG = this.getClass().getName();


    @Override
    public void onCreate() {
        super.onCreate();
        onStartService();

        //Log.(TAG, "onCreate(): Service Started.");
    }

    @Override
    public final int onStartCommand(Intent intent, int flags, int startId) {
        //Log.(TAG, "onStarCommand(): Received id " + startId + ": " + intent);

        return START_STICKY; // run until explicitly stopped.
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.(TAG, "Service Stopped.");
    }

    public abstract void onStartService();

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }


}
