package com.werq.patient.service.model.ResponcejsonPojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AppointmentData implements Serializable
{

    @SerializedName("Count")
    private Integer count;

    @SerializedName("Result")
    private AppointmentResult result[];

    public AppointmentData() {
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public AppointmentResult[] getResult() {
        return result;
    }

    public void setResult(AppointmentResult[] result) {
        this.result = result;
    }
    @Override
    public String toString() {
        return "AppointmentData{" +
                "count=" + count +
                ", result=" + result +
                '}';
    }



}
