package com.werq.patient.Factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.werq.patient.viewmodel.TabAppoinmentViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {
  private boolean isFromUpcoming;

  @Inject
  public ViewModelProviderFactory(boolean isFromUpcoming) {
    this.isFromUpcoming = isFromUpcoming;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(TabAppoinmentViewModel.class)) {
      //noinspection unchecked
      return (T) new TabAppoinmentViewModel(isFromUpcoming);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}