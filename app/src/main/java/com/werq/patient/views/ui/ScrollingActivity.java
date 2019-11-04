package com.werq.patient.views.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.werq.patient.views.adapter.PagerAdapter;
import com.werq.patient.views.ui.Fragments.DoctorsListFragment;
import com.werq.patient.views.ui.Fragments.PracticeFragment;
import com.werq.patient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity {



    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);


    }
    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PracticeFragment(), getString(R.string.label_practice));
        adapter.addFragment(new DoctorsListFragment(), getString(R.string.label_doctorname));
        viewpager.setAdapter(adapter);


    }
}
