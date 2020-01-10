package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Utils.SessionManager;
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

public class BottomTabActivity extends BaseActivity implements View.OnClickListener, DiologListner/*, GetSheetViewFromFragment*/ {
    @BindView(R.id.mainLayout)
    FrameLayout mainLayout;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.container)
    RelativeLayout container;
    Context mContext;
    BottomSheetDialog mBottomSheetDialog;
    String title;
    DiologListner diologListner;
    LinearLayout layoutNewInvitation;
    // GetSheetViewFromFragment  getSheetViewFromFragment=this;
    LinearLayout layoutDoctorBase;
    BottomTabViewModel tabViewModel;
    ImageView iv_visitnote_check, iv_referral_check, iv_all_check;
    Fragment appointmentFragment;
    Fragment chatFragment;
    Fragment doctorTeamFragment;
    Fragment filesFragment;
    Fragment profileFragment;
    Fragment active;
    FragmentManager fm = getSupportFragmentManager();
    private TextView mTextMessage;
    private RelativeLayout layout_filter_allDoctors;
    private RelativeLayout layout_filter_received;
    private RelativeLayout layout_filter_sent;
    private MenuItem setting;
    private MenuItem add;
    private MenuItem search;
    SessionManager sessionManager;

    private void VisibleMenuItem(boolean addValue, boolean settingValue, boolean searchValue) {
        if (add != null) {
            add.setVisible(addValue);
        }
        if (setting != null) {
            setting.setVisible(settingValue);
        }
        if (search != null) {
            search.setVisible(searchValue);
        }

    }

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

        VisibleMenuItem(true, false, false);
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
                        //startActivity(new Intent(mContext, NewChatActivity.class));
                        break;

                    case "Appointments":
                        startActivity(new Intent(mContext, BookNewAppointmentActivity.class));
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
        ActivityBottomTabBinding bottomTabBinding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_tab);
        bottomTabBinding.setLifecycleOwner(this);
        tabViewModel = ViewModelProviders.of(this).get(BottomTabViewModel.class);
        setBaseViewModel(tabViewModel);
        bottomTabBinding.setBottomViewModel(tabViewModel);
        ButterKnife.bind(this);
        initializeVariables();
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navView.setSelectedItemId(R.id.calendar);
        Helper.setToolbar(getSupportActionBar(), "Appointments");

       /* fm.beginTransaction().add(R.id.mainLayout, profileFragment, "3").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.mainLayout, filesFragment, "2").hide(filesFragment).commit();
        fm.beginTransaction().add(R.id.mainLayout, doctorTeamFragment, "3").hide(doctorTeamFragment).commit();
        fm.beginTransaction().add(R.id.mainLayout, chatFragment, "2").hide(chatFragment).commit();
        fm.beginTransaction().add(R.id.mainLayout,appointmentFragment, "1").commit();*/

       sessionManager=SessionManager.getSessionManager(mContext);
       if(TextUtils.isEmpty(Helper.autoken)){
           Helper.autoken= sessionManager.getAuthToken();
       }
        if(TextUtils.isEmpty(Helper.idToken)){
            Helper.idToken= sessionManager.getRefreshTokenId();
        }

        tabViewModel.getOpenFrag().observe(this, s -> {
            if (s != null) {
                Helper.hideKeyboardFrom(mContext, navView);

                switch (s) {
                    case "calendar":

                        title = "Appointments";
                        if (fm == null) {
                            fm = getSupportFragmentManager();
                        }
                        if (appointmentFragment == null) {
                            appointmentFragment = new AppointmentFragment();
                            fm.beginTransaction().add(R.id.mainLayout, appointmentFragment, "1").commit();
                            active = appointmentFragment;
                        } else {
                            fm.beginTransaction().hide(active).show(appointmentFragment).commit();
                            active = appointmentFragment;
                        }

                        if (add != null && setting != null && search != null) {
                            Helper.setToolbar(getSupportActionBar(), "Appointments");
                            setToolbarForbottom(title, true, false);
                            VisibleMenuItem(true, false, false);

                        }

                        break;
                    case "messages":
                        title = "Chats";
                        if (fm == null) {
                            fm = this.getSupportFragmentManager();
                        }
                        //if (chatFragment == null) {
                            chatFragment = new ChatFragments();
                            fm.beginTransaction().add(R.id.mainLayout, chatFragment, "2").hide(active).commit();
                            active = chatFragment;
                       /* } else {
                            fm.beginTransaction().hide(active).show(chatFragment).commit();
                            active = chatFragment;
                        }*/

                        setToolbarForbottom(title, true, false);
                        VisibleMenuItem(true, false, false);

                        break;
                    case "people":

                        title = "My Doctor Teams";
                        if (fm == null) {
                            fm = getSupportFragmentManager();
                        }
                        if (doctorTeamFragment == null) {
                            doctorTeamFragment = new DoctorTeamFragment();
                            fm.beginTransaction().add(R.id.mainLayout, doctorTeamFragment, "3").hide(active).commit();
                            active = doctorTeamFragment;
                        } else {
                            fm.beginTransaction().hide(active).show(doctorTeamFragment).commit();
                            active = doctorTeamFragment;
                        }

                        setToolbarForbottom(title, true, false);

                        VisibleMenuItem(false, false, false);

                        break;
                    case "profile":
                        title = "My Profile";
                        if (fm == null) {
                            fm = getSupportFragmentManager();
                        }
                        if (profileFragment == null) {
                            profileFragment = new ProfileFragment();
                            fm.beginTransaction().add(R.id.mainLayout, profileFragment, "4").hide(active).commit();
                            active = profileFragment;
                        } else {
                            fm.beginTransaction().hide(active).show(profileFragment).commit();
                            active = profileFragment;
                        }

                        Helper.setToolbar(getSupportActionBar(), "My Profile");
                        VisibleMenuItem(false, true, false);

                        break;

                    case "folder":
                        title = "Files";
                        if (fm == null) {
                            fm = getSupportFragmentManager();
                        }
                        if (filesFragment == null) {
                            filesFragment = new FilesFragment();
                            fm.beginTransaction().add(R.id.mainLayout, filesFragment, "5").hide(active).commit();
                            active = filesFragment;
                        } else {
                            fm.beginTransaction().hide(active).show(filesFragment).commit();
                            active = filesFragment;
                        }

                        Helper.setLog("folder", "FilesFragment");

                        //mBottomSheetDialog = DiologHelper.createDialogFromBottom(mContext,R.layout.filter_diolog_layout,diologListner,"file");
                        Helper.setToolbar(getSupportActionBar(), "Files");
                        VisibleMenuItem(false, false, false);

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
        layoutNewInvitation = view.findViewById(R.id.layout_new_invitation);
        layoutDoctorBase = view.findViewById(R.id.layout_doctor_base);
        layoutNewInvitation.setOnClickListener(this::onClick);
        layoutDoctorBase.setOnClickListener(this::onClick);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
