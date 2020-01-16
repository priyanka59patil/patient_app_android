package com.werq.patient.Factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.werq.patient.viewmodel.SetNewPasswordViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class SetNewPasswordVmFactory extends ViewModelProvider.NewInstanceFactory {
  private String authToken;

  @Inject
  public SetNewPasswordVmFactory(String authToken) {
    this.authToken=authToken;

  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(SetNewPasswordViewModel.class)) {
      //noinspection unchecked
      return (T) new SetNewPasswordViewModel(authToken);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}