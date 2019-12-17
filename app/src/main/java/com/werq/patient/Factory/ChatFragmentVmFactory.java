package com.werq.patient.Factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.viewmodel.ChatFragmentViewModel;
import com.werq.patient.viewmodel.ScheduleDetailsViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ChatFragmentVmFactory extends ViewModelProvider.NewInstanceFactory {
  //private AppointmentData data;
  Context mContext;

  @Inject
  public ChatFragmentVmFactory(Context mContext) {
    this.mContext=mContext;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(ChatFragmentViewModel.class)) {
      //noinspection unchecked
      return (T) new ChatFragmentViewModel(mContext);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}