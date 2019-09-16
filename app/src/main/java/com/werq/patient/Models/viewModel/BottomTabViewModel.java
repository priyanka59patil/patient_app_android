package com.werq.patient.Models.viewModel;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.werq.patient.Fragments.AppointmentFragment;
import com.werq.patient.Fragments.ChatFragments;
import com.werq.patient.Fragments.DoctorTeamFragment;
import com.werq.patient.Fragments.FilesFragment;
import com.werq.patient.Fragments.ProfileFragment;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;

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
                Log.e(TAG, "profile: " );
                /*ProfileFragment profileFragment = new ProfileFragment();
                addFragment(profileFragment);
                Helper.setToolbar(getSupportActionBar(), "My Profile");
                VisibleMenuItem(false, true, false);*/

                return true;
            case R.id.folder:
                Log.e(TAG, "folder: " );
                /*Helper.setToolbar(getSupportActionBar(), "Files");
                FilesFragment filesFragment = new FilesFragment();
                addFragment(filesFragment);
                VisibleMenuItem(false, false, true);*/

                return true;
        }
        return false;
    }
}
