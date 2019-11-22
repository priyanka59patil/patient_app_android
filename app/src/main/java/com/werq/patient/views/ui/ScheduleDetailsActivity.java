package com.werq.patient.views.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.BuildConfig;
import com.werq.patient.Factory.ViewModelProviderFactory;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.views.adapter.AttachmentsAdapter;
import com.werq.patient.views.adapter.FilesAdapter;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Factory.ScheduleDeatilsVmFactory;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.Files;
import com.werq.patient.viewmodel.ScheduleDetailsViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.databinding.ActivityScheduleDetailsBinding;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ScheduleDetailsActivity extends BaseActivity implements RecyclerViewClickListerner, BasicActivities {

    private static final int MY_PERMISSIONS_REQUEST = 3;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    Sprite fadingCircle;

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
    @BindView(R.id.cvNoAttachments)
    CardView cvNoAttachments;
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

    @BindView(R.id.apptDetailsLayout)
    ScrollView apptDetailsLayout;
    @BindView(R.id.tvNoApptDetails)
    TextView tvNoApptDetails;
    //context
    Context mContext;

    //adapter
    AttachmentsAdapter attachmentListAdapter;

    //data
    ArrayList<AttachmentResult> attachmentList;

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
    ActivityScheduleDetailsBinding detailsBinding;
    TabAppoinmentViewModel viewModel;
    private AppointmentResult appointmentResult;
    private String TAG="schedule_details";
    private  int appointmentId;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_schedule_details);

        initializeVariables();

        layoutScheduleView.requestFocus();
        //getIntentData();

    }




    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getScheduleDetailsVisibility().observe(this,aBoolean -> {
            if(aBoolean){
                apptDetailsLayout.setVisibility(View.VISIBLE);
                tvNoApptDetails.setVisibility(View.GONE);
            }else {
                apptDetailsLayout.setVisibility(View.GONE);
                tvNoApptDetails.setVisibility(View.VISIBLE);
            }
        });


        viewModel.appointmentResultData.observe(this,appointmentResult1 -> {
            if(isFromUpcoming){

               if( appointmentResult1!=null){

                    if(appointmentResult1.getConfirmByPatient()!=null && appointmentResult1.getConfirmByPatient()){
                      setStatusButton("confirmed");
                        btConfirm.setVisibility(View.GONE);
                    }
                    else {
                        setStatusButton("toconfirm");
                        btConfirm.setVisibility(View.VISIBLE);
                    }
                }

            }else {
                btConfirm.setVisibility(View.GONE);
                if(appointmentResult1.getAppointmentStatus()!=null && !appointmentResult1.getAppointmentStatus().trim().isEmpty()){

                    setStatusButton(appointmentResult1.getAppointmentStatus());
                }
                else {
                    tvstatus.setVisibility(View.GONE);
                }
            }
        });


        viewModel.attachmentVisibility.observe(this, aBoolean -> {
            if(aBoolean)
            {
                rvFiles.setVisibility(View.VISIBLE);
                cvNoAttachments.setVisibility(View.GONE);
            }
            else {
                rvFiles.setVisibility(View.GONE);
                cvNoAttachments.setVisibility(View.VISIBLE);
            }

        });



        viewModel.apptDetailsloading.observe(this,aBoolean -> {
            if(aBoolean ){
               loadingView.setVisibility(View.VISIBLE);
            }
            else {
                loadingView.setVisibility(View.GONE);
            }
        });

        viewModel.doctorProfilePhoto.observe(this,s -> {

            if(s!=null){
                String url = null;
                url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile+s;
                Glide.with(mContext).load(url).apply(new RequestOptions()
                        .placeholder(R.drawable.user_image_placeholder)
                        .error(R.drawable.user_image_placeholder).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(ivUseImage);

            }
            else {
                ivUseImage.setImageResource(R.drawable.user_image_placeholder);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setStatusButton(String appointmentStatus) {
        controller.statusButtonBackground(mContext, appointmentStatus, tvstatus);

    }

    private void setConfirmButton() {
        controller.setConfirmButton(mContext, appointmentResult.getAppointmentStatus(), btConfirm);
    }


    @Override
    public void getData() {

    }

    @Override
    public void setToolbar() {
       /* viewModel.toolbarTitle.observe(this, s -> {
            if(s!=null && !s.isEmpty())
            {
                Helper.setToolbarwithCross(getSupportActionBar(), s);

            }
        });*/
       Helper.setToolbarwithCross(getSupportActionBar(), appointmentResult.getDoctor().getFirstName()+" "+appointmentResult.getDoctor().getLastName());

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

        detailsBinding= DataBindingUtil.setContentView(this,R.layout.activity_schedule_details);
        detailsBinding.setLifecycleOwner(this);
        mContext = this;
        intent = getIntent();
        basicActivities = this;
        controller = new AppointmentController(basicActivities);

        ButterKnife.bind(this);
        fadingCircle=new Circle();
        loadingView.setIndeterminateDrawable(fadingCircle);
        setSupportActionBar(toolbar);
        getIntentData();

        viewModel= ViewModelProviders.of(this,new ViewModelProviderFactory(isFromUpcoming)).get(TabAppoinmentViewModel.class);
        setBaseViewModel(viewModel);
        detailsBinding.setTabAppoinmentViewModel(viewModel);
       // viewModel.setFromUpcoming(isFromUpcoming);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setAppointmentId(appointmentId);
        recyclerViewClickListerner = this::onclick;
        attachmentList = new ArrayList<>();
        setView(appointmentResult);
        setRecyclerView();
    }

    @Override
    public void setRecyclerView() {

        /*tvTextAttachedFiles.setVisibility(View.VISIBLE);
        rvFiles.setVisibility(View.VISIBLE);*/
        attachmentListAdapter = new AttachmentsAdapter(mContext, attachmentList, recyclerViewClickListerner,controller,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext, rvFiles, attachmentListAdapter);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext, rvFiles);

    }

    @Override
    public void setView(Object data) {
        //this.data = (AppointmentData) data;
        setToolbar();
        /*setConfirmButton();

        setStatusButton();*/

       /* tvUseFullName.setText(this.data.getProvider().getFirst_name() + " " + this.data.getProvider().getFirst_name());
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

        attachmentList.addAll(Arrays.asList(this.data.getFiles()));
        controller.checkFilesSize(attachmentList, basicActivities);*/

    }

    @Override
    public void getIntentData() {

            isFromUpcoming = getIntent().getBooleanExtra("IsFromUpcommming", false);

            appointmentResult = (AppointmentResult) getIntent().getSerializableExtra("AppointmentData");
            appointmentId=appointmentResult.getiD();
            Helper.setLog(TAG,appointmentId+"-:"+appointmentResult.toString());

    }


    @Override
    public void onclick(int position) {

        Helper.setLog("aaaa",attachmentList.get(position).toString());

        if(attachmentList.get(position).getVisitNoteId()!=null){
            if(attachmentList.get(position).getVisitNoteId()!=0){

                Helper.setLog("getAppointmentId",attachmentList.get(position).getAppointmentId()+"" );
                Helper.setLog("getVisitNoteId",attachmentList.get(position).getVisitNoteId()+"" );

                Intent intent =new Intent(mContext, ViewVisitNoteActivity.class);
                intent.putExtra("appointmentId",attachmentList.get(position).getAppointmentId());
                intent.putExtra("visitNoteId",attachmentList.get(position).getVisitNoteId());
                startActivity(intent);
            }
            else {
                Helper.showToast(mContext,"No Details Available");
            }
        }
        else {
            Helper.showToast(mContext,"No Details Available");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
