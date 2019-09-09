package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.FilesAdapter;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.pojo.AppointmentData;
import com.werq.patient.Models.pojo.Files;
import com.werq.patient.R;
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ScheduleDetailsActivity extends AppCompatActivity implements RecyclerViewClickListerner, BasicActivities {

    private static final int MY_PERMISSIONS_REQUEST = 3;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvday)
    TextView tvday;
    @BindView(R.id.tvMonth)
    TextView tvMonth;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvstatus)
    TextView tvstatus;
    @BindView(R.id.layout_schedule_view)
    ConstraintLayout layoutScheduleView;
    @BindView(R.id.ivUseImage)
    CircleImageView ivUseImage;
    @BindView(R.id.tvUseFullName)
    TextView tvUseFullName;
    @BindView(R.id.tvSpeciality)
    TextView tvSpeciality;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.btAskQuestion)
    ImageButton btAskQuestion;
    @BindView(R.id.tvAskQuestions)
    TextView tvAskQuestions;
    @BindView(R.id.btReSchedule)
    ImageButton btReSchedule;
    @BindView(R.id.tvReSchedule)
    TextView tvReSchedule;
    @BindView(R.id.layout_image_button)
    LinearLayout layoutImageButton;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.tvTextNote)
    TextView tvTextNote;
    @BindView(R.id.cvNote)
    CardView cvNote;
    @BindView(R.id.tvTextAttachedFiles)
    TextView tvTextAttachedFiles;
    @BindView(R.id.rvFiles)
    RecyclerView rvFiles;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.appointment)
    ConstraintLayout appointment;

    //context
    Context mContext;

    //adapter
    FilesAdapter filesAdapter;

    //data
    AppointmentData data;
    ArrayList<Files> files;

    //listner
    AppointmentInterface controller;
    BasicActivities basicActivities;
    //Intent
    Intent intent;


    //boolean
    boolean isFromUpcoming;
    @BindView(R.id.btConfirm)
    Button btConfirm;
    RecyclerViewClickListerner recyclerViewClickListerner;
    @BindView(R.id.tvAddressonMap)
    TextView tvAddressonMap;
    @BindView(R.id.map)
    ImageView map;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializeVariables();

        layoutScheduleView.requestFocus();

        getIntentData();


        ;


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setStatusButton() {
        controller.statusButtonBackground(mContext, data.getSchedule_status(), tvstatus);

    }

    private void setConfirmButton() {
        controller.setConfirmButton(mContext, data.getSchedule_status(), btConfirm);
    }


    @Override
    public void getData() {

    }

    @Override
    public void setToolbar() {
        Helper.setToolbarwithCross(getSupportActionBar(), data.getProvider().getFirst_name()+" "+data.getProvider().getLast_name());

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
        }
    }


    @Override
    public void initializeVariables() {
        //Context
        mContext = this;

        //intent
        intent = getIntent();

        //listner
        recyclerViewClickListerner = this::onclick;
        basicActivities = this;
        controller = new AppointmentController(basicActivities);

        //data
        files = new ArrayList<>();
    }

    @Override
    public void setRecyclerView() {

        tvTextAttachedFiles.setVisibility(View.VISIBLE);
        rvFiles.setVisibility(View.VISIBLE);
        filesAdapter = new FilesAdapter(mContext, files, recyclerViewClickListerner);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvFiles, filesAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvFiles);

    }

    @Override
    public void setView(Object data) {
        this.data = (AppointmentData) data;
        setToolbar();
        setConfirmButton();

        setStatusButton();

        tvUseFullName.setText(this.data.getProvider().getFirst_name() + " " + this.data.getProvider().getFirst_name());
        tvSpeciality.setText(this.data.getProvider().getSpeciality());
        try {
            Date date = DateHelper.dateFromUtc(this.data.getAppointment_date());
            tvday.setText(DateHelper.dayFromDate(date, "day"));
            tvMonth.setText(DateHelper.dayFromDate(date, "month"));
            tvTime.setText(DateHelper.dayFromDate(date, "time"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.data.getProvider().getOffice() != null) {
            tvAddress.setText(this.data.getProvider().getOffice().toString());
            tvAddressonMap.setText(this.data.getProvider().getOffice().toString());
        }


        files.addAll(Arrays.asList(this.data.getFiles()));
        controller.checkFilesSize(files, basicActivities);

    }

    @Override
    public void getIntentData() {
        isFromUpcoming = intent.getBooleanExtra(getResources().getString(R.string.intent_is_from_upcoming), false);
        AppointmentData appointmentData = intent.getParcelableExtra(getResources().getString(R.string.label_data));
        setView(appointmentData);

    }


    @Override
    public void onclick(int position) {

    }
}
