package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TimeSlotData implements Serializable {

    @SerializedName("availableTimeSlot")
    private List<AvailableTimeSlot> availableTimeSlot = null;

    public List<AvailableTimeSlot> getAvailableTimeSlot() {
        return availableTimeSlot;
    }

    public void setAvailableTimeSlot(List<AvailableTimeSlot> availableTimeSlot) {
        this.availableTimeSlot = availableTimeSlot;
    }

    @Override
    public String toString() {
        return "TimeSlotData{" +
                "availableTimeSlot=" + availableTimeSlot +
                '}';
    }
}
