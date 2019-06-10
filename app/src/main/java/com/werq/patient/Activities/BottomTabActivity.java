package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Fragments.AppointmentFragment;
import com.werq.patient.Fragments.ChatFragments;
import com.werq.patient.Fragments.DoctorTeamFragment;
import com.werq.patient.Fragments.FilesFragment;
import com.werq.patient.Fragments.PracticeFragment;
import com.werq.patient.Fragments.ProfileFragment;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomTabActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mainLayout)
    FrameLayout mainLayout;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.container)
    RelativeLayout container;
    private TextView mTextMessage;
    Context mContext;
    BottomSheetDialog mBottomSheetDialog;
    String title;


    LinearLayout layoutNewInvitation;
    LinearLayout layoutDoctorBase;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.calendar:
                    AppointmentFragment appointmentFragment = new AppointmentFragment();
                    addFragment(appointmentFragment);
                    if (add != null && setting != null) {
                        Helper.setToolbar(getSupportActionBar(), "Appointment");
                        add.setVisible(false);
                        setting.setVisible(true);
                        search.setVisible(false);
                    }

                    return true;
                case R.id.messages:
                    title="Chat";
                    setToolbarForbottom(title,true,false);
                    ChatFragments chatFragments=new ChatFragments();
                    addFragment(chatFragments);
                    return true;
                case R.id.people:

                    title="Doctor Name";
                    setToolbarForbottom(title,true,false);
                    DoctorTeamFragment doctorTeamFragment = new DoctorTeamFragment();
                    addFragment(doctorTeamFragment);

                    return true;
                case R.id.profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    addFragment(profileFragment);
                    Helper.setToolbar(getSupportActionBar(), "Profile");
                    add.setVisible(false);
                    setting.setVisible(true);
                    search.setVisible(false);
                    return true;
                case R.id.folder:
                    Helper.setToolbar(getSupportActionBar(), "Files");
                    FilesFragment filesFragment=new FilesFragment();
                    addFragment(filesFragment);
                    add.setVisible(false);
                    setting.setVisible(false);
                    search.setVisible(true);
                    return true;
            }
            return false;
        }
    };
    private MenuItem setting;
    private MenuItem add;
    private MenuItem search;


    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayout, fragment);
        transaction.commitNow();
    }
    private void setToolbarForbottom(String title,boolean isaddVisiable,boolean isSettingvisible){
        Helper.setToolbar(getSupportActionBar(), title);
        add.setVisible(true);
        setting.setVisible(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_activity, menu);
        setting = menu.findItem(R.id.action_settings);
        add = menu.findItem(R.id.action_Doctor_name);
        search = menu.findItem(R.id.action_Search);
        add.setVisible(false);
        setting.setVisible(true);
        search.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.action_Doctor_name:
                switch (title){
                    case "Doctor Name":
                        mBottomSheetDialog.show();
                        break;
                    case "Chat":
                        startActivity(new Intent(mContext,NewChatActivity.class));
                     break;
                }


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
        mContext = this;
        mBottomSheetDialog = new BottomSheetDialog(mContext);
        View sheetView = getLayoutInflater().inflate(R.layout.doctor_name_diolog_layout, null);
        layoutNewInvitation = (LinearLayout) sheetView.findViewById(R.id.layout_new_invitation);
        layoutDoctorBase = (LinearLayout) sheetView.findViewById(R.id.layout_doctor_base);
        layoutNewInvitation.setOnClickListener(this::onClick);
        layoutDoctorBase.setOnClickListener(this::onClick);
        mBottomSheetDialog.setContentView(sheetView);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_new_invitation:
                startActivity(new Intent(mContext, AddDoctorTeamActivity.class));
                mBottomSheetDialog.dismiss();
                break;
            case R.id.layout_doctor_base:
                startActivity(new Intent(mContext, SearchByNameActivity.class));
                mBottomSheetDialog.dismiss();
                break;

        }
    }
}
