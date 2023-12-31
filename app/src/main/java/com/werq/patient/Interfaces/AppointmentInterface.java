package com.werq.patient.Interfaces;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.werq.patient.service.model.Files;

import java.util.ArrayList;

public interface AppointmentInterface  {
    public void getUpcomingData();
    public void getHistoryData() ;
    public void statusButtonBackground(Context mContext, String status, TextView textView);
    public void statusButtonBackground(Context mContext, String status, TextView textView, LinearLayout appointment);
    public void setConfirmButton(Context mContext,String status, Button button);
    public void checkFilesSize(ArrayList<Files> files,BasicActivities basicActivities);

}
