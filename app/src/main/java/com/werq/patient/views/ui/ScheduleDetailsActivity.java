package com.werq.patient.views.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.werq.patient.BuildConfig;
import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Factory.TabApptVmProviderFactory;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.Callback.DiologListner;
import com.werq.patient.Interfaces.Callback.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.DiologHelper;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;
import com.werq.patient.Utils.SpacesItemDecoration;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityScheduleDetailsBinding;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AvailableTimeSlot;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;
import com.werq.patient.views.adapter.AttachmentsAdapter;
import com.werq.patient.views.adapter.NewTimeSlotAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ScheduleDetailsActivity extends BaseActivity implements RecyclerViewClickListerner, BasicActivities,
        DiologListner {

    private static final int MY_PERMISSIONS_REQUEST = 3;

    /*@BindView(R.id.loadingView)
    ProgressBar loadingView;
    Sprite fadingCircle;*/

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
    @BindView(R.id.rlReschedule)
    RelativeLayout rlReschedule;

    @BindView(R.id.llRescheduledLayout)
    LinearLayout llRescheduledLayout;
    @BindView(R.id.tvRescheduledDate)
    TextView tvRescheduledDate;
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
    ActivityScheduleDetailsBinding detailsBinding;
    TabAppoinmentViewModel viewModel;
    SupportMapFragment mapFragment;
    private AppointmentResult appointmentResult;
    private String TAG = "schedule_details";
    private int appointmentId;
    ProgressDialog progressDialog;

    //time slot dialog
    private Dialog dgAppointment;
    private RecyclerView rvdateslot;
    private TextView tvNoTimeSlot;
    private EditText et_selectDate;
    private TextView tvOk;
    private TextView tvCancel;
    private Calendar myCalendar = Calendar.getInstance();
    private String selectAppoinrmentDate;
    private NewTimeSlotAdapter adapter;
    private List<AvailableTimeSlot> timeSlotList;
    private EditText etReason;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_schedule_details);


        initializeVariables();
        layoutScheduleView.requestFocus();
        //getIntentData();

        if (Helper.hasNetworkConnection(mContext)) {
            viewModel.getAppointmentData(appointmentId);
        } else {
            viewModel.getScheduleDetailsVisibility().setValue(false);
            viewModel.getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
        }

        dgAppointment = DiologHelper.createDialogWithLayout(mContext, R.layout.new_appointmentdate, this);

        /*FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().add(R.id.mapframe,new MapFragment()).commit()*/;
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mapframe, new MapFragment());
        transaction.commitNow();*/
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapframe);



        viewModel.getRefreshListFlag().observe(this, aBoolean -> {
            if (aBoolean) {
                viewModel.getRefreshListFlag().setValue(false);
                Intent intent = new Intent(getResources().getString(R.string.CONFIRMED_APPOINTMENT_BROADCAST));
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }

        });

        viewModel.getScheduleDetailsVisibility().observe(this, aBoolean -> {
            if (aBoolean) {
                apptDetailsLayout.setVisibility(View.VISIBLE);
                tvNoApptDetails.setVisibility(View.GONE);
            } else {
                apptDetailsLayout.setVisibility(View.GONE);
                tvNoApptDetails.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getRescheduledDate().observe(this,s -> {
            if(!TextUtils.isEmpty(s)){
                llRescheduledLayout.setVisibility(View.VISIBLE);
                tvAddress.setMinLines(1);
                tvRescheduledDate.setText(s);

            }else {
                llRescheduledLayout.setVisibility(View.GONE);
                tvAddress.setMinLines(2);
            }
        });
        viewModel.getAppointmentResultData().observe(this, appointmentResult1 -> {


            double mLat, mLong;

            mLat = Double.parseDouble(appointmentResult1.getLocation().getLatitude());
            mLong = Double.parseDouble(appointmentResult1.getLocation().getLongitude());

            showMapOnFragment(mLat, mLong, appointmentResult1.getLocation().getOrganizationName());

            if (isFromUpcoming) {

                if (appointmentResult1 != null) {

                    if (appointmentResult1.getConfirmByPatient() != null && appointmentResult1.getConfirmByPatient()) {
                        setStatusButton("confirmed");
                        //btConfirm.setVisibility(View.GONE);
                        btConfirm.setEnabled(false);
                        btConfirm.setAlpha(0.5f);

                        rlReschedule.setAlpha(1);
                        btReSchedule.setEnabled(true);
                        //rlReschedule.setVisibility(View.VISIBLE);
                    } else {
                        setStatusButton("toconfirm");
                        // btConfirm.setVisibility(View.VISIBLE);
                        btConfirm.setEnabled(true);
                        btConfirm.setAlpha(1);
                        rlReschedule.setAlpha(0.5f);
                        btReSchedule.setEnabled(false);
                        //rlReschedule.setVisibility(View.GONE);
                    }
                }

            } else {


                if (appointmentResult1.getAppointmentStatus().equalsIgnoreCase("visited")) {
                    rlReschedule.setAlpha(0.5f);
                    btReSchedule.setEnabled(false);

                } else if (appointmentResult1.getAppointmentStatus().equalsIgnoreCase("Missed")) {
                    rlReschedule.setAlpha(1);
                    btReSchedule.setEnabled(true);
                } else {
                    rlReschedule.setAlpha(0.5f);
                    btReSchedule.setEnabled(false);
                }
                btConfirm.setEnabled(false);
                btConfirm.setAlpha(0.5f);
                if (appointmentResult1.getAppointmentStatus() != null && !appointmentResult1.getAppointmentStatus().trim().isEmpty()) {

                    setStatusButton(appointmentResult1.getAppointmentStatus());
                } else {
                    tvstatus.setVisibility(View.GONE);
                }
            }
        });


        viewModel.attachmentVisibility.observe(this, aBoolean -> {
            if (aBoolean) {
                rvFiles.setVisibility(View.VISIBLE);
                cvNoAttachments.setVisibility(View.GONE);
            } else {
                rvFiles.setVisibility(View.GONE);
                cvNoAttachments.setVisibility(View.VISIBLE);
            }

        });


        viewModel.getLoading().observe(this, aBoolean -> {
            try {
                if (aBoolean) {
                    if (progressDialog != null && !progressDialog.isShowing()) {
                        progressDialog.show();
                    } else {
                        progressDialog = Helper.createProgressDialog(mContext);
                    }
                } else {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                }
            } catch (Exception e) {
                Helper.setExceptionLog("Exception",e);
                if (!this.isFinishing() && progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.hide();
                }
            }

        });

        viewModel.doctorProfilePhoto.observe(this, s -> {

            if (s != null) {
                String url = null;
                url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile + s;
                Glide.with(mContext).load(url).apply(new RequestOptions()
                        .placeholder(R.drawable.user_image_placeholder)
                        .error(R.drawable.user_image_placeholder).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(ivUseImage);

            } else {
                ivUseImage.setImageResource(R.drawable.user_image_placeholder);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.setLog("Clicked", "btConfirm");
                if (Helper.hasNetworkConnection(mContext)) {

                    viewModel.setConfirmStatus();

                } else {
                    viewModel.getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
                }

            }
        });
        btReSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.setLog("Clicked", "btReSchedule");
                if (Helper.hasNetworkConnection(mContext)) {
                    showTimeSlot();
                    dgAppointment.show();
                    try {

                        Date d = new SimpleDateFormat(Helper.MMM_DD_YYYY).parse(et_selectDate.getText().toString());
                        viewModel.fetchTimeSlots(new SimpleDateFormat(Helper.YYYY_MM_DD).format(d));

                    } catch (ParseException e) {
                        Helper.setExceptionLog(TAG+ "-ParseException: ",e );
                        e.printStackTrace();
                    }


                } else {
                    viewModel.getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
                }

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
        Helper.setToolbarwithCross(getSupportActionBar(), appointmentResult.getDoctor().getFirstName() + " " + appointmentResult.getDoctor().getLastName());

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
        mContext = this;
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_schedule_details);
        detailsBinding.setLifecycleOwner(this);

        intent = getIntent();
        basicActivities = this;
        controller = new AppointmentController(basicActivities);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getIntentData();

        viewModel = ViewModelProviders.of(this, new TabApptVmProviderFactory(getAuthToken(),isFromUpcoming)).get(TabAppoinmentViewModel.class);
        setBaseViewModel(viewModel);
        detailsBinding.setTabAppoinmentViewModel(viewModel);
        recyclerViewClickListerner = this::onclick;
        attachmentList = new ArrayList<>();
        setView(appointmentResult);
        setRecyclerView();
    }

    @Override
    public void setRecyclerView() {

        /*tvTextAttachedFiles.setVisibility(View.VISIBLE);
        rvFiles.setVisibility(View.VISIBLE);*/
        attachmentListAdapter = new AttachmentsAdapter(mContext, attachmentList, recyclerViewClickListerner, controller, viewModel, this);
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
        appointmentId = appointmentResult.getiD();
        Helper.setLog(TAG, appointmentId + "-:" + appointmentResult.toString());

    }


    @Override
    public void onclick(int position) {

        Helper.setLog("aaaa", attachmentList.get(position).toString());

        if (attachmentList.get(position).getVisitNoteId() != null) {
            if (attachmentList.get(position).getVisitNoteId() != 0) {

                Helper.setLog("getAppointmentId", attachmentList.get(position).getAppointmentId() + "");
                Helper.setLog("getVisitNoteId", attachmentList.get(position).getVisitNoteId() + "");

                Intent intent = new Intent(mContext, ViewVisitNoteActivity.class);
                intent.putExtra("appointmentId", attachmentList.get(position).getAppointmentId());
                intent.putExtra("visitNoteId", attachmentList.get(position).getVisitNoteId());
                startActivity(intent);
            } else {
                Helper.showToast(mContext, "No Details Available");
            }
        } else {

            Helper.showToast(mContext, "No Details Available");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isFinishing() && progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }


    void showTimeSlot() {

        dgAppointment = new Dialog(mContext, R.style.Theme_Dialog);
        dgAppointment.setContentView(R.layout.new_appointmentdate);
        dgAppointment.setCancelable(false);
        Window window = dgAppointment.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        rvdateslot = (RecyclerView) dgAppointment.findViewById(R.id.rvdateslot);
        tvNoTimeSlot = (TextView) dgAppointment.findViewById(R.id.tvNoData);
        et_selectDate = (EditText) dgAppointment.findViewById(R.id.et_selectDate);
        et_selectDate.setFocusable(false);
        etReason = (EditText) dgAppointment.findViewById(R.id.etReason);
        tvOk = (TextView) dgAppointment.findViewById(R.id.tvOk);
        tvCancel = (TextView) dgAppointment.findViewById(R.id.tvCancel);
        rvdateslot.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvdateslot.addItemDecoration(new SpacesItemDecoration(2));
        rvdateslot.setHasFixedSize(true);

        et_selectDate.setText(viewModel.getCurrentAppointmentDate().getValue());


        viewModel.getAvailableTimeSlot().observe(this, availableTimeSlots -> {

            if (availableTimeSlots == null || availableTimeSlots.size() == 0) {
                tvNoTimeSlot.setVisibility(View.VISIBLE);
                rlReschedule.setVisibility(View.GONE);
            } else {
                tvNoTimeSlot.setVisibility(View.GONE);
                rlReschedule.setVisibility(View.VISIBLE);
            }
        });


        timeSlotList = new ArrayList<>();
        adapter = new NewTimeSlotAdapter(mContext, timeSlotList, viewModel, this);
        adapter.notifyDataSetChanged();
        rvdateslot.setAdapter(adapter);

        et_selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_selectDate.setError(null);
                Helper.hideKeyboardFrom(mContext, et_selectDate);
                DatePickerDialog dg = new DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dg.getDatePicker().setMinDate(System.currentTimeMillis());
                dg.show();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_selectDate.setText("");
                et_selectDate.setError(null);
                viewModel.getSelectTimeSlotItem().setValue(null);
                if (adapter != null)
                    adapter.notifyDataSetChanged();
                dgAppointment.cancel();


            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (viewModel.getSelectTimeSlotItem().getValue() == null) {

                    Helper.showToast(mContext, "Please select time slot");

                } else {

                    try {
                        if(!Helper.isOlderDate(et_selectDate.getText().toString()+" "+viewModel.availableTimeSlot.getValue().get(viewModel.getSelectTimeSlotItem().getValue()).getStartTime()))
                        {
                            viewModel.sendRescheduleRequest(et_selectDate.getText().toString(), etReason.getText().toString());
                            dgAppointment.cancel();
                        }
                        else {
                            viewModel.getToast().setValue("Requested reschedule appointment date is invalid or past.You can send reschedule appointment request before 2 hr");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Helper.setExceptionLog("ParseException",e);
                    }

                }

            }
        });

    }

    @Override
    public void setdiologview(View view) {

    }

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String date = new SimpleDateFormat(Helper.MMM_DD_YYYY).format(myCalendar.getTime());

            Log.e("AppointMent Date", date);
            et_selectDate.setText(date);

            if (Helper.hasNetworkConnection(mContext)) {
                try {
                    Date d = new SimpleDateFormat(Helper.MMM_DD_YYYY).parse(et_selectDate.getText().toString());

                    Helper.setLog("after", new SimpleDateFormat(Helper.YYYY_MM_DD).format(d));
                    viewModel.fetchTimeSlots(new SimpleDateFormat(Helper.YYYY_MM_DD).format(d));

                } catch (ParseException e) {
                    e.printStackTrace();
                    Helper.setExceptionLog("ParseException", e);
                }

            } else {
                viewModel.getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
            }
        }

    };

    public void showMapOnFragment(double latitude, double longitude, String locationName) {


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers
                //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latlng, 10));

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(latitude, longitude))
                        .zoom(15)
                        .bearing(0)
                        .tilt(10)
                        .build();

                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex)/*, 50000, null*/);


               /* mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4219999, -122.0862462))
                        .title("Spider Man")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.spider)));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4629101,-122.2449094))
                        .title("Iron Man")
                        .snippet("His Talent : Plenty of money"));
                        */

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(locationName));

                mMap.getUiSettings().setZoomControlsEnabled(true);

            }
        });




    }
}
