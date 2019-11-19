package com.werq.patient.Factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.viewmodel.ScheduleDetailsViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ScheduleDeatilsVmFactory extends ViewModelProvider.NewInstanceFactory {
  //private AppointmentData data;
  boolean isFromUpcomming;
  AppointmentResult appointmentResult;
  private AppointmentInterface controller;

  @Inject
  public ScheduleDeatilsVmFactory(boolean isFromUpcomming,
                                  AppointmentResult appointmentResult
          ,AppointmentInterface controller) {
    this.isFromUpcomming=isFromUpcomming;
    this.appointmentResult=appointmentResult;
    this.controller=controller;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(ScheduleDetailsViewModel.class)) {
      //noinspection unchecked
      return (T) new ScheduleDetailsViewModel(isFromUpcomming,appointmentResult,controller);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}