package com.werq.patient.Interfaces;

import android.os.Bundle;

import com.werq.patient.service.model.Responce;

public interface ProfileInterface {
  public void getData() ;
  public Bundle bundle(Responce responce,String bundleFor);


}
