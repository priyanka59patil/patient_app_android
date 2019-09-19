package com.werq.patient.Models.Factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.Models.pojo.AppointmentData;
import com.werq.patient.Models.viewModel.ScheduleDetailsViewModel;
import com.werq.patient.Models.viewModel.TabAppoinmentViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ScheduleDeatilsVmFactory extends ViewModelProvider.NewInstanceFactory {
  private AppointmentData data;
  private AppointmentInterface controller;

  @Inject
  public ScheduleDeatilsVmFactory(AppointmentData data,AppointmentInterface controller) {
    this.data=data;
    this.controller=controller;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(ScheduleDetailsViewModel.class)) {
      //noinspection unchecked
      return (T) new ScheduleDetailsViewModel(data,controller);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}