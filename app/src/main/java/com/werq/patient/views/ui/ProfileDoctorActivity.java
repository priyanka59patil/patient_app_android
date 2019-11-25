package com.werq.patient.views.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.werq.patient.BuildConfig;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityProfileDoctorBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;
import com.werq.patient.views.adapter.PagerAdapter;
import com.werq.patient.views.ui.Fragments.DoctorsListFragment;
import com.werq.patient.views.ui.Fragments.PracticeFragment;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private PagerAdapter adapter;
    RelativeLayout.LayoutParams params;
    private Context mContext;
    private Resources r;
    private int mPx;
    boolean isMessageDisabled;
    Intent intent;
    private  int doctorId;
    ProgressDialog progressDialog;
    Doctor doctorData;
    ProfileDoctorViewModel profileDoctorViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile_doctor);

        ActivityProfileDoctorBinding activityProfileDoctorBinding= DataBindingUtil.setContentView(this,R.layout.activity_profile_doctor);
        activityProfileDoctorBinding.setLifecycleOwner(this);
        mContext = this;
        intent = getIntent();
        progressDialog= Helper.createProgressDialog(mContext);
        ButterKnife.bind(this);

        getIntentData();
        tvAbout.setTrimCollapsedText("Read More...");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileDoctorViewModel= ViewModelProviders.of(this).get(ProfileDoctorViewModel.class);

        if(Helper.hasNetworkConnection(mContext)){
            profileDoctorViewModel.setDoctorId(doctorData.getiD());
        }else{
            Helper.showToast(mContext,getResources().getString(R.string.no_network_conection));
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
                    toolbarLayout.setTitle(profileDoctorViewModel.getDoctorName().getValue());
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


    }

    @Override
    protected void onResume() {
        super.onResume();

        profileDoctorViewModel.getDoctorDetailsResponse().observe(this,doctorDetailsResponse -> {
            if(doctorDetailsResponse!=null)
            {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                doctorDetailsLayout.setVisibility(View.VISIBLE);
                tvNoDoctorDetails.setVisibility(View.GONE);
            }else {
                setSupportActionBar(noDatatoolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                doctorDetailsLayout.setVisibility(View.GONE);
                tvNoDoctorDetails.setVisibility(View.VISIBLE);
            }
        });

        profileDoctorViewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean ){
                if(!progressDialog.isShowing())
                    progressDialog.show();
            }
            else {
                if(progressDialog.isShowing())
                    progressDialog.hide();
            }
        });

        profileDoctorViewModel.profileUrl.observe(this,s -> {

            if(s!=null && !s.equals(""))
            {
                String url = null;
                url = "https://s3.amazonaws.com/" + BuildConfig.s3BucketNameUserProfile+s;
                Glide.with(mContext).load(url).apply(new RequestOptions()
                        .placeholder(R.drawable.user_image_placeholder)
                        .error(R.drawable.user_image_placeholder).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)).into(ivUserProfile);
            }
            else {
                ivUserProfile.setImageResource(R.drawable.user_image_placeholder);
            }
        });

        profileDoctorViewModel.doctorName.observe(this,s -> {
            tvUsername.setText(s);
        });
        profileDoctorViewModel.doctorSpeciality.observe(this,s -> {
            tvSpeciality.setText(s);
        });

        profileDoctorViewModel.about.observe(this,s -> {
            tvAbout.setText(s);
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

        /*DoctorsListFragment fra=new DoctorsListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("doctorDetailsResponse", profileDoctorViewModel.doctorDetailsResponse.getValue());
        fra.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fra);
        transaction.commitNow();*/

        adapter.addFragment(new PracticeFragment(), getString(R.string.label_practice));
        adapter.addFragment(new DoctorsListFragment(), getString(R.string.label_doctorTEAM));
        viewpager.setAdapter(adapter);


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
        if(intent!=null){
            doctorData= (Doctor) intent.getSerializableExtra("doctorData");
            Helper.setLog("doctorData",doctorData.toString());
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
}
