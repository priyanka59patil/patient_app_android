package com.werq.patient.Factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.werq.patient.viewmodel.LoginViewModel;
import com.werq.patient.viewmodel.TabAppoinmentViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class LoginVmProviderFactory extends ViewModelProvider.NewInstanceFactory {
  private Context context;

  @Inject
  public LoginVmProviderFactory(Context context) {
    this.context = context;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(LoginViewModel.class)) {
      //noinspection unchecked
      return (T) new LoginViewModel(context);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}