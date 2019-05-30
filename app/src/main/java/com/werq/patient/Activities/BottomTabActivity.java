package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.werq.patient.Fragments.AppointmentFragment;
import com.werq.patient.Fragments.DoctorTeamFragment;
import com.werq.patient.Fragments.ProfileFragment;
import com.werq.patient.R;
import com.werq.patient.Utils.BottomNavigationViewHelper;
import com.werq.patient.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomTabActivity extends AppCompatActivity {
    @BindView(R.id.mainLayout)
    FrameLayout mainLayout;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.container)
    RelativeLayout container;
    private TextView mTextMessage;
    Context mContext;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.calendar:
                    AppointmentFragment  appointmentFragment = new AppointmentFragment();
                    addFragment(appointmentFragment);
                    return true;
                case R.id.messages:

                    return true;
                case R.id.people:
                    DoctorTeamFragment doctorTeamFragment=new DoctorTeamFragment();
                    addFragment(doctorTeamFragment);
                    return true;
                case R.id.profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    addFragment(profileFragment);
                    return true;
                case R.id.folder:

                    return true;
            }
            return false;
        }
    };

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayout, fragment);
        transaction.commitNow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_activity, menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        ButterKnife.bind(this);
        initializeVariables();


         navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         navView.setSelectedItemId(R.id.calendar);


        Helper.setToolbar(getSupportActionBar(), "Home");
    }

    private void initializeVariables() {
        mContext=this;
    }

}
