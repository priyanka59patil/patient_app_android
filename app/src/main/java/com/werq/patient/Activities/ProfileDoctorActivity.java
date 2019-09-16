package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.werq.patient.Adapters.PagerAdapter;
import com.werq.patient.Fragments.DoctorsListFragment;
import com.werq.patient.Fragments.PracticeFragment;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileDoctorActivity extends AppCompatActivity implements BasicActivities {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializeVariables();
        getIntentData();
        tvAbout.setTrimCollapsedText("Read More...");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                    toolbarLayout.setTitle(getResources().getString(R.string.value_username));
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
        adapter.addFragment(new PracticeFragment(), getString(R.string.label_practice));
        adapter.addFragment(new DoctorsListFragment(), getString(R.string.label_doctorTEAM));
        viewpager.setAdapter(adapter);


    }

    @Override
    public void initializeVariables() {
        mContext = this;
    }

    @Override
    public void setRecyclerView() {

    }

    @Override
    public void setView(Object data) {

    }
    public void addFragment(Fragment fra) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fra);
        transaction.commitNow();
    }

    @Override
    public void getIntentData() {
        Intent intent = getIntent();
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

    @Override
    public void getData() {

    }

    @Override
    public void setToolbar() {

    }
}
