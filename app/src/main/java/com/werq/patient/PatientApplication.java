package com.werq.patient;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

public class PatientApplication extends MultiDexApplication {
   public static PatientApplication ourInstance=new PatientApplication();
    private static Context context;
    public static PatientApplication getInstance() {

        return ourInstance;
    }
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PatientApplication.context = getApplicationContext();

    }
    }
