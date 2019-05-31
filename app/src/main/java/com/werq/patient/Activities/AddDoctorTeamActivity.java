package com.werq.patient.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.werq.patient.Fragments.EnterCodeFragment;
import com.werq.patient.Fragments.NewDoctorAddedFragment;
import com.werq.patient.Fragments.RepeateFragmentFragment;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDoctorTeamActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fram)
    FrameLayout fram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_team);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Helper.setToolbarwithCross(getSupportActionBar(), getResources().getString(R.string.title_add_doctor_team));
        EnterCodeFragment enterCodeFragment = new EnterCodeFragment();
        addFragment(enterCodeFragment);

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
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String action) {
        switch (action){
            case "new_doctor_team":
                NewDoctorAddedFragment newDoctorAddedFragment=new NewDoctorAddedFragment();
                addFragment(newDoctorAddedFragment);
                break;
        }

    };

    public void addFragment(Fragment fra) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fram, fra);
        transaction.commitNow();
    }


}
