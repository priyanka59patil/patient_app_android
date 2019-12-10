package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AvailableTimeSlot implements Serializable {

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "AvailableTimeSlot{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
