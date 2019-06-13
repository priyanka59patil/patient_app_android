package com.werq.patient.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.werq.patient.Adapters.FilesAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.Files;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ScheduleDetailsActivity extends AppCompatActivity implements RecyclerViewClickListerner {

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

    //list
    ArrayList<Files> allFiles;

    //Intent
    Intent intent;

    //boolean
    boolean isFromUpcoming;
    @BindView(R.id.btConfirm)
    Button btConfirm;
    RecyclerViewClickListerner recyclerViewClickListerner;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializeVariables();

        setToolbar();

        getIntentData();

        setConfirmButton();

        setStatusButton();

        setFiles();

     ;



    }

    private void setFiles() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvFiles,filesAdapter);
    }

    private void setToolbar() {
        Helper.setToolbarwithCross(getSupportActionBar(),"Petaul Emma Elizabeth");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setStatusButton() {
        if (!isFromUpcoming) {
            tvstatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button));
            tvstatus.setText(getResources().getString(R.string.label_status_missed));
        }


    }

    private void setConfirmButton() {
        if (isFromUpcoming)
            btConfirm.setVisibility(View.VISIBLE);
        else
            btConfirm.setVisibility(View.GONE);

    }

    private void getIntentData() {
        isFromUpcoming = intent.getBooleanExtra(getResources().getString(R.string.intent_is_from_upcoming), false);
    }

    private ArrayList<Files> getFilesData() {
        ArrayList<Files> files = new ArrayList<>();
        Files file = new Files(R.drawable.imageone, "image", "Image-Attachment-01.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file1 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file2 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        files.add(file);
        files.add(file1);
        files.add(file2);
        return files;
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

    private void initializeVariables() {
        //Context
        mContext = this;

        //intent
        intent = getIntent();

        //listner
        recyclerViewClickListerner=this::onclick;

        //data
        allFiles = getFilesData();

        //adapters
        filesAdapter = new FilesAdapter(mContext, allFiles,recyclerViewClickListerner);
    }


    @Override
    public void onclick(int position) {

    }
}
