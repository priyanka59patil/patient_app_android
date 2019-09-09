package com.werq.patient.Controller;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.MockData.JsonData;
import com.werq.patient.Models.pojo.AppointmentResponce;
import com.werq.patient.Models.pojo.Files;
import com.werq.patient.R;

import java.util.ArrayList;

public class AppointmentController implements AppointmentInterface {
BasicActivities basicActivities;

    public AppointmentController(BasicActivities basicActivities) {

        this.basicActivities=basicActivities;

    }

    @Override
    public void getUpcomingData()  {
       AppointmentResponce appointmentResponce= JsonData.getUpcomingData();
        basicActivities.setView(appointmentResponce);

    }

    @Override
    public void getHistoryData() {
        AppointmentResponce appointmentResponce= JsonData.getHistoryData();
        basicActivities.setView(appointmentResponce);

    }

    @Override
    public void statusButtonBackground(Context mContext, String status, TextView textView) {
        switch (status.toLowerCase()){
            case "missed":
                textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.red_button));
                textView.setText(mContext.getResources().getString(R.string.label_status_missed));
                break;
            case "visited" :
                textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.green_button));
                textView.setText(mContext.getResources().getString(R.string.label_status_visited));
                break;
            case "confirmed":
                textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.blue_button));
                textView.setText(mContext.getResources().getString(R.string.label_status_confirmed));
                break;
            case "toconfirm":
                textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.orange_button));
                textView.setText(mContext.getResources().getString(R.string.label_to_confirmed));

                break;

        }
    }

    @Override
    public void statusButtonBackground(Context mContext, String status, TextView textView, LinearLayout appointment) {

        statusButtonBackground( mContext,  status,  textView);



    }

    @Override
    public void setConfirmButton(Context mContext, String status, Button button) {
        if (status.toLowerCase().equals("toconfirm"))
            button.setVisibility(View.VISIBLE);
        else
            button.setVisibility(View.GONE);
    }



    @Override
    public void checkFilesSize(ArrayList<Files> files,BasicActivities basicActivities) {
        if(files.size()>0)
            basicActivities.setRecyclerView();
    }


}
