package com.werq.patient.viewmodel;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.werq.patient.R;
import com.werq.patient.base.BaseViewModel;

import okhttp3.internal.http2.ErrorCode;

public class BottomTabViewModel extends BaseViewModel implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "BottomTabViewModel";
    public  MutableLiveData<String> openFrag;
    public BottomTabViewModel() {
        super();
        openFrag=new MutableLiveData<>();
        openFrag.setValue("calendar");
    }

    public MutableLiveData<String> getOpenFrag() {
        return openFrag;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calendar:
                Log.e(TAG, "calendar: " );
                openFrag.setValue("calendar");
                /*AppointmentFragment appointmentFragment = new AppointmentFragment();
                addFragment(appointmentFragment);
                if (add != null && setting != null && search != null) {
                    Helper.setToolbar(getSupportActionBar(), "Appointments");
                    VisibleMenuItem(false, false, true);

                }*/

                return true;
            case R.id.messages:
                Log.e(TAG, "messages: " );
                openFrag.setValue("messages");
                /*title = "Chats";
                setToolbarForbottom(title, true, false);
                ChatFragments chatFragments = new ChatFragments();
                addFragment(chatFragments);
                VisibleMenuItem(true, false, false);*/


                return true;
            case R.id.people:
                openFrag.setValue("people");
                Log.e(TAG, "people: " );
               /* title = "My Doctor Teams";
                setToolbarForbottom(title, true, false);
                DoctorTeamFragment doctorTeamFragment = new DoctorTeamFragment();
                addFragment(doctorTeamFragment);
                VisibleMenuItem(true, false, false);*/

                return true;
            case R.id.profile:
                openFrag.setValue("profile");
                Log.e(TAG, "profile: " );
                /*ProfileFragment profileFragment = new ProfileFragment();
                addFragment(profileFragment);
                Helper.setToolbar(getSupportActionBar(), "My Profile");
                VisibleMenuItem(false, true, false);*/

                return true;
            case R.id.folder:
                openFrag.setValue("folder");
                Log.e(TAG, "folder: " );
                /*Helper.setToolbar(getSupportActionBar(), "Files");
                FilesFragment filesFragment = new FilesFragment();
                addFragment(filesFragment);
                VisibleMenuItem(false, false, true);*/

                return true;
        }
        return false;
    }

    @Override
    public void onSuccess(String url, String responseJson) {

    }
    @Override
    public void onError(String url, String errorCode) {

    }

    @Override
    public void onTokenRefersh(String responseJson) {

    }
}
