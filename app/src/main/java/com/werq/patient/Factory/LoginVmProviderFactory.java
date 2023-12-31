package com.werq.patient.Factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.werq.patient.viewmodel.LoginViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class LoginVmProviderFactory extends ViewModelProvider.NewInstanceFactory {
  private Context context;
  String authToken;

  @Inject
  public LoginVmProviderFactory(Context context,String authToken) {
    this.context = context;
    this.authToken=authToken;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(LoginViewModel.class)) {
      //noinspection unchecked
      return (T) new LoginViewModel(context,authToken);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}