package com.werq.patient.views.ui;

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

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.views.ui.Fragments.AppointmentFragment;
import com.werq.patient.views.ui.Fragments.ChatFragments;
import com.werq.patient.views.ui.Fragments.DoctorTeamFragment;
import com.werq.patient.views.ui.Fragments.FilesFragment;
import com.werq.patient.views.ui.Fragments.ProfileFragment;
import com.werq.patient.Interfaces.DiologListner;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.DiologHelper;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityBottomTabBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomTabActivity extends BaseActivity implements View.OnClickListener, DiologListner {
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
    DiologListner diologListner;


    LinearLayout layoutNewInvitation;
    LinearLayout layoutDoctorBase;
    BottomTabViewModel tabViewModel;

/*    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.calendar:
                    AppointmentFragment appointmentFragment = new AppointmentFragment();
                    addFragment(appointmentFragment);
                    if (add != null && setting != null && search != null) {
                        Helper.setToolbar(getSupportActionBar(), "Appointments");
                        VisibleMenuItem(false, false, true);

                    }

                    return true;
                case R.id.messages:
                    title = "Chats";
                    setToolbarForbottom(title, true, false);
                    ChatFragments chatFragments = new ChatFragments();
                    addFragment(chatFragments);
                    VisibleMenuItem(true, false, false);


                    return true;
                case R.id.people:

                    title = "My Doctor Teams";
                    setToolbarForbottom(title, true, false);
                    DoctorTeamFragment doctorTeamFragment = new DoctorTeamFragment();
                    addFragment(doctorTeamFragment);
                    VisibleMenuItem(true, false, false);

                    return true;
                case R.id.profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    addFragment(profileFragment);
                    Helper.setToolbar(getSupportActionBar(), "My Profile");
                    VisibleMenuItem(false, true, false);

                    return true;
                case R.id.folder:
                    Helper.setToolbar(getSupportActionBar(), "Files");
                    FilesFragment filesFragment = new FilesFragment();
                    addFragment(filesFragment);
                    VisibleMenuItem(false, false, true);

                    return true;
            }
            return false;
        }
    };*/

    private void VisibleMenuItem(boolean addValue, boolean settingValue, boolean searchValue) {
        add.setVisible(addValue);
        setting.setVisible(settingValue);
        search.setVisible(searchValue);
    }

    private MenuItem setting;
    private MenuItem add;
    private MenuItem search;


    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayout, fragment);
        transaction.commitNow();
    }

    private void setToolbarForbottom(String title, boolean isaddVisiable, boolean isSettingvisible) {
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

        VisibleMenuItem(false, false, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.action_Doctor_name:
                switch (title) {
                    case "My Doctor Teams":
                        mBottomSheetDialog.show();
                        break;
                    case "Chats":
                        startActivity(new Intent(mContext, NewChatActivity.class));
                        break;
                }


                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bottom_tab);
        ActivityBottomTabBinding bottomTabBinding= DataBindingUtil.setContentView(this,R.layout.activity_bottom_tab);
        bottomTabBinding.setLifecycleOwner(this);
        tabViewModel= ViewModelProviders.of(this).get(BottomTabViewModel.class);
        bottomTabBinding.setBottomViewModel(tabViewModel);
        ButterKnife.bind(this);
        initializeVariables();
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navView.setSelectedItemId(R.id.calendar);
        Helper.setToolbar(getSupportActionBar(), "Appointments");
        tabViewModel.getOpenFrag().observe(this,s -> {
            if(s!=null)
            {
                switch (s) {
                    case "calendar":
                        AppointmentFragment appointmentFragment = new AppointmentFragment();
                        addFragment(appointmentFragment);
                        if (add != null && setting != null && search != null) {
                            Helper.setToolbar(getSupportActionBar(), "Appointments");
                            VisibleMenuItem(false, false, true);

                        }

                        break;
                    case "messages":
                        title = "Chats";
                        setToolbarForbottom(title, true, false);
                        ChatFragments chatFragments = new ChatFragments();
                        addFragment(chatFragments);
                        VisibleMenuItem(true, false, false);


                        break;
                    case "people":

                        title = "My Doctor Teams";
                        setToolbarForbottom(title, true, false);
                        DoctorTeamFragment doctorTeamFragment = new DoctorTeamFragment();
                        addFragment(doctorTeamFragment);
                        VisibleMenuItem(true, false, false);

                        break;
                    case "profile":
                        ProfileFragment profileFragment = new ProfileFragment();
                        addFragment(profileFragment);
                        Helper.setToolbar(getSupportActionBar(), "My Profile");
                        VisibleMenuItem(false, true, false);

                        break;
                    case "folder":
                        Helper.setToolbar(getSupportActionBar(), "Files");
                        FilesFragment filesFragment = new FilesFragment();
                        addFragment(filesFragment);
                        VisibleMenuItem(false, false, true);

                        break;
                }
            }
        });
    }

    private void initializeVariables() {
        //context
        mContext = this;

        //listner
        diologListner = this;

        mBottomSheetDialog = DiologHelper.createDialogFromBottom(mContext, R.layout.doctor_name_diolog_layout, diologListner);


        // mBottomSheetDialog.setContentView(sheetView);


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


    @Override
    public void setdiologview(View view) {
        layoutNewInvitation = (LinearLayout) view.findViewById(R.id.layout_new_invitation);
        layoutDoctorBase = (LinearLayout) view.findViewById(R.id.layout_doctor_base);
        layoutNewInvitation.setOnClickListener(this::onClick);
        layoutDoctorBase.setOnClickListener(this::onClick);
    }
}
