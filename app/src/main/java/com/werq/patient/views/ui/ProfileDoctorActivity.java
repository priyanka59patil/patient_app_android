package com.werq.patient.views.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.werq.patient.BuildConfig;
import com.werq.patient.Factory.ProfileDoctorVmFactory;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityProfileDoctorBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.service.model.ResponeError.ProfileIntentService;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.PagerAdapter;
import com.werq.patient.views.ui.Fragments.DoctorsListFragment;
import com.werq.patient.views.ui.Fragments.PracticeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileDoctorActivity extends BaseActivity implements BasicActivities {

    @BindView(R.id.tvNoLayout)
    ConstraintLayout tvNoDoctorDetails;
    @BindView(R.id.doctorDetailsLayout)
    CoordinatorLayout doctorDetailsLayout;
    @BindView(R.id.noDatatoolbar)
    Toolbar noDatatoolbar;


    @BindView(R.id.ivUserProfile)
    CircleImageView ivUserProfile;
    @BindView(R.id.tvUsername)
    TextView tvUsername;
    @BindView(R.id.tvAbout)
    ReadMoreTextView tvAbout;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.btcall)
    ImageButton btcall;
    @BindView(R.id.tvcall)
    TextView tvcall;
    @BindView(R.id.btChat)
    ImageButton btChat;
    @BindView(R.id.tvChat)
    TextView tvChat;
    @BindView(R.id.layout_image_button)
    LinearLayout layoutImageButton;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.collasbale_view)
    RelativeLayout collasbaleView;
    @BindView(R.id.tbuserimageview)
    CircleImageView tbuserimageview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tvSpeciality)
    TextView tvSpeciality;
    @BindView(R.id.relTablayouts)
    RelativeLayout relTablayouts;
    @BindView(R.id.layout_bt_chat)
    RelativeLayout layoutBtChat;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.loadingView1)
    SpinKitView loadingView1;
    @BindView(R.id.loadingView2)
    SpinKitView loadingView2;
    @BindView(R.id.profileFrame)
    FrameLayout profileFrame;
    private PagerAdapter adapter;
    RelativeLayout.LayoutParams params;
    private Context mContext;
    private Resources r;
    private int mPx;
    boolean isMessageDisabled;
    Intent intent;
    private int doctorId;
    ProgressDialog progressDialog;
    Doctor doctorData;
    ProfileDoctorViewModel profileDoctorViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile_doctor);

        ActivityProfileDoctorBinding activityProfileDoctorBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_doctor);
        activityProfileDoctorBinding.setLifecycleOwner(this);
        mContext = this;
        intent = getIntent();
        ButterKnife.bind(this);

        getIntentData();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadingView1.setIndeterminateDrawable(fadingCircle);
        loadingView2.setIndeterminateDrawable(fadingCircle);

        profileDoctorViewModel = ViewModelProviders.of(this, new ProfileDoctorVmFactory(mContext,getAuthToken())).get(ProfileDoctorViewModel.class);

        if (Helper.hasNetworkConnection(mContext)) {

            profileDoctorViewModel.setDoctorId(doctorData.getiD());
        } else {
            setSupportActionBar(noDatatoolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            doctorDetailsLayout.setVisibility(View.GONE);
            tvNoDoctorDetails.setVisibility(View.VISIBLE);
            Helper.showToast(mContext, getResources().getString(R.string.no_network_conection));
        }


        r = mContext.getResources();

        mPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                20,
                r.getDisplayMetrics()
        );


        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((toolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(toolbarLayout))) {
                    Log.d("toolbarLayout", "true");

                    relTablayouts.setPadding(0, 0, 0, 0);
                    // getSupportActionBar().setDisplayShowTitleEnabled(true);

                    tbuserimageview.setVisibility(View.VISIBLE);
                    collasbaleView.setVisibility(View.GONE);
                    if (!TextUtils.isEmpty(profileDoctorViewModel.getDoctorName().getValue())) {
                        toolbarLayout.setTitle(profileDoctorViewModel.getDoctorName().getValue());
                    } else {
                        toolbarLayout.setTitle("");
                    }
                    tabs.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));
                    tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
                    tabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    // getSupportActionBar().setTitle(getResources().getString(R.string.value_username));
                    //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                } else {
                    tabs.setTabTextColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimary));
                    tabs.setBackgroundColor(getResources().getColor(R.color.white));
                    tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
                    tbuserimageview.setVisibility(View.GONE);
                    collasbaleView.setVisibility(View.VISIBLE);
                    toolbarLayout.setTitle("");
                    relTablayouts.setPadding(mPx, 0, mPx, 0);
                    //getSupportActionBar().setDisplayShowTitleEnabled(false);
                    Log.d("toolbarLayout", "false");
                    // toolbar.setTitleTextColor(getResources().getColor(R.color.black));
                }
            }
        });

        profileDoctorViewModel.getDoctorDetailsData().observe(this, doctorDetailsData  -> {
            if (doctorDetailsData != null) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                doctorDetailsLayout.setVisibility(View.VISIBLE);
                tvNoDoctorDetails.setVisibility(View.GONE);
            } else {
                setSupportActionBar(noDatatoolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                doctorDetailsLayout.setVisibility(View.GONE);
                tvNoDoctorDetails.setVisibility(View.VISIBLE);
            }
        });

        profileDoctorViewModel.getPracticePhoneNumber().observe(this, s -> {
            Helper.setLog("getPracticePhoneNumber", s + "-val");
            if (!TextUtils.isEmpty(s)) {
                btcall.setEnabled(true);
                btcall.setAlpha(1f);
            } else {
                btcall.setEnabled(false);
                btcall.setAlpha(0.5f);
            }
        });

       /* LocalBroadcastManager.getInstance(mContext).registerReceiver(
                newDoctorProfileReceiver,
                new IntentFilter(getString(R.string.NEW_DOCTOR_PROFILE)));*/

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        profileDoctorViewModel.getLoading().observe(this, aBoolean -> {
            /*try {
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
                Helper.setExceptionLog("Exception", e);
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.hide();
                }

            }*/
            if (aBoolean) {
                showProgressBar(loadingView1);
                showProgressBar(loadingView2);
                loadingView1.bringToFront();
                loadingView2.bringToFront();
            } else {
                hideProgressBar(loadingView1);
                hideProgressBar(loadingView2);
            }


        });

        profileDoctorViewModel.profileUrl.observe(this, s -> {

            if (s != null && !s.equals("")) {
                String url = null;
                url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile + s;
                Glide.with(mContext).load(url).apply(new RequestOptions()
                        .placeholder(R.drawable.user_image_placeholder)
                        .error(R.drawable.user_image_placeholder).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(ivUserProfile);

                Glide.with(mContext).load(url).apply(new RequestOptions()
                        .placeholder(R.drawable.user_image_placeholder)
                        .error(R.drawable.user_image_placeholder).skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(tbuserimageview);
            } else {
                ivUserProfile.setImageResource(R.drawable.user_image_placeholder);
            }
        });

        profileDoctorViewModel.doctorName.observe(this, s -> {
            tvUsername.setText(s);
        });
        profileDoctorViewModel.doctorSpeciality.observe(this, s -> {
            tvSpeciality.setText(s);
        });

        profileDoctorViewModel.getAbout().observe(this, aboutDoctor -> {

            if (!TextUtils.isEmpty(aboutDoctor)) {
                tvAbout.setVisibility(View.VISIBLE);
                tvAbout.setText(aboutDoctor);
            } else {
                tvAbout.setVisibility(View.GONE);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapter(getSupportFragmentManager());

       /* DoctorsListFragment fra=new DoctorsListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("clickList", profileDoctorViewModel.doctorDetailsResponse.getValue());
        fra.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fra);
        transaction.commitNow();*/
        DoctorsListFragment doctorsListFragment = new DoctorsListFragment();
        adapter.addFragment(new PracticeFragment(), getString(R.string.label_practice));
        adapter.addFragment(doctorsListFragment, getString(R.string.label_doctorTEAM));
        viewpager.setAdapter(adapter);

        doctorsListFragment.setMainContainer(profileFrame);

        //doctorsListFragment.setRecyclerViewClickListerner(this);


    }

    @Override
    public void initializeVariables() {
    }

    @Override
    public void setRecyclerView() {

    }

    @Override
    public void setView(Object data) {

    }

    public void addFragment(Fragment fra) {
        /*Bundle bundle = new Bundle();
        bundle.putSerializable("doctorDetailsResponse", profileDoctorViewModel.doctorDetailsResponse.getValue());
        fra.setArguments(bundle);*/
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fra);
        transaction.commitNow();

    }


    @Override
    public void getIntentData() {
        if (intent != null) {
            doctorData = (Doctor) intent.getSerializableExtra("doctorData");
            Helper.setLog("doctorData", doctorData.toString());
            isMessageDisabled = intent.getBooleanExtra("isMessageDisabled", false);
            if (isMessageDisabled) {
                layoutBtChat.setVisibility(View.GONE);
                relTablayouts.setVisibility(View.GONE);
                fragment.setVisibility(View.VISIBLE);
                addFragment(new PracticeFragment());
            } else {
                fragment.setVisibility(View.GONE);
                layoutBtChat.setVisibility(View.VISIBLE);
                relTablayouts.setVisibility(View.VISIBLE);
                setupViewPager(viewpager);
                tabs.setupWithViewPager(viewpager);
            }
        }

    }

    @Override
    public void getData() {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventReceive(ProfileIntentService profileIntentService){

        Helper.setLog("onEventReceive","profileIntentService");
        Intent i=new Intent(ProfileDoctorActivity.this,ProfileDoctorActivity.class);
        i.putExtra("doctorData",profileIntentService.getDoctor());
        i.putExtra("isMessageDisabled",profileIntentService.isMessageEnabled());
        startActivity(i);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(newDoctorProfileReceiver);
        EventBus.getDefault().unregister(this);
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @OnClick(R.id.btcall)
    public void onViewClicked() {

        String phoneNo = profileDoctorViewModel.getPracticePhoneNumber().getValue();
        if (!TextUtils.isEmpty(phoneNo)) {

            Helper.setLog("getPracticePhoneNumber", phoneNo + "-val");
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNo.trim()));
            //startActivity(callIntent);

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                Dexter.withActivity(this).withPermission(Manifest.permission.CALL_PHONE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        mContext.startActivity(callIntent);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {

                            Helper.setSnackbarWithMsg("Phone access is needed to make call", toolbar);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

                //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

            } else {

                mContext.startActivity(callIntent);
            }
        }


    }

    private BroadcastReceiver newDoctorProfileReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Helper.setLog("listening", "confirmedAppointmentBR");
            //
            Intent i=new Intent(ProfileDoctorActivity.this,ProfileDoctorActivity.class);
            i.putExtra("doctorData",intent.getSerializableExtra("doctorData"));
            i.putExtra("isMessageDisabled",intent.getBooleanExtra("isMessageDisabled", false));
            //overridePendingTransition(0, 0);
            startActivity(i);
            finish();
            //getIntentData();
            //overridePendingTransition(0, 0);

        }
    };

}
