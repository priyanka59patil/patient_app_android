package com.werq.patient.Factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.werq.patient.viewmodel.TabAppoinmentViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class TabApptVmProviderFactory extends ViewModelProvider.NewInstanceFactory {
  private String authToken;
  private boolean isFromUpcoming;

  @Inject
  public TabApptVmProviderFactory(String authToken,boolean isFromUpcoming) {
    this.authToken = authToken;
    this.isFromUpcoming=isFromUpcoming;
   // this.context=context;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(TabAppoinmentViewModel.class)) {
      //noinspection unchecked
      return (T) new TabAppoinmentViewModel(authToken,isFromUpcoming);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}