package com.werq.patient.Controller;

import android.os.Bundle;

import com.google.gson.Gson;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.ProfileInterface;
import com.werq.patient.MockData.JsonData;
import com.werq.patient.Models.pojo.Responce;

public class ProfileController implements ProfileInterface {
    BasicActivities basicActivities;
    Gson gson;

    public ProfileController(BasicActivities basicActivities) {
        this.basicActivities = basicActivities;
        gson =new Gson();;
    }

    @Override
    public void getData()  {
        Responce responce= JsonData.getProfileData();
        basicActivities.setView(responce);

    }

    @Override
    public Bundle bundle(Responce responce, String bundleFor) {
      Bundle bundle=new Bundle();
         switch (bundleFor){
             case "medical_info":
                 bundle.putString("personal_info", gson.toJson(responce.getResponse().getPersonal_info()));
                 bundle.putString("medical_info",  gson.toJson(responce.getResponse().getMedical_info()));
                 break;
             case "insurance_info":
                 bundle.putString("insurance_info", gson.toJson(responce.getResponse().getMedical_info()));

                 break;
             case  "medications":
                 bundle.putString("medications", gson.toJson(responce.getResponse().getMedications()));
                 break;
         }

        return bundle;
    }



}
