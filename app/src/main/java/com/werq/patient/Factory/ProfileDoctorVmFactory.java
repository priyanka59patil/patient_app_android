package com.werq.patient.Factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.FirebaseDatabase;
import com.werq.patient.viewmodel.ChatFragmentViewModel;
import com.werq.patient.viewmodel.ProfileDoctorViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ProfileDoctorVmFactory extends ViewModelProvider.NewInstanceFactory {
  //private AppointmentData data;
  Context mContext;
  @Inject
  public ProfileDoctorVmFactory(Context mContext) {
    this.mContext=mContext;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(ProfileDoctorViewModel.class)) {
      //noinspection unchecked
      return (T) new ProfileDoctorViewModel(mContext);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}